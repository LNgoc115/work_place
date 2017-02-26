/*     */ package com.viettel.ipcc.agentserver.listener.agent;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentLookup;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.SupervisorLookup;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.AgentEndCallResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.EndCallResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.GetListShiftResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.InviteResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.LoginResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.LogoutResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.LookupAgentResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.LookupSupervisorResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.MonitorAgentInfoResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.MonitorAgentInfoSupervisorResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.MonitorCurrentCallResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.MonitorSysInfoResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.MonitorSysInfoSupervisorResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.ReleaseFromQueueResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.ReloadAgentStatusMessage;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.SpyResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.TerminateResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.TransferResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.UpdateAgentStatusMessage;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.UserEndCallResponse;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.VSAResponse;
/*     */ import com.viettel.ipcc.agentserver.dao.AgentsDAO;
/*     */ import com.viettel.ipcc.agentserver.dao.Shifts;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import viettel.passport.client.ObjectToken;
/*     */ import viettel.passport.client.UserToken;
/*     */ import viettel.passport.client.VSAValidate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResponseProvider
/*     */ {
/*     */   public static final String STATUS_CODE = "status-code";
/*     */   public static final String ERROR = "error";
/*     */   public static final String OK = "ok";
/*     */   public static final String ERROR_CODE = "error-code";
/*     */   public static final String ERROR_INFO = "error-info";
/*     */   public static final String ACCOUNT_EXISTS = "account-exists";
/*     */   public static final String SIP_ID = "sip-id";
/*     */   public static final String SIP_PASSWORD = "sip-password";
/*     */   public static final String SIP_PRESENCE = "sip-presence";
/*     */   public static final String ON_THE_PHONE_MESSAGE = "on-the-phone-messasge";
/*     */   public static final String COMMAND = "command";
/*     */   public static final String TYPE = "type";
/*     */   public static final String NAME = "name";
/*     */   
/*     */   protected Map<String, Object> createErrorMap(ErrorCode errorCode, String reason)
/*     */   {
/*  61 */     Map<String, Object> retval = new HashMap();
/*  62 */     retval.put("status-code", "error");
/*  63 */     retval.put("error-code", errorCode.toString());
/*  64 */     retval.put("error-info", reason);
/*  65 */     return retval;
/*     */   }
/*     */   
/*     */   protected Map<String, Object> createSuccessMap() {
/*  69 */     Map<String, Object> retval = new HashMap();
/*  70 */     retval.put("status-code", "ok");
/*  71 */     return retval;
/*     */   }
/*     */   
/*     */   public static LoginResponse createLoginResponse(String status) {
/*  75 */     return new LoginResponse(status);
/*     */   }
/*     */   
/*     */   public static LogoutResponse createLogoutResponse(String status) {
/*  79 */     return new LogoutResponse(status);
/*     */   }
/*     */   
/*     */ 
/*     */   public static InviteResponse createInviteResponse(String callId, String superAgent, String agent, String status)
/*     */   {
/*  85 */     return new InviteResponse(callId, superAgent, agent, status);
/*     */   }
/*     */   
/*     */ 
/*     */   public static TerminateResponse createTerminateResponse(String callId, String supervisor, String agentId, String status)
/*     */   {
/*  91 */     return new TerminateResponse(callId, supervisor, agentId, status);
/*     */   }
/*     */   
/*     */ 
/*     */   public static TransferResponse createTransferResponse(String callId, String superAgent, String dropAgent, String newAgent, String status)
/*     */   {
/*  97 */     return new TransferResponse(callId, superAgent, dropAgent, newAgent, 
/*  98 */       status);
/*     */   }
/*     */   
/*     */   public static SpyResponse createSpyResponse(String callId, String status) {
/* 102 */     return new SpyResponse(callId, status);
/*     */   }
/*     */   
/*     */ 
/*     */   public static AgentEndCallResponse createAgentEndCallResponse(String callId, String agentId, String extention, String endTime)
/*     */   {
/* 108 */     return new AgentEndCallResponse(callId, agentId, extention, endTime);
/*     */   }
/*     */   
/*     */   public static ReleaseFromQueueResponse createReleaseFromQueueResponse(String callId)
/*     */   {
/* 113 */     return new ReleaseFromQueueResponse(callId);
/*     */   }
/*     */   
/*     */   public static UserEndCallResponse createUserEndCallResponse(String callId, String endTime)
/*     */   {
/* 118 */     return new UserEndCallResponse(callId, endTime);
/*     */   }
/*     */   
/*     */   public static EndCallResponse createEndCallResponse(String callId, String endTime)
/*     */   {
/* 123 */     return new EndCallResponse(callId, endTime);
/*     */   }
/*     */   
/*     */ 
/*     */   public static MonitorCurrentCallResponse createMonitorCurrentCallResponse(String callId, long startTime, String status, String statusAgent)
/*     */   {
/* 129 */     return new MonitorCurrentCallResponse(callId, startTime, status, 
/* 130 */       statusAgent);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static MonitorAgentInfoResponse createMonitorAgentInfoResponse(long serveredCall, long incomingCall, long missCall, long totalTime, long longestTime)
/*     */   {
/* 137 */     return new MonitorAgentInfoResponse(serveredCall, missCall, 
/* 138 */       incomingCall, totalTime, longestTime);
/*     */   }
/*     */   
/*     */ 
/*     */   public static MonitorSysInfoResponse createMonitorSysInfoResponse(String queue, long numWaitCallInQueue, long numCallInQueue)
/*     */   {
/* 144 */     return new MonitorSysInfoResponse(queue, numWaitCallInQueue, 
/* 145 */       numCallInQueue);
/*     */   }
/*     */   
/*     */ 
/*     */   public static MonitorAgentInfoSupervisorResponse createMonitorAgentInfoSupervisorResponse(long totalAgent, long asweringAgent)
/*     */   {
/* 151 */     return new MonitorAgentInfoSupervisorResponse(totalAgent, asweringAgent);
/*     */   }
/*     */   
/*     */ 
/*     */   public static MonitorSysInfoSupervisorResponse createMonitorSysInfoSupervisorResponse(Map<String, String> params)
/*     */   {
/* 157 */     String queue = (String)params.get("queue");
/* 158 */     String servedCalls = (String)params.get("servedCalls");
/* 159 */     String dropedCalls = (String)params.get("dropedCalls");
/* 160 */     String waitingCalls = (String)params.get("waitingCalls");
/* 161 */     String totalCalls = (String)params.get("totalCalls");
/* 162 */     String averageResponeTime = (String)params.get("averageResponeTime");
/* 163 */     String averageWaitTime = (String)params.get("averageWaitTime");
/*     */     
/* 165 */     return new MonitorSysInfoSupervisorResponse(queue, servedCalls, 
/* 166 */       dropedCalls, totalCalls, averageWaitTime, averageResponeTime, 
/* 167 */       waitingCalls);
/*     */   }
/*     */   
/*     */   public static VSAResponse createVSAResponse(VSAValidate vsav)
/*     */   {
/* 172 */     UserToken ut = vsav.getUserToken();
/* 173 */     List<ObjectToken> list = ut.getParentMenu();
/*     */     
/* 175 */     HashMap<String, ArrayList<String>> map = new HashMap();
/* 176 */     for (ObjectToken objectToken : list) {
/* 177 */       ArrayList<String> arrList = new ArrayList();
/* 178 */       String nameToken = objectToken.getObjectName();
/* 179 */       ArrayList<ObjectToken> arrObjToken = objectToken.getChildObjects();
/* 180 */       for (ObjectToken ot : arrObjToken) {
/* 181 */         arrList.add(ot.getObjectName());
/*     */       }
/* 183 */       map.put(nameToken, arrList);
/*     */     }
/*     */     
/* 186 */     VSAResponse vSAResponse = new VSAResponse(map);
/* 187 */     return vSAResponse;
/*     */   }
/*     */   
/*     */   public static GetListShiftResponse createGetListShift(Shifts shift) {
/* 191 */     String shiftId = shift.getShiftId();
/* 192 */     String shiftName = shift.getShiftName();
/* 193 */     String startTime = shift.getShiftFrom();
/* 194 */     String endTime = shift.getShiftTo();
/*     */     
/* 196 */     GetListShiftResponse getListShiftResponse = new GetListShiftResponse(
/* 197 */       shiftId, shiftName, startTime, endTime);
/*     */     
/* 199 */     return getListShiftResponse;
/*     */   }
/*     */   
/*     */ 
/*     */   public static ReloadAgentStatusMessage createReloadAgentStatusMessage(String agentId, String agentStatus)
/*     */   {
/* 205 */     return new ReloadAgentStatusMessage(agentId, agentStatus);
/*     */   }
/*     */   
/*     */ 
/*     */   public static UpdateAgentStatusMessage createUpdateAgentStatusMessage(String superAgent, String agentId, String agentStatus)
/*     */   {
/* 211 */     return new UpdateAgentStatusMessage(superAgent, agentId, agentStatus);
/*     */   }
/*     */   
/*     */   public static LookupAgentResponse createLookupAgentResponse(com.viettel.ipcc.agentserver.common.Agents agent) {
/* 215 */     com.viettel.ipcc.agentserver.dao.Agents dbAgent = null;
/* 216 */     AgentsDAO agentDao = (AgentsDAO)Utils.getCtx().getBean("AgentsDAO");
/*     */     
/* 218 */     dbAgent = agentDao.findById2(agent.getDeviceID());
/*     */     
/* 220 */     AgentLookup agentLookup = new AgentLookup(agent.getVsaID(), dbAgent
/* 221 */       .getUserStatus(), agent.getDeviceID(), 
/* 222 */       agent.isSuper() ? "SUPERVISOR" : "AGENT");
/*     */     
/* 224 */     return new LookupAgentResponse(agentLookup);
/*     */   }
/*     */   
/*     */   public static LookupSupervisorResponse createLookupSupervisorResponse(com.viettel.ipcc.agentserver.common.Agents agent)
/*     */   {
/* 229 */     com.viettel.ipcc.agentserver.dao.Agents dbAgent = null;
/* 230 */     AgentsDAO agentDao = (AgentsDAO)Utils.getCtx().getBean("AgentsDAO");
/*     */     
/* 232 */     dbAgent = agentDao.findById2(agent.getDeviceID());
/*     */     
/* 234 */     SupervisorLookup supervisorLookup = new SupervisorLookup(agent
/* 235 */       .getVsaID(), dbAgent.getUserStatus(), agent.getDeviceID(), 
/* 236 */       "SUPERVISOR");
/*     */     
/* 238 */     return new LookupSupervisorResponse(supervisorLookup);
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\ResponseProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */