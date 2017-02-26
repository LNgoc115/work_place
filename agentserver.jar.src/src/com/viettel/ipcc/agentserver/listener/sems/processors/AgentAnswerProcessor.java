/*     */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSpy;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.AgentAnswerResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.AgentAnswerSpyResponse;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*     */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopRequestManager;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.MonitoringBroadcastJob;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ public class AgentAnswerProcessor extends SemsProcessorBase
/*     */ {
/*     */   public void process(SemsCommandJob job)
/*     */   {
/*  24 */     String[] params = splitCommand(job);
/*  25 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/*  26 */       "AgentsManager");
/*     */     
/*  28 */     changeAgentIDAndForwardToOmap(job);
/*     */     
/*  30 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/*  31 */     dbUpdater.updateAgentAttr(params[2], "CALL_STATUS", 
/*  32 */       "NOT READY");
/*     */     
/*  34 */     CallManager callManager = (CallManager)Utils.getCtx().getBean(
/*  35 */       "CallManager");
/*  36 */     CallInfo call = callManager.getCall(params[1]);
/*     */     
/*  38 */     Agents agent = agentManager.findByDeviceID(params[2]);
/*     */     
/*  40 */     if (agent != null) {
/*  41 */       synchronized (agent) {
/*  42 */         agent.setAgentCallStatus(2);
/*  43 */         agent.setNumRejectCall(agentManager.getMaxNumberRejectCall());
/*  44 */         agent.setLastStartAnswer(System.currentTimeMillis());
/*  45 */         agent.setTotalAnswered(agent.getTotalAnswered() + 1);
/*     */         
/*  47 */         dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/*  48 */           "TOTAL_ANSWER_CALL", agent
/*  49 */           .getTotalAnswered());
/*     */         
/*     */ 
/*  52 */         if ((call != null) && (!call.isAnswered())) {
/*  53 */           call.setAnswered(true);
/*  54 */           agent.setTotalCustomerWaitTime(agent
/*  55 */             .getTotalCustomerWaitTime() + (
/*  56 */             System.currentTimeMillis() - call.getStartTime()
/*  57 */             .getTime()));
/*     */         }
/*     */         
/*     */ 
/*  61 */         if ((call != null) && (call.getFisrtAnswerTime() == 0L) && 
/*  62 */           (agent.getAgentStringType().equals("AGENT"))) {
/*  63 */           call.setFisrtAnswerTime(System.currentTimeMillis());
/*     */         }
/*     */       }
/*     */       
/*  67 */       List<Agents> listRelatedAgents = callManager
/*  68 */         .getAllAgentRelatedToCall(call.getCallID());
/*     */       
/*  70 */       AgentAnswerResponse answer = new AgentAnswerResponse(call
/*  71 */         .getCallID(), agent.getVsaID(), agent.getDeviceID(), 
/*  72 */         params[4]);
/*     */       
/*  74 */       for (Agents a : listRelatedAgents) {
/*  75 */         if ((a != null) && ((a.isSuper()) || (a == agent))) {
/*  76 */           this.logger.info("send agent answered of [" + agent + "] to[" + 
/*  77 */             a + "]");
/*  78 */           Utils.sendMessageToAgent(a.getAgentSession(), answer);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  83 */       AgentAnswerSpyResponse answerResponse = new AgentAnswerSpyResponse(
/*  84 */         new AgentSpy(agent.getVsaID(), agent.getDeviceID(), call
/*  85 */         .getCaller(), "", call.getCalled()));
/*     */       
/*  87 */       MonitoringBroadcastJob mntJob = new MonitoringBroadcastJob(
/*  88 */         Utils.genJobID());
/*     */       
/*  90 */       mntJob.setAgent(agent);
/*  91 */       mntJob.setMsg(answerResponse);
/*     */       
/*  93 */       AgentDesktopRequestManager adrm = (AgentDesktopRequestManager)
/*  94 */         Utils.getCtx().getBean("AgentDesktopRequestManager");
/*     */       
/*  96 */       adrm.processMonitoringBroadcast(mntJob);
/*     */     }
/*     */     else {
/*  99 */       this.logger.info("Something wrong!!! Can't not found agent by deviceid: " + 
/* 100 */         params[2]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\AgentAnswerProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */