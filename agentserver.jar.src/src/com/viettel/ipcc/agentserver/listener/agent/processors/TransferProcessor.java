/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.TransferRequest;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ErrorCode;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class TransferProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 16 */     Agents agent = null;
/* 17 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 18 */       if (agent.isSuper()) {
/* 19 */         TransferRequest request = (TransferRequest)job
/* 20 */           .getAgentDesktopMsg();
/* 21 */         String callid = request.getCallId();
/* 22 */         String oldAgent = request.getDropAgentId();
/* 23 */         String newAgent = request.getNewAgentId();
/* 24 */         AgentsManager agentManager = (AgentsManager)Utils.getCtx()
/* 25 */           .getBean("AgentsManager");
/*    */         
/* 27 */         Agents newAgentRef = agentManager.findByDeviceID(newAgent);
/*    */         String res;
/* 29 */         String res; if ((newAgentRef == null) || 
/* 30 */           (!newAgentRef.getGroupID().equals(agent.getGroupID())))
/*    */         {
/* 32 */           res = ErrorCode.IPCCERR0002.toString();
/*    */         }
/*    */         else {
/* 35 */           res = processSemsCmd("replaceagent", 
/* 36 */             callid, newAgent, oldAgent);
/*    */         }
/*    */         
/* 39 */         this.logger.info("process transfer for: " + agent + " old agent: " + 
/* 40 */           oldAgent + " new agent: " + newAgent + " callid: " + 
/* 41 */           callid + " res: " + res);
/*    */         
/* 43 */         com.viettel.ipcc.agentdesktop.protocol.response.TransferResponse response = 
/* 44 */           com.viettel.ipcc.agentserver.listener.agent.ResponseProvider.createTransferResponse(callid, agent.getVsaID(), 
/* 45 */           oldAgent, newAgent, res);
/*    */         
/* 47 */         Utils.sendMessageToAgent(job.getSession(), response);
/*    */         
/* 49 */         if (res.equals(ErrorCode.OK.toString()))
/*    */         {
/* 51 */           Agents oldAgentRef = agentManager.findByDeviceID(oldAgent);
/* 52 */           if (oldAgentRef != null) {
/* 53 */             Utils.sendMessageToAgent(oldAgentRef.getAgentSession(), 
/* 54 */               response);
/*    */           }
/*    */         }
/*    */       }
/*    */       else {
/* 59 */         this.logger.info("Permission deny! You aren't supervisor " + agent);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\TransferProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */