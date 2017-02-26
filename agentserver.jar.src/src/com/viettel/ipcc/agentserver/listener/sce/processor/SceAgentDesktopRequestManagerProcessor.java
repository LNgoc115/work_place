/*    */ package com.viettel.ipcc.agentserver.listener.sce.processor;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopRequestManager;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SceAgentDesktopRequestManagerProcessor
/*    */   extends SceProcessorBase
/*    */ {
/* 13 */   private static Logger logger = Logger.getLogger(SceAgentDesktopRequestManagerProcessor.class);
/*    */   
/*    */ 
/*    */   public void process(SceJob job)
/*    */   {
/* 18 */     boolean isRestart = false;
/* 19 */     AgentDesktopRequestManager agentDesktopRequestManager = (AgentDesktopRequestManager)
/* 20 */       Utils.getCtx().getBean("AgentDesktopRequestManager");
/*    */     
/* 22 */     String cmd = job.getCommand();
/* 23 */     String value = job.getValue();
/*    */     
/* 25 */     logger.info("process a sce job with params : " + cmd + "," + value);
/* 26 */     if ((cmd.equalsIgnoreCase("numThreadAgent")) && 
/* 27 */       (agentDesktopRequestManager.getNumThread() != 
/* 28 */       Integer.valueOf(value).intValue())) {
/* 29 */       agentDesktopRequestManager.setNumThread(Integer.valueOf(value)
/* 30 */         .intValue());
/*    */       
/* 32 */       isRestart = true;
/* 33 */     } else if ((cmd.equalsIgnoreCase("timeSleepAgent")) && 
/* 34 */       (agentDesktopRequestManager.getTimeSleep() != Long.valueOf(
/* 35 */       value).longValue()))
/*    */     {
/* 37 */       agentDesktopRequestManager.setTimeSleep(Long.valueOf(value)
/* 38 */         .longValue());
/* 39 */       isRestart = true;
/*    */     }
/*    */     
/* 42 */     if (!isRestart) {
/* 43 */       return;
/*    */     }
/* 45 */     agentDesktopRequestManager.stop();
/* 46 */     agentDesktopRequestManager.start();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\processor\SceAgentDesktopRequestManagerProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */