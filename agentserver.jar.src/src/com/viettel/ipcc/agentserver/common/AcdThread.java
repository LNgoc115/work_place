/*    */ package com.viettel.ipcc.agentserver.common;
/*    */ 
/*    */ public class AcdThread extends Thread {
/*  4 */   private volatile boolean isRunning = true;
/*    */   private IThreadProcessor processor;
/*    */   
/*    */   public AcdThread(String threadName, IThreadProcessor processor) {
/*  8 */     setName(threadName);
/*  9 */     this.processor = processor;
/*    */   }
/*    */   
/*    */   public void run() {
/* 13 */     while (isRunning()) {
/*    */       try {
/* 15 */         process();
/*    */       } catch (Exception ex) {
/* 17 */         ex.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void process() {
/* 23 */     this.processor.process();
/*    */   }
/*    */   
/*    */   public void setRunning(boolean isRunning) {
/* 27 */     this.isRunning = isRunning;
/*    */   }
/*    */   
/*    */   public boolean isRunning() {
/* 31 */     return this.isRunning;
/*    */   }
/*    */   
/*    */   public IThreadProcessor getProcessor() {
/* 35 */     return this.processor;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\common\AcdThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */