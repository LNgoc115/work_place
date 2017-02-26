/*     */ package com.viettel.ipcc.agentserver.manager;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSpy;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.request.LogoutRequest;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.ChangeAgentSpyResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.TestConnectionResponse;
/*     */ import com.viettel.ipcc.agentserver.common.AcdThread;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*     */ import com.viettel.ipcc.agentserver.common.IThreadProcessor;
/*     */ import com.viettel.ipcc.agentserver.common.MonitoringInfo;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopRequestManager;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.ErrorCode;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ public class AgentsManager
/*     */ {
/*     */   private Map<String, Agents> mapDeviceidAgent;
/*     */   private Map<String, Agents> mapVsaidAgent;
/*     */   private Map<String, List<Agents>> mapGroupAgent;
/*     */   private Map<IoSession, Agents> mapSessionAgent;
/*     */   private Map<IoSession, Long> mapAnonymousSession;
/*  37 */   private Logger logger = Logger.getLogger(AgentsManager.class);
/*  38 */   private boolean isStarted = false;
/*  39 */   private long intervalCheckConnection = 1000L;
/*  40 */   private long anonymousTimeout = 60000L;
/*  41 */   private int numRetryPing = 3;
/*  42 */   private int maxNumberRejectCall = 3;
/*     */   
/*     */   private String monitoringMediaServer;
/*     */   
/*     */   private List<AcdThread> listThread;
/*     */   private String sqlShiftleaderSearchNotZoonID;
/*     */   private String sqlShiftleaderSearch;
/*     */   private String sqlSupervisorSearchAgent;
/*     */   private Map<String, Agents> mapAllMonitoringAgents;
/*     */   private CheckAgentConnectionProcessor checkConnectionProcessor;
/*     */   
/*     */   public void start()
/*     */   {
/*  55 */     if (this.isStarted)
/*  56 */       return;
/*  57 */     this.isStarted = true;
/*  58 */     this.logger.info("Agents manager started!");
/*  59 */     this.listThread = new ArrayList();
/*  60 */     this.checkConnectionProcessor = new CheckAgentConnectionProcessor();
/*  61 */     AcdThread threadCheckConnection = new AcdThread(
/*  62 */       "thread-check-connection", this.checkConnectionProcessor);
/*     */     
/*  64 */     threadCheckConnection.start();
/*  65 */     this.listThread.add(threadCheckConnection);
/*     */   }
/*     */   
/*     */   public void stop()
/*     */   {
/*  70 */     if (!this.isStarted) {
/*  71 */       return;
/*     */     }
/*  73 */     this.isStarted = false;
/*     */     
/*  75 */     this.logger.info("Agents manager stopped!");
/*  76 */     for (AcdThread thread : this.listThread) {
/*  77 */       thread.setRunning(false);
/*     */     }
/*     */     
/*  80 */     for (Map.Entry<IoSession, Agents> entry : this.mapSessionAgent.entrySet()) {
/*  81 */       ((IoSession)entry.getKey()).close(true);
/*     */     }
/*     */     
/*  84 */     ??? = this.mapAnonymousSession.entrySet().iterator();
/*  83 */     while (???.hasNext()) {
/*  84 */       Map.Entry<IoSession, Long> entry = (Map.Entry)???.next();
/*  85 */       ((IoSession)entry.getKey()).close(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public AgentsManager()
/*     */   {
/*  91 */     this.mapDeviceidAgent = 
/*  92 */       Collections.synchronizedMap(new HashMap());
/*  93 */     this.mapVsaidAgent = 
/*  94 */       Collections.synchronizedMap(new HashMap());
/*  95 */     this.mapSessionAgent = 
/*  96 */       Collections.synchronizedMap(new HashMap());
/*  97 */     this.mapGroupAgent = 
/*  98 */       Collections.synchronizedMap(new HashMap());
/*  99 */     this.mapAnonymousSession = 
/* 100 */       Collections.synchronizedMap(new HashMap());
/* 101 */     setMapAllMonitoringAgents(
/* 102 */       Collections.synchronizedMap(new HashMap()));
/*     */   }
/*     */   
/*     */   public void addAgent(Agents agent) {
/* 106 */     if (agent == null) {
/* 107 */       return;
/*     */     }
/* 109 */     this.logger.info("add: " + agent);
/*     */     
/* 111 */     this.mapDeviceidAgent.put(agent.getDeviceID(), agent);
/* 112 */     this.mapVsaidAgent.put(agent.getVsaID(), agent);
/* 113 */     this.mapSessionAgent.put(agent.getAgentSession(), agent);
/* 114 */     this.mapAnonymousSession.remove(agent.getAgentSession());
/* 115 */     if (!this.mapGroupAgent.containsKey(agent.getGroupID())) {
/* 116 */       this.mapGroupAgent.put(agent.getGroupID(), 
/* 117 */         Collections.synchronizedList(new LinkedList()));
/*     */     }
/*     */     
/* 120 */     ((List)this.mapGroupAgent.get(agent.getGroupID())).add(agent);
/*     */   }
/*     */   
/*     */   public void removeAgent(Agents agent)
/*     */   {
/* 125 */     if (agent == null) {
/* 126 */       return;
/*     */     }
/* 128 */     this.logger.info("remove: " + agent);
/*     */     
/* 130 */     this.mapDeviceidAgent.remove(agent.getDeviceID());
/* 131 */     this.mapVsaidAgent.remove(agent.getVsaID());
/* 132 */     this.mapSessionAgent.remove(agent.getAgentSession());
/*     */     
/* 134 */     ((List)this.mapGroupAgent.get(agent.getGroupID())).remove(agent);
/*     */     
/* 136 */     if (((List)this.mapGroupAgent.get(agent.getGroupID())).size() == 0) {
/* 137 */       this.mapGroupAgent.remove(agent.getGroupID());
/*     */     }
/* 139 */     this.mapAnonymousSession.remove(agent.getAgentSession());
/*     */     
/* 141 */     getMapAllMonitoringAgents().remove(agent.getDeviceID());
/*     */   }
/*     */   
/*     */ 
/*     */   public Agents findByDeviceID(String id)
/*     */   {
/* 147 */     return (Agents)this.mapDeviceidAgent.get(id);
/*     */   }
/*     */   
/*     */ 
/*     */   public Agents findByVsaID(String id)
/*     */   {
/* 153 */     return (Agents)this.mapVsaidAgent.get(id);
/*     */   }
/*     */   
/*     */ 
/*     */   public Agents findBySession(IoSession session)
/*     */   {
/* 159 */     return (Agents)this.mapSessionAgent.get(session);
/*     */   }
/*     */   
/*     */ 
/*     */   public List<Agents> getGroupSuper(String groupid)
/*     */   {
/* 165 */     List<Agents> listSuper = new ArrayList();
/* 166 */     List<Agents> listAgentInGroup = (List)this.mapGroupAgent.get(groupid);
/* 167 */     if (listAgentInGroup != null) {
/* 168 */       for (Agents a : listAgentInGroup) {
/* 169 */         if (a.isSuper()) {
/* 170 */           listSuper.add(a);
/*     */         }
/*     */       }
/*     */     }
/* 174 */     return listSuper;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<Agents> getAgentInGroup(String groupid)
/*     */   {
/* 180 */     List<Agents> listAgentInGroup = (List)this.mapGroupAgent.get(groupid);
/* 181 */     List<Agents> res = new ArrayList();
/*     */     
/* 183 */     if (listAgentInGroup != null) {
/* 184 */       res.addAll(listAgentInGroup);
/*     */     }
/* 186 */     return res;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addAnonymousSession(IoSession session)
/*     */   {
/* 192 */     this.mapAnonymousSession.put(session, Long.valueOf(System.currentTimeMillis()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void removeAnonymousSession(IoSession session)
/*     */   {
/* 198 */     this.mapAnonymousSession.remove(session);
/*     */   }
/*     */   
/*     */   public void setMapSessionAgent(Map<IoSession, Agents> mapSessionAgent)
/*     */   {
/* 203 */     this.mapSessionAgent = mapSessionAgent;
/*     */   }
/*     */   
/*     */   public Map<IoSession, Agents> getMapSessionAgent() {
/* 207 */     return this.mapSessionAgent;
/*     */   }
/*     */   
/*     */   public void setIntervalCheckConnection(long intervalCheckConnection) {
/* 211 */     this.intervalCheckConnection = intervalCheckConnection;
/*     */   }
/*     */   
/*     */   public long getIntervalCheckConnection() {
/* 215 */     return this.intervalCheckConnection;
/*     */   }
/*     */   
/*     */   public void setNumRetryPing(int numRetryPing) {
/* 219 */     this.numRetryPing = numRetryPing;
/*     */   }
/*     */   
/*     */   public int getNumRetryPing() {
/* 223 */     return this.numRetryPing;
/*     */   }
/*     */   
/*     */   public void setAnonymousTimeout(long anonymousTimeout) {
/* 227 */     this.anonymousTimeout = anonymousTimeout;
/*     */   }
/*     */   
/*     */   public long getAnonymousTimeout() {
/* 231 */     return this.anonymousTimeout;
/*     */   }
/*     */   
/*     */   public void setMaxNumberRejectCall(int maxNumberRejectCall) {
/* 235 */     this.maxNumberRejectCall = maxNumberRejectCall;
/*     */   }
/*     */   
/*     */   public int getMaxNumberRejectCall() {
/* 239 */     return this.maxNumberRejectCall;
/*     */   }
/*     */   
/*     */   public int getNumAgentLogin() {
/* 243 */     return this.mapSessionAgent.size();
/*     */   }
/*     */   
/*     */   public int getNumSessionConnected() {
/* 247 */     return this.mapSessionAgent.size() + this.mapAnonymousSession.size();
/*     */   }
/*     */   
/*     */   public void setSqlShiftleaderSearchNotZoonID(String sqlShiftleaderSearchNotZoonID)
/*     */   {
/* 252 */     this.sqlShiftleaderSearchNotZoonID = sqlShiftleaderSearchNotZoonID;
/*     */   }
/*     */   
/*     */   public String getSqlShiftleaderSearchNotZoonID() {
/* 256 */     return this.sqlShiftleaderSearchNotZoonID;
/*     */   }
/*     */   
/*     */   public void setSqlShiftleaderSearch(String sqlShiftleaderSearch) {
/* 260 */     this.sqlShiftleaderSearch = sqlShiftleaderSearch;
/*     */   }
/*     */   
/*     */   public String getSqlShiftleaderSearch() {
/* 264 */     return this.sqlShiftleaderSearch;
/*     */   }
/*     */   
/*     */   public void setSqlSupervisorSearchAgent(String sqlSupervisorSearchAgent) {
/* 268 */     this.sqlSupervisorSearchAgent = sqlSupervisorSearchAgent;
/*     */   }
/*     */   
/*     */   public String getSqlSupervisorSearchAgent() {
/* 272 */     return this.sqlSupervisorSearchAgent;
/*     */   }
/*     */   
/*     */   public void setMonitoringMediaServer(String monitoringMediaServer) {
/* 276 */     this.monitoringMediaServer = monitoringMediaServer;
/*     */   }
/*     */   
/*     */   public String getMonitoringMediaServer() {
/* 280 */     return this.monitoringMediaServer;
/*     */   }
/*     */   
/*     */   public void setMapAllMonitoringAgents(Map<String, Agents> mapAllMonitoringAgents)
/*     */   {
/* 285 */     this.mapAllMonitoringAgents = mapAllMonitoringAgents;
/*     */   }
/*     */   
/*     */   public Map<String, Agents> getMapAllMonitoringAgents() {
/* 289 */     return this.mapAllMonitoringAgents;
/*     */   }
/*     */   
/*     */   public Set<String> getAllLoginAgent()
/*     */   {
/* 294 */     return this.mapVsaidAgent.keySet();
/*     */   }
/*     */   
/*     */ 
/*     */   private void checkMonitoringSpy()
/*     */   {
/* 300 */     CallManager callMng = (CallManager)Utils.getCtx().getBean(
/* 301 */       "CallManager");
/*     */     
/*     */ 
/* 304 */     Iterator localIterator1 = this.mapAllMonitoringAgents.entrySet().iterator();
/* 303 */     while (localIterator1.hasNext()) {
/* 304 */       Map.Entry<String, Agents> entry = (Map.Entry)localIterator1.next();
/*     */       
/* 306 */       Agents agent = (Agents)entry.getValue();
/* 307 */       MonitoringInfo mntInfo = agent.getMonitoringInfo();
/* 308 */       if (mntInfo != null)
/*     */       {
/*     */ 
/* 311 */         synchronized (mntInfo)
/*     */         {
/* 313 */           if ((!mntInfo.isSpying()) || (mntInfo.isCompleteSpy()))
/*     */           {
/*     */ 
/* 316 */             CallInfo callSpy = mntInfo.getNextSpyCall();
/* 317 */             mntInfo.setNextSpyCall(null);
/* 318 */             if (callSpy != null)
/*     */             {
/* 320 */               Object res = Utils.sendXmlrpc(callSpy.getSemsUrl(), 
/* 321 */                 "spy", new Object[] {
/* 322 */                 callSpy.getCallID(), agent.getDeviceID() });
/*     */               
/* 324 */               if (res.toString().equals("OK")) {
/* 325 */                 mntInfo.setCurrentMonotoringSpyCallid(callSpy
/* 326 */                   .getCallID());
/* 327 */                 mntInfo.setCompleteSpy(false);
/*     */                 
/* 329 */                 StringBuilder allAgentInCall = new StringBuilder();
/*     */                 
/* 331 */                 Agents firstAgent = null;
/*     */                 
/* 333 */                 Iterator localIterator2 = callMng.getAllAgentRelatedToCall(callSpy.getCallID()).iterator();
/* 332 */                 while (localIterator2.hasNext()) {
/* 333 */                   Agents a = (Agents)localIterator2.next();
/* 334 */                   allAgentInCall.append(a.getVsaID()).append(' ');
/* 335 */                   if (!a.isSuper()) {
/* 336 */                     firstAgent = a;
/*     */                   }
/*     */                 }
/*     */                 
/* 340 */                 this.logger.info(agent + " monitoring spy " + 
/* 341 */                   callSpy.getCallID() + 
/* 342 */                   " sucessful agent in call: " + 
/* 343 */                   allAgentInCall);
/*     */                 
/* 345 */                 ChangeAgentSpyResponse changeSpyResponse = new ChangeAgentSpyResponse(
/* 346 */                   new AgentSpy(firstAgent.getVsaID(), firstAgent
/* 347 */                   .getDeviceID(), callSpy.getCaller(), 
/* 348 */                   "", callSpy.getCalled()));
/*     */                 
/* 350 */                 Utils.sendMessageToAgent(agent.getAgentSession(), 
/* 351 */                   changeSpyResponse);
/*     */               }
/*     */               else
/*     */               {
/* 355 */                 if (res.equals(ErrorCode.IPCCERR0001.toString())) {
/* 356 */                   mntInfo.setSpying(false);
/*     */                 }
/*     */                 
/* 359 */                 this.logger.info(agent + " monitoring spy " + 
/* 360 */                   callSpy.getCallID() + " fail " + res);
/*     */               }
/*     */             }
/*     */             else {
/* 364 */               mntInfo.setNextSpyCall(
/* 365 */                 selectNextCallToMonitoringSpy(mntInfo));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public CallInfo selectNextCallToMonitoringSpy(MonitoringInfo info)
/*     */   {
/* 375 */     if (info != null) {
/* 376 */       CallManager callManager = (CallManager)Utils.getCtx().getBean(
/* 377 */         "CallManager");
/* 378 */       Set<String> setCallId = callManager.getAllCallID();
/*     */       
/* 380 */       CallInfo res = null;
/* 381 */       long maxTime = 0L;
/*     */       
/* 383 */       for (String callid : setCallId) {
/* 384 */         List<Agents> listAgent = callManager
/* 385 */           .getAllAgentRelatedToCall(callid);
/*     */         
/* 387 */         CallInfo call = callManager.getCall(callid);
/*     */         
/* 389 */         if (call != null)
/*     */         {
/*     */ 
/* 392 */           if (System.currentTimeMillis() - call.getFisrtAnswerTime() <= 2500L)
/*     */           {
/*     */ 
/* 395 */             for (Agents a : listAgent)
/* 396 */               if (info.getListSpyedAgents().contains(a.getVsaID()))
/*     */               {
/* 398 */                 if (maxTime >= call.getStartTime().getTime()) break;
/* 399 */                 maxTime = call.getStartTime().getTime();
/* 400 */                 res = call;
/*     */                 
/*     */ 
/* 403 */                 break;
/*     */               } }
/*     */         }
/*     */       }
/* 407 */       return res;
/*     */     }
/*     */     
/* 410 */     return null;
/*     */   }
/*     */   
/*     */   public CallInfo selectNextCall(String userName, MonitoringInfo mntInfo)
/*     */   {
/* 415 */     if (userName != null) {
/* 416 */       if (!mntInfo.getListSpyedAgents().contains(userName)) {
/* 417 */         return null;
/*     */       }
/* 419 */       CallManager callManager = (CallManager)Utils.getCtx().getBean(
/* 420 */         "CallManager");
/* 421 */       Set<String> setCallId = callManager.getAllCallID();
/*     */       
/* 423 */       for (String callid : setCallId) {
/* 424 */         List<Agents> listAgent = callManager
/* 425 */           .getAllAgentRelatedToCall(callid);
/* 426 */         CallInfo call = callManager.getCall(callid);
/* 427 */         if (call != null)
/*     */         {
/*     */ 
/* 430 */           for (Agents a : listAgent) {
/* 431 */             if (userName.equalsIgnoreCase(a.getVsaID()))
/*     */             {
/* 433 */               return call; }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 438 */     return null;
/*     */   }
/*     */   
/*     */   class CheckAgentConnectionProcessor implements IThreadProcessor
/*     */   {
/*     */     CheckAgentConnectionProcessor() {}
/*     */     
/*     */     public void process()
/*     */     {
/* 447 */       List<IoSession> removeSessionList = new ArrayList();
/*     */       
/* 449 */       Iterator localIterator = AgentsManager.this.mapAnonymousSession.entrySet().iterator();
/* 448 */       while (localIterator.hasNext()) {
/* 449 */         Map.Entry<IoSession, Long> entry = (Map.Entry)localIterator.next();
/* 450 */         if (System.currentTimeMillis() - ((Long)entry.getValue()).longValue() > AgentsManager.this.getAnonymousTimeout()) {
/* 451 */           removeSessionList.add((IoSession)entry.getKey());
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 456 */       for (IoSession s : removeSessionList) {
/* 457 */         AgentsManager.this.mapAnonymousSession.remove(s);
/* 458 */         s.close(true);
/* 459 */         AgentsManager.this.logger.warn("Anonymous session timeout: " + s + " close now!");
/*     */       }
/*     */       
/*     */ 
/* 463 */       localIterator = AgentsManager.this.mapSessionAgent.entrySet().iterator();
/* 462 */       while (localIterator.hasNext()) {
/* 463 */         Map.Entry<IoSession, Agents> entry = (Map.Entry)localIterator.next();
/* 464 */         if (((Agents)entry.getValue()).getNumSendPingNotRes() >= AgentsManager.this.getNumRetryPing()) {
/* 465 */           AgentDesktopJob jobLogout = new AgentDesktopJob(
/* 466 */             Utils.genJobID());
/* 467 */           jobLogout.setAgentDesktopMsg(new LogoutRequest());
/* 468 */           jobLogout.setSession((IoSession)entry.getKey());
/*     */           
/* 470 */           AgentDesktopRequestManager mng = (AgentDesktopRequestManager)
/* 471 */             Utils.getCtx().getBean("AgentDesktopRequestManager");
/*     */           
/* 473 */           mng.process(jobLogout);
/*     */           
/* 475 */           AgentsManager.this.logger.info("PING TIMEOUT " + entry.getValue() + 
/* 476 */             " disconnected!");
/*     */ 
/*     */         }
/* 479 */         else if (System.currentTimeMillis() - ((Agents)entry.getValue()).getLastCheckConnection() > AgentsManager.this.getIntervalCheckConnection())
/*     */         {
/* 481 */           ((Agents)entry.getValue()).setLastCheckConnection(
/* 482 */             System.currentTimeMillis());
/* 483 */           synchronized ((Agents)entry.getValue())
/*     */           {
/*     */ 
/*     */ 
/* 487 */             ((Agents)entry.getValue()).setNumSendPingNotRes(
/* 488 */               ((Agents)entry.getValue())
/* 489 */               .getNumSendPingNotRes() + 1);
/*     */           }
/*     */           
/* 492 */           Utils.sendMessageToAgent((IoSession)entry.getKey(), 
/* 493 */             new TestConnectionResponse());
/*     */           
/* 495 */           AgentsManager.this.logger.info("send ping to: " + entry.getValue());
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 500 */       AgentsManager.this.checkMonitoringSpy();
/*     */       try {
/* 502 */         Thread.sleep(1000L);
/*     */       }
/*     */       catch (InterruptedException e) {
/* 505 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\manager\AgentsManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */