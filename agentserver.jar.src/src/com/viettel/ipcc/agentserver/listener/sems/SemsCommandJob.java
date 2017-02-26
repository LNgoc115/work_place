/*    */ package com.viettel.ipcc.agentserver.listener.sems;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.JobBase;
/*    */ 
/*    */ public class SemsCommandJob extends JobBase
/*    */ {
/*    */   private String semsCommand;
/*    */   
/*    */   public SemsCommandJob(Long jobID) {
/* 10 */     super(jobID);
/*    */   }
/*    */   
/*    */   public void setSemsCommand(String semsCommand)
/*    */   {
/* 15 */     this.semsCommand = semsCommand;
/*    */   }
/*    */   
/*    */   public String getSemsCommand() {
/* 19 */     return this.semsCommand;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\SemsCommandJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */