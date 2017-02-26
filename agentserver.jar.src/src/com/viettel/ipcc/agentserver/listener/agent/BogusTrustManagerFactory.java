/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import java.security.InvalidAlgorithmParameterException;
/*    */ import java.security.KeyStore;
/*    */ import java.security.KeyStoreException;
/*    */ import java.security.cert.CertificateException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.ManagerFactoryParameters;
/*    */ import javax.net.ssl.TrustManager;
/*    */ import javax.net.ssl.TrustManagerFactorySpi;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class BogusTrustManagerFactory
/*    */   extends TrustManagerFactorySpi
/*    */ {
/* 28 */   static final X509TrustManager X509 = new X509TrustManager()
/*    */   {
/*    */     public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
/*    */       throws CertificateException
/*    */     {}
/*    */     
/*    */     public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException
/*    */     {}
/*    */     
/*    */     public X509Certificate[] getAcceptedIssuers()
/*    */     {
/* 39 */       return new X509Certificate[0];
/*    */     }
/*    */   };
/*    */   
/*    */ 
/*    */ 
/* 45 */   static final TrustManager[] X509_MANAGERS = { X509 };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected TrustManager[] engineGetTrustManagers()
/*    */   {
/* 52 */     return X509_MANAGERS;
/*    */   }
/*    */   
/*    */   protected void engineInit(KeyStore keystore)
/*    */     throws KeyStoreException
/*    */   {}
/*    */   
/*    */   protected void engineInit(ManagerFactoryParameters managerFactoryParameters)
/*    */     throws InvalidAlgorithmParameterException
/*    */   {}
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\BogusTrustManagerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */