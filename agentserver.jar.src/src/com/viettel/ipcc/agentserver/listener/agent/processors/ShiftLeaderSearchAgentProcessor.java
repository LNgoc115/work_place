/*     */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentSearch;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.request.ShiftLeaderSearchAgentRequest;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.ShiftLeaderSearchAgentResponse;
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
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ public class ShiftLeaderSearchAgentProcessor extends ProcessorBase
/*     */ {
/*     */   private static final String STR_NOT_CHECK = "420394203948923849NOTCHECK";
/*     */   private static final int INT_NOT_CHECK = -1292383;
/*     */   
/*     */   public void process(AgentDesktopJob job)
/*     */   {
/*  28 */     com.viettel.ipcc.agentserver.common.Agents agent = null;
/*     */     
/*  30 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/*  31 */       ShiftLeaderSearchAgentRequest request = (ShiftLeaderSearchAgentRequest)job
/*  32 */         .getAgentDesktopMsg();
/*     */       
/*  34 */       String userName = request.getUsername();
/*  35 */       String zoonID = request.getArea();
/*  36 */       String userStatus = request.getAgentStatus();
/*  37 */       String callStatus = request.getCallStatus();
/*  38 */       String timeOP = request.getOperator();
/*  39 */       String minute = request.getTimeInStatus();
/*     */       
/*  41 */       this.logger.info("Shift leader: " + agent + 
/*  42 */         " process search agent params[" + userName + "," + zoonID + 
/*  43 */         "," + userStatus + "," + callStatus + "," + timeOP + "," + 
/*  44 */         minute + "]");
/*     */       
/*  46 */       int nMinute = -1292383;
/*     */       
/*  48 */       if ((minute.isEmpty()) || (timeOP.isEmpty())) {
/*  49 */         timeOP = "=";
/*     */       } else {
/*  51 */         nMinute = Integer.valueOf(minute).intValue();
/*     */       }
/*     */       
/*  54 */       if (!userName.isEmpty()) {
/*  55 */         userName = "%" + userName + "%";
/*     */       } else {
/*  57 */         userName = "420394203948923849NOTCHECK";
/*     */       }
/*     */       
/*     */ 
/*  61 */       AgentsManager mng = (AgentsManager)Utils.getCtx().getBean(
/*  62 */         "AgentsManager");
/*     */       String sql;
/*  64 */       if (zoonID.isEmpty()) {
/*  65 */         sql = mng.getSqlShiftleaderSearchNotZoonID();
/*     */       } else {
/*  67 */         sql = mng.getSqlShiftleaderSearch();
/*     */       }
/*     */       
/*  70 */       String sql = sql.replaceAll("##op", timeOP);
/*     */       
/*  72 */       sql = "select vsa_user_login VSA_USER, agent_id EXTENSION , TO_CHAR (login_time , 'YYYY-MM-DD HH24:MI:SS') LOGIN_TIME, call_status CALL_STATUS, user_status AGENT_STATUS, ip_login AGENT_IP, (sysdate - TO_DATE (TO_CHAR (last_change_status , 'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'))*1440 STATUS_TIME ,  SYSTEM_STATUS SYSTEM_STATUS , TOTAL_ANSWER_CALL TOTAL_ANSWER_CALL , TOTAL_ANSWER_TIME TOTAL_ANSWER_TIME from agents WHERE agent_id IN ( " + 
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  77 */         sql + " )";
/*     */       
/*  79 */       Session session = HibernateSessionFactory.getSessionFactory()
/*  80 */         .openSession();
/*     */       
/*  82 */       SQLQuery sqlQuery = session.createSQLQuery(sql).addScalar(
/*  83 */         "VSA_USER", Hibernate.STRING).addScalar("EXTENSION", 
/*  84 */         Hibernate.STRING).addScalar("LOGIN_TIME", Hibernate.STRING)
/*  85 */         .addScalar("CALL_STATUS", Hibernate.STRING).addScalar(
/*  86 */         "AGENT_STATUS", Hibernate.STRING).addScalar(
/*  87 */         "AGENT_IP", Hibernate.STRING).addScalar(
/*  88 */         "STATUS_TIME", Hibernate.STRING).addScalar(
/*  89 */         "SYSTEM_STATUS", Hibernate.STRING).addScalar(
/*  90 */         "TOTAL_ANSWER_CALL", Hibernate.STRING).addScalar(
/*  91 */         "TOTAL_ANSWER_TIME", Hibernate.STRING);
/*     */       
/*  93 */       if (userStatus.isEmpty()) {
/*  94 */         userStatus = "420394203948923849NOTCHECK";
/*     */       }
/*  96 */       if (callStatus.isEmpty()) {
/*  97 */         callStatus = "420394203948923849NOTCHECK";
/*     */       }
/*  99 */       sqlQuery.setString("username", userName);
/* 100 */       sqlQuery.setString("userstatus", userStatus);
/* 101 */       sqlQuery.setString("callstatus", callStatus);
/* 102 */       sqlQuery.setInteger("minute", nMinute);
/* 103 */       if (!zoonID.isEmpty()) {
/* 104 */         sqlQuery.setString("zoonid", zoonID);
/*     */       }
/*     */       
/* 107 */       List res = sqlQuery.list();
/*     */       
/* 109 */       List<AgentSearch> listRes = new ArrayList();
/* 110 */       ShiftLeaderSearchAgentResponse response = new ShiftLeaderSearchAgentResponse(
/* 111 */         listRes);
/*     */       
/* 113 */       for (Object o : res) {
/* 114 */         StringBuilder logBuf = new StringBuilder();
/* 115 */         Object[] row = (Object[])o;
/*     */         
/* 117 */         for (int i = 0; i < row.length; i++) {
/* 118 */           if (row[i] == null) {
/* 119 */             row[i] = "";
/*     */           }
/*     */           
/* 122 */           logBuf.append(row[i]);
/* 123 */           if (i != row.length - 1) {
/* 124 */             logBuf.append(',');
/*     */           }
/*     */         }
/* 127 */         this.logger.debug("row " + logBuf.toString());
/* 128 */         Double statusTime = Double.valueOf(row[6].toString());
/* 129 */         int nTime = Math.abs(statusTime.intValue());
/* 130 */         long totalAnswerdTime = Long.valueOf(row[9].toString()).longValue() / 60L / 1000L;
/* 131 */         Formatter fmt = new Formatter();
/*     */         
/* 133 */         AgentSearch a = new AgentSearch(row[0].toString(), row[1]
/* 134 */           .toString(), row[2].toString(), row[3].toString(), 
/* 135 */           row[4].toString(), row[5].toString(), fmt.format(
/* 136 */           "%1$02d:%2$02d", new Object[] { Integer.valueOf(nTime / 60), Integer.valueOf(nTime % 60) })
/* 137 */           .toString(), row[7].toString(), new Formatter()
/* 138 */           .format("%1$02d:%2$02d", new Object[] { Long.valueOf(totalAnswerdTime / 60L), 
/* 139 */           Long.valueOf(totalAnswerdTime % 60L) }).toString(), 
/* 140 */           row[8].toString());
/*     */         
/* 142 */         listRes.add(a);
/*     */       }
/*     */       
/*     */ 
/* 146 */       session.close();
/* 147 */       Utils.sendMessageToAgent(job.getSession(), response);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 155 */     System.out.println(new Formatter().format("%1$02d:%2$02d", new Object[] { Integer.valueOf(1), Integer.valueOf(2) })
/* 156 */       .toString());
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\ShiftLeaderSearchAgentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */