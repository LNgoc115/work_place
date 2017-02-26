/*    */ package com.viettel.ipcc.agentserver.dbupdater;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.JobBase;
/*    */ 
/*    */ public class AgentUpdaterJob extends JobBase {
/*    */   private String agentid;
/*    */   
/*  8 */   public AgentUpdaterJob(Long jobID) { super(jobID); }
/*    */   
/*    */ 
/*    */   private String columnName;
/*    */   
/*    */   private Object value;
/*    */   
/*    */   public void setAgentid(String agentid)
/*    */   {
/* 17 */     this.agentid = agentid;
/*    */   }
/*    */   
/*    */   public String getAgentid() {
/* 21 */     return this.agentid;
/*    */   }
/*    */   
/*    */   public void setColumnName(String columnName) {
/* 25 */     this.columnName = columnName;
/*    */   }
/*    */   
/*    */   public String getColumnName() {
/* 29 */     return this.columnName;
/*    */   }
/*    */   
/*    */   public void setValue(Object value) {
/* 33 */     this.value = value;
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 37 */     return this.value;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 41 */     StringBuilder res = new StringBuilder();
/* 42 */     res.append("Update db : agentid ").append(this.agentid).append(" col: ")
/* 43 */       .append(this.columnName).append(" val: ").append(this.value);
/*    */     
/* 45 */     return res.toString();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dbupdater\AgentUpdaterJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */