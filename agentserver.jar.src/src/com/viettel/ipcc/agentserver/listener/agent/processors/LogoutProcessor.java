/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class LogoutProcessor extends ProcessorBase
/*    */ {
/* 14 */   private Logger logger = Logger.getLogger(LogoutProcessor.class);
/*    */   
/*    */ 
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 19 */     IoSession session = job.getSession();
/*    */     
/* 21 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 22 */       "AgentsManager");
/*    */     
/* 24 */     Agents agent = agentManager.findBySession(session);
/*    */     
/* 26 */     if (agent != null)
/*    */     {
/* 28 */       sendChangeStatusCDR(agent, "LOGOUT", "");
/* 29 */       agentManager.removeAgent(agent);
/* 30 */       this.logger.info("process logout agent: " + agent.getVsaID());
/*    */       
/* 32 */       DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean(
/* 33 */         "DbUpdater");
/* 34 */       dbUpdater.updateAgentLoginType(agent.getDeviceID(), "");
/* 35 */       dbUpdater.updateAgentStatus(agent.getDeviceID(), 
/* 36 */         "LOGOUT");
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 41 */     session.close(true);
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\LogoutProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */