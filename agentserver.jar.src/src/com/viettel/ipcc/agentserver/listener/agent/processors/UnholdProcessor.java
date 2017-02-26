/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.UnHoldRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.UnHoldResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class UnholdProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 14 */     Agents agent = null;
/* 15 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 16 */       UnHoldRequest request = (UnHoldRequest)job.getAgentDesktopMsg();
/* 17 */       String callid = request.getCallId();
/*    */       
/* 19 */       String agentid = agent.getDeviceID();
/* 20 */       String res = processSemsCmd("unholdmethod", 
/* 21 */         callid, agentid, null);
/*    */       
/* 23 */       this.logger.info("process unhold for: " + agent + " callid: " + callid + 
/* 24 */         " res: " + res);
/* 25 */       Utils.sendMessageToAgent(job.getSession(), new UnHoldResponse(
/* 26 */         callid, res));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\UnholdProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */