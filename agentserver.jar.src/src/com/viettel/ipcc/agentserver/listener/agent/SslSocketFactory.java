/*     */ package com.viettel.ipcc.agentserver.listener.agent;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.security.GeneralSecurityException;
/*     */ import javax.net.SocketFactory;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SslSocketFactory
/*     */   extends SocketFactory
/*     */ {
/*  24 */   private static boolean sslEnabled = false;
/*     */   
/*     */ 
/*     */ 
/*  28 */   private static SSLSocketFactory sslFactory = null;
/*     */   
/*     */ 
/*     */ 
/*  32 */   private static SocketFactory factory = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Socket createSocket(String arg1, int arg2)
/*     */     throws IOException
/*     */   {
/*  43 */     if (isSslEnabled()) {
/*  44 */       return getSSLFactory().createSocket(arg1, arg2);
/*     */     }
/*  46 */     return new Socket(arg1, arg2);
/*     */   }
/*     */   
/*     */ 
/*     */   public Socket createSocket(String arg1, int arg2, InetAddress arg3, int arg4)
/*     */     throws IOException
/*     */   {
/*  53 */     if (isSslEnabled()) {
/*  54 */       return getSSLFactory().createSocket(arg1, arg2, arg3, arg4);
/*     */     }
/*  56 */     return new Socket(arg1, arg2, arg3, arg4);
/*     */   }
/*     */   
/*     */   public Socket createSocket(InetAddress arg1, int arg2)
/*     */     throws IOException
/*     */   {
/*  62 */     if (isSslEnabled()) {
/*  63 */       return getSSLFactory().createSocket(arg1, arg2);
/*     */     }
/*  65 */     return new Socket(arg1, arg2);
/*     */   }
/*     */   
/*     */ 
/*     */   public Socket createSocket(InetAddress arg1, int arg2, InetAddress arg3, int arg4)
/*     */     throws IOException
/*     */   {
/*  72 */     if (isSslEnabled()) {
/*  73 */       return getSSLFactory().createSocket(arg1, arg2, arg3, arg4);
/*     */     }
/*  75 */     return new Socket(arg1, arg2, arg3, arg4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static SocketFactory getSocketFactory()
/*     */   {
/*  83 */     if (factory == null) {
/*  84 */       factory = new SslSocketFactory();
/*     */     }
/*  86 */     return factory;
/*     */   }
/*     */   
/*     */   private SSLSocketFactory getSSLFactory() {
/*  90 */     if (sslFactory == null) {
/*     */       try {
/*  92 */         sslFactory = BogusSslContextFactory.getInstance(false).getSocketFactory();
/*     */       } catch (GeneralSecurityException e) {
/*  94 */         throw new RuntimeException("could not create SSL socket", e);
/*     */       }
/*     */     }
/*  97 */     return sslFactory;
/*     */   }
/*     */   
/*     */   public static boolean isSslEnabled() {
/* 101 */     return sslEnabled;
/*     */   }
/*     */   
/*     */   public static void setSslEnabled(boolean newSslEnabled) {
/* 105 */     sslEnabled = newSslEnabled;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\SslSocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */