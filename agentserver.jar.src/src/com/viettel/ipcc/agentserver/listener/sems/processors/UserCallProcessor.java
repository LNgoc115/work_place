/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.CallInfo;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class UserCallProcessor extends SemsProcessorBase
/*    */ {
/* 13 */   private SimpleDateFormat dateFormat = new SimpleDateFormat(
/* 14 */     "yyyy-MM-dd HH:mm:ss");
/*    */   
/*    */ 
/*    */   public void process(SemsCommandJob job)
/*    */   {
/* 19 */     forwardToOmap(job.getSemsCommand());
/*    */     
/* 21 */     String[] params = splitCommand(job);
/* 22 */     CallInfo call = new CallInfo();
/*    */     try {
/* 24 */       call.setStartTime(this.dateFormat.parse(params[3]));
/*    */     }
/*    */     catch (ParseException e) {
/* 27 */       this.logger.error("parse time error");
/* 28 */       e.printStackTrace();
/*    */     }
/* 30 */     call.setCalled(params[4]);
/* 31 */     call.setCaller(params[2]);
/* 32 */     call.setCallID(params[1]);
/* 33 */     call.setSemsUrl(params[7]);
/*    */     
/* 35 */     CallManager callManager = (CallManager)Utils.getCtx()
/* 36 */       .getBean("CallManager");
/*    */     
/* 38 */     callManager.addCall(call);
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\UserCallProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */