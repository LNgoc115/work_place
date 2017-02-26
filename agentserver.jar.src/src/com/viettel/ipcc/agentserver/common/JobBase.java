/*    */ package com.viettel.ipcc.agentserver.common;
/*    */ 
/*    */ public class JobBase
/*    */ {
/*    */   private long startTime;
/*    */   private Long jobID;
/*    */   
/*    */   public JobBase(Long jobID) {
/*  9 */     setStartTime(System.currentTimeMillis());
/* 10 */     this.jobID = jobID;
/*    */   }
/*    */   
/*    */   public void setStartTime(long startTime) {
/* 14 */     this.startTime = startTime;
/*    */   }
/*    */   
/*    */   public long getStartTime() {
/* 18 */     return this.startTime;
/*    */   }
/*    */   
/*    */   public void setJobID(Long jobID) {
/* 22 */     this.jobID = jobID;
/*    */   }
/*    */   
/*    */   public Long getJobID() {
/* 26 */     return this.jobID;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 30 */     return getClass().getCanonicalName() + " " + getJobID();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\common\JobBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */