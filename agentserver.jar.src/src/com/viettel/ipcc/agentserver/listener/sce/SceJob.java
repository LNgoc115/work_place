/*    */ package com.viettel.ipcc.agentserver.listener.sce;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.JobBase;
/*    */ 
/*    */ public class SceJob extends JobBase {
/*    */   private String command;
/*    */   private String value;
/*    */   
/*    */   public SceJob(Long jobID) {
/* 10 */     super(jobID);
/*    */   }
/*    */   
/*    */   public void setCommand(String command)
/*    */   {
/* 15 */     this.command = command;
/*    */   }
/*    */   
/*    */   public String getCommand() {
/* 19 */     return this.command;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 23 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 27 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\SceJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */