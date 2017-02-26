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
/*    */ public class AgentZoon
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6942959072409371920L;
/*    */   private AgentZoonId id;
/*    */   
/*    */   public AgentZoon() {}
/*    */   
/*    */   public AgentZoon(AgentZoonId id)
/*    */   {
/* 25 */     this.id = id;
/*    */   }
/*    */   
/*    */ 
/*    */   public AgentZoonId getId()
/*    */   {
/* 31 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(AgentZoonId id) {
/* 35 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\AgentZoon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */