/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.MessageBase;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.LogoutRequest;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.service.IoHandlerAdapter;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AgentDesktopProtocolHandler
/*    */   extends IoHandlerAdapter
/*    */ {
/* 27 */   private static final Logger logger = Logger.getLogger(AgentDesktopProtocolHandler.class);
/*    */   
/*    */   public void sessionOpened(IoSession session) throws Exception
/*    */   {
/* 31 */     AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 32 */       "AgentsManager");
/* 33 */     mng.addAnonymousSession(session);
/*    */   }
/*    */   
/*    */ 
/*    */   public void exceptionCaught(IoSession session, Throwable cause)
/*    */   {
/* 39 */     logger.warn("Unexpected exception.", cause);
/*    */   }
/*    */   
/*    */ 
/*    */   public void messageReceived(IoSession session, Object message)
/*    */   {
/* 45 */     logger.info("RECEIVED from client: " + message);
/* 46 */     MessageBase messageBase = (MessageBase)message;
/*    */     
/* 48 */     AgentDesktopJob job = new AgentDesktopJob(Utils.genJobID());
/* 49 */     job.setAgentDesktopMsg(messageBase);
/* 50 */     job.setSession(session);
/*    */     
/* 52 */     AgentDesktopRequestManager mng = (AgentDesktopRequestManager)
/* 53 */       Utils.getCtx().getBean("AgentDesktopRequestManager");
/* 54 */     mng.process(job);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void sessionClosed(IoSession session)
/*    */     throws Exception
/*    */   {
/* 65 */     AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 66 */       "AgentsManager");
/* 67 */     mng.removeAnonymousSession(session);
/* 68 */     Agents agent = mng.findBySession(session);
/* 69 */     if (agent != null) {
/* 70 */       AgentDesktopJob job = new AgentDesktopJob(Utils.genJobID());
/* 71 */       job.setAgentDesktopMsg(new LogoutRequest());
/* 72 */       job.setSession(session);
/*    */       
/* 74 */       AgentDesktopRequestManager mngRequest = (AgentDesktopRequestManager)
/* 75 */         Utils.getCtx().getBean("AgentDesktopRequestManager");
/* 76 */       mngRequest.process(job);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\AgentDesktopProtocolHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */