/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.OutCallRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.OutCallResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.CalloutInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ErrorCode;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class CalloutRequestProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 16 */     Agents agent = null;
/* 17 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 18 */       OutCallRequest request = (OutCallRequest)job.getAgentDesktopMsg();
/* 19 */       String number = request.getTelephoneNumber();
/*    */       
/* 21 */       synchronized (agent) {
/* 22 */         CalloutInfo info = agent.getCalloutInfo();
/* 23 */         if (info != null) {
/* 24 */           Object res = Utils.sendXmlrpc(info.getCalloutServerUrl(), 
/* 25 */             "inviteagent", new Object[] {
/* 26 */             info.getCalloutID(), number });
/* 27 */           if (!res.equals(ErrorCode.OK.toString())) {
/* 28 */             OutCallResponse response = new OutCallResponse(number, 
/* 29 */               "ERROR", "", info.getCalloutID(), res
/* 30 */               .toString());
/* 31 */             Utils.sendMessageToAgent(agent.getAgentSession(), 
/* 32 */               response);
/*    */           }
/* 34 */           this.logger.info(agent + " callout process res: " + res);
/*    */         } else {
/* 36 */           OutCallResponse response = new OutCallResponse(number, 
/* 37 */             "ERROR", "", "", "IPCCERR0009");
/* 38 */           Utils.sendMessageToAgent(agent.getAgentSession(), response);
/* 39 */           this.logger
/* 40 */             .info(agent + 
/* 41 */             " not connected to callout server, can't dial out");
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\CalloutRequestProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */