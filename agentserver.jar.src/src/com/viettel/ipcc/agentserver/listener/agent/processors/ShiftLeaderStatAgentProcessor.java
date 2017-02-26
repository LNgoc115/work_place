/*     */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*     */ 
/*     */ import com.viettel.ipcc.agentdesktop.protocol.common.AgentStatistics;
/*     */ import com.viettel.ipcc.agentdesktop.protocol.response.ShiftLeaderStatisticsResponse;
/*     */ import com.viettel.ipcc.agentserver.common.Agents;
/*     */ import com.viettel.ipcc.agentserver.dao.HibernateSessionFactory;
/*     */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.hibernate.Hibernate;
/*     */ import org.hibernate.SQLQuery;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ 
/*     */ public class ShiftLeaderStatAgentProcessor
/*     */   extends ProcessorBase
/*     */ {
/*     */   public void process(AgentDesktopJob job)
/*     */   {
/*  24 */     Agents agent = null;
/*  25 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null)
/*     */     {
/*  27 */       this.logger.info("shift leader " + agent + " process stat agent");
/*     */       
/*  29 */       Session session = HibernateSessionFactory.getSessionFactory()
/*  30 */         .openSession();
/*     */       
/*  32 */       String sqlCallStat = "select zid ZID,count(*) COUNT , CALL_STATUS from (select az.zoon_id zid, a.CALL_STATUS CALL_STATUS from agents a, agent_zoon az where a.login_type= 'AGENT' and a.agent_id = az.agent_id and a.user_status <> 'LOGOUT') group by zid,CALL_STATUS";
/*     */       
/*  34 */       Map<String, Map<String, Integer>> callStatMap = new HashMap();
/*  35 */       Map<String, Map<String, Integer>> agentStatMap = new HashMap();
/*  36 */       Map<String, Integer> numrejectMap = new HashMap();
/*     */       
/*  38 */       SQLQuery sqlQueryCallStat = session.createSQLQuery(sqlCallStat)
/*  39 */         .addScalar("ZID", Hibernate.STRING).addScalar("COUNT", 
/*  40 */         Hibernate.INTEGER).addScalar("CALL_STATUS", 
/*  41 */         Hibernate.STRING);
/*     */       
/*  43 */       List listRes = sqlQueryCallStat.list();
/*     */       
/*  45 */       for (Object obj : listRes) {
/*  46 */         Object[] row = (Object[])obj;
/*     */         
/*  48 */         if (!callStatMap.containsKey(row[0].toString())) {
/*  49 */           callStatMap.put(row[0].toString(), 
/*  50 */             new HashMap());
/*     */         }
/*     */         
/*  53 */         if ((row[2] != null) && (row[1] != null)) {
/*  54 */           ((Map)callStatMap.get(row[0])).put(row[2].toString(), 
/*  55 */             (Integer)row[1]);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  60 */       String sqlAgentStat = "select zid ZID,count(*) COUNT , USER_STATUS , sum(NUMREJECT) SUMREJECT from (select az.zoon_id zid, a.user_status USER_STATUS,a.num_rejectcall NUMREJECT from agents a, agent_zoon az where a.login_type = 'AGENT' and a.agent_id = az.agent_id and a.user_status <> 'LOGOUT') group by zid,USER_STATUS";
/*     */       
/*  62 */       SQLQuery sqlQueryAgentStat = session.createSQLQuery(sqlAgentStat)
/*  63 */         .addScalar("ZID", Hibernate.STRING).addScalar("COUNT", 
/*  64 */         Hibernate.INTEGER).addScalar("USER_STATUS", 
/*  65 */         Hibernate.STRING).addScalar("SUMREJECT", 
/*  66 */         Hibernate.INTEGER);
/*     */       
/*  68 */       listRes = sqlQueryAgentStat.list();
/*     */       
/*  70 */       for (Object obj : listRes) {
/*  71 */         Object[] row = (Object[])obj;
/*  72 */         if (!agentStatMap.containsKey(row[0].toString()))
/*     */         {
/*  74 */           agentStatMap.put(row[0].toString(), 
/*  75 */             new HashMap());
/*     */         }
/*     */         
/*     */ 
/*  79 */         if (!numrejectMap.containsKey(row[0].toString())) {
/*  80 */           numrejectMap.put(row[0].toString(), Integer.valueOf(0));
/*     */         }
/*     */         
/*  83 */         if ((row[2] != null) && (row[1] != null)) {
/*  84 */           ((Map)agentStatMap.get(row[0])).put(row[2].toString(), 
/*  85 */             (Integer)row[1]);
/*     */         }
/*     */         
/*     */ 
/*  89 */         numrejectMap.put(row[0].toString(), 
/*     */         
/*  91 */           Integer.valueOf(((Integer)numrejectMap.get(row[0].toString())).intValue() + ((Integer)row[3]).intValue()));
/*     */       }
/*     */       
/*     */ 
/*  95 */       session.close();
/*     */       
/*  97 */       List<AgentStatistics> listAgentStatistic = new ArrayList();
/*  98 */       ShiftLeaderStatisticsResponse response = new ShiftLeaderStatisticsResponse(
/*  99 */         listAgentStatistic);
/*     */       
/* 101 */       for (String zid : agentStatMap.keySet())
/*     */       {
/* 103 */         Map<String, Integer> callStat = (Map)callStatMap.get(zid);
/* 104 */         String ready = "0";
/* 105 */         String noready = "0";
/* 106 */         String ringing = "0";
/*     */         
/* 108 */         if (callStat != null)
/*     */         {
/* 110 */           if (callStat.containsKey("READY")) {
/* 111 */             ready = 
/*     */             
/* 113 */               ((Integer)callStat.get("READY")).toString();
/*     */           }
/*     */           
/*     */ 
/* 117 */           if (callStat.containsKey("NOT READY")) {
/* 118 */             noready = 
/*     */             
/* 120 */               ((Integer)callStat.get("NOT READY")).toString();
/*     */           }
/*     */           
/*     */ 
/* 124 */           if (callStat.containsKey("RINGING")) {
/* 125 */             ringing = 
/*     */             
/* 127 */               ((Integer)callStat.get("RINGING")).toString();
/*     */           }
/*     */         }
/*     */         
/* 131 */         String noAnswer = "0";
/* 132 */         String avaiable = "0";
/* 133 */         String noAcd = "0";
/* 134 */         String meeting = "0";
/* 135 */         String typing = "0";
/* 136 */         String atLunch = "0";
/* 137 */         String goOut = "0";
/*     */         
/* 139 */         Map<String, Integer> agentMap = (Map)agentStatMap.get(zid);
/* 140 */         if (agentMap != null)
/*     */         {
/* 142 */           if (agentMap.containsKey("AVAILABLE")) {
/* 143 */             avaiable = 
/* 144 */               ((Integer)agentMap.get("AVAILABLE")).toString();
/*     */           }
/* 146 */           if (agentMap.containsKey("NOT AVAILABLE"))
/* 147 */             noAcd = 
/*     */             
/* 149 */               ((Integer)agentMap.get("NOT AVAILABLE")).toString();
/* 150 */           if (agentMap.containsKey("MEETING"))
/* 151 */             meeting = 
/* 152 */               ((Integer)agentMap.get("MEETING")).toString();
/* 153 */           if (agentMap.containsKey("TYPING")) {
/* 154 */             typing = 
/* 155 */               ((Integer)agentMap.get("TYPING")).toString();
/*     */           }
/* 157 */           if (agentMap.containsKey("AT LUNCH"))
/* 158 */             atLunch = 
/* 159 */               ((Integer)agentMap.get("AT LUNCH")).toString();
/* 160 */           if (agentMap.containsKey("GO OUT")) {
/* 161 */             goOut = 
/* 162 */               ((Integer)agentMap.get("GO OUT")).toString();
/*     */           }
/* 164 */           if (agentMap.containsKey("NO ANSWER")) {
/* 165 */             noAnswer = 
/* 166 */               ((Integer)agentMap.get("NO ANSWER")).toString();
/*     */           }
/*     */         }
/*     */         
/* 170 */         String dialNoAnswer = "0";
/* 171 */         if (numrejectMap.containsKey(zid)) {
/* 172 */           dialNoAnswer = ((Integer)numrejectMap.get(zid)).toString();
/*     */         }
/* 174 */         int totalAgent = Integer.valueOf(noAnswer).intValue() + 
/* 175 */           Integer.valueOf(avaiable).intValue() + Integer.valueOf(noAcd).intValue() + 
/* 176 */           Integer.valueOf(meeting).intValue() + Integer.valueOf(typing).intValue() + 
/* 177 */           Integer.valueOf(atLunch).intValue() + Integer.valueOf(goOut).intValue();
/*     */         
/* 179 */         AgentStatistics a = new AgentStatistics(ready, noready, 
/* 180 */           ringing, dialNoAnswer, noAnswer, avaiable, noAcd, 
/* 181 */           atLunch, meeting, goOut, typing, zid, totalAgent);
/*     */         
/* 183 */         StringBuilder bufLog = new StringBuilder();
/* 184 */         bufLog.append(zid).append(' ').append(ready).append(' ')
/* 185 */           .append(noready).append(' ').append(ringing)
/* 186 */           .append(' ').append(dialNoAnswer).append(' ').append(
/* 187 */           noAnswer).append(' ').append(avaiable).append(
/* 188 */           ' ').append(noAcd).append(' ').append(atLunch)
/* 189 */           .append(' ').append(meeting).append(' ').append(typing)
/* 190 */           .append(' ').append(totalAgent).append(' ');
/*     */         
/* 192 */         this.logger.debug(bufLog);
/* 193 */         listAgentStatistic.add(a);
/*     */       }
/*     */       
/*     */ 
/* 197 */       Utils.sendMessageToAgent(job.getSession(), response);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 219 */     new ShiftLeaderStatAgentProcessor().process(null);
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\ShiftLeaderStatAgentProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */