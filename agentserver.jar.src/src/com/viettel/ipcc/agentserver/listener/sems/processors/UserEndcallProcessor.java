/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.UserEndCallResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class UserEndcallProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 17 */     forwardToOmap(job.getSemsCommand());
/*    */     
/* 19 */     String[] params = splitCommand(job);
/*    */     
/* 21 */     CallManager callManager = (CallManager)Utils.getCtx().getBean(
/* 22 */       "CallManager");
/*    */     
/* 24 */     CallInfo call = callManager.getCall(params[1]);
/*    */     
/* 26 */     if (call != null) {
/* 27 */       List<Agents> listAllRelatedCall = callManager
/* 28 */         .getAllAgentRelatedToCall(call.getCallID());
/* 29 */       UserEndCallResponse userendcall = new UserEndCallResponse(call
/* 30 */         .getCallID(), params[2]);
/*    */       
/* 32 */       for (Agents a : listAllRelatedCall) {
/* 33 */         if (a != null) {
/* 34 */           this.logger.info("send userendcall " + call + " to [" + a + "]");
/* 35 */           Utils.sendMessageToAgent(a.getAgentSession(), userendcall);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\UserEndcallProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */