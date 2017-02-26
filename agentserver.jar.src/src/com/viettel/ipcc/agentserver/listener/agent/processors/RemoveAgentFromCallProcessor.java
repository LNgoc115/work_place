/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.RemoveAgentRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.SuperEndCallAgentResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ErrorCode;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class RemoveAgentFromCallProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 17 */     Agents agent = null;
/* 18 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 19 */       RemoveAgentRequest request = (RemoveAgentRequest)job
/* 20 */         .getAgentDesktopMsg();
/*    */       
/* 22 */       String deviceid = request.getRemoveAgentId();
/* 23 */       String callid = request.getCallId();
/* 24 */       String superAgent = agent.getDeviceID();
/*    */       
/* 26 */       String res = processSemsCmd("dropagent", callid, 
/* 27 */         deviceid, superAgent);
/*    */       
/* 29 */       this.logger.info("super: " + superAgent + " process drop agent: " + 
/* 30 */         agent + " from call: " + callid + " res: " + res);
/*    */       
/* 32 */       if ((!agent.getDeviceID().equals(deviceid)) && 
/* 33 */         (res.equals(ErrorCode.OK.toString()))) {
/* 34 */         AgentsManager agentManager = (AgentsManager)Utils.getCtx()
/* 35 */           .getBean("AgentsManager");
/* 36 */         Agents oldAgentRef = agentManager.findByDeviceID(deviceid);
/* 37 */         if (oldAgentRef != null) {
/* 38 */           SuperEndCallAgentResponse response = new SuperEndCallAgentResponse(
/* 39 */             callid, agent.getVsaID());
/* 40 */           Utils.sendMessageToAgent(oldAgentRef.getAgentSession(), 
/* 41 */             response);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\RemoveAgentFromCallProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */