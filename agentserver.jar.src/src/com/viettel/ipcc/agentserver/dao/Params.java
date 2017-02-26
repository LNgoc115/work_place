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
/*    */ public class Params
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -5417443661648837073L;
/*    */   private Long paramId;
/*    */   private String paramName;
/*    */   private String description;
/* 21 */   private Set queueParams = new HashSet(0);
/*    */   
/*    */ 
/*    */ 
/*    */   public Params() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public Params(Long paramId, String paramName, String description)
/*    */   {
/* 31 */     this.paramId = paramId;
/* 32 */     this.paramName = paramName;
/* 33 */     this.description = description;
/*    */   }
/*    */   
/*    */ 
/*    */   public Params(Long paramId, String paramName, String description, Set queueParams)
/*    */   {
/* 39 */     this.paramId = paramId;
/* 40 */     this.paramName = paramName;
/* 41 */     this.description = description;
/* 42 */     this.queueParams = queueParams;
/*    */   }
/*    */   
/*    */ 
/*    */   public Long getParamId()
/*    */   {
/* 48 */     return this.paramId;
/*    */   }
/*    */   
/*    */   public void setParamId(Long paramId) {
/* 52 */     this.paramId = paramId;
/*    */   }
/*    */   
/*    */   public String getParamName() {
/* 56 */     return this.paramName;
/*    */   }
/*    */   
/*    */   public void setParamName(String paramName) {
/* 60 */     this.paramName = paramName;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 64 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 68 */     this.description = description;
/*    */   }
/*    */   
/*    */   public Set getQueueParams() {
/* 72 */     return this.queueParams;
/*    */   }
/*    */   
/*    */   public void setQueueParams(Set queueParams) {
/* 76 */     this.queueParams = queueParams;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\Params.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */