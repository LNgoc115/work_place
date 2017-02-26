/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ResponseProvider;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.processors.ProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.Date;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class AgentNotAnsweredProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 18 */     changeAgentIDAndForwardToOmap(job);
/*    */     
/* 20 */     String[] params = splitCommand(job);
/*    */     
/* 22 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/*    */     
/* 24 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 25 */       "AgentsManager");
/* 26 */     Agents agent = agentManager.findByDeviceID(params[2]);
/*    */     
/* 28 */     if (agent != null) {
/* 29 */       synchronized (agent)
/*    */       {
/* 31 */         agent.setTotalNumRejectCall(agent.getTotalNumRejectCall() + 1);
/*    */         
/* 33 */         dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 34 */           "NUM_REJECTCALL", agent
/* 35 */           .getTotalNumRejectCall());
/*    */         
/*    */ 
/* 38 */         agent.setNumRejectCall(agent.getNumRejectCall() - 1);
/* 39 */         if (agent.getNumRejectCall() <= 0) {
/* 40 */           this.logger.warn(agent + 
/* 41 */             " reject too many times! Set not available now!");
/* 42 */           Utils.sendMessageToAgent(agent.getAgentSession(), 
/* 43 */             ResponseProvider.createReloadAgentStatusMessage(
/* 44 */             agent.getDeviceID(), 
/* 45 */             "NO ANSWER"));
/*    */           
/* 47 */           dbUpdater.updateAgentStatus(agent.getDeviceID(), 
/* 48 */             "NO ANSWER");
/*    */           
/* 50 */           dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 51 */             "LAST_CHANGE_STATUS", new Date(
/* 52 */             System.currentTimeMillis()));
/*    */           
/* 54 */           ProcessorBase.sendChangeStatusCDR(agent, 
/* 55 */             "NO ANSWER", "");
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\AgentNotAnsweredProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */