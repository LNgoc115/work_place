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
/*     */ public class QueueParamDAO
/*     */   extends BaseHibernateDAO
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(QueueParamDAO.class);
/*     */   public static final String DESCRIPTION = "description";
/*     */   public static final String VALUE = "value";
/*     */   
/*     */   public void save(QueueParam transientInstance)
/*     */   {
/*  30 */     log.debug("saving QueueParam instance");
/*     */     try {
/*  32 */       getSession().save(transientInstance);
/*  33 */       log.debug("save successful");
/*     */     } catch (RuntimeException re) {
/*  35 */       log.error("save failed", re);
/*  36 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(QueueParam persistentInstance) {
/*  41 */     log.debug("deleting QueueParam instance");
/*     */     try {
/*  43 */       getSession().delete(persistentInstance);
/*  44 */       log.debug("delete successful");
/*     */     } catch (RuntimeException re) {
/*  46 */       log.error("delete failed", re);
/*  47 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public QueueParam findById(QueueParamId id) {
/*  52 */     log.debug("getting QueueParam instance with id: " + id);
/*     */     try {
/*  54 */       return (QueueParam)getSession().get(
/*  55 */         "com.viettel.ipcc.agentserver.dao.QueueParam", id);
/*     */     }
/*     */     catch (RuntimeException re) {
/*  58 */       log.error("get failed", re);
/*  59 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByExample(QueueParam instance) {
/*  64 */     log.debug("finding QueueParam instance by example");
/*     */     try {
/*  66 */       List results = getSession().createCriteria(
/*  67 */         "com.viettel.ipcc.agentserver.dao.QueueParam").add(
/*  68 */         Example.create(instance)).list();
/*  69 */       log.debug("find by example successful, result size: " + 
/*  70 */         results.size());
/*  71 */       return results;
/*     */     } catch (RuntimeException re) {
/*  73 */       log.error("find by example failed", re);
/*  74 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByProperty(String propertyName, Object value) {
/*  79 */     log.debug("finding QueueParam instance with property: " + propertyName + 
/*  80 */       ", value: " + value);
/*     */     try {
/*  82 */       String queryString = "from QueueParam as model where model." + 
/*  83 */         propertyName + "= ?";
/*  84 */       Query queryObject = getSession().createQuery(queryString);
/*  85 */       queryObject.setParameter(0, value);
/*  86 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/*  88 */       log.error("find by property name failed", re);
/*  89 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByDescription(Object description) {
/*  94 */     return findByProperty("description", description);
/*     */   }
/*     */   
/*     */   public List findByValue(Object value) {
/*  98 */     return findByProperty("value", value);
/*     */   }
/*     */   
/*     */   public List findAll() {
/* 102 */     log.debug("finding all QueueParam instances");
/*     */     try {
/* 104 */       String queryString = "from QueueParam";
/* 105 */       Query queryObject = getSession().createQuery(queryString);
/* 106 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 108 */       log.error("find all failed", re);
/* 109 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public QueueParam merge(QueueParam detachedInstance) {
/* 114 */     log.debug("merging QueueParam instance");
/*     */     try {
/* 116 */       QueueParam result = (QueueParam)getSession().merge(
/* 117 */         detachedInstance);
/* 118 */       log.debug("merge successful");
/* 119 */       return result;
/*     */     } catch (RuntimeException re) {
/* 121 */       log.error("merge failed", re);
/* 122 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachDirty(QueueParam instance) {
/* 127 */     log.debug("attaching dirty QueueParam instance");
/*     */     try {
/* 129 */       getSession().saveOrUpdate(instance);
/* 130 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 132 */       log.error("attach failed", re);
/* 133 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachClean(QueueParam instance) {
/* 138 */     log.debug("attaching clean QueueParam instance");
/*     */     try {
/* 140 */       getSession().lock(instance, LockMode.NONE);
/* 141 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 143 */       log.error("attach failed", re);
/* 144 */       throw re;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\QueueParamDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */