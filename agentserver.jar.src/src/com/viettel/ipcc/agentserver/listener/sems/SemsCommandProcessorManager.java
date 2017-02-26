/*    */ package com.viettel.ipcc.agentserver.listener.sems;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.AcdThread;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SemsCommandProcessorManager
/*    */ {
/*  9 */   private int numThread = 1;
/* 10 */   private long delayTime = 100L;
/* 11 */   private boolean isStarted = false;
/*    */   private List<AcdThread> listThread;
/*    */   private List<SemsCommandProcessor> listProcessor;
/*    */   public static final String SEMS_COMMAND_DELIM = ";";
/*    */   
/*    */   public void start() {
/* 17 */     if (this.isStarted) {
/* 18 */       return;
/*    */     }
/* 20 */     this.isStarted = true;
/*    */     
/* 22 */     this.listThread = new ArrayList();
/* 23 */     this.listProcessor = new ArrayList();
/*    */     
/* 25 */     for (int i = 0; i < this.numThread; i++) {
/* 26 */       SemsCommandProcessor processor = new SemsCommandProcessor();
/* 27 */       AcdThread thread = new AcdThread("sems-command-processor-" + i, 
/* 28 */         processor);
/* 29 */       this.listThread.add(thread);
/* 30 */       this.listProcessor.add(processor);
/* 31 */       processor.setSleepTime(this.delayTime);
/* 32 */       thread.start();
/*    */     }
/*    */   }
/*    */   
/*    */   public void stop()
/*    */   {
/* 38 */     if (!this.isStarted)
/* 39 */       return;
/* 40 */     this.isStarted = false;
/*    */     
/* 42 */     for (AcdThread thread : this.listThread) {
/* 43 */       thread.setRunning(false);
/*    */     }
/*    */     
/*    */ 
/* 47 */     for (SemsCommandProcessor processor : this.listProcessor) {
/* 48 */       processor.process();
/*    */     }
/*    */   }
/*    */   
/*    */   public void setNumThread(int numThread) {
/* 53 */     this.numThread = numThread;
/*    */   }
/*    */   
/*    */   public int getNumThread() {
/* 57 */     return this.numThread;
/*    */   }
/*    */   
/*    */   public void setDelayTime(long delayTime) {
/* 61 */     this.delayTime = delayTime;
/*    */   }
/*    */   
/*    */   public long getDelayTime() {
/* 65 */     return this.delayTime;
/*    */   }
/*    */   
/*    */   public void process(SemsCommandJob job) {
/* 69 */     String[] items = job.getSemsCommand().split(
/* 70 */       ";");
/* 71 */     int hash = Math.abs(items[1].hashCode());
/* 72 */     hash %= this.listThread.size();
/* 73 */     ((SemsCommandProcessor)this.listProcessor.get(hash)).addJob(job);
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\SemsCommandProcessorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */