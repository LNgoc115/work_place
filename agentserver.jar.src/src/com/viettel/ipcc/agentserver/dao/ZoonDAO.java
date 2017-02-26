/*     */ package com.viettel.ipcc.agentserver.dao;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.LockMode;
/*     */ import org.hibernate.Query;
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
/*     */ public class ZoonDAO
/*     */   extends BaseHibernateDAO
/*     */ {
/*  25 */   private static final Logger log = LoggerFactory.getLogger(ZoonDAO.class);
/*     */   public static final String ZOON_NAME = "zoonName";
/*     */   public static final String DESCRIPTION = "description";
/*     */   
/*     */   public void save(Zoon transientInstance)
/*     */   {
/*  31 */     log.debug("saving Zoon instance");
/*     */     try {
/*  33 */       getSession().save(transientInstance);
/*  34 */       log.debug("save successful");
/*     */     } catch (RuntimeException re) {
/*  36 */       log.error("save failed", re);
/*  37 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(Zoon persistentInstance) {
/*  42 */     log.debug("deleting Zoon instance");
/*     */     try {
/*  44 */       getSession().delete(persistentInstance);
/*  45 */       log.debug("delete successful");
/*     */     } catch (RuntimeException re) {
/*  47 */       log.error("delete failed", re);
/*  48 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Zoon findById(String id) {
/*  53 */     log.debug("getting Zoon instance with id: " + id);
/*     */     try {
/*  55 */       return (Zoon)getSession().get(
/*  56 */         "com.viettel.ipcc.agentserver.dao.Zoon", id);
/*     */     }
/*     */     catch (RuntimeException re) {
/*  59 */       log.error("get failed", re);
/*  60 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByExample(Zoon instance) {
/*  65 */     log.debug("finding Zoon instance by example");
/*     */     try {
/*  67 */       List results = getSession().createCriteria(
/*  68 */         "com.viettel.ipcc.agentserver.dao.Zoon").add(
/*  69 */         Example.create(instance)).list();
/*  70 */       log.debug("find by example successful, result size: " + 
/*  71 */         results.size());
/*  72 */       return results;
/*     */     } catch (RuntimeException re) {
/*  74 */       log.error("find by example failed", re);
/*  75 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByProperty(String propertyName, Object value) {
/*  80 */     log.debug("finding Zoon instance with property: " + propertyName + 
/*  81 */       ", value: " + value);
/*     */     try {
/*  83 */       String queryString = "from Zoon as model where model." + 
/*  84 */         propertyName + "= ?";
/*  85 */       Query queryObject = getSession().createQuery(queryString);
/*  86 */       queryObject.setParameter(0, value);
/*  87 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/*  89 */       log.error("find by property name failed", re);
/*  90 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByZoonName(Object zoonName) {
/*  95 */     return findByProperty("zoonName", zoonName);
/*     */   }
/*     */   
/*     */   public List findByDescription(Object description) {
/*  99 */     return findByProperty("description", description);
/*     */   }
/*     */   
/*     */   public List findAll() {
/* 103 */     log.debug("finding all Zoon instances");
/*     */     try {
/* 105 */       String queryString = "from Zoon";
/* 106 */       Query queryObject = getSession().createQuery(queryString);
/* 107 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 109 */       log.error("find all failed", re);
/* 110 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findAll2() {
/* 115 */     log.debug("finding all Zoon instances");
/*     */     try {
/* 117 */       String queryString = "from Zoon";
/* 118 */       Session session = HibernateSessionFactory.getSessionFactory()
/* 119 */         .openSession();
/* 120 */       Query queryObject = getSession().createQuery(queryString);
/* 121 */       List listRes = queryObject.list();
/* 122 */       session.close();
/* 123 */       return listRes;
/*     */     } catch (RuntimeException re) {
/* 125 */       log.error("find all failed", re);
/* 126 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Zoon merge(Zoon detachedInstance) {
/* 131 */     log.debug("merging Zoon instance");
/*     */     try {
/* 133 */       Zoon result = (Zoon)getSession().merge(detachedInstance);
/* 134 */       log.debug("merge successful");
/* 135 */       return result;
/*     */     } catch (RuntimeException re) {
/* 137 */       log.error("merge failed", re);
/* 138 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachDirty(Zoon instance) {
/* 143 */     log.debug("attaching dirty Zoon instance");
/*     */     try {
/* 145 */       getSession().saveOrUpdate(instance);
/* 146 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 148 */       log.error("attach failed", re);
/* 149 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachClean(Zoon instance) {
/* 154 */     log.debug("attaching clean Zoon instance");
/*     */     try {
/* 156 */       getSession().lock(instance, LockMode.NONE);
/* 157 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 159 */       log.error("attach failed", re);
/* 160 */       throw re;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\ZoonDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */