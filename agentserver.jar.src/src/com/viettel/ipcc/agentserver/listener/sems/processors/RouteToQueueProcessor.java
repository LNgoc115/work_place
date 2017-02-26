/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class RouteToQueueProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 13 */     forwardToOmap(job.getSemsCommand());
/* 14 */     String[] params = splitCommand(job);
/*    */     
/* 16 */     CallManager callManager = (CallManager)Utils.getCtx()
/* 17 */       .getBean("CallManager");
/* 18 */     CallInfo call = callManager.getCall(params[1]);
/* 19 */     if (call != null) {
/* 20 */       call.setQueueid(params[2]);
/*    */     } else {
/* 22 */       this.logger.info("Can't found callid: " + params[1]);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\RouteToQueueProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */