/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.OutCallResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class CalloutCustomerEndcallProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 14 */     String[] items = splitCommand(job);
/* 15 */     if (items.length >= 5)
/*    */     {
/* 17 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/* 18 */         "AgentsManager");
/* 19 */       Agents agent = mng.findByDeviceID(items[4]);
/*    */       
/* 21 */       if (agent != null) {
/* 22 */         items[4] = agent.getVsaID();
/* 23 */         OutCallResponse res = new OutCallResponse("", "ENDCALL", 
/* 24 */           items[2], items[1], "");
/*    */         
/* 26 */         Utils.sendMessageToAgent(agent.getAgentSession(), res);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 31 */     forwardToOmap(mergeCommand(items));
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\CalloutCustomerEndcallProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */