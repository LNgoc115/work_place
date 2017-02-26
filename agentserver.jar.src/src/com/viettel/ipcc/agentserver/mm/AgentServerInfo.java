/*    */ package com.viettel.ipcc.agentserver.mm;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AgentServerInfo
/*    */   implements AgentServerInfoMBean
/*    */ {
/* 17 */   private Logger logger = Logger.getLogger(AgentServerInfo.class);
/*    */   
/*    */   public int getNumLoginInfo() {
/* 20 */     this.logger.info("process invoking getNumLoginInfo");
/* 21 */     int result = 0;
/* 22 */     AgentsManager agentsManager = (AgentsManager)Utils.getCtx().getBean("AgentsManager");
/* 23 */     result = agentsManager.getNumAgentLogin();
/*    */     
/* 25 */     return result;
/*    */   }
/*    */   
/*    */   public int getNumSessionConnected() {
/* 29 */     this.logger.info("process invoking getNumSessionConnected");
/* 30 */     int result = 0;
/* 31 */     AgentsManager agentsManager = (AgentsManager)Utils.getCtx().getBean("AgentsManager");
/* 32 */     result = agentsManager.getNumSessionConnected();
/*    */     
/* 34 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */   public int getNumSessionIdle()
/*    */   {
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\mm\AgentServerInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */