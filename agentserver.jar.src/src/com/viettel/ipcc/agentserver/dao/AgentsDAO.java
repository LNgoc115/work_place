/*     */ package com.viettel.ipcc.agentserver.dao;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.LockMode;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.SQLQuery;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ import org.hibernate.criterion.Example;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
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
/*     */ public class AgentsDAO
/*     */   extends BaseHibernateDAO
/*     */ {
/*  26 */   private static final Logger log = LoggerFactory.getLogger(AgentsDAO.class);
/*     */   public static final String DESCRIPTION = "description";
/*     */   public static final String SYSTEM_STATUS = "systemStatus";
/*     */   public static final String USER_STATUS = "userStatus";
/*     */   public static final String LAST_START_WORK = "lastStartWork";
/*     */   public static final String LAST_FINISH_WORK = "lastFinishWork";
/*     */   public static final String LOGIN_TYPE = "loginType";
/*     */   public static final String VSA_USER_LOGIN = "vsaUserLogin";
/*     */   public static final String CALL_STATUS = "callStatus";
/*     */   public static final String LAST_CHANGE_STATUS = "lastChangeStatus";
/*     */   public static final String IP_LOGIN = "ipLogin";
/*     */   public static final String NUM_REJECTCALL = "numRejectcall";
/*     */   public static final String LOGIN_TIME = "loginTime";
/*     */   
/*     */   public void save(Agents transientInstance)
/*     */   {
/*  42 */     log.debug("saving Agents instance");
/*     */     try {
/*  44 */       getSession().save(transientInstance);
/*  45 */       log.debug("save successful");
/*     */     } catch (RuntimeException re) {
/*  47 */       log.error("save failed", re);
/*  48 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(Agents persistentInstance) {
/*  53 */     log.debug("deleting Agents instance");
/*     */     try {
/*  55 */       getSession().delete(persistentInstance);
/*  56 */       log.debug("delete successful");
/*     */     } catch (RuntimeException re) {
/*  58 */       log.error("delete failed", re);
/*  59 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Agents findById(String id) {
/*  64 */     log.debug("getting Agents instance with id: " + id);
/*     */     try {
/*  66 */       return (Agents)getSession().get(
/*  67 */         "com.viettel.ipcc.agentserver.dao.Agents", id);
/*     */     }
/*     */     catch (RuntimeException re) {
/*  70 */       log.error("get failed", re);
/*  71 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByExample(Agents instance) {
/*  76 */     log.debug("finding Agents instance by example");
/*     */     try {
/*  78 */       List results = getSession().createCriteria(
/*  79 */         "com.viettel.ipcc.agentserver.dao.Agents").add(
/*  80 */         Example.create(instance)).list();
/*  81 */       log.debug("find by example successful, result size: " + 
/*  82 */         results.size());
/*  83 */       return results;
/*     */     } catch (RuntimeException re) {
/*  85 */       log.error("find by example failed", re);
/*  86 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByProperty(String propertyName, Object value) {
/*  91 */     log.debug("finding Agents instance with property: " + propertyName + 
/*  92 */       ", value: " + value);
/*     */     try {
/*  94 */       String queryString = "from Agents as model where model." + 
/*  95 */         propertyName + "= ?";
/*  96 */       Query queryObject = getSession().createQuery(queryString);
/*  97 */       queryObject.setParameter(0, value);
/*  98 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 100 */       log.error("find by property name failed", re);
/* 101 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByDescription(Object description) {
/* 106 */     return findByProperty("description", description);
/*     */   }
/*     */   
/*     */   public List findBySystemStatus(Object systemStatus) {
/* 110 */     return findByProperty("systemStatus", systemStatus);
/*     */   }
/*     */   
/*     */   public List findByUserStatus(Object userStatus) {
/* 114 */     return findByProperty("userStatus", userStatus);
/*     */   }
/*     */   
/*     */   public List findByLastStartWork(Object lastStartWork) {
/* 118 */     return findByProperty("lastStartWork", lastStartWork);
/*     */   }
/*     */   
/*     */   public List findByLastFinishWork(Object lastFinishWork) {
/* 122 */     return findByProperty("lastFinishWork", lastFinishWork);
/*     */   }
/*     */   
/*     */   public List findByLoginType(Object loginType) {
/* 126 */     return findByProperty("loginType", loginType);
/*     */   }
/*     */   
/*     */   public List findByVsaUserLogin(Object vsaUserLogin) {
/* 130 */     return findByProperty("vsaUserLogin", vsaUserLogin);
/*     */   }
/*     */   
/*     */   public List findByCallStatus(Object callStatus) {
/* 134 */     return findByProperty("callStatus", callStatus);
/*     */   }
/*     */   
/*     */   public List findByLastChangeStatus(Object lastChangeStatus) {
/* 138 */     return findByProperty("lastChangeStatus", lastChangeStatus);
/*     */   }
/*     */   
/*     */   public List findByIpLogin(Object ipLogin) {
/* 142 */     return findByProperty("ipLogin", ipLogin);
/*     */   }
/*     */   
/*     */   public List findByNumRejectcall(Object numRejectcall) {
/* 146 */     return findByProperty("numRejectcall", numRejectcall);
/*     */   }
/*     */   
/*     */   public List findByLoginTime(Object loginTime) {
/* 150 */     return findByProperty("loginTime", loginTime);
/*     */   }
/*     */   
/*     */   public List findAll() {
/* 154 */     log.debug("finding all Agents instances");
/*     */     try {
/* 156 */       String queryString = "from Agents";
/* 157 */       Query queryObject = getSession().createQuery(queryString);
/* 158 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 160 */       log.error("find all failed", re);
/* 161 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Agents merge(Agents detachedInstance) {
/* 166 */     log.debug("merging Agents instance");
/*     */     try {
/* 168 */       Agents result = (Agents)getSession().merge(detachedInstance);
/* 169 */       log.debug("merge successful");
/* 170 */       return result;
/*     */     } catch (RuntimeException re) {
/* 172 */       log.error("merge failed", re);
/* 173 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachDirty(Agents instance) {
/* 178 */     log.debug("attaching dirty Agents instance");
/*     */     try {
/* 180 */       getSession().saveOrUpdate(instance);
/* 181 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 183 */       log.error("attach failed", re);
/* 184 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachClean(Agents instance) {
/* 189 */     log.debug("attaching clean Agents instance");
/*     */     try {
/* 191 */       getSession().lock(instance, LockMode.NONE);
/* 192 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 194 */       log.error("attach failed", re);
/* 195 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Agents findById2(String id) {
/* 200 */     Agents res = null;
/* 201 */     Session session = HibernateSessionFactory.getSessionFactory()
/* 202 */       .openSession();
/*     */     
/* 204 */     SQLQuery query = session.createSQLQuery(
/* 205 */       "SELECT * FROM agents WHERE agent_id=:agent_id").addEntity(
/* 206 */       Agents.class);
/*     */     
/* 208 */     query.setString("agent_id", id);
/*     */     
/* 210 */     List listAgent = query.list();
/* 211 */     if ((listAgent != null) && (listAgent.size() > 0)) {
/* 212 */       res = (Agents)listAgent.get(0);
/*     */     }
/*     */     
/* 215 */     session.close();
/*     */     
/* 217 */     return res;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\AgentsDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */