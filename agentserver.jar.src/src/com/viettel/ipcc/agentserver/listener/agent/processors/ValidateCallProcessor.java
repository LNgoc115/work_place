/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.ValidateCallRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.ValidateCallResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class ValidateCallProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 16 */     Agents agent = null;
/* 17 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 18 */       ValidateCallRequest request = (ValidateCallRequest)job.getAgentDesktopMsg();
/* 19 */       String callId = request.getCallId().trim();
/*    */       
/* 21 */       this.logger.debug("process check validated callId : " + 
/* 22 */         callId + ",agent = " + agent.getDeviceID());
/*    */       
/* 24 */       CallManager callMng = (CallManager)Utils.getCtx().getBean("CallManager");
/* 25 */       CallInfo callInfo = callMng.getCall(callId);
/* 26 */       if (callInfo == null) {
/* 27 */         this.logger.info("callId " + callId + " is not valid..");
/* 28 */         Utils.sendMessageToAgent(job.getSession(), 
/* 29 */           new ValidateCallResponse("NOT OK", callId));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\ValidateCallProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */