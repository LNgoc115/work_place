/*    */ package com.viettel.ipcc.agentserver.mm;
/*    */ 
/*    */ import java.lang.management.ManagementFactory;
/*    */ import javax.management.MBeanServer;
/*    */ import javax.management.ObjectName;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MMServer
/*    */ {
/*    */   public static void start()
/*    */   {
/* 18 */     MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
/* 19 */     AgentServerInfo agentServerInfo = new AgentServerInfo();
/*    */     try {
/* 21 */       mbs.registerMBean(agentServerInfo, new ObjectName("AgentServer:type=agentServerInfo"));
/*    */     } catch (Exception ex) {
/* 23 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\mm\MMServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */