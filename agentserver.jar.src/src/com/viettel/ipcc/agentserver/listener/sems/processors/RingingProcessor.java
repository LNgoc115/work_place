/*     */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSpy;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.RingAgentResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.RingAgentSpyResponse;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*     */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopRequestManager;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.MonitoringBroadcastJob;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ public class RingingProcessor
/*     */   extends SemsProcessorBase
/*     */ {
/*     */   public void process(SemsCommandJob job)
/*     */   {
/*  24 */     String[] items2 = splitCommand(job);
/*  25 */     String[] items = new String[items2.length + 1];
/*     */     
/*  27 */     for (int i = 0; i < items2.length; i++) {
/*  28 */       items[i] = items2[i];
/*     */     }
/*  30 */     items[items2.length] = "";
/*     */     
/*  32 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/*  33 */       "AgentsManager");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  38 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/*  39 */     dbUpdater.updateAgentAttr(items[2], "CALL_STATUS", 
/*  40 */       "RINGING");
/*     */     
/*  42 */     Agents agent = agentManager.findByDeviceID(items[2]);
/*  43 */     if (agent == null) {
/*  44 */       this.logger.info("Something wrong!!! Can't not found agent by deviceid: " + 
/*  45 */         items[2]);
/*     */       
/*  47 */       forwardToOmap(mergeCommand(items));
/*     */     } else {
/*  49 */       items[2] = agent.getVsaID();
/*  50 */       items[5] = agent.getDeviceID();
/*  51 */       items[6] = agent.getShiftid();
/*  52 */       items[items2.length] = agent.getAgentStringType();
/*     */       
/*  54 */       forwardToOmap(mergeCommand(items));
/*     */       
/*  56 */       CallManager callManager = (CallManager)Utils.getCtx().getBean(
/*  57 */         "CallManager");
/*  58 */       CallInfo call = callManager.getCall(items[1]);
/*     */       
/*  60 */       if (call != null) {
/*  61 */         synchronized (agent)
/*     */         {
/*  63 */           agent.setLastStartAnswer(-1L);
/*  64 */           agent.setTotalCall(agent.getTotalCall() + 1);
/*  65 */           agent.setCurCall(call);
/*  66 */           agent.setAgentCallStatus(1);
/*     */         }
/*     */         
/*  69 */         if (agent.getAgentStringType().equals("AGENT")) {
/*  70 */           call.setGroupID(agent.getGroupID());
/*     */         }
/*     */         
/*  73 */         callManager.addAgentToCall(items[1], agent);
/*     */         
/*  75 */         List<Agents> listAgentRelatedCall = callManager
/*  76 */           .getAllAgentRelatedToCall(call.getCallID());
/*     */         
/*  78 */         RingAgentResponse ringing = new RingAgentResponse(call
/*  79 */           .getCallID(), call.getCaller(), agent.getVsaID(), agent
/*  80 */           .getDeviceID(), items[4]);
/*     */         
/*  82 */         for (Agents a : listAgentRelatedCall)
/*     */         {
/*  84 */           if ((a.isSuper()) || (a == agent)) {
/*  85 */             this.logger.info("Send ringing of [" + agent + "] to [" + a + 
/*  86 */               "]");
/*  87 */             Utils.sendMessageToAgent(a.getAgentSession(), ringing);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*  93 */         RingAgentSpyResponse spyResopnse = new RingAgentSpyResponse(
/*  94 */           new AgentSpy(agent.getVsaID(), agent.getDeviceID(), 
/*  95 */           call.getCaller(), "", call.getCalled()));
/*     */         
/*  97 */         MonitoringBroadcastJob mntJob = new MonitoringBroadcastJob(
/*  98 */           Utils.genJobID());
/*     */         
/* 100 */         mntJob.setAgent(agent);
/* 101 */         mntJob.setMsg(spyResopnse);
/*     */         
/* 103 */         AgentDesktopRequestManager adrm = (AgentDesktopRequestManager)
/* 104 */           Utils.getCtx().getBean("AgentDesktopRequestManager");
/*     */         
/* 106 */         adrm.processMonitoringBroadcast(mntJob);
/*     */         
/*     */ 
/*     */ 
/* 110 */         call.setFirstRingTime(false);
/*     */       }
/*     */       else {
/* 113 */         this.logger.info("Can't found callid: " + items[1]);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\RingingProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */