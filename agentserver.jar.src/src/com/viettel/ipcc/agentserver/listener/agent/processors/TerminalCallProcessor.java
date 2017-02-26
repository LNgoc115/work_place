/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.TerminateRequest;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ResponseProvider;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ 
/*    */ public class TerminalCallProcessor
/*    */   extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 14 */     Agents agent = null;
/* 15 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null)
/*    */     {
/*    */ 
/* 18 */       TerminateRequest request = (TerminateRequest)job
/* 19 */         .getAgentDesktopMsg();
/* 20 */       String callid = request.getCallId();
/* 21 */       String res = processSemsCmd(
/* 22 */         "terminateconf", callid, null, 
/* 23 */         null);
/*    */       
/* 25 */       Utils.sendMessageToAgent(job.getSession(), 
/* 26 */         ResponseProvider.createTerminateResponse(callid, 
/* 27 */         agent.getDeviceID(), agent.getDeviceID(), res));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\TerminalCallProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */