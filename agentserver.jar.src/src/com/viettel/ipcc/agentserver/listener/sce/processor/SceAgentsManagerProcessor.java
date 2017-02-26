/*    */ package com.viettel.ipcc.agentserver.listener.sce.processor;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SceAgentsManagerProcessor extends SceProcessorBase
/*    */ {
/* 12 */   private static Logger logger = Logger.getLogger(SceAgentsManagerProcessor.class);
/*    */   
/*    */ 
/*    */   public void process(SceJob job)
/*    */   {
/* 17 */     boolean isRestart = false;
/* 18 */     AgentsManager agentMng = (AgentsManager)Utils.getCtx().getBean(
/* 19 */       "AgentsManager");
/*    */     
/* 21 */     String cmd = job.getCommand();
/* 22 */     String value = job.getValue();
/* 23 */     long oldIntervalCheckConnection = agentMng.getIntervalCheckConnection();
/* 24 */     int oldNumRetryPing = agentMng.getNumRetryPing();
/* 25 */     long oldAnonymousTimout = agentMng.getAnonymousTimeout();
/* 26 */     int oldMaxNumberRejectCall = agentMng.getMaxNumberRejectCall();
/*    */     
/*    */ 
/* 29 */     logger.info("process a sce job with params : " + cmd + "," + value);
/* 30 */     if ((cmd.equalsIgnoreCase("intervalCheckConnection")) && 
/* 31 */       (oldIntervalCheckConnection != Long.valueOf(value).longValue()))
/*    */     {
/* 33 */       agentMng.setIntervalCheckConnection(Long.valueOf(value).longValue());
/* 34 */       isRestart = true;
/* 35 */     } else if ((cmd.equalsIgnoreCase("numRetryPing")) && 
/* 36 */       (oldNumRetryPing != Integer.valueOf(value).intValue()))
/*    */     {
/* 38 */       agentMng.setNumRetryPing(Integer.valueOf(value).intValue());
/* 39 */       isRestart = true;
/* 40 */     } else if ((cmd.equalsIgnoreCase("anonymousTimeout")) && 
/* 41 */       (oldAnonymousTimout != Long.valueOf(value).longValue()))
/*    */     {
/* 43 */       agentMng.setAnonymousTimeout(Long.valueOf(value).longValue());
/* 44 */       isRestart = true;
/* 45 */     } else if ((cmd.equalsIgnoreCase("maxNumberRejectCall")) && 
/* 46 */       (oldMaxNumberRejectCall != Integer.valueOf(value).intValue()))
/*    */     {
/* 48 */       agentMng.setMaxNumberRejectCall(Integer.valueOf(value).intValue());
/* 49 */       isRestart = true;
/*    */     }
/*    */     
/* 52 */     if (!isRestart) {
/* 53 */       return;
/*    */     }
/* 55 */     agentMng.stop();
/* 56 */     agentMng.start();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\processor\SceAgentsManagerProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */