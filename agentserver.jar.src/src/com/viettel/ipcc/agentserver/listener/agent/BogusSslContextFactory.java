/*     */ package com.viettel.ipcc.agentserver.listener.agent;
/*     */ 
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.Security;
/*     */ import javax.net.ssl.KeyManagerFactory;
/*     */ import javax.net.ssl.SSLContext;
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
/*     */ public class BogusSslContextFactory
/*     */ {
/*     */   private static final String PROTOCOL = "TLS";
/*     */   private static final String KEY_MANAGER_FACTORY_ALGORITHM;
/*     */   private static final String BOGUS_KEYSTORE = "bogus.cert";
/*     */   
/*     */   static
/*     */   {
/*  36 */     String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
/*  37 */     if (algorithm == null) {
/*  38 */       algorithm = KeyManagerFactory.getDefaultAlgorithm();
/*     */     }
/*     */     
/*  41 */     KEY_MANAGER_FACTORY_ALGORITHM = algorithm;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  50 */   private static final char[] BOGUS_PW = { 'b', 'o', 'g', 'u', 's', 'p', 'w' };
/*     */   
/*     */ 
/*     */ 
/*  54 */   private static SSLContext serverInstance = null;
/*     */   
/*     */ 
/*     */ 
/*  58 */   private static SSLContext clientInstance = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static SSLContext getInstance(boolean server)
/*     */     throws GeneralSecurityException
/*     */   {
/*  67 */     SSLContext retInstance = null;
/*  68 */     if (server) {
/*  69 */       synchronized (BogusSslContextFactory.class) {
/*  70 */         if (serverInstance == null) {
/*     */           try {
/*  72 */             serverInstance = createBougusServerSslContext();
/*     */           } catch (Exception ioe) {
/*  74 */             throw new GeneralSecurityException(
/*  75 */               "Can't create Server SSLContext:" + ioe);
/*     */           }
/*     */         }
/*     */       }
/*  79 */       retInstance = serverInstance;
/*     */     } else {
/*  81 */       synchronized (BogusSslContextFactory.class) {
/*  82 */         if (clientInstance == null) {
/*  83 */           clientInstance = createBougusClientSslContext();
/*     */         }
/*     */       }
/*  86 */       retInstance = clientInstance;
/*     */     }
/*  88 */     return retInstance;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   private static SSLContext createBougusServerSslContext()
/*     */     throws GeneralSecurityException, java.io.IOException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: ldc 97
/*     */     //   2: invokestatic 99	java/security/KeyStore:getInstance	(Ljava/lang/String;)Ljava/security/KeyStore;
/*     */     //   5: astore_0
/*     */     //   6: aconst_null
/*     */     //   7: astore_1
/*     */     //   8: ldc 1
/*     */     //   10: ldc 12
/*     */     //   12: invokevirtual 104	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
/*     */     //   15: astore_1
/*     */     //   16: aload_0
/*     */     //   17: aload_1
/*     */     //   18: getstatic 38	com/viettel/ipcc/agentserver/listener/agent/BogusSslContextFactory:BOGUS_PW	[C
/*     */     //   21: invokevirtual 108	java/security/KeyStore:load	(Ljava/io/InputStream;[C)V
/*     */     //   24: goto +22 -> 46
/*     */     //   27: astore_2
/*     */     //   28: aload_1
/*     */     //   29: ifnull +15 -> 44
/*     */     //   32: aload_1
/*     */     //   33: invokevirtual 112	java/io/InputStream:close	()V
/*     */     //   36: goto +8 -> 44
/*     */     //   39: astore_3
/*     */     //   40: aload_3
/*     */     //   41: invokevirtual 117	java/io/IOException:printStackTrace	()V
/*     */     //   44: aload_2
/*     */     //   45: athrow
/*     */     //   46: aload_1
/*     */     //   47: ifnull +15 -> 62
/*     */     //   50: aload_1
/*     */     //   51: invokevirtual 112	java/io/InputStream:close	()V
/*     */     //   54: goto +8 -> 62
/*     */     //   57: astore_3
/*     */     //   58: aload_3
/*     */     //   59: invokevirtual 117	java/io/IOException:printStackTrace	()V
/*     */     //   62: getstatic 36	com/viettel/ipcc/agentserver/listener/agent/BogusSslContextFactory:KEY_MANAGER_FACTORY_ALGORITHM	Ljava/lang/String;
/*     */     //   65: invokestatic 120	javax/net/ssl/KeyManagerFactory:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/KeyManagerFactory;
/*     */     //   68: astore_2
/*     */     //   69: aload_2
/*     */     //   70: aload_0
/*     */     //   71: getstatic 38	com/viettel/ipcc/agentserver/listener/agent/BogusSslContextFactory:BOGUS_PW	[C
/*     */     //   74: invokevirtual 123	javax/net/ssl/KeyManagerFactory:init	(Ljava/security/KeyStore;[C)V
/*     */     //   77: ldc 8
/*     */     //   79: invokestatic 127	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
/*     */     //   82: astore_3
/*     */     //   83: aload_3
/*     */     //   84: aload_2
/*     */     //   85: invokevirtual 130	javax/net/ssl/KeyManagerFactory:getKeyManagers	()[Ljavax/net/ssl/KeyManager;
/*     */     //   88: getstatic 134	com/viettel/ipcc/agentserver/listener/agent/BogusTrustManagerFactory:X509_MANAGERS	[Ljavax/net/ssl/TrustManager;
/*     */     //   91: aconst_null
/*     */     //   92: invokevirtual 140	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
/*     */     //   95: aload_3
/*     */     //   96: areturn
/*     */     // Line number table:
/*     */     //   Java source line #94	-> byte code offset #0
/*     */     //   Java source line #95	-> byte code offset #6
/*     */     //   Java source line #97	-> byte code offset #8
/*     */     //   Java source line #98	-> byte code offset #16
/*     */     //   Java source line #99	-> byte code offset #27
/*     */     //   Java source line #100	-> byte code offset #28
/*     */     //   Java source line #102	-> byte code offset #32
/*     */     //   Java source line #103	-> byte code offset #39
/*     */     //   Java source line #104	-> byte code offset #40
/*     */     //   Java source line #107	-> byte code offset #44
/*     */     //   Java source line #100	-> byte code offset #46
/*     */     //   Java source line #102	-> byte code offset #50
/*     */     //   Java source line #103	-> byte code offset #57
/*     */     //   Java source line #104	-> byte code offset #58
/*     */     //   Java source line #110	-> byte code offset #62
/*     */     //   Java source line #111	-> byte code offset #69
/*     */     //   Java source line #114	-> byte code offset #77
/*     */     //   Java source line #115	-> byte code offset #83
/*     */     //   Java source line #116	-> byte code offset #88
/*     */     //   Java source line #115	-> byte code offset #92
/*     */     //   Java source line #118	-> byte code offset #95
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   5	66	0	ks	java.security.KeyStore
/*     */     //   7	44	1	in	java.io.InputStream
/*     */     //   27	18	2	localObject	Object
/*     */     //   68	17	2	kmf	KeyManagerFactory
/*     */     //   39	2	3	ignored	java.io.IOException
/*     */     //   57	2	3	ignored	java.io.IOException
/*     */     //   82	14	3	sslContext	SSLContext
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   8	27	27	finally
/*     */     //   32	36	39	java/io/IOException
/*     */     //   50	54	57	java/io/IOException
/*     */   }
/*     */   
/*     */   private static SSLContext createBougusClientSslContext()
/*     */     throws GeneralSecurityException
/*     */   {
/* 123 */     SSLContext context = SSLContext.getInstance("TLS");
/* 124 */     context.init(null, BogusTrustManagerFactory.X509_MANAGERS, null);
/* 125 */     return context;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\BogusSslContextFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */