/*     */ package com.viettel.ipcc.agentserver.listener.sems;
/*     */ 
/*     */ import com.viettel.ipcc.agentserver.common.AcdThread;
/*     */ import com.viettel.ipcc.agentserver.common.JobBase;
/*     */ import com.viettel.ipcc.agentserver.common.JobProcessorBase;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.jms.Connection;
/*     */ import javax.jms.ConnectionFactory;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.MessageProducer;
/*     */ import javax.jms.Session;
/*     */ import org.apache.activemq.ActiveMQConnectionFactory;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OmapCdrSender
/*     */   extends JobProcessorBase
/*     */ {
/*     */   private String brokerUrl;
/*     */   private String queuename;
/*  27 */   private Connection connection = null;
/*     */   
/*  29 */   private Destination destination = null;
/*     */   
/*  31 */   private Session session = null;
/*     */   
/*  33 */   private MessageProducer messageProducer = null;
/*     */   
/*  35 */   private boolean isStarted = false;
/*     */   private List<AcdThread> listThread;
/*  37 */   private Logger logger = Logger.getLogger(OmapCdrSender.class);
/*     */   
/*     */   private synchronized void closeConnection() {
/*  40 */     this.logger.info("close connection");
/*  41 */     if (this.messageProducer != null) {
/*     */       try {
/*  43 */         this.messageProducer.close();
/*     */       }
/*     */       catch (JMSException e) {
/*  46 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  49 */     this.messageProducer = null;
/*     */     
/*  51 */     if (this.session != null) {
/*     */       try {
/*  53 */         this.session.close();
/*     */       }
/*     */       catch (JMSException e) {
/*  56 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  59 */     this.session = null;
/*     */     
/*  61 */     if (this.connection != null) {
/*     */       try {
/*  63 */         this.connection.close();
/*     */       }
/*     */       catch (JMSException e) {
/*  66 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     
/*  70 */     this.connection = null;
/*     */   }
/*     */   
/*     */   public void start() {
/*  74 */     if (this.isStarted) {
/*  75 */       return;
/*     */     }
/*  77 */     this.isStarted = true;
/*     */     
/*  79 */     this.listThread = new ArrayList();
/*  80 */     AcdThread thread = new AcdThread("thread-send-cdr-to-omap", this);
/*  81 */     this.listThread.add(thread);
/*     */     
/*  83 */     thread.start();
/*  84 */     this.logger.info(thread.getName() + " started!");
/*     */   }
/*     */   
/*     */   public void stop()
/*     */   {
/*  89 */     if (!this.isStarted) {
/*  90 */       return;
/*     */     }
/*  92 */     this.isStarted = false;
/*     */     
/*  94 */     for (AcdThread thread : this.listThread) {
/*  95 */       thread.setRunning(false);
/*  96 */       this.logger.info(thread.getName() + " stopped!");
/*     */     }
/*     */     
/*     */ 
/* 100 */     process();
/* 101 */     closeConnection();
/*     */   }
/*     */   
/*     */   private synchronized void openConnection() {
/* 105 */     this.logger.info("open connection : brokerUrl=" + this.brokerUrl + " queueName=" + this.queuename);
/* 106 */     ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
/* 107 */       this.brokerUrl);
/*     */     try {
/* 109 */       this.connection = connectionFactory.createConnection(null, null);
/* 110 */       this.session = this.connection.createSession(false, 1);
/* 111 */       this.destination = this.session.createQueue(this.queuename);
/*     */       
/* 113 */       this.messageProducer = this.session.createProducer(this.destination);
/* 114 */       this.messageProducer.setDeliveryMode(1);
/*     */     }
/*     */     catch (JMSException e)
/*     */     {
/* 118 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private void _sendMessage(String msg)
/*     */   {
/*     */     try {
/* 125 */       if (this.session == null) {
/* 126 */         openConnection();
/*     */       }
/*     */       
/* 129 */       Message message = this.session.createTextMessage(msg);
/* 130 */       this.logger.info("send to omap: " + msg);
/* 131 */       this.messageProducer.send(message);
/*     */     }
/*     */     catch (Exception ex) {
/* 134 */       closeConnection();
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendMessage(String msg) {
/* 139 */     SendOmapJob job = new SendOmapJob(Utils.genJobID());
/* 140 */     job.setMsg(msg);
/* 141 */     addJob(job);
/*     */   }
/*     */   
/*     */   public void setBrokerUrl(String brokerUrl) {
/* 145 */     this.brokerUrl = brokerUrl;
/*     */   }
/*     */   
/*     */   public String getBrokerUrl() {
/* 149 */     return this.brokerUrl;
/*     */   }
/*     */   
/*     */   public void setQueuename(String queuename) {
/* 153 */     this.queuename = queuename;
/*     */   }
/*     */   
/*     */   public String getQueuename() {
/* 157 */     return this.queuename;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 161 */     OmapCdrSender m = new OmapCdrSender();
/* 162 */     m.setBrokerUrl("tcp://192.168.133.152:61617");
/* 163 */     m.setQueuename("QUEUE4CDR");
/* 164 */     for (int i = 0; i < 1; i++) {
/* 165 */       m._sendMessage("AGENTENDCALL;20110324143724-FBYNPEXD-1;tungnt37;Callee;2011-03-24 14:37:56;20110324143724-FBYNPEXD-1-1");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void processJob(List<JobBase> listJob)
/*     */   {
/* 174 */     for (JobBase j : listJob) {
/* 175 */       SendOmapJob job = (SendOmapJob)j;
/*     */       
/* 177 */       _sendMessage(job.getMsg());
/*     */     }
/*     */   }
/*     */   
/*     */   class SendOmapJob extends JobBase
/*     */   {
/*     */     private String msg;
/*     */     
/*     */     public SendOmapJob(Long jobID) {
/* 186 */       super();
/*     */     }
/*     */     
/*     */     public void setMsg(String msg)
/*     */     {
/* 191 */       this.msg = msg;
/*     */     }
/*     */     
/*     */     public String getMsg() {
/* 195 */       return this.msg;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\OmapCdrSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */