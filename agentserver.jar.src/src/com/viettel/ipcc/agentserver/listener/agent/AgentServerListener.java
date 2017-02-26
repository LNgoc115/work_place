/*     */ package com.viettel.ipcc.agentserver.listener.agent;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.codec.CodecFactory;
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.security.GeneralSecurityException;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
/*     */ import org.apache.mina.core.service.IoHandlerAdapter;
/*     */ import org.apache.mina.filter.codec.ProtocolCodecFilter;
/*     */ import org.apache.mina.filter.logging.LoggingFilter;
/*     */ import org.apache.mina.filter.ssl.SslFilter;
/*     */ import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AgentServerListener
/*     */ {
/*  23 */   private int nIdleTime = 1000;
/*     */   
/*     */   private int port;
/*     */   
/*     */   private IoHandlerAdapter handler;
/*  28 */   private final boolean USE_LOGGING_FILTER = false;
/*     */   
/*  30 */   private final boolean USE_SSL = false;
/*     */   
/*  32 */   private Logger logger = Logger.getLogger(AgentServerListener.class);
/*     */   NioSocketAcceptor acceptor;
/*  34 */   private boolean isStarted = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public NioSocketAcceptor start()
/*     */   {
/*  41 */     if (this.isStarted)
/*  42 */       return this.acceptor;
/*  43 */     this.isStarted = true;
/*     */     
/*     */ 
/*  46 */     NioSocketAcceptor acceptor = new NioSocketAcceptor();
/*  47 */     DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
/*     */     
/*     */ 
/*  50 */     filterChain.addLast("protocol", new ProtocolCodecFilter(
/*  51 */       new CodecFactory()));
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
/*  64 */     acceptor.setHandler(this.handler);
/*     */     
/*     */ 
/*     */ 
/*  68 */     acceptor.setReuseAddress(true);
/*     */     try
/*     */     {
/*  71 */       acceptor.bind(new InetSocketAddress(getPort()));
/*     */     } catch (IOException ex) {
/*  73 */       this.logger.error("bind port " + getPort() + " error");
/*  74 */       ex.printStackTrace();
/*     */     }
/*     */     
/*  77 */     this.logger.info("Listening on port " + getPort());
/*     */     
/*  79 */     this.acceptor = acceptor;
/*  80 */     return acceptor;
/*     */   }
/*     */   
/*     */   public void stop()
/*     */   {
/*  85 */     if (!this.isStarted)
/*  86 */       return;
/*  87 */     this.isStarted = false;
/*  88 */     this.logger.info("agentserver stopped!");
/*     */     
/*  90 */     this.acceptor.unbind();
/*  91 */     this.acceptor.dispose();
/*     */   }
/*     */   
/*     */   private void addSSLSupport(DefaultIoFilterChainBuilder chain)
/*     */   {
/*     */     try
/*     */     {
/*  98 */       SslFilter sslFilter = new SslFilter(BogusSslContextFactory.getInstance(true));
/*  99 */       chain.addLast("sslFilter", sslFilter);
/*     */     } catch (GeneralSecurityException ex) {
/* 101 */       this.logger.error("add ssl filter unsuccesfull");
/*     */     }
/*     */     
/* 104 */     this.logger.info("mina add ssl filter successfull");
/*     */   }
/*     */   
/*     */   private static void addLogger(DefaultIoFilterChainBuilder chain) {
/* 108 */     chain.addLast("logger", new LoggingFilter());
/*     */   }
/*     */   
/*     */   public void setHandler(IoHandlerAdapter handler)
/*     */   {
/* 113 */     this.handler = handler;
/*     */   }
/*     */   
/*     */   public IoHandlerAdapter getHandler() {
/* 117 */     return this.handler;
/*     */   }
/*     */   
/*     */   public void setnIdleTime(int nIdleTime) {
/* 121 */     this.nIdleTime = nIdleTime;
/*     */   }
/*     */   
/*     */   public int getnIdleTime() {
/* 125 */     return this.nIdleTime;
/*     */   }
/*     */   
/*     */   public void setPort(int port) {
/* 129 */     this.port = port;
/*     */   }
/*     */   
/*     */   public int getPort() {
/* 133 */     return this.port;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\AgentServerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */