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
/*     */ public class QueuesDAO
/*     */   extends BaseHibernateDAO
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(QueuesDAO.class);
/*     */   public static final String DESCRIPTION = "description";
/*     */   
/*     */   public void save(Queues transientInstance)
/*     */   {
/*  29 */     log.debug("saving Queues instance");
/*     */     try {
/*  31 */       getSession().save(transientInstance);
/*  32 */       log.debug("save successful");
/*     */     } catch (RuntimeException re) {
/*  34 */       log.error("save failed", re);
/*  35 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(Queues persistentInstance) {
/*  40 */     log.debug("deleting Queues instance");
/*     */     try {
/*  42 */       getSession().delete(persistentInstance);
/*  43 */       log.debug("delete successful");
/*     */     } catch (RuntimeException re) {
/*  45 */       log.error("delete failed", re);
/*  46 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Queues findById(Long id) {
/*  51 */     log.debug("getting Queues instance with id: " + id);
/*     */     try {
/*  53 */       return (Queues)getSession().get(
/*  54 */         "com.viettel.ipcc.agentserver.dao.Queues", id);
/*     */     }
/*     */     catch (RuntimeException re) {
/*  57 */       log.error("get failed", re);
/*  58 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByExample(Queues instance) {
/*  63 */     log.debug("finding Queues instance by example");
/*     */     try {
/*  65 */       List results = getSession().createCriteria(
/*  66 */         "com.viettel.ipcc.agentserver.dao.Queues").add(
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
/*  78 */     log.debug("finding Queues instance with property: " + propertyName + 
/*  79 */       ", value: " + value);
/*     */     try {
/*  81 */       String queryString = "from Queues as model where model." + 
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
/*     */   public List findByDescription(Object description) {
/*  93 */     return findByProperty("description", description);
/*     */   }
/*     */   
/*     */   public List findAll() {
/*  97 */     log.debug("finding all Queues instances");
/*     */     try {
/*  99 */       String queryString = "from Queues";
/* 100 */       Query queryObject = getSession().createQuery(queryString);
/* 101 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 103 */       log.error("find all failed", re);
/* 104 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Queues merge(Queues detachedInstance) {
/* 109 */     log.debug("merging Queues instance");
/*     */     try {
/* 111 */       Queues result = (Queues)getSession().merge(detachedInstance);
/* 112 */       log.debug("merge successful");
/* 113 */       return result;
/*     */     } catch (RuntimeException re) {
/* 115 */       log.error("merge failed", re);
/* 116 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachDirty(Queues instance) {
/* 121 */     log.debug("attaching dirty Queues instance");
/*     */     try {
/* 123 */       getSession().saveOrUpdate(instance);
/* 124 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 126 */       log.error("attach failed", re);
/* 127 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachClean(Queues instance) {
/* 132 */     log.debug("attaching clean Queues instance");
/*     */     try {
/* 134 */       getSession().lock(instance, LockMode.NONE);
/* 135 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 137 */       log.error("attach failed", re);
/* 138 */       throw re;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\QueuesDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */