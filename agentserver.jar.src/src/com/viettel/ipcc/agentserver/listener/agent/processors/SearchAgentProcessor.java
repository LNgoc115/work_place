/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.SearchRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.LookupStopResponse;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.LookupStopSupervisorResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ResponseProvider;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SearchAgentProcessor extends ProcessorBase
/*    */ {
/*    */   private static final String AGENT = "agent";
/*    */   private static final String SUPERVISOR = "supervisor";
/* 21 */   private Logger logger = Logger.getLogger(SearchAgentProcessor.class);
/*    */   
/*    */   private void processSearchAgent(Agents agent, AgentDesktopJob job) {
/* 24 */     SearchRequest request = (SearchRequest)job.getAgentDesktopMsg();
/* 25 */     String firstName = request.getAgentId();
/*    */     
/* 27 */     this.logger.info("process search agent for: " + agent + " search name: " + 
/* 28 */       firstName);
/*    */     
/* 30 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 31 */       "AgentsManager");
/*    */     
/* 33 */     List<Agents> listRes = new ArrayList();
/*    */     Agents a;
/* 35 */     if (agent != null) {
/* 36 */       List<Agents> agentInGroup = agentManager.getAgentInGroup(agent
/* 37 */         .getGroupID());
/*    */       
/* 39 */       for (Iterator localIterator = agentInGroup.iterator(); localIterator.hasNext();) { a = (Agents)localIterator.next();
/* 40 */         if (a.getVsaID().contains(firstName)) {
/* 41 */           listRes.add(a);
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 48 */     for (Agents a : listRes) {
/* 49 */       Utils.sendMessageToAgent(job.getSession(), 
/* 50 */         ResponseProvider.createLookupAgentResponse(a));
/*    */     }
/*    */     
/* 53 */     Utils.sendMessageToAgent(job.getSession(), new LookupStopResponse());
/*    */   }
/*    */   
/*    */   private void processSuperSearch(Agents agent, AgentDesktopJob job)
/*    */   {
/* 58 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 59 */       "AgentsManager");
/*    */     
/* 61 */     this.logger.info("process search supervisor for agent: " + agent);
/*    */     
/* 63 */     List<Agents> listSuper = agentManager.getGroupSuper(agent.getGroupID());
/*    */     
/* 65 */     for (Agents a : listSuper) {
/* 66 */       Utils.sendMessageToAgent(job.getSession(), 
/* 67 */         ResponseProvider.createLookupSupervisorResponse(a));
/*    */     }
/*    */     
/* 70 */     Utils.sendMessageToAgent(job.getSession(), 
/* 71 */       new LookupStopSupervisorResponse());
/*    */   }
/*    */   
/*    */ 
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 77 */     Agents agent = null;
/* 78 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 79 */       SearchRequest request = (SearchRequest)job.getAgentDesktopMsg();
/*    */       
/* 81 */       if (request.getObjectCommand().equalsIgnoreCase(
/* 82 */         "agent")) {
/* 83 */         processSearchAgent(agent, job);
/*    */       }
/* 85 */       else if (request.getObjectCommand().equalsIgnoreCase(
/* 86 */         "supervisor")) {
/* 87 */         processSuperSearch(agent, job);
/*    */       }
/*    */       else {
/* 90 */         this.logger.error("Can't process lookup command: " + 
/* 91 */           request.getObjectCommand());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\SearchAgentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */