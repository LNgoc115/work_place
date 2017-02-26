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
/*    */ public class Zoon
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5373544141993943309L;
/*    */   private String zoonId;
/*    */   private String zoonName;
/*    */   private String description;
/* 21 */   private Set agentZoons = new HashSet(0);
/*    */   
/*    */ 
/*    */ 
/*    */   public Zoon() {}
/*    */   
/*    */ 
/*    */ 
/*    */   public Zoon(String zoonId)
/*    */   {
/* 31 */     this.zoonId = zoonId;
/*    */   }
/*    */   
/*    */ 
/*    */   public Zoon(String zoonId, String zoonName, String description, Set agentZoons)
/*    */   {
/* 37 */     this.zoonId = zoonId;
/* 38 */     this.zoonName = zoonName;
/* 39 */     this.description = description;
/* 40 */     this.agentZoons = agentZoons;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getZoonId()
/*    */   {
/* 46 */     return this.zoonId;
/*    */   }
/*    */   
/*    */   public void setZoonId(String zoonId) {
/* 50 */     this.zoonId = zoonId;
/*    */   }
/*    */   
/*    */   public String getZoonName() {
/* 54 */     return this.zoonName;
/*    */   }
/*    */   
/*    */   public void setZoonName(String zoonName) {
/* 58 */     this.zoonName = zoonName;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 62 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 66 */     this.description = description;
/*    */   }
/*    */   
/*    */   public Set getAgentZoons() {
/* 70 */     return this.agentZoons;
/*    */   }
/*    */   
/*    */   public void setAgentZoons(Set agentZoons) {
/* 74 */     this.agentZoons = agentZoons;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\Zoon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */