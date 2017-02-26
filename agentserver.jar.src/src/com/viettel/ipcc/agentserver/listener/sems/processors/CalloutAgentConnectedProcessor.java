/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.CalloutInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class CalloutAgentConnectedProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 14 */     String[] items = splitCommand(job);
/* 15 */     if (items.length >= 8) {
/* 16 */       String calloutid = items[1];
/* 17 */       String agentid = items[2];
/* 18 */       String calloutServerUrl = items[7];
/* 19 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 20 */         "AgentsManager");
/*    */       
/* 22 */       Agents agent = mng.findByDeviceID(agentid);
/* 23 */       if (agent != null)
/*    */       {
/* 25 */         CalloutInfo calloutInfo = new CalloutInfo();
/* 26 */         calloutInfo.setAgentid(agentid);
/* 27 */         calloutInfo.setCalloutID(calloutid);
/* 28 */         calloutInfo.setCalloutServerUrl(calloutServerUrl);
/*    */         
/* 30 */         synchronized (agent) {
/* 31 */           agent.setCalloutInfo(calloutInfo);
/* 32 */           this.logger.info(agent + " connected callout session");
/*    */         }
/*    */       }
/*    */     }
/*    */     else {
/* 37 */       this.logger.info("syntax error: " + job.getSemsCommand());
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\CalloutAgentConnectedProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */