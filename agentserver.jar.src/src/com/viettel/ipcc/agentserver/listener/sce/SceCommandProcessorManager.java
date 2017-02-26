/*    */ package com.viettel.ipcc.agentserver.listener.sce;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.AcdThread;
/*    */ import java.io.PrintStream;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class SceCommandProcessorManager
/*    */ {
/*    */   private int numThread;
/* 14 */   private boolean isStarted = false;
/*    */   private List<AcdThread> listThread;
/*    */   private List<SceCommandProcessor> listProcessors;
/* 17 */   private long delayTime = 100L;
/*    */   private Random random;
/* 19 */   private static Logger logger = Logger.getLogger(SceCommandProcessorManager.class);
/*    */   
/*    */   public void start() {
/* 22 */     if (this.isStarted) {
/* 23 */       return;
/*    */     }
/* 25 */     this.isStarted = true;
/*    */     
/* 27 */     this.listThread = new LinkedList();
/* 28 */     this.listProcessors = new LinkedList();
/* 29 */     this.random = new Random();
/*    */     
/* 31 */     for (int i = 0; i < this.numThread; i++) {
/* 32 */       SceCommandProcessor processor = new SceCommandProcessor();
/* 33 */       processor.setSleepTime(this.delayTime);
/* 34 */       this.listProcessors.add(processor);
/*    */       
/* 36 */       AcdThread acdThread = new AcdThread("sce-command-process " + i, processor);
/* 37 */       acdThread.start();
/* 38 */       this.listThread.add(acdThread);
/* 39 */       logger.info("sce-command-processor-" + i + " stated.!");
/*    */     }
/*    */   }
/*    */   
/*    */   public void stop() {
/* 44 */     if (!this.isStarted) {
/* 45 */       return;
/*    */     }
/* 47 */     this.isStarted = false;
/*    */     
/* 49 */     for (AcdThread thread : this.listThread) {
/* 50 */       thread.setRunning(false);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getNumThread() {
/* 55 */     return this.numThread;
/*    */   }
/*    */   
/*    */   public void setNumThread(int numThread) {
/* 59 */     this.numThread = numThread;
/*    */   }
/*    */   
/*    */   public long getDelayTime() {
/* 63 */     return this.delayTime;
/*    */   }
/*    */   
/*    */   public void setDelayTime(long delayTime) {
/* 67 */     this.delayTime = delayTime;
/*    */   }
/*    */   
/*    */   public void processJob(SceJob job)
/*    */   {
/* 72 */     int index = this.random.nextInt(this.listProcessors.size());
/* 73 */     ((SceCommandProcessor)this.listProcessors.get(index)).addJob(job);
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 77 */     Random ran = new Random();
/* 78 */     System.out.println(ran.nextInt(2));
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\SceCommandProcessorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */