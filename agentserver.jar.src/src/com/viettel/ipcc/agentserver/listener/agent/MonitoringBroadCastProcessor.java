/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.MessageBase;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.JobBase;
/*    */ import com.viettel.ipcc.agentserver.common.MonitoringInfo;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class MonitoringBroadCastProcessor extends com.viettel.ipcc.agentserver.common.JobProcessorBase
/*    */ {
/* 19 */   protected Logger logger = Logger.getLogger(MonitoringBroadCastProcessor.class);
/*    */   
/*    */ 
/*    */   public void processJob(List<JobBase> listJob)
/*    */   {
/* 24 */     for (JobBase j : listJob)
/* 25 */       _process(j);
/*    */   }
/*    */   
/*    */   private void _process(JobBase j) {
/* 29 */     MonitoringBroadcastJob job = (MonitoringBroadcastJob)j;
/* 30 */     Agents agent = job.getAgent();
/* 31 */     MessageBase msg = job.getMsg();
/* 32 */     prepareBroadcastToAlMonitoring(agent, msg);
/*    */   }
/*    */   
/*    */ 
/*    */   private void prepareBroadcastToAlMonitoring(Agents agent, MessageBase msg)
/*    */   {
/* 38 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 39 */       "AgentsManager");
/*    */     
/*    */ 
/* 42 */     Iterator localIterator = agentManager.getMapAllMonitoringAgents().entrySet().iterator();
/* 41 */     while (localIterator.hasNext()) {
/* 42 */       Map.Entry<String, Agents> entry = (Map.Entry)localIterator.next();
/* 43 */       Agents monitoringAgent = (Agents)entry.getValue();
/* 44 */       MonitoringInfo mntInfo = monitoringAgent.getMonitoringInfo();
/*    */       
/* 46 */       if ((mntInfo != null) && 
/* 47 */         (mntInfo.getListSpyedAgents().contains(agent.getVsaID()))) {
/* 48 */         this.logger.info("send to motoring " + monitoringAgent + 
/* 49 */           msg.getClass().getName());
/* 50 */         Utils.sendMessageToAgent(monitoringAgent.getAgentSession(), 
/* 51 */           msg);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\MonitoringBroadCastProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */