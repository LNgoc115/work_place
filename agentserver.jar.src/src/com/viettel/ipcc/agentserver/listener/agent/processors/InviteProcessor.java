/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.InviteRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.InviteResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ErrorCode;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ResponseProvider;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class InviteProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 19 */     Agents agent = null;
/* 20 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 21 */       InviteRequest inviteRequest = (InviteRequest)job
/* 22 */         .getAgentDesktopMsg();
/*    */       
/* 24 */       CallManager callMng = (CallManager)Utils.getCtx().getBean(
/* 25 */         "CallManager");
/*    */       
/* 27 */       String agentid = inviteRequest.getInvitedAgent();
/*    */       
/* 29 */       String invitedAgent = inviteRequest.getAgentId();
/* 30 */       String callid = inviteRequest.getCallId();
/*    */       
/* 32 */       String res = processSemsCmd("inviteagent", 
/* 33 */         callid, invitedAgent, null);
/*    */       
/* 35 */       this.logger.info("Invite: " + invitedAgent + " who invite: " + agent + 
/* 36 */         " res: " + res);
/*    */       
/* 38 */       InviteResponse response = ResponseProvider.createInviteResponse(
/* 39 */         callid, invitedAgent, agentid, res);
/*    */       
/* 41 */       Utils.sendMessageToAgent(job.getSession(), response);
/*    */       
/* 43 */       List<Agents> listAgentInCall = callMng
/* 44 */         .getAllAgentRelatedToCall(callid);
/*    */       
/* 46 */       if (res.equals(ErrorCode.OK.toString())) {
/* 47 */         for (Agents a : listAgentInCall) {
/* 48 */           if (!a.isSuper()) {
/* 49 */             Utils.sendMessageToAgent(a.getAgentSession(), response);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\InviteProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */