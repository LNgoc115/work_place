/*    */ package com.viettel.ipcc.agentserver.dao;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QueueAgentId
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8335100974384237951L;
/*    */   private Long queueId;
/*    */   private Agents agents;
/*    */   
/*    */   public QueueAgentId() {}
/*    */   
/*    */   public QueueAgentId(Long queueId, Agents agents)
/*    */   {
/* 26 */     this.queueId = queueId;
/* 27 */     this.agents = agents;
/*    */   }
/*    */   
/*    */ 
/*    */   public Long getQueueId()
/*    */   {
/* 33 */     return this.queueId;
/*    */   }
/*    */   
/*    */   public void setQueueId(Long queueId) {
/* 37 */     this.queueId = queueId;
/*    */   }
/*    */   
/*    */   public Agents getAgents() {
/* 41 */     return this.agents;
/*    */   }
/*    */   
/*    */   public void setAgents(Agents agents) {
/* 45 */     this.agents = agents;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 49 */     if (this == other)
/* 50 */       return true;
/* 51 */     if (other == null)
/* 52 */       return false;
/* 53 */     if (!(other instanceof QueueAgentId))
/* 54 */       return false;
/* 55 */     QueueAgentId castOther = (QueueAgentId)other;
/*    */     
/* 57 */     if ((getQueueId() == castOther.getQueueId()) || (
/* 58 */       (getQueueId() != null) && 
/* 59 */       (castOther.getQueueId() != null) && (getQueueId().equals(
/* 60 */       castOther.getQueueId())))) {
/* 61 */       if ((getAgents() == castOther.getAgents()) || (
/* 62 */         (getAgents() != null) && 
/* 63 */         (castOther.getAgents() != null) && 
/* 64 */         (getAgents().equals(castOther.getAgents())))) return true;
/*    */     }
/* 57 */     return 
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 64 */       false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 68 */     int result = 17;
/*    */     
/* 70 */     result = 37 * result + (
/* 71 */       getQueueId() == null ? 0 : getQueueId().hashCode());
/* 72 */     result = 37 * result + (
/* 73 */       getAgents() == null ? 0 : getAgents().hashCode());
/* 74 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\QueueAgentId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */