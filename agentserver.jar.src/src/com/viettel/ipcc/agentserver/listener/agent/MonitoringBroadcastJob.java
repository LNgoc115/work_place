/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.MessageBase;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ 
/*    */ public class MonitoringBroadcastJob extends com.viettel.ipcc.agentserver.common.JobBase
/*    */ {
/*    */   private Agents agent;
/*    */   private MessageBase msg;
/*    */   
/*    */   public MonitoringBroadcastJob(Long jobID)
/*    */   {
/* 13 */     super(jobID);
/*    */   }
/*    */   
/*    */   public void setAgent(Agents agent)
/*    */   {
/* 18 */     this.agent = agent;
/*    */   }
/*    */   
/*    */   public Agents getAgent() {
/* 22 */     return this.agent;
/*    */   }
/*    */   
/*    */   public void setMsg(MessageBase msg) {
/* 26 */     this.msg = msg;
/*    */   }
/*    */   
/*    */   public MessageBase getMsg() {
/* 30 */     return this.msg;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\MonitoringBroadcastJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */