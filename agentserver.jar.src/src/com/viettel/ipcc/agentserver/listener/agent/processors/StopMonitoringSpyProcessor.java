/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.MonitoringInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class StopMonitoringSpyProcessor extends MonitoringSpyBaseProcessor
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 14 */     Agents agent = null;
/* 15 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 16 */       this.logger.info("process StopMonitoringSpyProcessor for agent: " + 
/* 17 */         agent);
/* 18 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 19 */         "AgentsManager");
/*    */       
/* 21 */       initMonitoring(agent);
/*    */       
/* 23 */       MonitoringInfo mntInfo = agent.getMonitoringInfo();
/* 24 */       synchronized (mntInfo)
/*    */       {
/* 26 */         mntInfo.setSpying(false);
/* 27 */         mntInfo.setCompleteSpy(true);
/* 28 */         mntInfo.setNextSpyCall(null);
/* 29 */         tryEndCurrentSpyCall(mntInfo, agent);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\StopMonitoringSpyProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */