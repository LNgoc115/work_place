/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.SpyRequest;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ResponseProvider;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class SpyProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 14 */     Agents agent = null;
/* 15 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 16 */       if (agent.isSuper()) {
/* 17 */         SpyRequest request = (SpyRequest)job.getAgentDesktopMsg();
/* 18 */         String callid = request.getCallId();
/* 19 */         String agentid = agent.getDeviceID();
/* 20 */         String res = processSemsCmd("spy", 
/* 21 */           callid, agentid, null);
/*    */         
/* 23 */         this.logger.info("process spy for: " + agent + " callid: " + callid);
/*    */         
/* 25 */         Utils.sendMessageToAgent(job.getSession(), 
/* 26 */           ResponseProvider.createSpyResponse(callid, res));
/*    */       } else {
/* 28 */         this.logger.info("Permission deny! You aren't supervisor " + agent);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\SpyProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */