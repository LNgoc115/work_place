/*    */ package com.viettel.ipcc.agentserver.listener.sce.processor;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AcdServerRequestSender;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SceAcdServerRequestSenderProcessor extends SceProcessorBase
/*    */ {
/*    */   public void process(SceJob job)
/*    */   {
/* 13 */     AcdServerRequestSender acdServerRequestSender = (AcdServerRequestSender)
/* 14 */       Utils.getCtx().getBean("AcdServerRequestSender");
/*    */     
/* 16 */     acdServerRequestSender.initList();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\processor\SceAcdServerRequestSenderProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */