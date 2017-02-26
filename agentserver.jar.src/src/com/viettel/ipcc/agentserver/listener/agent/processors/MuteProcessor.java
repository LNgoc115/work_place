/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.MuteRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.MuteResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ 
/*    */ public class MuteProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 13 */     Agents agent = null;
/* 14 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 15 */       MuteRequest request = (MuteRequest)job.getAgentDesktopMsg();
/* 16 */       String callid = request.getCallId();
/*    */       
/* 18 */       String res = processSemsCmd("mutemethod", 
/* 19 */         callid, agent.getDeviceID(), null);
/*    */       
/* 21 */       this.logger.info("process mute for " + agent + " callid: " + callid);
/*    */       
/*    */ 
/* 24 */       Utils.sendMessageToAgent(job.getSession(), new MuteResponse(callid, 
/* 25 */         res));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\MuteProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */