/*    */ package com.viettel.ipcc.agentserver.listener.sce;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.common.JobBase;
/*    */ import com.viettel.ipcc.agentserver.common.JobProcessorBase;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.processor.SceAgentDesktopRequestManagerProcessor;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.processor.SceAgentServerListenerProcessor;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.processor.SceAgentsManagerProcessor;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.processor.SceCallManagerProcessor;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.processor.SceDbUpdaterProcessor;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.processor.SceOmapCdrSenderProcessor;
/*    */ import com.viettel.ipcc.agentserver.listener.sce.processor.SceSemsCommandProcessorManagerProcessor;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class SceCommandProcessor
/*    */   extends JobProcessorBase
/*    */ {
/*    */   public static final String QUEUE_NAME = "queueName";
/*    */   public static final String BROKER_URL = "brokerUrl";
/*    */   public static final String NUM_THREAD_AGENT = "numThreadAgent";
/*    */   public static final String TIME_SLEEP_AGENT = "timeSleepAgent";
/*    */   public static final String MINA_PORT = "minaPort";
/*    */   public static final String NUM_THREAD_SEMS = "numThreadSems";
/*    */   public static final String DELAY_TIME_SEMS = "delayTimeSems";
/*    */   public static final String INTERVAL_CHECK_CONNECTION = "intervalCheckConnection";
/*    */   public static final String NUM_RETRY_PING = "numRetryPing";
/*    */   public static final String ANONYMOUS_TIMEOUT = "anonymousTimeout";
/*    */   public static final String MAX_NUMBER_REJECT_CALL = "maxNumberRejectCall";
/*    */   public static final String CALL_TIMEOUT = "callTimeOut";
/*    */   public static final String CHECK_CALL_INTERVAL = "checkCallInterval";
/*    */   public static final String NUM_THREAD_AGENT_UPDATER = "numThreadAgentUpdater";
/*    */   public static final String THREAD_AGENT_UPDATER_DELAY = "threadAgentUpdaterDelay";
/*    */   private HashMap<String, SceProcessorBase> mapProcessor;
/* 35 */   private static Logger logger = Logger.getLogger(SceCommandProcessor.class);
/*    */   
/*    */   public SceCommandProcessor() {
/* 38 */     this.mapProcessor = new HashMap();
/*    */     
/* 40 */     this.mapProcessor.put("queueName", new SceOmapCdrSenderProcessor());
/* 41 */     this.mapProcessor.put("brokerUrl", new SceOmapCdrSenderProcessor());
/* 42 */     this.mapProcessor.put("numThreadAgent", new SceAgentDesktopRequestManagerProcessor());
/* 43 */     this.mapProcessor.put("timeSleepAgent", new SceAgentDesktopRequestManagerProcessor());
/* 44 */     this.mapProcessor.put("minaPort", new SceAgentServerListenerProcessor());
/* 45 */     this.mapProcessor.put("numThreadSems", new SceSemsCommandProcessorManagerProcessor());
/* 46 */     this.mapProcessor.put("delayTimeSems", new SceSemsCommandProcessorManagerProcessor());
/* 47 */     this.mapProcessor.put("intervalCheckConnection", new SceAgentsManagerProcessor());
/* 48 */     this.mapProcessor.put("numRetryPing", new SceAgentsManagerProcessor());
/* 49 */     this.mapProcessor.put("anonymousTimeout", new SceAgentsManagerProcessor());
/* 50 */     this.mapProcessor.put("maxNumberRejectCall", new SceAgentsManagerProcessor());
/* 51 */     this.mapProcessor.put("callTimeOut", new SceCallManagerProcessor());
/* 52 */     this.mapProcessor.put("checkCallInterval", new SceCallManagerProcessor());
/* 53 */     this.mapProcessor.put("numThreadAgentUpdater", new SceDbUpdaterProcessor());
/* 54 */     this.mapProcessor.put("threadAgentUpdaterDelay", new SceDbUpdaterProcessor());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void processJob(List<JobBase> listJob)
/*    */   {
/* 61 */     for (JobBase job : listJob) {
/* 62 */       processSceJob((SceJob)job);
/*    */     }
/*    */   }
/*    */   
/*    */   public void processSceJob(SceJob job) {
/* 67 */     String cmd = job.getCommand().trim();
/* 68 */     SceProcessorBase processor = (SceProcessorBase)this.mapProcessor.get(cmd);
/* 69 */     if (processor != null) {
/* 70 */       processor.process(job);
/*    */     } else {
/* 72 */       logger.error("can't find processor for sce cmd: " + 
/* 73 */         job.getCommand());
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sce\SceCommandProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */