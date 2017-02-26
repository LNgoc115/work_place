/*    */ package com.viettel.ipcc.agentserver.listener.sce.processor;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SceCallManagerProcessor
/*    */   extends SceProcessorBase
/*    */ {
/* 13 */   private static Logger logger = Logger.getLogger(SceCallManagerProcessor.class);
/*    */   
/*    */ 
/*    */   public void process(SceJob job)
/*    */   {
/* 18 */     boolean isRestart = false;
/* 19 */     CallManager callManager = (CallManager)Utils.getCtx().getBean(
/* 20 */       "CallManager");
/*    */     
/* 22 */     String cmd = job.getCommand();
/* 23 */     String value = job.getValue();
/* 24 */     logger.info("process a sce job with params : " + cmd + "," + value);
/*    */     
/* 26 */     long oldCallTimeout = callManager.getCallTimeOut();
/* 27 */     long oldCheckCallInterval = callManager.getCheckCallInterval();
/*    */     
/* 29 */     if ((cmd.equalsIgnoreCase("callTimeOut")) && 
/* 30 */       (oldCallTimeout != Long.valueOf(value).longValue()))
/*    */     {
/* 32 */       callManager.setCallTimeOut(Long.valueOf(value).longValue());
/* 33 */       isRestart = true;
/* 34 */     } else if ((cmd.equalsIgnoreCase("checkCallInterval")) && 
/* 35 */       (oldCheckCallInterval != Long.valueOf(value).longValue()))
/*    */     {
/* 37 */       callManager.setCheckCallInterval(Long.valueOf(value).longValue());
/* 38 */       isRestart = true;
/*    */     }
/*    */     
/*    */ 
/* 42 */     if (!isRestart) {
/* 43 */       return;
/*    */     }
/* 45 */     callManager.stop();
/* 46 */     callManager.start();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\processor\SceCallManagerProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */