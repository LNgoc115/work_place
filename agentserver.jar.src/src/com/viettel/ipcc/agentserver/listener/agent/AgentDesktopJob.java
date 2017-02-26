/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.MessageBase;
/*    */ import com.viettel.ipcc.agentserver.common.JobBase;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class AgentDesktopJob extends JobBase
/*    */ {
/*    */   private IoSession session;
/*    */   private MessageBase agentDesktopMsg;
/*    */   
/*    */   public AgentDesktopJob(Long jobID)
/*    */   {
/* 14 */     super(jobID);
/*    */   }
/*    */   
/*    */   public void setSession(IoSession session)
/*    */   {
/* 19 */     this.session = session;
/*    */   }
/*    */   
/*    */   public IoSession getSession() {
/* 23 */     return this.session;
/*    */   }
/*    */   
/*    */   public void setAgentDesktopMsg(MessageBase agentDesktopMsg) {
/* 27 */     this.agentDesktopMsg = agentDesktopMsg;
/*    */   }
/*    */   
/*    */   public MessageBase getAgentDesktopMsg() {
/* 31 */     return this.agentDesktopMsg;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\AgentDesktopJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */