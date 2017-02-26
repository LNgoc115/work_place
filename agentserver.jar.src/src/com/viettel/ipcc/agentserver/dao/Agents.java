/*     */ package com.viettel.ipcc.agentserver.dao;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Agents
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5022144823314635771L;
/*     */   private String agentId;
/*     */   private String description;
/*     */   private String systemStatus;
/*     */   private String userStatus;
/*     */   private String lastStartWork;
/*     */   private String lastFinishWork;
/*     */   private String loginType;
/*     */   private String vsaUserLogin;
/*     */   private String callStatus;
/*     */   private String lastChangeStatus;
/*     */   private String ipLogin;
/*     */   private String numRejectcall;
/*     */   private String loginTime;
/*     */   private String groupName;
/*  32 */   private Set queueAgents = new HashSet(0);
/*  33 */   private Set agentZoons = new HashSet(0);
/*     */   
/*     */ 
/*     */ 
/*     */   public Agents() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public Agents(String agentId)
/*     */   {
/*  43 */     this.agentId = agentId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Agents(String agentId, String description, String systemStatus, String userStatus, String lastStartWork, String lastFinishWork, String loginType, String vsaUserLogin, String callStatus, String lastChangeStatus, String ipLogin, String numRejectcall, String loginTime, Set queueAgents, Set agentZoons)
/*     */   {
/*  52 */     this.agentId = agentId;
/*  53 */     this.description = description;
/*  54 */     this.systemStatus = systemStatus;
/*  55 */     this.userStatus = userStatus;
/*  56 */     this.lastStartWork = lastStartWork;
/*  57 */     this.lastFinishWork = lastFinishWork;
/*  58 */     this.loginType = loginType;
/*  59 */     this.vsaUserLogin = vsaUserLogin;
/*  60 */     this.callStatus = callStatus;
/*  61 */     this.lastChangeStatus = lastChangeStatus;
/*  62 */     this.ipLogin = ipLogin;
/*  63 */     this.numRejectcall = numRejectcall;
/*  64 */     this.loginTime = loginTime;
/*  65 */     this.queueAgents = queueAgents;
/*  66 */     this.agentZoons = agentZoons;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getAgentId()
/*     */   {
/*  72 */     return this.agentId;
/*     */   }
/*     */   
/*     */   public void setAgentId(String agentId) {
/*  76 */     this.agentId = agentId;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  80 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  84 */     this.description = description;
/*     */   }
/*     */   
/*     */   public String getSystemStatus() {
/*  88 */     return this.systemStatus;
/*     */   }
/*     */   
/*     */   public void setSystemStatus(String systemStatus) {
/*  92 */     this.systemStatus = systemStatus;
/*     */   }
/*     */   
/*     */   public String getUserStatus() {
/*  96 */     return this.userStatus;
/*     */   }
/*     */   
/*     */   public void setUserStatus(String userStatus) {
/* 100 */     this.userStatus = userStatus;
/*     */   }
/*     */   
/*     */   public String getLastStartWork() {
/* 104 */     return this.lastStartWork;
/*     */   }
/*     */   
/*     */   public void setLastStartWork(String lastStartWork) {
/* 108 */     this.lastStartWork = lastStartWork;
/*     */   }
/*     */   
/*     */   public String getLastFinishWork() {
/* 112 */     return this.lastFinishWork;
/*     */   }
/*     */   
/*     */   public void setLastFinishWork(String lastFinishWork) {
/* 116 */     this.lastFinishWork = lastFinishWork;
/*     */   }
/*     */   
/*     */   public String getLoginType() {
/* 120 */     return this.loginType;
/*     */   }
/*     */   
/*     */   public void setLoginType(String loginType) {
/* 124 */     this.loginType = loginType;
/*     */   }
/*     */   
/*     */   public String getVsaUserLogin() {
/* 128 */     return this.vsaUserLogin;
/*     */   }
/*     */   
/*     */   public void setVsaUserLogin(String vsaUserLogin) {
/* 132 */     this.vsaUserLogin = vsaUserLogin;
/*     */   }
/*     */   
/*     */   public String getCallStatus() {
/* 136 */     return this.callStatus;
/*     */   }
/*     */   
/*     */   public void setCallStatus(String callStatus) {
/* 140 */     this.callStatus = callStatus;
/*     */   }
/*     */   
/*     */   public String getLastChangeStatus() {
/* 144 */     return this.lastChangeStatus;
/*     */   }
/*     */   
/*     */   public void setLastChangeStatus(String lastChangeStatus) {
/* 148 */     this.lastChangeStatus = lastChangeStatus;
/*     */   }
/*     */   
/*     */   public String getIpLogin() {
/* 152 */     return this.ipLogin;
/*     */   }
/*     */   
/*     */   public void setIpLogin(String ipLogin) {
/* 156 */     this.ipLogin = ipLogin;
/*     */   }
/*     */   
/*     */   public String getNumRejectcall() {
/* 160 */     return this.numRejectcall;
/*     */   }
/*     */   
/*     */   public void setNumRejectcall(String numRejectcall) {
/* 164 */     this.numRejectcall = numRejectcall;
/*     */   }
/*     */   
/*     */   public String getLoginTime() {
/* 168 */     return this.loginTime;
/*     */   }
/*     */   
/*     */   public void setLoginTime(String loginTime) {
/* 172 */     this.loginTime = loginTime;
/*     */   }
/*     */   
/*     */   public Set getQueueAgents() {
/* 176 */     return this.queueAgents;
/*     */   }
/*     */   
/*     */   public void setQueueAgents(Set queueAgents) {
/* 180 */     this.queueAgents = queueAgents;
/*     */   }
/*     */   
/*     */   public Set getAgentZoons() {
/* 184 */     return this.agentZoons;
/*     */   }
/*     */   
/*     */   public void setAgentZoons(Set agentZoons) {
/* 188 */     this.agentZoons = agentZoons;
/*     */   }
/*     */   
/*     */   public void setGroupName(String groupName) {
/* 192 */     this.groupName = groupName;
/*     */   }
/*     */   
/*     */   public String getGroupName() {
/* 196 */     return this.groupName;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\Agents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */