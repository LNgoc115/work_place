/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class DisagentProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 12 */     String[] items = splitCommand(job);
/* 13 */     AgentsManager agentManager = (AgentsManager)
/* 14 */       com.viettel.ipcc.agentserver.utils.Utils.getCtx().getBean("AgentsManager");
/* 15 */     Agents agent = agentManager.findByDeviceID(items[2]);
/* 16 */     if (agent == null) {
/* 17 */       this.logger.error("Something wrong!!! Can't not found agent by deviceid: " + 
/* 18 */         items[2]);
/*    */     }
/*    */     else {
/* 21 */       items[2] = agent.getVsaID();
/* 22 */       Agents superAgent = agentManager.findByDeviceID(items[3]);
/*    */       
/* 24 */       if (superAgent == null) {
/* 25 */         this.logger.error("Something wrong!!! Can't not found super agent by deviceid: " + 
/*    */         
/* 27 */           items[3]);
/*    */       } else {
/* 29 */         items[3] = superAgent.getVsaID();
/* 30 */         forwardToOmap(mergeCommand(items));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\DisagentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */