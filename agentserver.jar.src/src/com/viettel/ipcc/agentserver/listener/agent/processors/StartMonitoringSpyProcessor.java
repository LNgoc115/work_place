/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSpy;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.StartSpyAgentRequest;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.MonitoringInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ 
/*    */ public class StartMonitoringSpyProcessor extends MonitoringSpyBaseProcessor
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 15 */     Agents agent = null;
/* 16 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 17 */       StartSpyAgentRequest request = (StartSpyAgentRequest)job
/* 18 */         .getAgentDesktopMsg();
/*    */       
/* 20 */       this.logger.info("process StartMonitoringSpyProcessor for agent: " + 
/* 21 */         agent + " spyed agent ");
/*    */       
/* 23 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 24 */         "AgentsManager");
/*    */       
/* 26 */       initMonitoring(agent);
/*    */       
/* 28 */       MonitoringInfo mntInfo = agent.getMonitoringInfo();
/* 29 */       synchronized (mntInfo)
/*    */       {
/*    */ 
/* 32 */         mntInfo.setSpying(true);
/* 33 */         mntInfo.setCompleteSpy(true);
/*    */         
/* 35 */         tryEndCurrentSpyCall(mntInfo, agent);
/*    */         
/* 37 */         if (request.getAgentSpy() != null) {
/* 38 */           mntInfo.setNextSpyCall(mng.selectNextCall(request
/* 39 */             .getAgentSpy().getUsername(), mntInfo));
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\StartMonitoringSpyProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */