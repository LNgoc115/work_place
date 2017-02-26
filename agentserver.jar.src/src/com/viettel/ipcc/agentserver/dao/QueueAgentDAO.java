/*     */ package com.viettel.ipcc.agentserver.dao;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.LockMode;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
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
/*     */ public class QueueAgentDAO
/*     */   extends BaseHibernateDAO
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(QueueAgentDAO.class);
/*     */   public static final String PRIORITY = "priority";
/*     */   
/*     */   public void save(QueueAgent transientInstance)
/*     */   {
/*  29 */     log.debug("saving QueueAgent instance");
/*     */     try {
/*  31 */       getSession().save(transientInstance);
/*  32 */       log.debug("save successful");
/*     */     } catch (RuntimeException re) {
/*  34 */       log.error("save failed", re);
/*  35 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(QueueAgent persistentInstance) {
/*  40 */     log.debug("deleting QueueAgent instance");
/*     */     try {
/*  42 */       getSession().delete(persistentInstance);
/*  43 */       log.debug("delete successful");
/*     */     } catch (RuntimeException re) {
/*  45 */       log.error("delete failed", re);
/*  46 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public QueueAgent findById(QueueAgentId id) {
/*  51 */     log.debug("getting QueueAgent instance with id: " + id);
/*     */     try {
/*  53 */       return (QueueAgent)getSession().get(
/*  54 */         "com.viettel.ipcc.agentserver.dao.QueueAgent", id);
/*     */     }
/*     */     catch (RuntimeException re) {
/*  57 */       log.error("get failed", re);
/*  58 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByExample(QueueAgent instance) {
/*  63 */     log.debug("finding QueueAgent instance by example");
/*     */     try {
/*  65 */       List results = getSession().createCriteria(
/*  66 */         "com.viettel.ipcc.agentserver.dao.QueueAgent").add(
/*  67 */         Example.create(instance)).list();
/*  68 */       log.debug("find by example successful, result size: " + 
/*  69 */         results.size());
/*  70 */       return results;
/*     */     } catch (RuntimeException re) {
/*  72 */       log.error("find by example failed", re);
/*  73 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByProperty(String propertyName, Object value) {
/*  78 */     log.debug("finding QueueAgent instance with property: " + propertyName + 
/*  79 */       ", value: " + value);
/*     */     try {
/*  81 */       String queryString = "from QueueAgent as model where model." + 
/*  82 */         propertyName + "= ?";
/*  83 */       Query queryObject = getSession().createQuery(queryString);
/*  84 */       queryObject.setParameter(0, value);
/*  85 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/*  87 */       log.error("find by property name failed", re);
/*  88 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByPriority(Object priority) {
/*  93 */     return findByProperty("priority", priority);
/*     */   }
/*     */   
/*     */   public List findAll() {
/*  97 */     log.debug("finding all QueueAgent instances");
/*     */     try {
/*  99 */       String queryString = "from QueueAgent";
/* 100 */       Query queryObject = getSession().createQuery(queryString);
/* 101 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 103 */       log.error("find all failed", re);
/* 104 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public QueueAgent merge(QueueAgent detachedInstance) {
/* 109 */     log.debug("merging QueueAgent instance");
/*     */     try {
/* 111 */       QueueAgent result = (QueueAgent)getSession().merge(
/* 112 */         detachedInstance);
/* 113 */       log.debug("merge successful");
/* 114 */       return result;
/*     */     } catch (RuntimeException re) {
/* 116 */       log.error("merge failed", re);
/* 117 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachDirty(QueueAgent instance) {
/* 122 */     log.debug("attaching dirty QueueAgent instance");
/*     */     try {
/* 124 */       getSession().saveOrUpdate(instance);
/* 125 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 127 */       log.error("attach failed", re);
/* 128 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachClean(QueueAgent instance) {
/* 133 */     log.debug("attaching clean QueueAgent instance");
/*     */     try {
/* 135 */       getSession().lock(instance, LockMode.NONE);
/* 136 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 138 */       log.error("attach failed", re);
/* 139 */       throw re;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\QueueAgentDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */