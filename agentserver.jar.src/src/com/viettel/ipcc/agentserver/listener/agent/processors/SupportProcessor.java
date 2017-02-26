/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.SupportRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.SuportResponse;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class SupportProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 13 */     com.viettel.ipcc.agentserver.common.Agents agent = null;
/* 14 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 15 */       SupportRequest request = (SupportRequest)job.getAgentDesktopMsg();
/*    */       
/* 17 */       String callID = request.getCallId();
/* 18 */       String superAgentid = request.getSupervisorId();
/* 19 */       String res = processSemsCmd("inviteagent", 
/* 20 */         callID, superAgentid, null);
/*    */       
/* 22 */       this.logger.info("process support for " + agent + " super agentid: " + 
/* 23 */         superAgentid + " callid: " + callID + " res " + res);
/* 24 */       Utils.sendMessageToAgent(job.getSession(), new SuportResponse(
/* 25 */         callID, res));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\SupportProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */