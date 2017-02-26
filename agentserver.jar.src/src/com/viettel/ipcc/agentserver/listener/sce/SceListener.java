/*    */ package com.viettel.ipcc.agentserver.listener.sce;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SceListener
/*    */ {
/*    */   public static final String DELIM_CMD_VALUE = ":@:";
/*    */   public static final String DELIM = ";";
/* 15 */   private static Logger logger = Logger.getLogger(SceListener.class);
/*    */   
/*    */   public int reload(String params) {
/* 18 */     logger.info("call reload function from SCE : command= " + params);
/* 19 */     SceCommandProcessorManager mng = (SceCommandProcessorManager)
/* 20 */       Utils.getCtx().getBean("SceCommandProcessorManager");
/*    */     
/* 22 */     String[] cmdList = params.split(";");
/*    */     String[] arrayOfString1;
/* 24 */     int j = (arrayOfString1 = cmdList).length; for (int i = 0; i < j; i++) { String cmd = arrayOfString1[i];
/* 25 */       String[] res = cmd.split(":@:");
/* 26 */       if (res.length == 2)
/*    */       {
/*    */ 
/*    */ 
/* 30 */         if (!res[1].equalsIgnoreCase(""))
/*    */         {
/*    */ 
/*    */ 
/* 34 */           SceJob job = new SceJob(Utils.genJobID());
/* 35 */           job.setCommand(res[0]);
/* 36 */           job.setValue(res[1]);
/* 37 */           mng.processJob(job);
/*    */         }
/*    */       }
/*    */     }
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
/* 63 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\SceListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */