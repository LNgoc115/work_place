/*     */ package com.viettel.ipcc.agentserver.listener.agent;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.MessageBase;
/*     */ import com.viettel.ipcc.agentserver.common.JobBase;
/*     */ import com.viettel.ipcc.agentserver.common.JobProcessorBase;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.AddSpyedAgentProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.CalloutRequestProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.ChangeAgentStatusProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.ChangeShiftProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.ChangeSystemStatusProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.DeleteSpyedAgentProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.GetSystemInfoProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.HoldProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.InviteProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.LoginProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.LogoutProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.MonitoringProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.MuteProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.PingProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.ProcessorBase;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.QueryZoonProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.RemoveAgentFromCallProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.SearchAgentProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.ShiftLeaderSearchAgentProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.ShiftLeaderStatAgentProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.SpyProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.StartMonitoringSpyProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.StopMonitoringSpyProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.SupervisorSearchAgentProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.SupportProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.TerminalCallProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.TerminateCalloutProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.TransferProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.UnholdProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.UnmuteProcessor;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.processors.ValidateCallProcessor;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class AgentDesktopRequestProcessor
/*     */   extends JobProcessorBase
/*     */ {
/*  49 */   private Logger logger = Logger.getLogger(AgentDesktopRequestProcessor.class);
/*     */   private Map<Integer, ProcessorBase> mapIDProcessor;
/*     */   
/*     */   public AgentDesktopRequestProcessor() {
/*  53 */     this.mapIDProcessor = new HashMap();
/*  54 */     this.mapIDProcessor.put(Integer.valueOf(2000), 
/*  55 */       new LoginProcessor());
/*  56 */     this.mapIDProcessor.put(Integer.valueOf(2014), 
/*  57 */       new LogoutProcessor());
/*  58 */     this.mapIDProcessor.put(Integer.valueOf(2013), 
/*  59 */       new ChangeAgentStatusProcessor());
/*  60 */     this.mapIDProcessor.put(Integer.valueOf(2005), 
/*  61 */       new ChangeShiftProcessor());
/*  62 */     this.mapIDProcessor
/*  63 */       .put(Integer.valueOf(2015), new HoldProcessor());
/*  64 */     this.mapIDProcessor.put(Integer.valueOf(2016), 
/*  65 */       new UnholdProcessor());
/*  66 */     this.mapIDProcessor.put(Integer.valueOf(2007), 
/*  67 */       new InviteProcessor());
/*  68 */     this.mapIDProcessor.put(Integer.valueOf(2001), 
/*  69 */       new MonitoringProcessor());
/*  70 */     this.mapIDProcessor.put(Integer.valueOf(2017), 
/*  71 */       new UnmuteProcessor());
/*     */     
/*  73 */     this.mapIDProcessor
/*  74 */       .put(Integer.valueOf(2018), new MuteProcessor());
/*     */     
/*  76 */     this.mapIDProcessor.put(Integer.valueOf(2012), 
/*  77 */       new PingProcessor());
/*     */     
/*  79 */     this.mapIDProcessor.put(Integer.valueOf(2004), 
/*  80 */       new SearchAgentProcessor());
/*  81 */     this.mapIDProcessor.put(Integer.valueOf(2011), new SpyProcessor());
/*  82 */     this.mapIDProcessor.put(Integer.valueOf(2003), 
/*  83 */       new SupportProcessor());
/*  84 */     this.mapIDProcessor.put(Integer.valueOf(2009), 
/*  85 */       new TerminalCallProcessor());
/*  86 */     this.mapIDProcessor.put(Integer.valueOf(2010), 
/*  87 */       new TransferProcessor());
/*     */     
/*  89 */     this.mapIDProcessor.put(Integer.valueOf(2019), 
/*  90 */       new RemoveAgentFromCallProcessor());
/*     */     
/*  92 */     this.mapIDProcessor.put(
/*  93 */       Integer.valueOf(2020), 
/*  94 */       new ShiftLeaderSearchAgentProcessor());
/*     */     
/*  96 */     this.mapIDProcessor.put(Integer.valueOf(2021), 
/*  97 */       new ShiftLeaderStatAgentProcessor());
/*     */     
/*  99 */     this.mapIDProcessor.put(Integer.valueOf(2022), 
/* 100 */       new QueryZoonProcessor());
/*     */     
/* 102 */     this.mapIDProcessor.put(Integer.valueOf(2023), 
/* 103 */       new ChangeSystemStatusProcessor());
/*     */     
/* 105 */     this.mapIDProcessor.put(Integer.valueOf(2024), 
/* 106 */       new SupervisorSearchAgentProcessor());
/*     */     
/* 108 */     this.mapIDProcessor.put(Integer.valueOf(2027), 
/* 109 */       new AddSpyedAgentProcessor());
/*     */     
/* 111 */     this.mapIDProcessor.put(Integer.valueOf(2026), 
/* 112 */       new DeleteSpyedAgentProcessor());
/*     */     
/* 114 */     this.mapIDProcessor.put(Integer.valueOf(2029), 
/* 115 */       new StartMonitoringSpyProcessor());
/*     */     
/* 117 */     this.mapIDProcessor.put(Integer.valueOf(2028), 
/* 118 */       new StopMonitoringSpyProcessor());
/*     */     
/* 120 */     this.mapIDProcessor.put(Integer.valueOf(2030), 
/* 121 */       new GetSystemInfoProcessor());
/* 122 */     this.mapIDProcessor.put(Integer.valueOf(2031), 
/* 123 */       new ValidateCallProcessor());
/*     */     
/* 125 */     this.mapIDProcessor.put(Integer.valueOf(2032), 
/* 126 */       new CalloutRequestProcessor());
/*     */     
/* 128 */     this.mapIDProcessor.put(Integer.valueOf(2033), 
/* 129 */       new TerminateCalloutProcessor());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void processJob(List<JobBase> listJob)
/*     */   {
/* 136 */     for (JobBase j : listJob) {
/*     */       try {
/* 138 */         AgentDesktopJob job = (AgentDesktopJob)j;
/* 139 */         process(job);
/*     */       } catch (Exception ex) {
/* 141 */         ex.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void process(AgentDesktopJob job)
/*     */   {
/* 148 */     MessageBase msg = job.getAgentDesktopMsg();
/*     */     
/* 150 */     ProcessorBase processor = (ProcessorBase)this.mapIDProcessor.get(Integer.valueOf(msg.getObjectId()));
/* 151 */     if (processor != null) {
/* 152 */       processor.process(job);
/*     */     } else {
/* 154 */       this.logger.info("can't find processor for message type: " + 
/* 155 */         msg.getClass().getName());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 161 */     Pattern p = Pattern.compile("GROUP[0-9]+");
/* 162 */     System.out.println(p.matcher("GROUPx123").matches());
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\AgentDesktopRequestProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */