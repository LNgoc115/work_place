/*     */ package com.viettel.ipcc.agentserver.dao;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import org.hibernate.HibernateException;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ import org.hibernate.cfg.AnnotationConfiguration;
/*     */ import org.hibernate.cfg.Configuration;
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
/*     */ public class HibernateSessionFactory
/*     */ {
/*  25 */   private static String CONFIG_FILE_LOCATION = "../etc/hibernate.cfg.xml";
/*  26 */   private static final ThreadLocal<Session> threadLocal = new ThreadLocal();
/*  27 */   private static Configuration configuration = new AnnotationConfiguration();
/*     */   private static SessionFactory sessionFactory;
/*  29 */   private static String configFile = CONFIG_FILE_LOCATION;
/*     */   
/*     */   static {
/*     */     try {
/*  33 */       configuration.configure(new File(configFile));
/*  34 */       sessionFactory = configuration.buildSessionFactory();
/*     */     }
/*     */     catch (Exception e) {
/*  37 */       System.err.println("%%%% Error Creating SessionFactory %%%%");
/*  38 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Session getSession()
/*     */     throws HibernateException
/*     */   {
/*  52 */     Session session = (Session)threadLocal.get();
/*     */     
/*  54 */     if ((session == null) || (!session.isOpen())) {
/*  55 */       if (sessionFactory == null) {
/*  56 */         rebuildSessionFactory();
/*     */       }
/*  58 */       session = sessionFactory != null ? sessionFactory.openSession() : 
/*  59 */         null;
/*  60 */       threadLocal.set(session);
/*     */     }
/*     */     
/*  63 */     return session;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void rebuildSessionFactory()
/*     */   {
/*     */     try
/*     */     {
/*  72 */       configuration.configure(new File(configFile));
/*  73 */       sessionFactory = configuration.buildSessionFactory();
/*     */     }
/*     */     catch (Exception e) {
/*  76 */       System.err.println("%%%% Error Creating SessionFactory %%%%");
/*  77 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void closeSession()
/*     */     throws HibernateException
/*     */   {
/*  87 */     Session session = (Session)threadLocal.get();
/*  88 */     threadLocal.set(null);
/*     */     
/*  90 */     if (session != null) {
/*  91 */       session.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static SessionFactory getSessionFactory()
/*     */   {
/* 100 */     return sessionFactory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setConfigFile(String configFile)
/*     */   {
/* 109 */     configFile = configFile;
/* 110 */     sessionFactory = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Configuration getConfiguration()
/*     */   {
/* 118 */     return configuration;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\HibernateSessionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */