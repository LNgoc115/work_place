/*     */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSearch;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.request.SupervisorSearchAgentRequest;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.SupervisorSearchAgentResponse;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.dao.HibernateSessionFactory;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*     */ import com.viettel.ipcc.agentserver.manager.AgentsManager;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Formatter;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.hibernate.Hibernate;
/*     */ import org.hibernate.SQLQuery;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ 
/*     */ public class SupervisorSearchAgentProcessor extends ProcessorBase
/*     */ {
/*     */   private static final String STR_NOT_CHECK = "420394203948923849NOTCHECK";
/*     */   private static final int INT_NOT_CHECK = -1292383;
/*     */   
/*     */   public void process(AgentDesktopJob job)
/*     */   {
/*  28 */     Agents agent = null;
/*  29 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/*  30 */       SupervisorSearchAgentRequest request = (SupervisorSearchAgentRequest)job
/*  31 */         .getAgentDesktopMsg();
/*     */       
/*  33 */       if (agent.isSuper()) {
/*  34 */         if (agent.getGroupID() != null)
/*     */         {
/*  36 */           this.logger.info(new Formatter()
/*  37 */             .format(
/*  38 */             "Process SupervisorSearchAgentProcessor agentstatus=[%1$s] and callstatus=[%2$s] and username=[%3$s] timeinstatus %4$s %5$s", new Object[] {
/*     */             
/*  40 */             request.getAgentStatus(), 
/*  41 */             request.getCallStatus(), 
/*  42 */             request.getUsername(), 
/*  43 */             request.getOperator(), 
/*  44 */             request.getTimeInStatus() })
/*  45 */             .toString());
/*     */           
/*  47 */           String userName = request.getUsername();
/*     */           
/*  49 */           String userStatus = request.getAgentStatus();
/*  50 */           String callStatus = request.getCallStatus();
/*  51 */           String timeOP = request.getOperator();
/*  52 */           String minute = request.getTimeInStatus();
/*  53 */           String groupname = agent.getGroupID();
/*     */           
/*  55 */           int nMinute = -1292383;
/*     */           
/*  57 */           if ((minute.isEmpty()) || (timeOP.isEmpty())) {
/*  58 */             timeOP = "=";
/*     */           } else {
/*  60 */             nMinute = Integer.valueOf(minute).intValue();
/*     */           }
/*     */           
/*  63 */           if (!userName.isEmpty()) {
/*  64 */             userName = "%" + userName + "%";
/*     */           } else {
/*  66 */             userName = "420394203948923849NOTCHECK";
/*     */           }
/*     */           
/*  69 */           if (userStatus.isEmpty()) {
/*  70 */             userStatus = "420394203948923849NOTCHECK";
/*     */           }
/*  72 */           if (callStatus.isEmpty()) {
/*  73 */             callStatus = "420394203948923849NOTCHECK";
/*     */           }
/*     */           
/*     */ 
/*  77 */           AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/*  78 */             "AgentsManager");
/*     */           
/*  80 */           String sql = mng.getSqlSupervisorSearchAgent();
/*     */           
/*  82 */           sql = sql.replaceAll("##op", timeOP);
/*     */           
/*  84 */           sql = "select vsa_user_login VSA_USER, agent_id EXTENSION , TO_CHAR (login_time , 'YYYY-MM-DD HH24:MI:SS') LOGIN_TIME, call_status CALL_STATUS, user_status AGENT_STATUS, ip_login AGENT_IP, (sysdate - TO_DATE (TO_CHAR (last_change_status , 'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'))*1440 STATUS_TIME ,  SYSTEM_STATUS SYSTEM_STATUS, TOTAL_ANSWER_CALL TOTAL_ANSWER_CALL , TOTAL_ANSWER_TIME TOTAL_ANSWER_TIME from agents WHERE agent_id IN ( " + 
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*  89 */             sql + " )";
/*     */           
/*  91 */           Session session = 
/*  92 */             HibernateSessionFactory.getSessionFactory().openSession();
/*     */           
/*  94 */           SQLQuery sqlQuery = session.createSQLQuery(sql).addScalar(
/*  95 */             "VSA_USER", Hibernate.STRING).addScalar(
/*  96 */             "EXTENSION", Hibernate.STRING).addScalar(
/*  97 */             "LOGIN_TIME", Hibernate.STRING).addScalar(
/*  98 */             "CALL_STATUS", Hibernate.STRING).addScalar(
/*  99 */             "AGENT_STATUS", Hibernate.STRING).addScalar(
/* 100 */             "AGENT_IP", Hibernate.STRING).addScalar(
/* 101 */             "STATUS_TIME", Hibernate.STRING).addScalar(
/* 102 */             "SYSTEM_STATUS", Hibernate.STRING).addScalar(
/* 103 */             "TOTAL_ANSWER_CALL", Hibernate.STRING).addScalar(
/* 104 */             "TOTAL_ANSWER_TIME", Hibernate.STRING);
/*     */           
/* 106 */           sqlQuery.setString("username", userName);
/* 107 */           sqlQuery.setString("userstatus", userStatus);
/* 108 */           sqlQuery.setString("callstatus", callStatus);
/* 109 */           sqlQuery.setInteger("minute", nMinute);
/* 110 */           sqlQuery.setString("GROUP_NAME", groupname);
/*     */           
/* 112 */           List res = sqlQuery.list();
/*     */           
/* 114 */           List<AgentSearch> listRes = new ArrayList();
/*     */           
/* 116 */           for (Object o : res) {
/* 117 */             StringBuilder logBuf = new StringBuilder();
/* 118 */             Object[] row = (Object[])o;
/*     */             
/* 120 */             for (int i = 0; i < row.length; i++) {
/* 121 */               if (row[i] == null) {
/* 122 */                 row[i] = "";
/*     */               }
/*     */               
/* 125 */               logBuf.append(row[i]);
/* 126 */               if (i != row.length - 1) {
/* 127 */                 logBuf.append(',');
/*     */               }
/*     */             }
/* 130 */             this.logger.debug("row " + logBuf.toString());
/* 131 */             Double statusTime = Double.valueOf(row[6].toString());
/* 132 */             int nTime = Math.abs(statusTime.intValue());
/* 133 */             Formatter fmt = new Formatter();
/* 134 */             long totalAnswerdTime = Long.valueOf(row[9].toString()).longValue() / 60L / 1000L;
/*     */             
/* 136 */             AgentSearch a = new AgentSearch(row[0].toString(), 
/* 137 */               row[1].toString(), row[2].toString(), row[3]
/* 138 */               .toString(), row[4].toString(), row[5]
/* 139 */               .toString(), fmt
/* 140 */               .format("%1$02d:%2$02d", new Object[] { Integer.valueOf(nTime / 60), 
/* 141 */               Integer.valueOf(nTime % 60) }).toString(), row[7]
/* 142 */               .toString(), new Formatter().format(
/* 143 */               "%1$02d:%2$02d", new Object[] { Long.valueOf(totalAnswerdTime / 60L), 
/* 144 */               Long.valueOf(totalAnswerdTime % 60L) }).toString(), 
/* 145 */               row[8].toString());
/*     */             
/* 147 */             listRes.add(a);
/*     */           }
/*     */           
/*     */ 
/* 151 */           SupervisorSearchAgentResponse response = new SupervisorSearchAgentResponse(
/* 152 */             listRes);
/* 153 */           Utils.sendMessageToAgent(agent.getAgentSession(), response);
/*     */           
/* 155 */           session.close();
/*     */         }
/*     */         else
/*     */         {
/* 159 */           this.logger.info(agent + 
/* 160 */             " group is null can't process SupervisorSearchAgentProcessor !");
/*     */         }
/*     */       }
/*     */       else {
/* 164 */         this.logger.info("Permission deny! You aren't a super.");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 170 */     System.out.println(new Formatter().format("[%1$s]", new Object[] { "xxx" }).toString());
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\SupervisorSearchAgentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */