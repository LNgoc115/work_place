/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.InetAddress;
/*    */ import java.net.ServerSocket;
/*    */ import java.security.GeneralSecurityException;
/*    */ import javax.net.ServerSocketFactory;
/*    */ import javax.net.ssl.SSLContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SslServerSocketFactory
/*    */   extends ServerSocketFactory
/*    */ {
/* 21 */   private static boolean sslEnabled = false;
/*    */   
/*    */ 
/* 24 */   private static ServerSocketFactory sslFactory = null;
/*    */   
/*    */ 
/* 27 */   private static ServerSocketFactory factory = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ServerSocket createServerSocket(int port)
/*    */     throws IOException
/*    */   {
/* 38 */     return new ServerSocket(port);
/*    */   }
/*    */   
/*    */   public ServerSocket createServerSocket(int port, int backlog)
/*    */     throws IOException
/*    */   {
/* 44 */     return new ServerSocket(port, backlog);
/*    */   }
/*    */   
/*    */   public ServerSocket createServerSocket(int port, int backlog, InetAddress ifAddress)
/*    */     throws IOException
/*    */   {
/* 50 */     return new ServerSocket(port, backlog, ifAddress);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static ServerSocketFactory getServerSocketFactory()
/*    */     throws IOException
/*    */   {
/* 59 */     if (isSslEnabled()) {
/* 60 */       if (sslFactory == null) {
/*    */         try {
/* 62 */           sslFactory = 
/* 63 */             BogusSslContextFactory.getInstance(true).getServerSocketFactory();
/*    */         } catch (GeneralSecurityException e) {
/* 65 */           IOException ioe = new IOException(
/* 66 */             "could not create SSL socket");
/* 67 */           ioe.initCause(e);
/* 68 */           throw ioe;
/*    */         }
/*    */       }
/* 71 */       return sslFactory;
/*    */     }
/* 73 */     if (factory == null) {
/* 74 */       factory = new SslServerSocketFactory();
/*    */     }
/* 76 */     return factory;
/*    */   }
/*    */   
/*    */ 
/*    */   public static boolean isSslEnabled()
/*    */   {
/* 82 */     return sslEnabled;
/*    */   }
/*    */   
/*    */   public static void setSslEnabled(boolean newSslEnabled) {
/* 86 */     sslEnabled = newSslEnabled;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\SslServerSocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */