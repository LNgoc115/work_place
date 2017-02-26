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
/*    */ public class AgentZoonId
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7755216250849335029L;
/*    */   private Agents agents;
/*    */   private Zoon zoon;
/*    */   
/*    */   public AgentZoonId() {}
/*    */   
/*    */   public AgentZoonId(Agents agents, Zoon zoon)
/*    */   {
/* 26 */     this.agents = agents;
/* 27 */     this.zoon = zoon;
/*    */   }
/*    */   
/*    */ 
/*    */   public Agents getAgents()
/*    */   {
/* 33 */     return this.agents;
/*    */   }
/*    */   
/*    */   public void setAgents(Agents agents) {
/* 37 */     this.agents = agents;
/*    */   }
/*    */   
/*    */   public Zoon getZoon() {
/* 41 */     return this.zoon;
/*    */   }
/*    */   
/*    */   public void setZoon(Zoon zoon) {
/* 45 */     this.zoon = zoon;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 49 */     if (this == other)
/* 50 */       return true;
/* 51 */     if (other == null)
/* 52 */       return false;
/* 53 */     if (!(other instanceof AgentZoonId))
/* 54 */       return false;
/* 55 */     AgentZoonId castOther = (AgentZoonId)other;
/*    */     
/* 57 */     if ((getAgents() == castOther.getAgents()) || (
/* 58 */       (getAgents() != null) && 
/* 59 */       (castOther.getAgents() != null) && (getAgents().equals(
/* 60 */       castOther.getAgents())))) {
/* 61 */       if ((getZoon() == castOther.getZoon()) || ((getZoon() != null) && 
/* 62 */         (castOther.getZoon() != null) && 
/* 63 */         (getZoon().equals(castOther.getZoon())))) return true;
/*    */     }
/* 57 */     return 
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 63 */       false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 67 */     int result = 17;
/*    */     
/* 69 */     result = 37 * result + (
/* 70 */       getAgents() == null ? 0 : getAgents().hashCode());
/* 71 */     result = 37 * result + (
/* 72 */       getZoon() == null ? 0 : getZoon().hashCode());
/* 73 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\AgentZoonId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */