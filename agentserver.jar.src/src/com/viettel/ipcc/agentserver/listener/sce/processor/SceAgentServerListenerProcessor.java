/*    */ package com.viettel.ipcc.agentserver.listener.sce.processor;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentServerListener;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SceAgentServerListenerProcessor
/*    */   extends SceProcessorBase
/*    */ {
/* 13 */   private static Logger logger = Logger.getLogger(SceAgentServerListenerProcessor.class);
/*    */   
/*    */ 
/*    */   public void process(SceJob job)
/*    */   {
/* 18 */     boolean isRestart = false;
/* 19 */     AgentServerListener agentServerListener = (AgentServerListener)
/* 20 */       Utils.getCtx().getBean("AgentServerListener");
/*    */     
/* 22 */     String cmd = job.getCommand();
/* 23 */     String value = job.getValue();
/*    */     
/* 25 */     logger.info("process a sce job with params : " + cmd + "," + value);
/*    */     
/* 27 */     if ((cmd.equalsIgnoreCase("minaPort")) && 
/* 28 */       (Integer.valueOf(value).intValue() != agentServerListener
/* 29 */       .getPort())) {
/* 30 */       agentServerListener.setPort(Integer.valueOf(value).intValue());
/*    */       
/* 32 */       isRestart = true;
/*    */     }
/*    */     
/* 35 */     if (!isRestart) {
/* 36 */       return;
/*    */     }
/* 38 */     agentServerListener.stop();
/* 39 */     agentServerListener.start();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\processor\SceAgentServerListenerProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */