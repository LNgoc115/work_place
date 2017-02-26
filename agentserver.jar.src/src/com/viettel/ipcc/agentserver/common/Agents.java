/*     */ package com.viettel.ipcc.agentserver.common;
/*     */ 
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Agents
/*     */ {
/*     */   public static final int AGENT_CALL_RINGING = 1;
/*     */   public static final int AGENT_CALL_ANSWERING = 2;
/*     */   public static final int AGENT_CALL_FREE = 3;
/*  18 */   private volatile int agentCallStatus = 3;
/*     */   
/*     */   private volatile List<String> listRole;
/*     */   private volatile String vsaID;
/*     */   private volatile String deviceID;
/*     */   private volatile String groupID;
/*     */   private volatile String shiftid;
/*  25 */   private volatile boolean isSuper = false;
/*     */   private volatile long loginTime;
/*     */   private volatile IoSession agentSession;
/*  28 */   private volatile int totalCall = 0;
/*  29 */   private volatile int totalAnswered = 0;
/*  30 */   private volatile int totalMissed = 0;
/*  31 */   private volatile long totalAnswerTime = 0L;
/*  32 */   private volatile long lastStartAnswer = -1L;
/*  33 */   private volatile long longestTimeAnswerd = 0L;
/*  34 */   private volatile long totalCustomerWaitTime = 0L;
/*     */   private volatile CallInfo curCall;
/*  36 */   private volatile int numSendPingNotRes = 0;
/*     */   private volatile int numRejectCall;
/*  38 */   private volatile long lastCheckConnection = System.currentTimeMillis();
/*  39 */   private volatile MonitoringInfo monitoringInfo = null;
/*     */   
/*     */   private volatile CalloutInfo calloutInfo;
/*  42 */   private int totalNumRejectCall = 0;
/*     */   
/*     */   public Agents() {
/*  45 */     AgentsManager agentMng = (AgentsManager)Utils.getCtx().getBean(
/*  46 */       "AgentsManager");
/*  47 */     setNumRejectCall(agentMng.getMaxNumberRejectCall());
/*  48 */     this.listRole = new ArrayList();
/*     */   }
/*     */   
/*     */   public void setVsaID(String vsaID)
/*     */   {
/*  53 */     this.vsaID = vsaID;
/*     */   }
/*     */   
/*     */   public String getVsaID() {
/*  57 */     return this.vsaID;
/*     */   }
/*     */   
/*     */   public void setListRole(List<String> listRole) {
/*  61 */     this.listRole = listRole;
/*     */   }
/*     */   
/*     */   public List<String> getListRole() {
/*  65 */     return this.listRole;
/*     */   }
/*     */   
/*     */   public void setDeviceID(String deviceID) {
/*  69 */     this.deviceID = deviceID;
/*     */   }
/*     */   
/*     */   public String getDeviceID() {
/*  73 */     return this.deviceID;
/*     */   }
/*     */   
/*     */   public void setGroupID(String groupID) {
/*  77 */     this.groupID = groupID;
/*     */   }
/*     */   
/*     */   public String getGroupID() {
/*  81 */     return this.groupID;
/*     */   }
/*     */   
/*     */   public void setLoginTime(long loginTime) {
/*  85 */     this.loginTime = loginTime;
/*     */   }
/*     */   
/*     */   public long getLoginTime() {
/*  89 */     return this.loginTime;
/*     */   }
/*     */   
/*     */   public void setAgentSession(IoSession agentSession) {
/*  93 */     this.agentSession = agentSession;
/*     */   }
/*     */   
/*     */   public IoSession getAgentSession() {
/*  97 */     return this.agentSession;
/*     */   }
/*     */   
/*     */   public void setSuper(boolean isSuper) {
/* 101 */     this.isSuper = isSuper;
/*     */   }
/*     */   
/*     */   public boolean isSuper() {
/* 105 */     return this.isSuper;
/*     */   }
/*     */   
/*     */   public void setShiftid(String shiftid) {
/* 109 */     this.shiftid = shiftid;
/*     */   }
/*     */   
/*     */   public String getShiftid() {
/* 113 */     return this.shiftid;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 117 */     StringBuilder buf = new StringBuilder();
/* 118 */     buf.append("agent deviceid[").append(getDeviceID()).append(
/* 119 */       "] vsaid[").append(getVsaID()).append("] group[").append(
/* 120 */       getGroupID()).append("] connection[").append(
/* 121 */       getAgentSession()).append("] is Supervisor[").append(
/* 122 */       isSuper()).append("]");
/* 123 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void setTotalCall(int totalCall) {
/* 127 */     this.totalCall = totalCall;
/*     */   }
/*     */   
/*     */   public int getTotalCall() {
/* 131 */     return this.totalCall;
/*     */   }
/*     */   
/*     */   public void setTotalAnswered(int totalAnswered) {
/* 135 */     this.totalAnswered = totalAnswered;
/*     */   }
/*     */   
/*     */   public int getTotalAnswered() {
/* 139 */     return this.totalAnswered;
/*     */   }
/*     */   
/*     */   public void setTotalMissed(int totalMissed) {
/* 143 */     this.totalMissed = totalMissed;
/*     */   }
/*     */   
/*     */   public int getTotalMissed() {
/* 147 */     return this.totalMissed;
/*     */   }
/*     */   
/*     */   public void setTotalAnswerTime(long totalAnswerTime) {
/* 151 */     this.totalAnswerTime = totalAnswerTime;
/*     */   }
/*     */   
/*     */   public long getTotalAnswerTime() {
/* 155 */     return this.totalAnswerTime;
/*     */   }
/*     */   
/*     */   public void setLastStartAnswer(long lastStartAnswer) {
/* 159 */     this.lastStartAnswer = lastStartAnswer;
/*     */   }
/*     */   
/*     */   public long getLastStartAnswer() {
/* 163 */     return this.lastStartAnswer;
/*     */   }
/*     */   
/*     */   public void setCurCall(CallInfo curCall) {
/* 167 */     this.curCall = curCall;
/*     */   }
/*     */   
/*     */   public CallInfo getCurCall() {
/* 171 */     return this.curCall;
/*     */   }
/*     */   
/*     */   public void setLongestTimeAnswerd(long longestTimeAnswerd) {
/* 175 */     this.longestTimeAnswerd = longestTimeAnswerd;
/*     */   }
/*     */   
/*     */   public long getLongestTimeAnswerd() {
/* 179 */     return this.longestTimeAnswerd;
/*     */   }
/*     */   
/*     */   public void setAgentCallStatus(int agentCallStatus) {
/* 183 */     this.agentCallStatus = agentCallStatus;
/*     */   }
/*     */   
/*     */   public int getAgentCallStatus() {
/* 187 */     return this.agentCallStatus;
/*     */   }
/*     */   
/*     */   public void setTotalCustomerWaitTime(long totalCustomerWaitTime) {
/* 191 */     this.totalCustomerWaitTime = totalCustomerWaitTime;
/*     */   }
/*     */   
/*     */   public long getTotalCustomerWaitTime() {
/* 195 */     return this.totalCustomerWaitTime;
/*     */   }
/*     */   
/*     */   public void setNumSendPingNotRes(int numSendPingNotRes) {
/* 199 */     this.numSendPingNotRes = numSendPingNotRes;
/*     */   }
/*     */   
/*     */   public int getNumSendPingNotRes() {
/* 203 */     return this.numSendPingNotRes;
/*     */   }
/*     */   
/*     */   public void setNumRejectCall(int numRejectCall) {
/* 207 */     this.numRejectCall = numRejectCall;
/*     */   }
/*     */   
/*     */   public int getNumRejectCall() {
/* 211 */     return this.numRejectCall;
/*     */   }
/*     */   
/*     */   public void setLastCheckConnection(long lastCheckConnection) {
/* 215 */     this.lastCheckConnection = lastCheckConnection;
/*     */   }
/*     */   
/*     */   public long getLastCheckConnection() {
/* 219 */     return this.lastCheckConnection;
/*     */   }
/*     */   
/*     */   public String getAgentStringType()
/*     */   {
/* 224 */     String type = "UNKNOWN";
/*     */     
/* 226 */     for (String strRole : this.listRole)
/*     */     {
/* 228 */       if ((strRole.equalsIgnoreCase("ipcc_ShiftLeaderStatAgent")) || 
/*     */       
/* 230 */         (strRole.equalsIgnoreCase("ipcc_ShiftLeaderStatAgent")) || 
/*     */         
/* 232 */         (strRole.equalsIgnoreCase("ipcc_ShiftLeaderViewQueue")))
/*     */       {
/* 234 */         type = "SHIFTLEADER";
/* 235 */         return type;
/*     */       }
/*     */     }
/* 238 */     for (String strRole : this.listRole) {
/* 239 */       if (strRole.equalsIgnoreCase("ipcc_Supervisor")) {
/* 240 */         type = "SUPERVISOR";
/* 241 */         return type;
/*     */       }
/*     */     }
/*     */     
/* 245 */     for (String strRole : this.listRole) {
/* 246 */       if (strRole.equalsIgnoreCase("ipcc_Agent")) {
/* 247 */         type = "AGENT";
/* 248 */         return type;
/*     */       }
/*     */     }
/*     */     
/* 252 */     return type;
/*     */   }
/*     */   
/*     */   public void setMonitoringInfo(MonitoringInfo monitoringInfo) {
/* 256 */     this.monitoringInfo = monitoringInfo;
/*     */   }
/*     */   
/*     */   public MonitoringInfo getMonitoringInfo() {
/* 260 */     return this.monitoringInfo;
/*     */   }
/*     */   
/*     */   public void setTotalNumRejectCall(int totalNumRejectCall) {
/* 264 */     this.totalNumRejectCall = totalNumRejectCall;
/*     */   }
/*     */   
/*     */   public int getTotalNumRejectCall() {
/* 268 */     return this.totalNumRejectCall;
/*     */   }
/*     */   
/*     */   public void setCalloutInfo(CalloutInfo calloutInfo) {
/* 272 */     this.calloutInfo = calloutInfo;
/*     */   }
/*     */   
/*     */   public CalloutInfo getCalloutInfo() {
/* 276 */     return this.calloutInfo;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\common\Agents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */