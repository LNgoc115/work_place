/*    */ package com.viettel.ipcc.agentserver;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AcdServerRequestSender;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopRequestManager;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentServerListener;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.SceCommandProcessorManager;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.OmapCdrSender;
/*    */ import com.viettel.ipcc.agentserver.listener.sems.SemsCommandProcessorManager;
/*    */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*    */ import com.viettel.ipcc.agentserver.manager.CallManager;
/*    */ import com.viettel.ipcc.agentserver.mm.MMServer;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.log4j.PropertyConfigurator;
/*    */ import org.mortbay.jetty.Server;
/*    */ import org.mortbay.jetty.servlet.ServletHandler;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class Start
/*    */ {
/* 22 */   private static Logger logger = Logger.getLogger(Start.class);
/*    */   
/*    */   public static void main(String[] args) throws Exception
/*    */   {
/* 26 */     PropertyConfigurator.configure("../etc/log.conf");
/*    */     
/* 28 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/*    */     
/* 30 */     dbUpdater.start();
/*    */     
/* 32 */     OmapCdrSender omapSender = (OmapCdrSender)Utils.getCtx().getBean(
/* 33 */       "OmapCdrSender");
/* 34 */     omapSender.start();
/* 35 */     logger.info("OmapCdrSender started!");
/*    */     
/* 37 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/* 38 */       "AgentsManager");
/* 39 */     agentManager.start();
/*    */     
/* 41 */     CallManager callMng = (CallManager)Utils.getCtx().getBean(
/* 42 */       "CallManager");
/* 43 */     callMng.start();
/*    */     
/* 45 */     AgentDesktopRequestManager agentDesktopRequestManager = (AgentDesktopRequestManager)
/* 46 */       Utils.getCtx().getBean("AgentDesktopRequestManager");
/* 47 */     agentDesktopRequestManager.start();
/*    */     
/* 49 */     AgentServerListener agentServerListener = (AgentServerListener)
/* 50 */       Utils.getCtx().getBean("AgentServerListener");
/*    */     
/* 52 */     agentServerListener.start();
/*    */     
/* 54 */     AcdServerRequestSender acdSender = (AcdServerRequestSender)
/* 55 */       Utils.getCtx().getBean("AcdServerRequestSender");
/* 56 */     acdSender.initList();
/*    */     
/* 58 */     SemsCommandProcessorManager semsCmdMng = (SemsCommandProcessorManager)
/* 59 */       Utils.getCtx().getBean("SemsCommandProcessorManager");
/* 60 */     semsCmdMng.start();
/*    */     
/* 62 */     logger.info("SemsCommandProcessorManager started!");
/*    */     
/* 64 */     Server server = (Server)Utils.getCtx().getBean("XmlrpcServer");
/*    */     
/* 66 */     ServletHandler handler = new ServletHandler();
/* 67 */     server.setHandler(handler);
/*    */     
/* 69 */     handler.addServletWithMapping(
/* 70 */       "org.apache.xmlrpc.webserver.XmlRpcServlet", "/agentserver");
/*    */     
/* 72 */     server.start();
/*    */     
/* 74 */     logger.info("Xmlrpc started!");
/*    */     
/* 76 */     MMServer.start();
/* 77 */     logger.info("MMServer started");
/*    */     
/* 79 */     SceCommandProcessorManager sceCmdMng = (SceCommandProcessorManager)
/* 80 */       Utils.getCtx().getBean("SceCommandProcessorManager");
/* 81 */     sceCmdMng.start();
/* 82 */     logger.info("sce processor manager is started");
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\Start.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */