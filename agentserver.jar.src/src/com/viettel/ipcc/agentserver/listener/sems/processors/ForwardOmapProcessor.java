/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ 
/*    */ 
/*    */ public class ForwardOmapProcessor
/*    */   extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 11 */     changeAgentIDAndForwardToOmap(job);
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\ForwardOmapProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */