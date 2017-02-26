/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.UnMuteRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.UnMuteResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ 
/*    */ public class UnmuteProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 13 */     Agents agent = null;
/* 14 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 15 */       UnMuteRequest request = (UnMuteRequest)job.getAgentDesktopMsg();
/*    */       
/* 17 */       String callid = request.getCallId();
/* 18 */       String agentid = agent.getDeviceID();
/* 19 */       String res = processSemsCmd("unmutemethod", 
/* 20 */         callid, agentid, null);
/*    */       
/* 22 */       this.logger.info("process unmute for: " + agent + " callid: " + callid + 
/* 23 */         " res: " + res);
/*    */       
/* 25 */       Utils.sendMessageToAgent(job.getSession(), new UnMuteResponse(
/* 26 */         callid, res));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\UnmuteProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */