/*     */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.request.LoginRequest;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.ShiftInfoStopResponse;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.dao.AgentsDAO;
/*     */ import com.viettel.ipcc.agentserver.dao.Shifts;
/*     */ import com.viettel.ipcc.agentserver.dao.ShiftsDAO;
/*     */ import com.viettel.ipcc.agentserver.dbupdater.DbUpdater;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.ErrorCode;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.ResponseProvider;
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import viettel.passport.client.RoleToken;
/*     */ import viettel.passport.client.UserToken;
/*     */ import viettel.passport.client.VSAValidate;
/*     */ 
/*     */ public class LoginProcessor
/*     */   extends ProcessorBase
/*     */ {
/*  30 */   private Logger logger = Logger.getLogger(LoginProcessor.class);
/*     */   
/*     */ 
/*     */ 
/*     */   public void process(AgentDesktopJob job)
/*     */   {
/*  36 */     LoginRequest request = (LoginRequest)job.getAgentDesktopMsg();
/*     */     
/*  38 */     String vsaUser = request.getAgentId();
/*  39 */     String password = request.getPassword();
/*  40 */     String deviceid = request.getExtension();
/*  41 */     IoSession session = job.getSession();
/*     */     
/*  43 */     this.logger.info("process login: vsaUser[" + vsaUser + "] deviceid[" + 
/*  44 */       deviceid + "]");
/*     */     
/*  46 */     if ((vsaUser.equals("?")) || (password.equals("?")))
/*     */     {
/*  48 */       Utils.sendMessageToAgent(session, 
/*  49 */         ResponseProvider.createLoginResponse(ErrorCode.INVALID_COMMAND.toString()));
/*     */       try {
/*  51 */         session.close(true);
/*     */       }
/*     */       catch (Exception localException) {}
/*     */       
/*  55 */       return;
/*     */     }
/*     */     
/*  58 */     AgentsManager agentManager = (AgentsManager)Utils.getCtx().getBean(
/*  59 */       "AgentsManager");
/*     */     
/*  61 */     if (agentManager.findByDeviceID(deviceid) != null) {
/*  62 */       Utils.sendMessageToAgent(session, 
/*  63 */         ResponseProvider.createLoginResponse(ErrorCode.EXTENSION_EXIST.toString()));
/*     */       try {
/*  65 */         session.close(true);
/*     */       }
/*     */       catch (Exception localException1) {}
/*     */       
/*     */ 
/*  70 */       return;
/*     */     }
/*     */     
/*  73 */     if (agentManager.findByVsaID(vsaUser) != null)
/*     */     {
/*  75 */       Utils.sendMessageToAgent(session, 
/*  76 */         ResponseProvider.createLoginResponse(ErrorCode.USER_EXIST.toString()));
/*     */       try {
/*  78 */         session.close(true);
/*     */       }
/*     */       catch (Exception localException2) {}
/*     */       
/*  82 */       return;
/*     */     }
/*     */     
/*  85 */     VSAValidate validate = authen(vsaUser, password);
/*     */     
/*  87 */     if (!validate.isAuthenticationSuccesful())
/*     */     {
/*     */ 
/*  90 */       Utils.sendMessageToAgent(session, 
/*  91 */         ResponseProvider.createLoginResponse(ErrorCode.USER_PASSWORD_NOT_MATCH
/*  92 */         .toString()));
/*     */       try {
/*  94 */         session.close(true);
/*     */       }
/*     */       catch (Exception localException3) {}
/*     */       
/*  98 */       return;
/*     */     }
/* 100 */     AgentsDAO agentDao = (AgentsDAO)Utils.getCtx().getBean("AgentsDAO");
/*     */     
/* 102 */     if (agentDao.findById2(deviceid) == null)
/*     */     {
/* 104 */       Utils.sendMessageToAgent(session, 
/* 105 */         ResponseProvider.createLoginResponse(ErrorCode.EXTENSION_NOT_EXIST
/* 106 */         .toString()));
/*     */       try {
/* 108 */         session.close(true);
/*     */       }
/*     */       catch (Exception localException4) {}
/*     */       
/* 112 */       return;
/*     */     }
/*     */     
/*     */ 
/* 116 */     Agents agent = new Agents();
/* 117 */     agent.setAgentSession(session);
/* 118 */     agent.setDeviceID(deviceid);
/* 119 */     agent.setVsaID(vsaUser);
/* 120 */     agent.setShiftid("");
/* 121 */     agent.setLoginTime(System.currentTimeMillis());
/* 122 */     agent.setSuper(false);
/*     */     
/* 124 */     List<RoleToken> listRole = validate.getUserToken().getRolesList();
/* 125 */     for (RoleToken role : listRole) {
/* 126 */       this.logger.debug("Agent: " + agent.getVsaID() + " role : " + 
/* 127 */         role.getRoleName());
/* 128 */       if (role.getRoleName().equals("ipcc_Supervisor")) {
/* 129 */         agent.setSuper(true);
/*     */       }
/*     */       
/* 132 */       agent.getListRole().add(role.getRoleName());
/*     */     }
/*     */     
/*     */ 
/* 136 */     agent.setGroupID(validate.getUserToken().getDeptName());
/*     */     
/* 138 */     agentManager.addAgent(agent);
/*     */     
/* 140 */     this.logger.info(agent + " login ok");
/* 141 */     Utils.sendMessageToAgent(session, 
/* 142 */       ResponseProvider.createLoginResponse(ErrorCode.OK.toString()));
/*     */     
/* 144 */     Utils.sendMessageToAgent(session, 
/* 145 */       ResponseProvider.createVSAResponse(validate));
/*     */     
/* 147 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/* 148 */     dbUpdater.updateAgentStatus(agent.getDeviceID(), 
/* 149 */       "NOT AVAILABLE");
/*     */     
/* 151 */     dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 152 */       "LAST_CHANGE_STATUS", new Date(
/* 153 */       System.currentTimeMillis()));
/*     */     
/* 155 */     sendChangeStatusCDR(agent, "LOGIN", "");
/*     */     
/* 157 */     dbUpdater.updateAgentLoginType(agent.getDeviceID(), agent
/* 158 */       .getAgentStringType());
/*     */     
/* 160 */     agent.setShiftid(request.getShiftId());
/* 161 */     this.logger.info(agent + " shiftid: " + request.getShiftId());
/*     */     
/* 163 */     dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 164 */       "NUM_REJECTCALL", "0");
/*     */     
/* 166 */     dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 167 */       "TOTAL_ANSWER_CALL", "0");
/*     */     
/* 169 */     dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 170 */       "TOTAL_ANSWER_TIME", "0");
/*     */     
/* 172 */     dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 173 */       "VSA_USER_LOGIN", agent.getVsaID());
/*     */     
/* 175 */     dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 176 */       "LOGIN_TIME", new Date(System.currentTimeMillis()));
/*     */     
/* 178 */     dbUpdater.updateAgentAttr(agent.getDeviceID(), "IP_LOGIN", 
/* 179 */       Utils.getIp(agent.getAgentSession()));
/*     */     
/* 181 */     dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 182 */       "CALL_STATUS", "READY");
/*     */     
/* 184 */     if (agent.getGroupID() == null) {
/* 185 */       this.logger.error("Can't get group id of agent: " + agent);
/*     */     } else {
/* 187 */       dbUpdater.updateAgentAttr(agent.getDeviceID(), 
/* 188 */         "GROUP_NAME", agent.getGroupID());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private VSAValidate authen(String vsauser, String pass)
/*     */   {
/* 195 */     String vsaDomain = (String)Utils.getCtx().getBean("VsaDomain");
/* 196 */     String vsaUrl = (String)Utils.getCtx().getBean("VsaUrl");
/* 197 */     VSAValidate vsaValidate = new VSAValidate();
/* 198 */     vsaValidate.setUser(vsauser);
/* 199 */     vsaValidate.setPassword(pass);
/* 200 */     vsaValidate.setDomainCode(vsaDomain);
/* 201 */     vsaValidate.setCasValidateUrl(vsaUrl);
/*     */     try {
/* 203 */       this.logger.info("Check vsa username[" + vsauser + "] pass[***]");
/* 204 */       vsaValidate.validate();
/*     */     }
/*     */     catch (IOException e) {
/* 207 */       e.printStackTrace();
/*     */     }
/*     */     catch (ParserConfigurationException e) {
/* 210 */       e.printStackTrace();
/*     */     }
/*     */     
/* 213 */     return vsaValidate;
/*     */   }
/*     */   
/*     */ 
/*     */   private void sendShiftInfoObj(IoSession session)
/*     */   {
/* 219 */     ShiftsDAO shiftDao = (ShiftsDAO)Utils.getCtx().getBean("ShiftsDAO");
/*     */     
/* 221 */     List listShifts = shiftDao.findAll2();
/* 222 */     if (listShifts != null)
/*     */     {
/* 224 */       for (Object o : listShifts) {
/* 225 */         Shifts shift = (Shifts)o;
/* 226 */         this.logger.info("SEND to client shift info " + shift);
/* 227 */         session.write(ResponseProvider.createGetListShift(shift));
/*     */       }
/*     */       
/* 230 */       ShiftInfoStopResponse shiftInfoStop = new ShiftInfoStopResponse();
/* 231 */       session.write(shiftInfoStop);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */     throws IOException, ParserConfigurationException
/*     */   {
/* 238 */     VSAValidate vsaValidate = new VSAValidate();
/* 239 */     vsaValidate.setUser("muoi1");
/* 240 */     vsaValidate.setPassword("123");
/* 241 */     vsaValidate.setDomainCode("ipcc");
/* 242 */     vsaValidate
/* 243 */       .setCasValidateUrl("http://192.168.81.19:5676/passportv3/passportWS?wsdl");
/* 244 */     vsaValidate.validate();
/*     */     
/* 246 */     System.out.println(vsaValidate.getUserToken().getDeptName());
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\LoginProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */