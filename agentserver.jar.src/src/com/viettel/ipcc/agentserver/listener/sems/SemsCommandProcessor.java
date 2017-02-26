/*     */ package com.viettel.ipcc.agentserver.listener.sems;
/*     */ 
/*     */ import com.viettel.ipcc.agentserver.common.JobBase;
/*     */ import com.viettel.ipcc.agentserver.common.JobProcessorBase;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.AgentAnswerProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.AgentEndcallProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.AgentNotAnsweredProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.AgentRejectCallProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.CalloutAgentConnectedProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.CalloutAgentDisconnectedProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.CalloutCustomerAnswerProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.CalloutCustomerEndcallProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.CalloutCustomerRingingProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.DisagentProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.EndCallProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.ForwardOmapProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.IvrForwardProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.RingingProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.RouteToQueueProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.SemsCrashProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.SemsProcessorBase;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.SemsTransferProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.TypeCallerProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.UserCallProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.UserEndcallProcessor;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class SemsCommandProcessor
/*     */   extends JobProcessorBase
/*     */ {
/*     */   public static final String USERCALL_CMD = "USERCALL";
/*     */   public static final String ROUTEUSERTOQUEUE_CMD = "ROUTEUSERTOQUEUE";
/*     */   public static final String RINGAGENT_CMD = "RINGAGENT";
/*     */   public static final String AGENTANSWERUSER_CMD = "AGENTANSWERUSER";
/*     */   public static final String AGENTENDCALL_CMD = "AGENTENDCALL";
/*     */   public static final String USERENDCALL_CMD = "USERENDCALL";
/*     */   public static final String ENDCALL_CMD = "ENDCALL";
/*     */   public static final String NOTANSWERING_CMD = "NOTANSWERING";
/*     */   public static final String REJECTCALL_CMD = "REJECTCALL";
/*     */   public static final String MUTEEVENT_CMD = "MUTEEVENT";
/*     */   public static final String UNMUTEEVENT_CMD = "UNMUTEEVENT";
/*     */   public static final String HOLDEVENT_CMD = "HOLDEVENT";
/*     */   public static final String UNHOLDEVENT_CMD = "UNHOLDEVENT";
/*     */   public static final String SPYEVENT_CMD = "SPYEVENT";
/*     */   public static final String DISAGENTEVENT_CMD = "DISAGENTEVENT";
/*     */   public static final String TRANSFEREVENT_CMD = "TRANSFEREVENT";
/*     */   public static final String DROPCALL_CMD = "DROPCALL";
/*     */   public static final String INVITEEVENT_CMD = "INVITEEVENT";
/*     */   public static final String COMETO_IVR_CMD = "COMETOIVR";
/*     */   public static final String COMEOUT_IVR_CMD = "OUTOFIVR";
/*     */   public static final String SEMSCRASH_CMD = "SEMSCRASH";
/*     */   public static final String CALLOUTUSERCONNECTED_CMD = "CALLOUTUSERCONNECTED";
/*     */   public static final String CALLOUTUSERDISCONNECTED_CMD = "CALLOUTUSERDISCONNECTED";
/*     */   public static final String OUTCALLRING_CMD = "OUTCALLRING";
/*     */   public static final String OUTCALLANSWER_CMD = "OUTCALLANSWER";
/*     */   public static final String OUTCALLSTOP_CMD = "OUTCALLSTOP";
/*     */   public static final String PROCESSTYPECALLER_CMD = "PROCESSTYPECALLER";
/*     */   public static final String COMMAND_DELIM = ";";
/*  64 */   private Logger logger = Logger.getLogger(SemsCommandProcessor.class);
/*     */   private Map<String, SemsProcessorBase> mapCmdProcessor;
/*     */   
/*     */   public SemsCommandProcessor()
/*     */   {
/*  69 */     this.mapCmdProcessor = new HashMap();
/*  70 */     this.mapCmdProcessor.put("USERCALL", new UserCallProcessor());
/*  71 */     this.mapCmdProcessor.put("ROUTEUSERTOQUEUE", new RouteToQueueProcessor());
/*  72 */     this.mapCmdProcessor.put("RINGAGENT", new RingingProcessor());
/*  73 */     this.mapCmdProcessor.put("AGENTANSWERUSER", new AgentAnswerProcessor());
/*  74 */     this.mapCmdProcessor.put("AGENTENDCALL", new AgentEndcallProcessor());
/*  75 */     this.mapCmdProcessor.put("USERENDCALL", new UserEndcallProcessor());
/*  76 */     this.mapCmdProcessor.put("ENDCALL", new EndCallProcessor());
/*  77 */     this.mapCmdProcessor.put("TRANSFEREVENT", new SemsTransferProcessor());
/*  78 */     this.mapCmdProcessor.put("DISAGENTEVENT", new DisagentProcessor());
/*     */     
/*  80 */     this.mapCmdProcessor.put("NOTANSWERING", new AgentNotAnsweredProcessor());
/*  81 */     this.mapCmdProcessor.put("REJECTCALL", new AgentRejectCallProcessor());
/*     */     
/*  83 */     this.mapCmdProcessor.put("MUTEEVENT", new ForwardOmapProcessor());
/*  84 */     this.mapCmdProcessor.put("UNMUTEEVENT", new ForwardOmapProcessor());
/*  85 */     this.mapCmdProcessor.put("HOLDEVENT", new ForwardOmapProcessor());
/*  86 */     this.mapCmdProcessor.put("UNHOLDEVENT", new ForwardOmapProcessor());
/*  87 */     this.mapCmdProcessor.put("SPYEVENT", new ForwardOmapProcessor());
/*  88 */     this.mapCmdProcessor.put("DROPCALL", new ForwardOmapProcessor());
/*  89 */     this.mapCmdProcessor.put("INVITEEVENT", new ForwardOmapProcessor());
/*     */     
/*  91 */     this.mapCmdProcessor.put("COMETOIVR", new IvrForwardProcessor());
/*  92 */     this.mapCmdProcessor.put("OUTOFIVR", new IvrForwardProcessor());
/*  93 */     this.mapCmdProcessor.put("SEMSCRASH", new SemsCrashProcessor());
/*     */     
/*  95 */     this.mapCmdProcessor.put("CALLOUTUSERCONNECTED", 
/*  96 */       new CalloutAgentConnectedProcessor());
/*     */     
/*  98 */     this.mapCmdProcessor.put("CALLOUTUSERDISCONNECTED", 
/*  99 */       new CalloutAgentDisconnectedProcessor());
/*     */     
/* 101 */     this.mapCmdProcessor.put("OUTCALLRING", 
/* 102 */       new CalloutCustomerRingingProcessor());
/*     */     
/* 104 */     this.mapCmdProcessor.put("OUTCALLANSWER", 
/* 105 */       new CalloutCustomerAnswerProcessor());
/*     */     
/* 107 */     this.mapCmdProcessor.put("OUTCALLSTOP", 
/* 108 */       new CalloutCustomerEndcallProcessor());
/*     */     
/* 110 */     this.mapCmdProcessor.put("PROCESSTYPECALLER", new TypeCallerProcessor());
/*     */   }
/*     */   
/*     */   private void processJob(SemsCommandJob job) {
/* 114 */     String cmd = job.getSemsCommand().substring(0, 
/* 115 */       job.getSemsCommand().indexOf(';'));
/* 116 */     SemsProcessorBase processor = (SemsProcessorBase)this.mapCmdProcessor.get(cmd);
/* 117 */     this.logger.info("process sems cmd: " + job.getSemsCommand());
/* 118 */     if (processor != null) {
/* 119 */       processor.process(job);
/*     */     } else {
/* 121 */       this.logger.error("can't find processor for sems cmd: " + 
/* 122 */         job.getSemsCommand());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void processJob(List<JobBase> listJob)
/*     */   {
/* 129 */     for (JobBase j : listJob) {
/*     */       try {
/* 131 */         processJob((SemsCommandJob)j);
/*     */       } catch (Exception ex) {
/* 133 */         ex.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 139 */     String str = "xxx;234892384";
/* 140 */     System.out.println(str.substring(0, str.indexOf(';')));
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\sems\SemsCommandProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */