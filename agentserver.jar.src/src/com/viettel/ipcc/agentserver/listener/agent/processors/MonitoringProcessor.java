/*     */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.request.RealTimeStatisticsRequest;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.ResponseProvider;
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ public class MonitoringProcessor
/*     */   extends ProcessorBase
/*     */ {
/*  18 */   private Logger logger = Logger.getLogger(MonitoringProcessor.class);
/*     */   
/*     */   public void process(AgentDesktopJob job)
/*     */   {
/*  22 */     Agents agent = null;
/*  23 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null)
/*     */     {
/*  25 */       AgentsManager agentManager = (AgentsManager)
/*  26 */         Utils.getCtx().getBean("AgentsManager");
/*     */       
/*  28 */       RealTimeStatisticsRequest request = (RealTimeStatisticsRequest)job
/*  29 */         .getAgentDesktopMsg();
/*     */       
/*     */ 
/*  32 */       this.logger.info("process mobitoring cmd: " + request.getInfo() + 
/*  33 */         " for " + agent);
/*     */       
/*  35 */       if (request.getInfo().equalsIgnoreCase("agentInfo")) {
/*  36 */         Utils.sendMessageToAgent(job.getSession(), 
/*  37 */           ResponseProvider.createMonitorAgentInfoResponse(agent
/*  38 */           .getTotalAnswered(), agent.getTotalCall(), 
/*  39 */           agent.getTotalMissed(), agent
/*  40 */           .getTotalAnswerTime(), agent
/*  41 */           .getLongestTimeAnswerd()));
/*     */       }
/*  43 */       else if (request.getInfo().equalsIgnoreCase(
/*  44 */         "agentInfoSupervisor"))
/*     */       {
/*  46 */         List<Agents> allAgentInGroup = agentManager
/*  47 */           .getAgentInGroup(agent.getGroupID());
/*     */         
/*  49 */         int numberAgentOfGroup = allAgentInGroup.size();
/*  50 */         int numAnswering = 0;
/*  51 */         for (Agents a : allAgentInGroup) {
/*  52 */           if (a.getAgentCallStatus() == 2) {
/*  53 */             numAnswering++;
/*     */           }
/*     */         }
/*     */         
/*  57 */         Utils.sendMessageToAgent(job.getSession(), 
/*  58 */           ResponseProvider.createMonitorAgentInfoSupervisorResponse(
/*  59 */           numberAgentOfGroup, numAnswering));
/*     */       }
/*  61 */       else if (request.getInfo().equalsIgnoreCase(
/*  62 */         "sysInfoSupervisor")) {
/*  63 */         List<Agents> allAgentInGroup = agentManager
/*  64 */           .getAgentInGroup(agent.getGroupID());
/*  65 */         int numberAnswerd = 0;
/*  66 */         int totalCall = 0;
/*  67 */         int totalMissedCall = 0;
/*  68 */         int totalWating = 0;
/*  69 */         long totalAnswerTime = 0L;
/*  70 */         long totalWaitingTime = 0L;
/*  71 */         for (Agents a : allAgentInGroup) {
/*  72 */           numberAnswerd += a.getTotalAnswered();
/*  73 */           totalCall += a.getTotalCall();
/*  74 */           totalMissedCall += a.getTotalMissed();
/*  75 */           totalAnswerTime += a.getTotalAnswerTime();
/*  76 */           totalWaitingTime += a.getTotalCustomerWaitTime();
/*     */         }
/*  78 */         totalWating = totalCall - (totalMissedCall + numberAnswerd);
/*     */         
/*  80 */         float averageAnswerTime = (float)totalAnswerTime / 
/*  81 */           totalCall;
/*     */         
/*  83 */         float averageWaitingTime = (float)totalWaitingTime / 
/*  84 */           totalCall;
/*     */         
/*  86 */         Map<String, String> params = new HashMap();
/*  87 */         params.put("queue", agent.getGroupID());
/*  88 */         params.put("servedCalls", numberAnswerd);
/*  89 */         params.put("waitingCalls", totalWating);
/*  90 */         params.put("dropedCalls", totalMissedCall);
/*  91 */         params.put("totalCalls", totalCall);
/*  92 */         params.put("averageWaitTime", averageWaitingTime);
/*  93 */         params.put("averageResponeTime", averageAnswerTime);
/*     */         
/*  95 */         Utils.sendMessageToAgent(job.getSession(), 
/*  96 */           ResponseProvider.createMonitorSysInfoSupervisorResponse(params));
/*     */       }
/*     */       else {
/*  99 */         this.logger.error("Can't process monitor request command: " + 
/* 100 */           request.getInfo());
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\MonitoringProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */