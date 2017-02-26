/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.OutCallResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class CalloutCustomerRingingProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 14 */     String[] items = splitCommand(job);
/* 15 */     if (items.length >= 5) {
/* 16 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 17 */         "AgentsManager");
/*    */       
/* 19 */       Agents agent = mng.findByDeviceID(items[4]);
/* 20 */       if (agent != null) {
/* 21 */         items[4] = agent.getVsaID();
/* 22 */         OutCallResponse res = new OutCallResponse(items[2], "RINGING", 
/* 23 */           items[3], items[1], "");
/*    */         
/* 25 */         Utils.sendMessageToAgent(agent.getAgentSession(), res);
/*    */       }
/*    */       
/* 28 */       String[] outCallStartItems = new String[7];
/* 29 */       outCallStartItems[0] = "OUTCALLSTART";
/* 30 */       outCallStartItems[1] = items[1];
/* 31 */       outCallStartItems[2] = items[4];
/* 32 */       outCallStartItems[3] = "";
/* 33 */       outCallStartItems[4] = items[3];
/* 34 */       outCallStartItems[5] = (agent == null ? "" : agent.getDeviceID());
/* 35 */       outCallStartItems[6] = (agent.getCalloutInfo() == null ? "" : agent
/* 36 */         .getCalloutInfo().getCalloutServerUrl());
/*    */       
/* 38 */       forwardToOmap(mergeCommand(outCallStartItems));
/*    */     }
/*    */     
/* 41 */     forwardToOmap(mergeCommand(items));
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\CalloutCustomerRingingProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */