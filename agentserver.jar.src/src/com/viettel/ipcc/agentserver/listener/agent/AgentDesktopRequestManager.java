/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.AcdThread;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class AgentDesktopRequestManager
/*    */ {
/* 11 */   private int numThread = 1;
/* 12 */   private long timeSleep = 10L;
/*    */   private AgentDesktopRequestProcessor processor;
/*    */   private MonitoringBroadCastProcessor monitoringBroadCastProcessor;
/*    */   private List<AcdThread> listThread;
/* 16 */   private boolean isStarted = false;
/* 17 */   private Logger logger = Logger.getLogger(AgentDesktopRequestManager.class);
/*    */   
/*    */   public void setNumThread(int numThread) {
/* 20 */     this.numThread = numThread;
/*    */   }
/*    */   
/*    */   public int getNumThread() {
/* 24 */     return this.numThread;
/*    */   }
/*    */   
/*    */   public void setTimeSleep(long timeSleep) {
/* 28 */     this.timeSleep = timeSleep;
/*    */   }
/*    */   
/*    */   public long getTimeSleep() {
/* 32 */     return this.timeSleep;
/*    */   }
/*    */   
/*    */   public void setProcessor(AgentDesktopRequestProcessor processor) {
/* 36 */     this.processor = processor;
/*    */   }
/*    */   
/*    */   public AgentDesktopRequestProcessor getProcessor() {
/* 40 */     return this.processor;
/*    */   }
/*    */   
/*    */   public void start() {
/* 44 */     if (this.isStarted)
/* 45 */       return;
/* 46 */     this.isStarted = true;
/*    */     
/* 48 */     this.logger.info("AgentDesktopRequestManager started!");
/* 49 */     this.listThread = new ArrayList();
/* 50 */     this.processor.setSleepTime(getTimeSleep());
/*    */     
/* 52 */     for (int i = 0; i < this.numThread; i++) {
/* 53 */       AcdThread thread = new AcdThread("thread-agentdesktop-processor-" + 
/* 54 */         i, this.processor);
/* 55 */       this.logger.info(thread.getName() + " started!");
/* 56 */       thread.start();
/* 57 */       this.listThread.add(thread);
/*    */     }
/*    */     
/*    */ 
/* 61 */     AcdThread thread = new AcdThread("thread-broadcast-monitoring", 
/* 62 */       getMonitoringBroadCastProcessor());
/*    */     
/* 64 */     thread.start();
/* 65 */     this.listThread.add(thread);
/*    */   }
/*    */   
/*    */   public void stop()
/*    */   {
/* 70 */     if (!this.isStarted)
/* 71 */       return;
/* 72 */     this.isStarted = false;
/* 73 */     this.logger.info("AgentDesktopRequestManager stopped!");
/* 74 */     for (AcdThread thread : this.listThread) {
/* 75 */       thread.setRunning(false);
/* 76 */       this.logger.info(thread.getName() + " stopped!");
/*    */     }
/*    */     
/*    */ 
/* 80 */     this.processor.process();
/* 81 */     getMonitoringBroadCastProcessor().process();
/*    */   }
/*    */   
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 86 */     this.processor.addJob(job);
/*    */   }
/*    */   
/*    */   public void processMonitoringBroadcast(MonitoringBroadcastJob job) {
/* 90 */     getMonitoringBroadCastProcessor().addJob(job);
/*    */   }
/*    */   
/*    */   public void setMonitoringBroadCastProcessor(MonitoringBroadCastProcessor monitoringBroadCastProcessor)
/*    */   {
/* 95 */     this.monitoringBroadCastProcessor = monitoringBroadCastProcessor;
/*    */   }
/*    */   
/*    */   public MonitoringBroadCastProcessor getMonitoringBroadCastProcessor() {
/* 99 */     return this.monitoringBroadCastProcessor;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\AgentDesktopRequestManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */