/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*    */ import com.viettel.ipcc.agentserver.common.MonitoringInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ 
/*    */ public abstract class MonitoringSpyBaseProcessor extends ProcessorBase
/*    */ {
/*    */   public abstract void process(AgentDesktopJob paramAgentDesktopJob);
/*    */   
/*    */   protected void initMonitoring(Agents agent)
/*    */   {
/* 17 */     AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 18 */       "AgentsManager");
/* 19 */     synchronized (agent)
/*    */     {
/* 21 */       if (!mng.getMapAllMonitoringAgents().containsKey(
/* 22 */         agent.getDeviceID())) {
/* 23 */         mng.getMapAllMonitoringAgents().put(agent.getDeviceID(), agent);
/*    */       }
/*    */       
/* 26 */       MonitoringInfo mntInfo = agent.getMonitoringInfo();
/*    */       
/* 28 */       if (mntInfo == null) {
/* 29 */         mntInfo = new MonitoringInfo();
/* 30 */         mntInfo.setAgentDoMonitoring(agent);
/* 31 */         agent.setMonitoringInfo(mntInfo);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   protected void tryEndCurrentSpyCall(MonitoringInfo mntInfo, Agents agent)
/*    */   {
/* 38 */     if (mntInfo.getCurrentMonotoringSpyCallid() != null) {
/* 39 */       CallManager callMng = (CallManager)Utils.getCtx().getBean(
/* 40 */         "CallManager");
/* 41 */       CallInfo callInfo = callMng.getCall(mntInfo
/* 42 */         .getCurrentMonotoringSpyCallid());
/* 43 */       if (callInfo != null) {
/* 44 */         Object res = Utils.sendXmlrpc(callInfo.getSemsUrl(), 
/* 45 */           "dropagent", new Object[] {
/* 46 */           callInfo.getCallID(), agent.getDeviceID(), 
/* 47 */           agent.getDeviceID() });
/*    */         
/* 49 */         this.logger.info("try end spy call " + callInfo.getCallID() + 
/* 50 */           " agent " + agent.getDeviceID() + " res: " + res);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\MonitoringSpyBaseProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */