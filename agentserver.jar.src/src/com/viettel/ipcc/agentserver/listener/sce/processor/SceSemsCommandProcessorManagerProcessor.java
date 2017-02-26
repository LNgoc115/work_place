/*    */ package com.viettel.ipcc.agentserver.listener.sce.processor;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandProcessorManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SceSemsCommandProcessorManagerProcessor extends SceProcessorBase
/*    */ {
/* 12 */   private static Logger logger = Logger.getLogger(SceSemsCommandProcessorManagerProcessor.class);
/*    */   
/*    */ 
/*    */   public void process(SceJob job)
/*    */   {
/* 17 */     boolean isRestart = false;
/* 18 */     SemsCommandProcessorManager semsCmdMng = (SemsCommandProcessorManager)
/* 19 */       Utils.getCtx().getBean("SemsCommandProcessorManager");
/*    */     
/* 21 */     String cmd = job.getCommand();
/* 22 */     String value = job.getValue();
/* 23 */     logger.info("process a sce job with params : " + cmd + "," + value);
/*    */     
/* 25 */     if ((cmd.equalsIgnoreCase("numThreadSems")) && 
/* 26 */       (semsCmdMng.getNumThread() != Integer.valueOf(value)
/* 27 */       .intValue())) {
/* 28 */       semsCmdMng.setNumThread(Integer.valueOf(value).intValue());
/* 29 */       isRestart = true;
/* 30 */     } else if ((cmd.equalsIgnoreCase("delayTimeSems")) && 
/* 31 */       (semsCmdMng.getDelayTime() != Long.valueOf(value).longValue())) {
/* 32 */       semsCmdMng.setDelayTime(Long.valueOf(value).longValue());
/* 33 */       isRestart = true;
/*    */     }
/*    */     
/* 36 */     if (!isRestart) {
/* 37 */       return;
/*    */     }
/* 39 */     semsCmdMng.stop();
/* 40 */     logger.info("SemsCommandProcessorManager stopped!");
/* 41 */     semsCmdMng.start();
/* 42 */     logger.info("SemsCommandProcessorManager started!");
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\processor\SceSemsCommandProcessorManagerProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */