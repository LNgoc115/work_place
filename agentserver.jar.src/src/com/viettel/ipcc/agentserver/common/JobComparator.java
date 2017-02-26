/*    */ package com.viettel.ipcc.agentserver.common;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class JobComparator
/*    */   implements Comparator<JobBase>
/*    */ {
/*    */   public int compare(JobBase o1, JobBase o2)
/*    */   {
/* 17 */     if (o2.getStartTime() == o1.getStartTime()) {
/* 18 */       return o1.getJobID().compareTo(o2.getJobID());
/*    */     }
/* 20 */     return o1.getStartTime() > o2.getStartTime() ? 1 : -1;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\common\JobComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */