/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.ChangeAgentStatusRequest;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.ChangeAgentStatusReponse;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.SuperChangeAgentStatusReponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.ErrorCode;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.Date;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class ChangeAgentStatusProcessor
/*    */   extends ProcessorBase
/*    */ {
/* 19 */   private Logger logger = Logger.getLogger(ChangeAgentStatusProcessor.class);
/*    */   
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 23 */     Agents agent = null;
/* 24 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null)
/*    */     {
/* 26 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 27 */         "AgentsManager");
/* 28 */       ChangeAgentStatusRequest request = (ChangeAgentStatusRequest)job
/* 29 */         .getAgentDesktopMsg();
/*    */       
/* 31 */       String agentid = request.getExtension();
/* 32 */       String status = request.getStatus().toUpperCase();
/* 33 */       this.logger.debug("before change status for agent: " + agentid + 
/* 34 */         " new status: " + status);
/*    */       
/* 36 */       Agents agentReset = mng.findByDeviceID(agentid);
/*    */       
/* 38 */       if ((status.equals("AVAILABLE")) && 
/* 39 */         (agentReset != null)) {
/* 40 */         synchronized (agentReset)
/*    */         {
/* 42 */           agentReset.setNumRejectCall(mng
/* 43 */             .getMaxNumberRejectCall());
/*    */         }
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 49 */       this.logger.info("process change status for: " + agent + " new status: " + 
/* 50 */         status);
/*    */       
/* 52 */       DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean(
/* 53 */         "DbUpdater");
/* 54 */       dbUpdater.updateAgentStatus(agentid, status);
/* 55 */       dbUpdater.updateAgentAttr(agentid, 
/* 56 */         "LAST_CHANGE_STATUS", new Date(
/* 57 */         System.currentTimeMillis()));
/*    */       
/* 59 */       Utils.sendMessageToAgent(job.getSession(), 
/* 60 */         new ChangeAgentStatusReponse(ErrorCode.OK.toString()));
/*    */       
/*    */ 
/* 63 */       if (!agent.getDeviceID().equals(agentid)) {
/* 64 */         Agents agentChanged = mng.findByDeviceID(agentid);
/*    */         
/* 66 */         if (agentChanged != null) {
/* 67 */           SuperChangeAgentStatusReponse response = new SuperChangeAgentStatusReponse(
/* 68 */             agent.getDeviceID(), status);
/* 69 */           Utils.sendMessageToAgent(agentChanged.getAgentSession(), 
/* 70 */             response);
/*    */         }
/*    */         
/* 73 */         sendChangeStatusCDR(agent, status, agent.getVsaID());
/*    */       } else {
/* 75 */         sendChangeStatusCDR(agent, status, "");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\ChangeAgentStatusProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */