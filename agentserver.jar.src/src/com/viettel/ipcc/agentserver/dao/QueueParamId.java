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
/*    */ public class QueueParamId
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8529278109490016984L;
/*    */   private Queues queues;
/*    */   private Params params;
/*    */   
/*    */   public QueueParamId() {}
/*    */   
/*    */   public QueueParamId(Queues queues, Params params)
/*    */   {
/* 26 */     this.queues = queues;
/* 27 */     this.params = params;
/*    */   }
/*    */   
/*    */ 
/*    */   public Queues getQueues()
/*    */   {
/* 33 */     return this.queues;
/*    */   }
/*    */   
/*    */   public void setQueues(Queues queues) {
/* 37 */     this.queues = queues;
/*    */   }
/*    */   
/*    */   public Params getParams() {
/* 41 */     return this.params;
/*    */   }
/*    */   
/*    */   public void setParams(Params params) {
/* 45 */     this.params = params;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 49 */     if (this == other)
/* 50 */       return true;
/* 51 */     if (other == null)
/* 52 */       return false;
/* 53 */     if (!(other instanceof QueueParamId))
/* 54 */       return false;
/* 55 */     QueueParamId castOther = (QueueParamId)other;
/*    */     
/* 57 */     if ((getQueues() == castOther.getQueues()) || (
/* 58 */       (getQueues() != null) && 
/* 59 */       (castOther.getQueues() != null) && (getQueues().equals(
/* 60 */       castOther.getQueues())))) {
/* 61 */       if ((getParams() == castOther.getParams()) || (
/* 62 */         (getParams() != null) && 
/* 63 */         (castOther.getParams() != null) && 
/* 64 */         (getParams().equals(castOther.getParams())))) return true;
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
/* 71 */       getQueues() == null ? 0 : getQueues().hashCode());
/* 72 */     result = 37 * result + (
/* 73 */       getParams() == null ? 0 : getParams().hashCode());
/* 74 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\QueueParamId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */