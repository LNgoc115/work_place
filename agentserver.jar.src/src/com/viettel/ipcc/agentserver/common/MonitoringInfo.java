/*    */ package com.viettel.ipcc.agentserver.common;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class MonitoringInfo
/*    */ {
/*    */   private volatile Agents agentDoMonitoring;
/*  9 */   private volatile boolean isSpying = false;
/* 10 */   private volatile CallInfo nextSpyCall = null;
/* 11 */   private volatile boolean isCompleteSpy = true;
/*    */   
/*    */   private volatile String currentMonotoringSpyCallid;
/* 14 */   private volatile boolean isEnableShowCallDetail = true;
/*    */   private Set<String> listSpyedAgents;
/*    */   
/*    */   public MonitoringInfo() {
/* 18 */     setListSpyedAgents(Collections.synchronizedSet(new java.util.HashSet()));
/*    */   }
/*    */   
/*    */   public void setAgentDoMonitoring(Agents agentDoMonitoring) {
/* 22 */     this.agentDoMonitoring = agentDoMonitoring;
/*    */   }
/*    */   
/*    */   public Agents getAgentDoMonitoring() {
/* 26 */     return this.agentDoMonitoring;
/*    */   }
/*    */   
/*    */   public void setSpying(boolean isSpying) {
/* 30 */     this.isSpying = isSpying;
/*    */   }
/*    */   
/*    */   public boolean isSpying() {
/* 34 */     return this.isSpying;
/*    */   }
/*    */   
/*    */   public void setCompleteSpy(boolean isCompleteSpy) {
/* 38 */     this.isCompleteSpy = isCompleteSpy;
/*    */   }
/*    */   
/*    */   public boolean isCompleteSpy() {
/* 42 */     return this.isCompleteSpy;
/*    */   }
/*    */   
/*    */   public void setEnableShowCallDetail(boolean isEnableShowCallDetail) {
/* 46 */     this.isEnableShowCallDetail = isEnableShowCallDetail;
/*    */   }
/*    */   
/*    */   public boolean isEnableShowCallDetail() {
/* 50 */     return this.isEnableShowCallDetail;
/*    */   }
/*    */   
/*    */   public void setListSpyedAgents(Set<String> listSpyedAgents) {
/* 54 */     this.listSpyedAgents = listSpyedAgents;
/*    */   }
/*    */   
/*    */   public Set<String> getListSpyedAgents() {
/* 58 */     return this.listSpyedAgents;
/*    */   }
/*    */   
/*    */   public void setNextSpyCall(CallInfo nextSpyCall) {
/* 62 */     this.nextSpyCall = nextSpyCall;
/*    */   }
/*    */   
/*    */   public CallInfo getNextSpyCall() {
/* 66 */     return this.nextSpyCall;
/*    */   }
/*    */   
/*    */   public void setCurrentMonotoringSpyCallid(String currentMonotoringSpyCallid) {
/* 70 */     this.currentMonotoringSpyCallid = currentMonotoringSpyCallid;
/*    */   }
/*    */   
/*    */   public String getCurrentMonotoringSpyCallid() {
/* 74 */     return this.currentMonotoringSpyCallid;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\common\MonitoringInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */