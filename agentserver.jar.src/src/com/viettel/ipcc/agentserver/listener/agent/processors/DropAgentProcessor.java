/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ 
/*    */ public class DropAgentProcessor
/*    */   extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 11 */     Agents agent = null;
/* 12 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null)
/*    */     {
/* 14 */       String callid = "";
/* 15 */       String droppedAgent = "";
/* 16 */       String superAgent = "";
/*    */       
/* 18 */       String str1 = processSemsCmd(
/* 19 */         "dropagent", 
/* 20 */         callid, droppedAgent, superAgent);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\DropAgentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */