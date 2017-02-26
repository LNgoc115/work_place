/*     */ package com.viettel.ipcc.agentserver.manager;
/*     */ 
/*     */ import com.viettel.ipcc.agentserver.common.AcdThread;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*     */ import com.viettel.ipcc.agentserver.common.IThreadProcessor;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class CallManager
/*     */ {
/*     */   private Map<String, CallInfo> mapCallIDCall;
/*     */   private List<AcdThread> listThread;
/*  23 */   private Logger logger = Logger.getLogger(CallManager.class);
/*  24 */   private long checkCallInterval = 3600000L;
/*  25 */   private long callTimeOut = 86400000L;
/*  26 */   private boolean isStarted = false;
/*     */   
/*     */   public void start() {
/*  29 */     if (this.isStarted) {
/*  30 */       return;
/*     */     }
/*  32 */     this.isStarted = true;
/*     */     
/*  34 */     this.logger.info("Call manager started!");
/*  35 */     this.listThread = new ArrayList();
/*     */     
/*  37 */     AcdThread thread = new AcdThread("thread-check-call-timeout", 
/*  38 */       new CheckCallProcessor());
/*  39 */     thread.start();
/*  40 */     this.listThread.add(thread);
/*     */   }
/*     */   
/*     */   public void stop()
/*     */   {
/*  45 */     if (!this.isStarted) {
/*  46 */       return;
/*     */     }
/*     */     
/*  49 */     this.isStarted = false;
/*  50 */     this.logger.info("Call manager stopped!");
/*  51 */     for (AcdThread thread : this.listThread) {
/*  52 */       thread.setRunning(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public CallManager() {
/*  57 */     this.mapCallIDCall = 
/*  58 */       java.util.Collections.synchronizedMap(new HashMap());
/*     */   }
/*     */   
/*     */   public void addCall(CallInfo call)
/*     */   {
/*  63 */     this.mapCallIDCall.put(call.getCallID(), call);
/*     */   }
/*     */   
/*     */ 
/*     */   public void removeCall(CallInfo call)
/*     */   {
/*  69 */     this.mapCallIDCall.remove(call.getCallID());
/*     */   }
/*     */   
/*     */ 
/*     */   public CallInfo getCall(String callID)
/*     */   {
/*  75 */     return (CallInfo)this.mapCallIDCall.get(callID);
/*     */   }
/*     */   
/*     */ 
/*     */   public void addAgentToCall(String callid, Agents agent)
/*     */   {
/*  81 */     CallInfo call = getCall(callid);
/*     */     
/*  83 */     if (call != null) {
/*  84 */       call.getAgentsInCall().add(agent);
/*     */     } else {
/*  86 */       this.logger.info("can't found callid: " + callid);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void removeAgentFromCall(String callid, Agents agent)
/*     */   {
/*  93 */     CallInfo call = getCall(callid);
/*     */     
/*  95 */     if (call != null) {
/*  96 */       call.getAgentsInCall().remove(agent);
/*     */     } else {
/*  98 */       this.logger.info("can't found callid: " + callid);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public List<Agents> getAllAgentRelatedToCall(String callid)
/*     */   {
/* 105 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 106 */       "AgentsManager");
/*     */     
/* 108 */     CallInfo call = getCall(callid);
/* 109 */     Set<Agents> res = new HashSet();
/* 110 */     if (call != null) {
/* 111 */       res.addAll(call.getAgentsInCall());
/* 112 */       List<Agents> listSuper = agentManager.getGroupSuper(call
/* 113 */         .getGroupID());
/* 114 */       if (listSuper != null) {
/* 115 */         res.addAll(listSuper);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 121 */     return new ArrayList(res);
/*     */   }
/*     */   
/*     */   public void setCheckCallInterval(long checkCallInterval)
/*     */   {
/* 126 */     this.checkCallInterval = checkCallInterval;
/*     */   }
/*     */   
/*     */   public long getCheckCallInterval() {
/* 130 */     return this.checkCallInterval;
/*     */   }
/*     */   
/*     */   public void setCallTimeOut(long callTimeOut) {
/* 134 */     this.callTimeOut = callTimeOut;
/*     */   }
/*     */   
/*     */   public long getCallTimeOut() {
/* 138 */     return this.callTimeOut;
/*     */   }
/*     */   
/*     */   public Set<String> getAllCallID()
/*     */   {
/* 143 */     return this.mapCallIDCall.keySet();
/*     */   }
/*     */   
/*     */   public int getNumAnsweredCall()
/*     */   {
/* 148 */     int count = 0;
/*     */     
/* 150 */     for (Map.Entry<String, CallInfo> entry : this.mapCallIDCall.entrySet()) {
/* 151 */       if (((CallInfo)entry.getValue()).isAnswered()) {
/* 152 */         count++;
/*     */       }
/*     */     }
/*     */     
/* 156 */     return count;
/*     */   }
/*     */   
/*     */   class CheckCallProcessor implements IThreadProcessor
/*     */   {
/* 161 */     private long lastCheck = System.currentTimeMillis();
/*     */     
/*     */     CheckCallProcessor() {}
/*     */     
/*     */     public void process()
/*     */     {
/* 167 */       if (System.currentTimeMillis() - this.lastCheck > CallManager.this.getCheckCallInterval()) {
/* 168 */         List<String> listRemovedCall = new ArrayList();
/*     */         
/*     */ 
/* 171 */         Iterator localIterator = CallManager.this.mapCallIDCall.entrySet().iterator();
/* 170 */         while (localIterator.hasNext()) {
/* 171 */           Map.Entry<String, CallInfo> entry = (Map.Entry)localIterator.next();
/*     */           
/* 173 */           if (System.currentTimeMillis() - ((CallInfo)entry.getValue()).getStartTime().getTime() > CallManager.this.getCallTimeOut()) {
/* 174 */             listRemovedCall.add((String)entry.getKey());
/*     */           }
/*     */         }
/*     */         
/* 178 */         for (String key : listRemovedCall) {
/* 179 */           CallManager.this.logger.warn("call: " + key + " timeout, remove now!");
/* 180 */           CallManager.this.mapCallIDCall.remove(key);
/*     */         }
/*     */         
/* 183 */         this.lastCheck = System.currentTimeMillis();
/*     */       }
/*     */       else {
/*     */         try {
/* 187 */           Thread.sleep(1000L);
/*     */         }
/*     */         catch (InterruptedException e) {
/* 190 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\manager\CallManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */