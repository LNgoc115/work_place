/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.EndCallResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class EndCallProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 17 */     forwardToOmap(job.getSemsCommand());
/*    */     
/* 19 */     String[] params = splitCommand(job);
/* 20 */     CallManager callManager = (CallManager)Utils.getCtx().getBean(
/* 21 */       "CallManager");
/* 22 */     CallInfo call = callManager.getCall(params[1]);
/*    */     
/* 24 */     if (call != null)
/*    */     {
/* 26 */       EndCallResponse endcall = new EndCallResponse(call.getCallID(), 
/* 27 */         params[2]);
/* 28 */       List<Agents> listRelatedAgent = callManager
/* 29 */         .getAllAgentRelatedToCall(call.getCallID());
/*    */       
/* 31 */       for (Agents a : listRelatedAgent) {
/* 32 */         if (a != null) {
/* 33 */           this.logger.info("Send endcall " + call + " to [" + a + "]");
/* 34 */           Utils.sendMessageToAgent(a.getAgentSession(), endcall);
/*    */         }
/*    */       }
/*    */       
/* 38 */       callManager.removeCall(call);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\EndCallProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */