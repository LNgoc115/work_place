/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.CalloutInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class TerminateCalloutProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 13 */     Agents agent = null;
/* 14 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null)
/*    */     {
/* 16 */       synchronized (agent) {
/* 17 */         CalloutInfo info = agent.getCalloutInfo();
/* 18 */         if (info != null) {
/* 19 */           Object res = Utils.sendXmlrpc(info.getCalloutServerUrl(), 
/* 20 */             "dropagent", 
/* 21 */             new Object[] { info.getCalloutID() });
/*    */           
/* 23 */           this.logger.info(agent + " end callout process res: " + res);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\TerminateCalloutProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */