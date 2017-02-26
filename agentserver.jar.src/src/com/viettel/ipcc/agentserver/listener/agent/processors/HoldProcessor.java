/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.HoldRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.HoldResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ 
/*    */ public class HoldProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 13 */     Agents agent = null;
/* 14 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 15 */       HoldRequest request = (HoldRequest)job.getAgentDesktopMsg();
/* 16 */       String callid = request.getCallId();
/* 17 */       String agentid = agent.getDeviceID();
/* 18 */       String res = processSemsCmd("holdmethod", 
/* 19 */         callid, agentid, null);
/*    */       
/* 21 */       this.logger.info("agent hold: " + agent + " callid: " + callid + 
/* 22 */         " res: " + res);
/*    */       
/* 24 */       Utils.sendMessageToAgent(job.getSession(), new HoldResponse(callid, 
/* 25 */         res));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\HoldProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */