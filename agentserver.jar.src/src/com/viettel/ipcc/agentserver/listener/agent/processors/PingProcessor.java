/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class PingProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 11 */     Agents agent = null;
/* 12 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 13 */       synchronized (agent)
/*    */       {
/* 15 */         agent.setNumSendPingNotRes(0);
/*    */       }
/* 17 */       this.logger.debug("process ping response for: " + agent);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\PingProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */