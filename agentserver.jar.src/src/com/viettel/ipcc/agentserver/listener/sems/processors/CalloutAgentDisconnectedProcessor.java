/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class CalloutAgentDisconnectedProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 13 */     String[] items = splitCommand(job);
/*    */     
/* 15 */     if (items.length >= 4) {
/* 16 */       String agentid = items[3];
/* 17 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 18 */         "AgentsManager");
/*    */       
/* 20 */       Agents agent = mng.findByDeviceID(agentid);
/*    */       
/* 22 */       if (agent != null) {
/* 23 */         synchronized (agent) {
/* 24 */           agent.setCalloutInfo(null);
/* 25 */           this.logger.info(agent + " disconnected callout session");
/*    */         }
/*    */       }
/*    */     } else {
/* 29 */       this.logger.info("syntax error " + job.getSemsCommand());
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\CalloutAgentDisconnectedProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */