/*     */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.OmapCdrSender;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.SemsProcessorBase;
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ProcessorBase
/*     */ {
/*     */   public static final String USER_STATUS_AVAILABLE = "AVAILABLE";
/*     */   public static final String USER_STATUS_NOT_AVAILABLE = "NOT AVAILABLE";
/*     */   public static final String USER_STATUS_LOG_OUT = "LOGOUT";
/*     */   public static final String USER_STATUS_LOG_IN = "LOGIN";
/*     */   public static final String USER_STATUS_GO_OUT = "GO OUT";
/*     */   public static final String USER_STATUS_MEETING = "MEETING";
/*     */   public static final String USER_STATUS_TYPING = "TYPING";
/*     */   public static final String USER_STATUS_AT_LUNCH = "AT LUNCH";
/*     */   public static final String USER_STATUS_NO_ANSWER = "NO ANSWER";
/*     */   public static final String IPPHONE_STATUS_READY = "READY";
/*     */   public static final String IPPHONE_STATUS_NOT_READY = "NOT READY";
/*     */   public static final String IPPHONE_STATUS_RINGING = "RINGING";
/*     */   public static final String SUPERVISOR_ROLE_NAME = "ipcc_Supervisor";
/*     */   public static final String AGENT_ROLE_NAME = "ipcc_Agent";
/*     */   public static final String SHIFT_LEADER_STAT_AGENT_ROLE_NAME = "ipcc_ShiftLeaderStatAgent";
/*     */   public static final String SHIFT_LEADER_SEARCH_AGENT_ROLE_NAME = "ipcc_ShiftLeaderSearchAgent";
/*     */   public static final String SHIFT_LEADER_VIEW_QUEUE_ROLE_NAME = "ipcc_ShiftLeaderViewQueue";
/*  42 */   public static final Pattern GROUP_PATTERN_ROLE_NAME = Pattern.compile("GROUP.*");
/*     */   
/*     */   public static final String INVITE_SEMS_CMD_NAME = "inviteagent";
/*     */   
/*     */   public static final String MUTE_SEMS_CMD_NAME = "mutemethod";
/*     */   public static final String UNMUTE_SEMS_CMD_NAME = "unmutemethod";
/*     */   public static final String HOLD_SEMS_CMD_NAME = "holdmethod";
/*     */   public static final String UNHOLD_SEMS_CMD_NAME = "unholdmethod";
/*     */   public static final String SPY_SEMS_CMD_NAME = "spy";
/*     */   public static final String TERMINAL_SEMS_CMD_NAME = "terminateconf";
/*     */   public static final String DROP_AGENT_SEMS_CMD_NAME = "dropagent";
/*     */   public static final String TRANSFER_SEMS_CMD_NAME = "replaceagent";
/*     */   public static final String MONITORING_CHK_USER_CMD_NAME = "CheckUserConnected";
/*     */   public static final String MONITORING_END_SYSTEM_SESSION_CMD_NAME = "monitoringdisconnectsystemsession";
/*     */   protected static final String CALL_NOT_FOUND = "IPCCERR0000";
/*  57 */   protected Logger logger = Logger.getLogger(ProcessorBase.class);
/*     */   
/*     */   public abstract void process(AgentDesktopJob paramAgentDesktopJob);
/*     */   
/*     */   public static void sendChangeStatusCDR(Agents agent, String newStatus, String superChange)
/*     */   {
/*  63 */     String message = "AGENTCHANGESTATUS;" + 
/*  64 */       agent.getVsaID() + 
/*  65 */       ";" + 
/*  66 */       statusToInt(newStatus) + 
/*  67 */       ";" + 
/*  68 */       SemsProcessorBase.STANDARD_TIME_FORMAT.format(new Date(
/*  69 */       System.currentTimeMillis())) + ";" + agent.getShiftid() + ";" + (
/*  70 */       agent.isSuper() ? "SUPERVISOR" : "AGENT") + ";" + 
/*  71 */       superChange;
/*     */     
/*  73 */     OmapCdrSender cdrSener = (OmapCdrSender)Utils.getCtx().getBean(
/*  74 */       "OmapCdrSender");
/*  75 */     cdrSener.sendMessage(message);
/*     */   }
/*     */   
/*     */   private static int statusToInt(String status)
/*     */   {
/*  80 */     if (status.equals("LOGOUT"))
/*  81 */       return 0;
/*  82 */     if (status.equals("LOGIN"))
/*  83 */       return 1;
/*  84 */     if (status.equals("NOT AVAILABLE"))
/*  85 */       return 2;
/*  86 */     if (status.equals("AVAILABLE"))
/*  87 */       return 3;
/*  88 */     if (status.equals("MEETING"))
/*  89 */       return 4;
/*  90 */     if (status.equals("TYPING"))
/*  91 */       return 5;
/*  92 */     if (status.equals("AT LUNCH"))
/*  93 */       return 6;
/*  94 */     if (status.equals("GO OUT"))
/*  95 */       return 7;
/*  96 */     if (status.equals("NO ANSWER")) {
/*  97 */       return 16;
/*     */     }
/*     */     
/* 100 */     return 0;
/*     */   }
/*     */   
/*     */   protected String processSemsCmd(String method, String callid, String p1, String p2)
/*     */   {
/* 105 */     CallManager callManager = (CallManager)Utils.getCtx().getBean(
/* 106 */       "CallManager");
/*     */     
/* 108 */     CallInfo call = callManager.getCall(callid);
/*     */     
/* 110 */     if (call != null)
/*     */     {
/* 112 */       List<Object> listParams = new ArrayList();
/* 113 */       listParams.add(callid);
/* 114 */       if (p1 != null)
/* 115 */         listParams.add(p1);
/* 116 */       if (p2 != null) {
/* 117 */         listParams.add(p2);
/*     */       }
/* 119 */       Object res = Utils.sendXmlrpc(call.getSemsUrl(), method, listParams
/* 120 */         .toArray());
/*     */       
/* 122 */       if (res.toString().equals("OK"))
/*     */       {
/* 124 */         this.logger.info("Process method: " + method + " callid: " + callid + 
/* 125 */           " agent: " + p1 + " OK");
/*     */       } else {
/* 127 */         this.logger.info("Process method: " + method + " callid: " + callid + 
/* 128 */           " agent: " + p1 + " NOT OK");
/*     */       }
/*     */       
/* 131 */       return res.toString();
/*     */     }
/*     */     
/*     */ 
/* 135 */     this.logger.warn("can't found callid: " + callid);
/* 136 */     return "IPCCERR0000";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Agents checkLogInAndDisconnect(IoSession session)
/*     */   {
/* 142 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 143 */       "AgentsManager");
/*     */     
/* 145 */     Agents agent = agentManager.findBySession(session);
/*     */     
/* 147 */     if (agent != null) {
/* 148 */       return agent;
/*     */     }
/*     */     
/* 151 */     this.logger.info("may be spam session, close now " + session);
/* 152 */     session.close(true);
/*     */     
/*     */ 
/* 155 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\ProcessorBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */