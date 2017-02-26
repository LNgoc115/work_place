/*    */ package com.viettel.ipcc.agentserver;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopRequestManager;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentServerListener;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceCommandProcessorManager;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.OmapCdrSender;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandProcessorManager;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.mortbay.jetty.Server;
/*    */ import org.mortbay.jetty.servlet.ServletHandler;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class Stop
/*    */ {
/* 19 */   private static Logger logger = Logger.getLogger(Stop.class);
/*    */   
/*    */   public static void main(String[] args) throws Exception
/*    */   {
/* 23 */     org.apache.log4j.PropertyConfigurator.configure("../etc/log.conf");
/*    */     
/* 25 */     Server server = (Server)Utils.getCtx().getBean("XmlrpcServer");
/*    */     
/* 27 */     ServletHandler handler = new ServletHandler();
/* 28 */     server.setHandler(handler);
/*    */     
/* 30 */     handler.addServletWithMapping(
/* 31 */       "org.apache.xmlrpc.webserver.XmlRpcServlet", "/");
/*    */     
/* 33 */     server.stop();
/*    */     
/* 35 */     logger.info("Xmlrpc stop!");
/*    */     
/* 37 */     AgentServerListener agentServerListener = (AgentServerListener)
/* 38 */       Utils.getCtx().getBean("AgentServerListener");
/*    */     
/* 40 */     agentServerListener.stop();
/*    */     
/* 42 */     logger.info("Agent server stopped!");
/*    */     
/* 44 */     SemsCommandProcessorManager semsCmdMng = (SemsCommandProcessorManager)
/* 45 */       Utils.getCtx().getBean("SemsCommandProcessorManager");
/* 46 */     semsCmdMng.stop();
/*    */     
/* 48 */     logger.info("SemsCommandProcessorManager stopped!");
/*    */     
/* 50 */     CallManager callMng = (CallManager)Utils.getCtx().getBean(
/* 51 */       "CallManager");
/* 52 */     callMng.stop();
/* 53 */     logger.info("call manager stopped!");
/*    */     
/* 55 */     AgentDesktopRequestManager agentDesktopRequestManager = (AgentDesktopRequestManager)
/* 56 */       Utils.getCtx().getBean("AgentDesktopRequestManager");
/* 57 */     agentDesktopRequestManager.stop();
/* 58 */     logger.info("agent desktop processor request stoped!");
/*    */     
/* 60 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 61 */       "AgentsManager");
/* 62 */     agentManager.stop();
/*    */     
/* 64 */     logger.info("agent manager stopped!");
/*    */     
/* 66 */     OmapCdrSender omapSender = (OmapCdrSender)Utils.getCtx().getBean(
/* 67 */       "OmapCdrSender");
/* 68 */     omapSender.stop();
/* 69 */     logger.info("OmapCdrSender stopped!");
/*    */     
/* 71 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/*    */     
/* 73 */     dbUpdater.stop();
/*    */     
/* 75 */     logger.info("database update stopped!");
/*    */     
/* 77 */     SceCommandProcessorManager sceCmdMng = (SceCommandProcessorManager)
/* 78 */       Utils.getCtx().getBean("SceCommandProcessorManager");
/* 79 */     sceCmdMng.stop();
/* 80 */     logger.info("Sce processor manager is stopped");
/*    */     
/* 82 */     logger.info("all thread stopped!");
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\Stop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */