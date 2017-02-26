/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class SemsTransferProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 12 */     String[] items = splitCommand(job);
/* 13 */     AgentsManager agentManager = (AgentsManager)
/* 14 */       com.viettel.ipcc.agentserver.utils.Utils.getCtx().getBean("AgentsManager");
/* 15 */     Agents newAgent = agentManager.findByDeviceID(items[2]);
/* 16 */     if (newAgent == null) {
/* 17 */       this.logger.error("Something wrong!!! Can't not found agent by deviceid: " + 
/* 18 */         items[2]);
/*    */     }
/*    */     else {
/* 21 */       items[2] = newAgent.getVsaID();
/* 22 */       Agents oldAgents = agentManager.findByDeviceID(items[3]);
/*    */       
/* 24 */       if (oldAgents == null) {
/* 25 */         this.logger.error("Something wrong!!! Can't not found  agent by deviceid: " + 
/* 26 */           items[3]);
/*    */       } else {
/* 28 */         items[3] = oldAgents.getVsaID();
/* 29 */         items[5] = oldAgents.getDeviceID();
/* 30 */         forwardToOmap(mergeCommand(items));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\SemsTransferProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */