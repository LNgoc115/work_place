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
/*     */ public class ShiftsDAO
/*     */   extends BaseHibernateDAO
/*     */ {
/*  26 */   private static final Logger log = LoggerFactory.getLogger(ShiftsDAO.class);
/*     */   public static final String SHIFT_NAME = "shiftName";
/*     */   public static final String SHIFT_FROM = "shiftFrom";
/*     */   public static final String SHIFT_TO = "shiftTo";
/*     */   
/*     */   public void save(Shifts transientInstance)
/*     */   {
/*  33 */     log.debug("saving Shifts instance");
/*     */     try {
/*  35 */       getSession().save(transientInstance);
/*  36 */       log.debug("save successful");
/*     */     } catch (RuntimeException re) {
/*  38 */       log.error("save failed", re);
/*  39 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(Shifts persistentInstance) {
/*  44 */     log.debug("deleting Shifts instance");
/*     */     try {
/*  46 */       getSession().delete(persistentInstance);
/*  47 */       log.debug("delete successful");
/*     */     } catch (RuntimeException re) {
/*  49 */       log.error("delete failed", re);
/*  50 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Shifts findById(String id) {
/*  55 */     log.debug("getting Shifts instance with id: " + id);
/*     */     try {
/*  57 */       return (Shifts)getSession().get(
/*  58 */         "com.viettel.ipcc.agentserver.dao.Shifts", id);
/*     */     }
/*     */     catch (RuntimeException re) {
/*  61 */       log.error("get failed", re);
/*  62 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByExample(Shifts instance) {
/*  67 */     log.debug("finding Shifts instance by example");
/*     */     try {
/*  69 */       List results = getSession().createCriteria(
/*  70 */         "com.viettel.ipcc.agentserver.dao.Shifts").add(
/*  71 */         Example.create(instance)).list();
/*  72 */       log.debug("find by example successful, result size: " + 
/*  73 */         results.size());
/*  74 */       return results;
/*     */     } catch (RuntimeException re) {
/*  76 */       log.error("find by example failed", re);
/*  77 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByProperty(String propertyName, Object value) {
/*  82 */     log.debug("finding Shifts instance with property: " + propertyName + 
/*  83 */       ", value: " + value);
/*     */     try {
/*  85 */       String queryString = "from Shifts as model where model." + 
/*  86 */         propertyName + "= ?";
/*  87 */       Query queryObject = getSession().createQuery(queryString);
/*  88 */       queryObject.setParameter(0, value);
/*  89 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/*  91 */       log.error("find by property name failed", re);
/*  92 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByShiftName(Object shiftName) {
/*  97 */     return findByProperty("shiftName", shiftName);
/*     */   }
/*     */   
/*     */   public List findByShiftFrom(Object shiftFrom) {
/* 101 */     return findByProperty("shiftFrom", shiftFrom);
/*     */   }
/*     */   
/*     */   public List findByShiftTo(Object shiftTo) {
/* 105 */     return findByProperty("shiftTo", shiftTo);
/*     */   }
/*     */   
/*     */   public List findAll() {
/* 109 */     log.debug("finding all Shifts instances");
/*     */     try {
/* 111 */       String queryString = "from Shifts";
/* 112 */       Query queryObject = getSession().createQuery(queryString);
/* 113 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 115 */       log.error("find all failed", re);
/* 116 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Shifts merge(Shifts detachedInstance) {
/* 121 */     log.debug("merging Shifts instance");
/*     */     try {
/* 123 */       Shifts result = (Shifts)getSession().merge(detachedInstance);
/* 124 */       log.debug("merge successful");
/* 125 */       return result;
/*     */     } catch (RuntimeException re) {
/* 127 */       log.error("merge failed", re);
/* 128 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachDirty(Shifts instance) {
/* 133 */     log.debug("attaching dirty Shifts instance");
/*     */     try {
/* 135 */       getSession().saveOrUpdate(instance);
/* 136 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 138 */       log.error("attach failed", re);
/* 139 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachClean(Shifts instance) {
/* 144 */     log.debug("attaching clean Shifts instance");
/*     */     try {
/* 146 */       getSession().lock(instance, LockMode.NONE);
/* 147 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 149 */       log.error("attach failed", re);
/* 150 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public List findAll2()
/*     */   {
/* 157 */     Session session = HibernateSessionFactory.getSessionFactory()
/* 158 */       .openSession();
/*     */     
/* 160 */     String sqlQuery = "SELECT * FROM shifts";
/* 161 */     SQLQuery query = session.createSQLQuery(sqlQuery).addEntity(
/* 162 */       Shifts.class);
/*     */     
/* 164 */     List res = query.list();
/*     */     
/* 166 */     session.close();
/* 167 */     return res;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\ShiftsDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */