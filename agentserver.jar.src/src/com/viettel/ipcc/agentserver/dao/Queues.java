/*    */ package com.viettel.ipcc.agentserver.dao;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Queues
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 540842924922754101L;
/*    */   private Long queueId;
/*    */   private String description;
/* 20 */   private Set queueParams = new HashSet(0);
/*    */   
/*    */ 
/*    */ 
/*    */   public Queues() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public Queues(Long queueId, String description)
/*    */   {
/* 30 */     this.queueId = queueId;
/* 31 */     this.description = description;
/*    */   }
/*    */   
/*    */   public Queues(Long queueId, String description, Set queueParams)
/*    */   {
/* 36 */     this.queueId = queueId;
/* 37 */     this.description = description;
/* 38 */     this.queueParams = queueParams;
/*    */   }
/*    */   
/*    */ 
/*    */   public Long getQueueId()
/*    */   {
/* 44 */     return this.queueId;
/*    */   }
/*    */   
/*    */   public void setQueueId(Long queueId) {
/* 48 */     this.queueId = queueId;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 52 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 56 */     this.description = description;
/*    */   }
/*    */   
/*    */   public Set getQueueParams() {
/* 60 */     return this.queueParams;
/*    */   }
/*    */   
/*    */   public void setQueueParams(Set queueParams) {
/* 64 */     this.queueParams = queueParams;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\Queues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */