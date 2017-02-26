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
/*    */ public class QueueParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1110082340350085100L;
/*    */   private QueueParamId id;
/*    */   private String description;
/*    */   private String value;
/*    */   
/*    */   public QueueParam() {}
/*    */   
/*    */   public QueueParam(QueueParamId id, String description, String value)
/*    */   {
/* 27 */     this.id = id;
/* 28 */     this.description = description;
/* 29 */     this.value = value;
/*    */   }
/*    */   
/*    */ 
/*    */   public QueueParamId getId()
/*    */   {
/* 35 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(QueueParamId id) {
/* 39 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 43 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 47 */     this.description = description;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 51 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 55 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\QueueParam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */