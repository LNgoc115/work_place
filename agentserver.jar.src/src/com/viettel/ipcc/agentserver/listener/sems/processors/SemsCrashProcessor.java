/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandProcessorManager;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class SemsCrashProcessor extends SemsProcessorBase
/*    */ {
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 18 */     String[] items = splitCommand(job);
/*    */     
/* 20 */     this.logger.info("Process: " + job.getSemsCommand());
/*    */     
/* 22 */     if (items.length >= 2) {
/* 23 */       String[] listCrashCall = items[1].split(",", -1);
/* 24 */       CallManager callMng = (CallManager)Utils.getCtx().getBean(
/* 25 */         "CallManager");
/*    */       
/* 27 */       SemsCommandProcessorManager mng = (SemsCommandProcessorManager)
/* 28 */         Utils.getCtx().getBean("SemsCommandProcessorManager");
/*    */       String[] arrayOfString1;
/* 30 */       int j = (arrayOfString1 = listCrashCall).length; for (int i = 0; i < j; i++) { String callid = arrayOfString1[i];
/*    */         
/*    */ 
/*    */ 
/* 34 */         CallInfo callInfo = callMng.getCall(callid);
/* 35 */         if (callInfo != null)
/*    */         {
/*    */ 
/*    */ 
/*    */ 
/* 40 */           String usercallCmd = "USERENDCALL;" + 
/* 41 */             callid + 
/* 42 */             ";" + 
/* 43 */             SemsProcessorBase.STANDARD_TIME_FORMAT
/* 44 */             .format(new Date(System.currentTimeMillis())) + 
/* 45 */             ";" + callid + "-1";
/*    */           
/* 47 */           SemsCommandJob j = new SemsCommandJob(Utils.genJobID());
/* 48 */           j.setSemsCommand(usercallCmd);
/* 49 */           mng.process(j);
/*    */           
/* 51 */           java.util.List<Agents> listAgent = callInfo.getAgentsInCall();
/*    */           
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 58 */           for (Agents a : listAgent) {
/* 59 */             String agentcallCmd = "AGENTENDCALL;" + 
/* 60 */               callid + 
/* 61 */               ";" + 
/* 62 */               a.getDeviceID() + 
/* 63 */               ";" + 
/* 64 */               "callee;" + 
/* 65 */               SemsProcessorBase.STANDARD_TIME_FORMAT
/* 66 */               .format(new Date(
/* 67 */               System.currentTimeMillis())) + ";" + 
/* 68 */               callid + "-1;";
/*    */             
/* 70 */             SemsCommandJob jagentendcall = new SemsCommandJob(
/* 71 */               Utils.genJobID());
/* 72 */             jagentendcall.setSemsCommand(agentcallCmd);
/* 73 */             mng.process(jagentendcall);
/*    */           }
/*    */           
/*    */ 
/*    */ 
/* 78 */           String endcallcmd = "ENDCALL;" + 
/* 79 */             callid + 
/* 80 */             ";" + 
/* 81 */             SemsProcessorBase.STANDARD_TIME_FORMAT
/* 82 */             .format(new Date(System.currentTimeMillis())) + 
/* 83 */             ";113;" + callid + "-1";
/*    */           
/* 85 */           SemsCommandJob jendcall = new SemsCommandJob(
/* 86 */             Utils.genJobID());
/* 87 */           jendcall.setSemsCommand(endcallcmd);
/* 88 */           mng.process(jendcall);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\SemsCrashProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */