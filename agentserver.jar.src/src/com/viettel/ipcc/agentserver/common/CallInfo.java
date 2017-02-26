/*     */ package com.viettel.ipcc.agentserver.common;
/*     */ 
/*     */ import com.viettel.ipcc.agentserver.listener.sems.processors.SemsProcessorBase;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ public class CallInfo
/*     */ {
/*     */   private volatile String callID;
/*     */   private volatile Date startTime;
/*     */   private volatile long fisrtAnswerTime;
/*     */   private volatile List<Agents> agentsInCall;
/*     */   private volatile String groupID;
/*     */   private volatile String queueid;
/*     */   private volatile String called;
/*     */   private volatile String caller;
/*     */   private volatile String semsUrl;
/*  24 */   private volatile boolean isAnswered = false;
/*  25 */   private volatile boolean isFirstRingTime = true;
/*     */   private volatile Set<Agents> setAgentSentNewCall;
/*     */   
/*     */   public CallInfo() {
/*  29 */     setAgentsInCall(Collections.synchronizedList(new LinkedList()));
/*  30 */     setSetAgentSentNewCall(
/*  31 */       Collections.synchronizedSet(new HashSet()));
/*     */   }
/*     */   
/*     */   public void setGroupID(String groupID) {
/*  35 */     this.groupID = groupID;
/*     */   }
/*     */   
/*     */   public String getGroupID() {
/*  39 */     return this.groupID;
/*     */   }
/*     */   
/*     */   public void setStartTime(Date startTime) {
/*  43 */     this.startTime = startTime;
/*     */   }
/*     */   
/*     */   public Date getStartTime() {
/*  47 */     return this.startTime;
/*     */   }
/*     */   
/*     */   public void setCallID(String callID) {
/*  51 */     this.callID = callID;
/*     */   }
/*     */   
/*     */   public String getCallID() {
/*  55 */     return this.callID;
/*     */   }
/*     */   
/*     */   public void setAgentsInCall(List<Agents> agentsInCall) {
/*  59 */     this.agentsInCall = agentsInCall;
/*     */   }
/*     */   
/*     */   public List<Agents> getAgentsInCall() {
/*  63 */     return this.agentsInCall;
/*     */   }
/*     */   
/*     */   public void setQueueid(String queueid) {
/*  67 */     this.queueid = queueid;
/*     */   }
/*     */   
/*     */   public String getQueueid() {
/*  71 */     return this.queueid;
/*     */   }
/*     */   
/*     */   public void setCalled(String called) {
/*  75 */     this.called = called;
/*     */   }
/*     */   
/*     */   public String getCalled() {
/*  79 */     return this.called;
/*     */   }
/*     */   
/*     */   public void setCaller(String caller) {
/*  83 */     this.caller = caller;
/*     */   }
/*     */   
/*     */   public String getCaller() {
/*  87 */     return this.caller;
/*     */   }
/*     */   
/*     */   public void setSemsUrl(String semsUrl) {
/*  91 */     this.semsUrl = semsUrl;
/*     */   }
/*     */   
/*     */   public String getSemsUrl() {
/*  95 */     return this.semsUrl;
/*     */   }
/*     */   
/*     */   public void setAnswered(boolean isAnswered) {
/*  99 */     this.isAnswered = isAnswered;
/*     */   }
/*     */   
/*     */   public boolean isAnswered() {
/* 103 */     return this.isAnswered;
/*     */   }
/*     */   
/*     */   public void setFirstRingTime(boolean isFirstRingTime) {
/* 107 */     this.isFirstRingTime = isFirstRingTime;
/*     */   }
/*     */   
/*     */   public boolean isFirstRingTime() {
/* 111 */     return this.isFirstRingTime;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 115 */     StringBuilder buf = new StringBuilder();
/*     */     
/* 117 */     buf.append("Callid [").append(getCallID()).append("] caller[")
/* 118 */       .append(getCaller()).append("] called [").append(
/* 119 */       getCalled()).append("] calltime[").append(
/* 120 */       SemsProcessorBase.STANDARD_TIME_FORMAT.format(
/* 121 */       getStartTime())).append("]");
/*     */     
/* 123 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void setSetAgentSentNewCall(Set<Agents> setAgentSentNewCall) {
/* 127 */     this.setAgentSentNewCall = setAgentSentNewCall;
/*     */   }
/*     */   
/*     */   public Set<Agents> getSetAgentSentNewCall() {
/* 131 */     return this.setAgentSentNewCall;
/*     */   }
/*     */   
/*     */   public void setFisrtAnswerTime(long fisrtAnswerTime) {
/* 135 */     this.fisrtAnswerTime = fisrtAnswerTime;
/*     */   }
/*     */   
/*     */   public long getFisrtAnswerTime() {
/* 139 */     return this.fisrtAnswerTime;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\common\CallInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */