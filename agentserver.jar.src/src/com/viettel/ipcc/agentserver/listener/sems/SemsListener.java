/*    */ package com.viettel.ipcc.agentserver.listener.sems;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ 
/*    */ public class SemsListener
/*    */ {
/*    */   public int listenCommand(String command) {
/*  8 */     SemsCommandJob job = new SemsCommandJob(Utils.genJobID());
/*  9 */     job.setSemsCommand(command);
/* 10 */     SemsCommandProcessorManager mng = (SemsCommandProcessorManager)
/* 11 */       Utils.getCtx().getBean("SemsCommandProcessorManager");
/* 12 */     mng.process(job);
/*    */     
/* 14 */     return 0;
/*    */   }
/*    */   
/*    */   public int isActive()
/*    */   {
/* 19 */     int res = 1;
/*    */     
/* 21 */     return res;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\SemsListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */