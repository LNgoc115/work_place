/*    */ package com.viettel.ipcc.agentserver.listener.sce.processor;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.OmapCdrSender;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SceOmapCdrSenderProcessor extends SceProcessorBase
/*    */ {
/* 12 */   private static Logger logger = Logger.getLogger(SceOmapCdrSenderProcessor.class);
/* 13 */   private final String PRE_BROKER_URL = "tcp://";
/*    */   
/*    */ 
/*    */   public void process(SceJob job)
/*    */   {
/* 18 */     boolean isRestart = false;
/* 19 */     OmapCdrSender omapCdrSender = (OmapCdrSender)Utils.getCtx().getBean(
/* 20 */       "OmapCdrSender");
/*    */     
/* 22 */     String cmd = job.getCommand();
/* 23 */     String value = job.getValue();
/*    */     
/* 25 */     logger.info("process a sce job with params : " + cmd + "," + value);
/* 26 */     if ((cmd.equalsIgnoreCase("brokerUrl")) && 
/* 27 */       (!omapCdrSender.getBrokerUrl().equals(value)))
/*    */     {
/* 29 */       omapCdrSender.setBrokerUrl("tcp://" + value);
/* 30 */       isRestart = true;
/* 31 */     } else if ((cmd.equalsIgnoreCase("queueName")) && 
/* 32 */       (!omapCdrSender.getQueuename().equals(value)))
/*    */     {
/* 34 */       omapCdrSender.setQueuename(value);
/* 35 */       isRestart = true;
/*    */     }
/*    */     
/* 38 */     if (!isRestart) {
/* 39 */       return;
/*    */     }
/* 41 */     omapCdrSender.stop();
/* 42 */     omapCdrSender.start();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\processor\SceOmapCdrSenderProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */