/*    */ package com.viettel.ipcc.agentserver.listener.sce.processor;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SceDbUpdaterProcessor
/*    */   extends SceProcessorBase
/*    */ {
/* 13 */   private static Logger logger = Logger.getLogger(SceDbUpdaterProcessor.class);
/*    */   
/*    */ 
/*    */   public void process(SceJob job)
/*    */   {
/* 18 */     boolean isRestart = false;
/* 19 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/*    */     
/* 21 */     String cmd = job.getCommand();
/* 22 */     String value = job.getValue();
/* 23 */     int oldNumThread = dbUpdater.getNumThreadAgentUpdater();
/* 24 */     long oldTreadAgentUpdaterDelay = dbUpdater.getThreadAgentUpdaterDelay();
/*    */     
/* 26 */     logger.info("process a sce job with params : " + cmd + "," + value);
/* 27 */     if ((cmd.equalsIgnoreCase("numThreadAgentUpdater")) && 
/* 28 */       (oldNumThread != Integer.valueOf(value).intValue()))
/*    */     {
/* 30 */       dbUpdater.setNumThreadAgentUpdater(Integer.valueOf(value).intValue());
/* 31 */       isRestart = true;
/* 32 */     } else if ((cmd.equalsIgnoreCase("threadAgentUpdaterDelay")) && 
/* 33 */       (oldTreadAgentUpdaterDelay != Long.valueOf(value).longValue()))
/*    */     {
/* 35 */       dbUpdater.setThreadAgentUpdaterDelay(Long.valueOf(value).longValue());
/* 36 */       isRestart = true;
/*    */     }
/*    */     
/*    */ 
/* 40 */     if (!isRestart) {
/* 41 */       return;
/*    */     }
/* 43 */     dbUpdater.stop();
/* 44 */     dbUpdater.start();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\processor\SceDbUpdaterProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */