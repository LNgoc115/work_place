/*     */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSpy;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.AgentEndCallResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.EndCallSpyResponse;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*     */ import com.viettel.ipcc.agentserver.common.MonitoringInfo;
/*     */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopRequestManager;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.MonitoringBroadcastJob;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ public class AgentEndcallProcessor extends SemsProcessorBase
/*     */ {
/*     */   public void process(SemsCommandJob job)
/*     */   {
/*  25 */     String[] params = splitCommand(job);
/*     */     
/*  27 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/*  28 */       "AgentsManager");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  33 */     changeAgentIDAndForwardToOmap(job);
/*     */     
/*  35 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/*  36 */     dbUpdater.updateAgentAttr(params[2], "CALL_STATUS", 
/*  37 */       "READY");
/*     */     
/*  39 */     Agents agent = agentManager.findByDeviceID(params[2]);
/*     */     
/*  41 */     CallManager callManager = (CallManager)Utils.getCtx().getBean(
/*  42 */       "CallManager");
/*     */     
/*  44 */     CallInfo call = callManager.getCall(params[1]);
/*     */     
/*  46 */     this.logger.info("process endcall");
/*     */     
/*  48 */     if (call != null) {
/*  49 */       AgentEndCallResponse agentendcall = new AgentEndCallResponse(call
/*  50 */         .getCallID(), agent != null ? agent.getVsaID() : "", 
/*  51 */         params[2], params[4]);
/*     */       
/*  53 */       List<Agents> listAgentsRelatedCall = callManager
/*  54 */         .getAllAgentRelatedToCall(call.getCallID());
/*     */       
/*  56 */       for (Agents a : listAgentsRelatedCall) {
/*  57 */         if ((a != null) && ((a.isSuper()) || (a == agent))) {
/*  58 */           this.logger.info("send agentendcall of [" + agent + "] to [" + a + 
/*  59 */             "]");
/*  60 */           Utils.sendMessageToAgent(a.getAgentSession(), agentendcall);
/*     */         }
/*     */       }
/*     */       
/*  64 */       callManager.removeAgentFromCall(params[1], agent);
/*     */     }
/*     */     
/*     */ 
/*  68 */     if (agent != null) {
/*  69 */       synchronized (agent)
/*     */       {
/*  71 */         agent.setAgentCallStatus(3);
/*  72 */         agent.setTotalMissed(agent.getTotalCall() - 
/*  73 */           agent.getTotalAnswered());
/*     */         
/*  75 */         long lastStartAnswer = agent.getLastStartAnswer();
/*  76 */         if (lastStartAnswer != -1L) {
/*  77 */           long timeAnswerd = System.currentTimeMillis() - lastStartAnswer;
/*     */           
/*  79 */           agent.setTotalAnswerTime(agent.getTotalAnswerTime() + 
/*  80 */             timeAnswerd);
/*     */           
/*  82 */           dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/*  83 */             "TOTAL_ANSWER_TIME", 
/*  84 */             Long.valueOf(agent.getTotalAnswerTime()));
/*     */           
/*  86 */           agent.setLongestTimeAnswerd(Math.max(timeAnswerd, agent
/*  87 */             .getLongestTimeAnswerd()));
/*     */         }
/*     */         
/*     */ 
/*  91 */         agent.setLastStartAnswer(-1L);
/*     */         
/*  93 */         agent.setCurCall(null);
/*     */       }
/*     */       
/*     */ 
/*  97 */       if (call != null)
/*     */       {
/*  99 */         MonitoringInfo mntInfo = agent.getMonitoringInfo();
/* 100 */         if (mntInfo != null) {
/* 101 */           synchronized (mntInfo)
/*     */           {
/* 103 */             if ((mntInfo.getCurrentMonotoringSpyCallid() != null) && 
/* 104 */               (mntInfo.getCurrentMonotoringSpyCallid().equals(
/* 105 */               call.getCallID())))
/*     */             {
/* 107 */               mntInfo.setCurrentMonotoringSpyCallid(null);
/* 108 */               mntInfo.setCompleteSpy(true);
/* 109 */               mntInfo.setNextSpyCall(null);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 118 */       EndCallSpyResponse endcallResponse = new EndCallSpyResponse(
/* 119 */         new AgentSpy(agent.getVsaID(), agent.getDeviceID(), call
/* 120 */         .getCaller(), "", call.getCalled()));
/*     */       
/* 122 */       MonitoringBroadcastJob mntJob = new MonitoringBroadcastJob(
/* 123 */         Utils.genJobID());
/*     */       
/* 125 */       mntJob.setAgent(agent);
/* 126 */       mntJob.setMsg(endcallResponse);
/*     */       
/* 128 */       AgentDesktopRequestManager adrm = (AgentDesktopRequestManager)
/* 129 */         Utils.getCtx().getBean("AgentDesktopRequestManager");
/*     */       
/* 131 */       adrm.processMonitoringBroadcast(mntJob);
/*     */     }
/*     */     else {
/* 134 */       this.logger.info("Something wrong!!! Can't not found agent by deviceid: " + 
/* 135 */         params[2]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 141 */     System.out.println(";1;1;1;1;1;".split(";", -1).length);
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\AgentEndcallProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */