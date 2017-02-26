/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSpy;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.AddSpyListRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.AddSpyListResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.MonitoringInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class AddSpyedAgentProcessor extends MonitoringSpyBaseProcessor
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 20 */     Agents agent = null;
/* 21 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 22 */       AddSpyListRequest request = (AddSpyListRequest)job
/* 23 */         .getAgentDesktopMsg();
/* 24 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 25 */         "AgentsManager");
/* 26 */       List<AgentSpy> listAllAgentLoginInList = new ArrayList();
/*    */       
/* 28 */       initMonitoring(agent);
/*    */       
/* 30 */       StringBuilder log = new StringBuilder();
/*    */       AgentSpy a2;
/* 32 */       for (AgentSpy a : request.getListAgentSpy()) {
/* 33 */         Agents aa = mng.findByVsaID(a.getUsername());
/* 34 */         if ((aa != null) && (!aa.isSuper()) && (
/* 35 */           (!agent.isSuper()) || 
/* 36 */           (agent.getGroupID().equals(aa.getGroupID()))))
/*    */         {
/*    */ 
/* 39 */           a2 = new AgentSpy(aa.getVsaID(), aa.getDeviceID(), 
/* 40 */             "", "", "");
/*    */           
/* 42 */           listAllAgentLoginInList.add(a2);
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 47 */       MonitoringInfo mntInfo = agent.getMonitoringInfo();
/* 48 */       synchronized (mntInfo)
/*    */       {
/* 50 */         for (AgentSpy a : listAllAgentLoginInList) {
/* 51 */           mntInfo.getListSpyedAgents().add(a.getUsername());
/* 52 */           log.append(a.getUsername()).append(' ');
/*    */         }
/*    */       }
/* 55 */       this.logger.info(agent + " add list " + log + " to spy");
/*    */       
/* 57 */       AddSpyListResponse response = new AddSpyListResponse(
/* 58 */         listAllAgentLoginInList);
/*    */       
/* 60 */       Utils.sendMessageToAgent(agent.getAgentSession(), response);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\AddSpyedAgentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */