/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSpy;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.DelSpyListRequest;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.MonitoringInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class DeleteSpyedAgentProcessor extends MonitoringSpyBaseProcessor
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 16 */     Agents agent = null;
/* 17 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 18 */       DelSpyListRequest request = (DelSpyListRequest)job
/* 19 */         .getAgentDesktopMsg();
/* 20 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 21 */         "AgentsManager");
/*    */       
/* 23 */       initMonitoring(agent);
/*    */       
/* 25 */       MonitoringInfo mntInfo = agent.getMonitoringInfo();
/* 26 */       StringBuilder log = new StringBuilder();
/* 27 */       synchronized (mntInfo)
/*    */       {
/* 29 */         for (AgentSpy a : request.getListAgentSpy()) {
/* 30 */           mntInfo.getListSpyedAgents().remove(a.getUsername());
/* 31 */           log.append(a.getUsername()).append(' ');
/*    */         }
/*    */       }
/*    */       
/* 35 */       this.logger.info(agent + " remove spy list " + log);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\DeleteSpyedAgentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */