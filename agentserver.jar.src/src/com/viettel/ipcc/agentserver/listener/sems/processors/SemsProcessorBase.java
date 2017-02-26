/*    */ package com.viettel.ipcc.agentserver.listener.sems.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.OmapCdrSender;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandJob;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.text.SimpleDateFormat;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ 
/*    */ public abstract class SemsProcessorBase
/*    */ {
/* 15 */   protected Logger logger = Logger.getLogger(SemsProcessorBase.class);
/* 16 */   public static final SimpleDateFormat STANDARD_TIME_FORMAT = new SimpleDateFormat(
/* 17 */     "yyyy-MM-dd HH:mm:ss");
/*    */   
/*    */   protected void changeAgentIDAndForwardToOmap(SemsCommandJob job) {
/* 20 */     String[] items = splitCommand(job);
/* 21 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 22 */       "AgentsManager");
/*    */     
/*    */ 
/* 25 */     Agents agent = agentManager.findByDeviceID(items[2]);
/* 26 */     if (agent == null) {
/* 27 */       this.logger.error("Something wrong!!! Can't not found agent by deviceid: " + 
/* 28 */         items[2]);
/* 29 */       forwardToOmap(mergeCommand(items));
/*    */     }
/*    */     else {
/* 32 */       items[2] = agent.getVsaID();
/*    */       
/* 34 */       forwardToOmap(mergeCommand(items));
/*    */     }
/*    */   }
/*    */   
/*    */   protected String[] splitCommand(SemsCommandJob job)
/*    */   {
/* 40 */     return job.getSemsCommand().split(";", 
/* 41 */       -1);
/*    */   }
/*    */   
/*    */   protected String mergeCommand(String[] items)
/*    */   {
/* 46 */     if (items.length > 0) {
/* 47 */       StringBuilder buf = new StringBuilder();
/* 48 */       buf.append(items[0]);
/*    */       
/* 50 */       for (int i = 1; i < items.length; i++) {
/* 51 */         buf.append(';').append(items[i]);
/*    */       }
/*    */       
/* 54 */       return buf.toString();
/*    */     }
/*    */     
/* 57 */     return "";
/*    */   }
/*    */   
/*    */   protected void forwardToOmap(String cmd) {
/* 61 */     this.logger.info("forward command to omap: " + cmd);
/*    */     
/* 63 */     OmapCdrSender sender = (OmapCdrSender)Utils.getCtx().getBean(
/* 64 */       "OmapCdrSender");
/* 65 */     sender.sendMessage(cmd);
/*    */   }
/*    */   
/*    */   public abstract void process(SemsCommandJob paramSemsCommandJob);
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\processors\SemsProcessorBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */