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
/*    */ public class QueueAgent
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2120931259827343135L;
/*    */   private QueueAgentId id;
/*    */   private Long priority;
/*    */   
/*    */   public QueueAgent() {}
/*    */   
/*    */   public QueueAgent(QueueAgentId id, Long priority)
/*    */   {
/* 26 */     this.id = id;
/* 27 */     this.priority = priority;
/*    */   }
/*    */   
/*    */ 
/*    */   public QueueAgentId getId()
/*    */   {
/* 33 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(QueueAgentId id) {
/* 37 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Long getPriority() {
/* 41 */     return this.priority;
/*    */   }
/*    */   
/*    */   public void setPriority(Long priority) {
/* 45 */     this.priority = priority;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\QueueAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */