/*     */ package com.viettel.ipcc.agentserver.dbupdater;
/*     */ 
/*     */ import com.viettel.ipcc.agentserver.common.AcdThread;
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ 
/*     */ public class DbUpdater
/*     */ {
/*  14 */   private int numThreadAgentUpdater = 1;
/*  15 */   private long threadAgentUpdaterDelay = 10L;
/*  16 */   private long maxTimeInqueue = 360L;
/*  17 */   private boolean isStart = false;
/*     */   private AgentTableUpdater agentUpdater;
/*     */   private List<AcdThread> listThread;
/*  20 */   private Logger logger = Logger.getLogger(DbUpdater.class);
/*     */   
/*     */   public static final String IP_LOGIN_COL = "IP_LOGIN";
/*     */   
/*     */   public static final String NUM_REJECTCALL_COL = "NUM_REJECTCALL";
/*     */   public static final String LOGIN_TIME_COL = "LOGIN_TIME";
/*     */   public static final String LAST_CHANGE_STATUS_COL = "LAST_CHANGE_STATUS";
/*     */   public static final String VSA_USER_LOGIN_COL = "VSA_USER_LOGIN";
/*     */   public static final String CALL_STATUS_COL = "CALL_STATUS";
/*     */   public static final String SYSTEM_STATUS_COL = "SYSTEM_STATUS";
/*     */   public static final String TOTAL_ANSWER_CALL_COL = "TOTAL_ANSWER_CALL";
/*     */   public static final String TOTAL_ANSWER_TIME_COL = "TOTAL_ANSWER_TIME";
/*     */   public static final String GROUP_NAME_COL = "GROUP_NAME";
/*     */   
/*     */   public DbUpdater()
/*     */   {
/*  36 */     this.agentUpdater = new AgentTableUpdater();
/*     */   }
/*     */   
/*     */   public void start() {
/*  40 */     if (this.isStart)
/*  41 */       return;
/*  42 */     this.isStart = true;
/*  43 */     this.agentUpdater.setSleepTime(this.threadAgentUpdaterDelay);
/*  44 */     this.listThread = new ArrayList();
/*  45 */     for (int i = 0; i < this.numThreadAgentUpdater; i++) {
/*  46 */       AcdThread thread = new AcdThread("thread-update-agent-table-" + i, 
/*  47 */         this.agentUpdater);
/*  48 */       this.logger.info(thread.getName() + " started!");
/*  49 */       thread.start();
/*  50 */       this.listThread.add(thread);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void stop()
/*     */   {
/*  58 */     if (!this.isStart)
/*  59 */       return;
/*  60 */     this.isStart = false;
/*     */     
/*  62 */     for (AcdThread th : this.listThread) {
/*  63 */       th.setRunning(false);
/*  64 */       this.logger.info(th.getName() + " stopped!");
/*     */     }
/*     */     
/*  67 */     this.agentUpdater.process();
/*     */   }
/*     */   
/*     */   public void setThreadAgentUpdaterDelay(long threadAgentUpdaterDelay)
/*     */   {
/*  72 */     this.threadAgentUpdaterDelay = threadAgentUpdaterDelay;
/*     */   }
/*     */   
/*     */   public long getThreadAgentUpdaterDelay() {
/*  76 */     return this.threadAgentUpdaterDelay;
/*     */   }
/*     */   
/*     */   public void setNumThreadAgentUpdater(int numThreadAgentUpdater) {
/*  80 */     this.numThreadAgentUpdater = numThreadAgentUpdater;
/*     */   }
/*     */   
/*     */   public int getNumThreadAgentUpdater() {
/*  84 */     return this.numThreadAgentUpdater;
/*     */   }
/*     */   
/*     */   public void updateAgentLoginType(String agentid, String type) {
/*  88 */     AgentsManager mng = (AgentsManager)Utils.getCtx().getBean("AgentsManager");
/*     */     
/*     */ 
/*     */ 
/*  92 */     AgentUpdaterJob job = new AgentUpdaterJob(Utils.genJobID());
/*  93 */     job.setAgentid(agentid);
/*  94 */     job.setColumnName("LOGIN_TYPE");
/*  95 */     job.setValue(type);
/*  96 */     this.agentUpdater.addJob(job);
/*     */   }
/*     */   
/*     */ 
/*     */   public void updateAgentStatus(String agentid, String status)
/*     */   {
/* 102 */     AgentsManager mng = (AgentsManager)Utils.getCtx().getBean("AgentsManager");
/*     */     
/*     */ 
/*     */ 
/* 106 */     AgentUpdaterJob job = new AgentUpdaterJob(Utils.genJobID());
/* 107 */     job.setAgentid(agentid);
/* 108 */     job.setColumnName("USER_STATUS");
/* 109 */     job.setValue(status);
/* 110 */     this.agentUpdater.addJob(job);
/*     */   }
/*     */   
/*     */   public void updateAgentAttr(String agentid, String col, Object obj) {
/* 114 */     AgentsManager mng = (AgentsManager)Utils.getCtx().getBean("AgentsManager");
/*     */     
/*     */ 
/*     */ 
/* 118 */     AgentUpdaterJob job = new AgentUpdaterJob(Utils.genJobID());
/* 119 */     job.setAgentid(agentid);
/* 120 */     job.setColumnName(col);
/* 121 */     job.setValue(obj);
/* 122 */     this.agentUpdater.addJob(job);
/*     */   }
/*     */   
/*     */   public void setMaxTimeInqueue(long maxTimeInqueue) {
/* 126 */     this.maxTimeInqueue = maxTimeInqueue;
/*     */   }
/*     */   
/*     */   public long getMaxTimeInqueue() {
/* 130 */     return this.maxTimeInqueue;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dbupdater\DbUpdater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */