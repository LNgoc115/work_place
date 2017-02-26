/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.ChangeSystemStatusRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.ChangeSystemStatusReponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ErrorCode;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ 
/*    */ public class ChangeSystemStatusProcessor
/*    */   extends ProcessorBase
/*    */ {
/* 17 */   private Logger logger = Logger.getLogger(ChangeSystemStatusProcessor.class);
/*    */   
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 21 */     Agents agent = null;
/* 22 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null)
/*    */     {
/* 24 */       ChangeSystemStatusRequest request = (ChangeSystemStatusRequest)job
/* 25 */         .getAgentDesktopMsg();
/*    */       
/* 27 */       String agentid = request.getExtension();
/* 28 */       String status = request.getStatus().toUpperCase();
/* 29 */       this.logger.debug("before change system status for agent: " + agentid + 
/* 30 */         " new status: " + status);
/*    */       
/* 32 */       this.logger.info("process change system status for: " + agent + " new status: " + 
/* 33 */         status);
/*    */       
/* 35 */       DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean(
/* 36 */         "DbUpdater");
/* 37 */       dbUpdater.updateAgentAttr(agentid, "SYSTEM_STATUS", status);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 42 */       Utils.sendMessageToAgent(job.getSession(), 
/* 43 */         new ChangeSystemStatusReponse(ErrorCode.OK.toString()));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\ChangeSystemStatusProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */