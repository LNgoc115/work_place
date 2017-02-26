/*     */ package com.viettel.ipcc.agentserver.dbupdater;
/*     */ 
/*     */ import com.viettel.ipcc.agentserver.common.JobBase;
/*     */ import com.viettel.ipcc.agentserver.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.hibernate.Hibernate;
/*     */ import org.hibernate.HibernateException;
/*     */ import org.hibernate.SQLQuery;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.Transaction;
/*     */ 
/*     */ public class AgentTableUpdater extends com.viettel.ipcc.agentserver.common.JobProcessorBase
/*     */ {
/*  22 */   private Logger logger = Logger.getLogger(AgentTableUpdater.class);
/*     */   
/*     */ 
/*     */ 
/*     */   public void processJob(List<JobBase> listJob)
/*     */   {
/*  28 */     Session session = com.viettel.ipcc.agentserver.dao.HibernateSessionFactory.getSessionFactory()
/*  29 */       .openSession();
/*     */     
/*  31 */     StringBuilder sqlSelect = new StringBuilder(
/*  32 */       " SELECT agent_id FROM agents WHERE agent_id IN ( ");
/*     */     
/*  34 */     sqlSelect.append(Utils.toSqlVar(listJob.size()));
/*     */     
/*  36 */     sqlSelect.append(" ) FOR UPDATE SKIP LOCKED ");
/*     */     
/*  38 */     SQLQuery selectQuery = session.createSQLQuery(sqlSelect.toString())
/*  39 */       .addScalar("agent_id", Hibernate.STRING);
/*     */     
/*  41 */     Map<String, List<AgentUpdaterJob>> mapIDJob = new HashMap();
/*     */     
/*  43 */     for (int i = 0; i < listJob.size(); i++) {
/*  44 */       AgentUpdaterJob j = (AgentUpdaterJob)listJob.get(i);
/*  45 */       String strValName = i;
/*  46 */       selectQuery.setString(strValName, j.getAgentid());
/*     */       
/*  48 */       if (!mapIDJob.containsKey(j.getAgentid())) {
/*  49 */         mapIDJob.put(j.getAgentid(), new ArrayList());
/*     */       }
/*     */       
/*  52 */       ((List)mapIDJob.get(j.getAgentid())).add(j);
/*     */     }
/*     */     
/*  55 */     List listAllAgentCanUpdate = selectQuery.list();
/*     */     
/*  57 */     Transaction tran = session.beginTransaction();
/*  58 */     boolean ok = true;
/*     */     
/*  60 */     for (Object o : listAllAgentCanUpdate) {
/*  61 */       String agentid = o.toString();
/*  62 */       for (AgentUpdaterJob job : (List)mapIDJob.get(agentid)) {
/*  63 */         if (!ok) {
/*     */           break;
/*     */         }
/*  66 */         if (job != null)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  77 */           String updateSql = "";
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */           updateSql = "UPDATE agents SET " + job.getColumnName() + 
/*  85 */             " = :val WHERE agent_id = :agent_id";
/*     */           
/*     */ 
/*  88 */           SQLQuery updateQuery = session.createSQLQuery(updateSql);
/*  89 */           updateQuery.setString("agent_id", agentid);
/*     */           
/*     */ 
/*  92 */           setValueForQuery(updateQuery, "val", job.getValue());
/*     */           
/*     */           try
/*     */           {
/*  96 */             int res = updateQuery.executeUpdate();
/*  97 */             if (res == 0) {
/*  98 */               this.logger.error("update agents: " + job.getAgentid() + 
/*  99 */                 " col: " + job.getColumnName() + " val: " + 
/* 100 */                 job.getValue() + " error");
/* 101 */               ok = false;
/*     */               
/* 103 */               break;
/*     */             }
/* 105 */             this.logger.debug("update agents: " + job.getAgentid() + 
/* 106 */               " col: " + job.getColumnName() + " val: " + 
/* 107 */               job.getValue() + " OK");
/*     */           }
/*     */           catch (HibernateException ex) {
/* 110 */             ok = false;
/*     */             
/* 112 */             ex.printStackTrace();
/* 113 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 121 */     if (ok) {
/* 122 */       tran.commit();
/* 123 */       for (Object o : listAllAgentCanUpdate) {
/* 124 */         agentid = o.toString();
/* 125 */         mapIDJob.remove(agentid);
/*     */       }
/*     */     } else {
/* 128 */       tran.rollback();
/*     */     }
/*     */     
/*     */ 
/* 132 */     DbUpdater dbUpdater = (DbUpdater)Utils.getCtx().getBean("DbUpdater");
/*     */     
/* 134 */     for (String agentid = mapIDJob.entrySet().iterator(); agentid.hasNext(); 
/* 135 */         ???.hasNext())
/*     */     {
/* 134 */       Object e = (Map.Entry)agentid.next();
/* 135 */       ??? = ((List)((Map.Entry)e).getValue()).iterator(); continue;AgentUpdaterJob job = (AgentUpdaterJob)???.next();
/*     */       
/* 137 */       if (System.currentTimeMillis() - job.getStartTime() <= dbUpdater.getMaxTimeInqueue() * 1000L) {
/* 138 */         this.logger.debug("retry update agents col: " + 
/* 139 */           job.getColumnName() + " val: " + job.getValue());
/*     */         
/* 141 */         addJob(job);
/*     */       } else {
/* 143 */         this.logger.info("update atrr for agent " + job.getAgentid() + 
/* 144 */           " col " + job.getColumnName() + " val " + 
/* 145 */           job.getValue() + " timeout, drop now!");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 150 */     session.close();
/*     */   }
/*     */   
/*     */   private static void setValueForQuery(SQLQuery q, String valName, Object o)
/*     */   {
/* 155 */     if ((o instanceof String)) {
/* 156 */       String val = (String)o;
/*     */       
/* 158 */       q.setString(valName, val);
/* 159 */     } else if ((o instanceof Integer))
/*     */     {
/* 161 */       Integer val = (Integer)o;
/*     */       
/* 163 */       q.setInteger(valName, val.intValue());
/*     */     }
/* 165 */     else if ((o instanceof Long))
/*     */     {
/* 167 */       Long val = (Long)o;
/*     */       
/* 169 */       q.setLong(valName, val.longValue());
/* 170 */     } else if ((o instanceof Date)) {
/* 171 */       Date val = (Date)o;
/* 172 */       q.setTimestamp(valName, val);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dbupdater\AgentTableUpdater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */