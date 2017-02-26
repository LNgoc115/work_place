/*    */ package com.viettel.ipcc.agentserver.utils;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.MessageBase;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.apache.xmlrpc.XmlRpcException;
/*    */ import org.apache.xmlrpc.client.XmlRpcClient;
/*    */ import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.context.support.FileSystemXmlApplicationContext;
/*    */ 
/*    */ 
/*    */ public class Utils
/*    */ {
/* 16 */   private static long jobIndex = 0L;
/*    */   private static ApplicationContext ctx;
/*    */   
/*    */   public static synchronized Long genJobID()
/*    */   {
/* 21 */     return Long.valueOf(jobIndex++);
/*    */   }
/*    */   
/*    */   public static String toSqlVar(int size) {
/* 25 */     StringBuilder buf = new StringBuilder();
/*    */     
/* 27 */     if (size > 0) {
/* 28 */       buf.append(":0");
/* 29 */       for (int i = 1; i < size; i++) {
/* 30 */         buf.append(",:").append(i);
/*    */       }
/*    */     }
/*    */     
/* 34 */     return buf.toString();
/*    */   }
/*    */   
/*    */   public static synchronized ApplicationContext getCtx() {
/* 38 */     if (ctx == null) {
/* 39 */       ctx = new FileSystemXmlApplicationContext(
/* 40 */         new String[] { "../etc/applicationContext.xml" });
/*    */     }
/* 42 */     return ctx;
/*    */   }
/*    */   
/*    */   public static Object sendXmlrpc(String url, String method, Object[] params)
/*    */   {
/* 47 */     Object res = null;
/* 48 */     XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
/*    */     try {
/* 50 */       config.setServerURL(new URL(url));
/*    */     } catch (MalformedURLException ex) {
/* 52 */       ex.printStackTrace();
/*    */     }
/* 54 */     XmlRpcClient client = new XmlRpcClient();
/* 55 */     client.setConfig(config);
/*    */     try
/*    */     {
/* 58 */       res = client.execute(method, params);
/*    */     } catch (XmlRpcException ex) {
/* 60 */       res = Boolean.valueOf(false);
/* 61 */       ex.printStackTrace();
/*    */     }
/* 63 */     return res;
/*    */   }
/*    */   
/*    */   public static void sendMessageToAgent(IoSession session, MessageBase msg) {
/* 67 */     session.write(msg);
/*    */   }
/*    */   
/*    */   public static String getIp(IoSession session) {
/*    */     try {
/* 72 */       String ip = session.getRemoteAddress().toString();
/* 73 */       ip = ip.substring(ip.lastIndexOf('/') + 1);
/* 74 */       return ip.substring(0, ip.indexOf(':'));
/*    */     }
/*    */     catch (Exception localException) {}
/*    */     
/*    */ 
/* 79 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\utils\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */