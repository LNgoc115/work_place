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
/*     */ public class ParamsDAO
/*     */   extends BaseHibernateDAO
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(ParamsDAO.class);
/*     */   public static final String PARAM_NAME = "paramName";
/*     */   public static final String DESCRIPTION = "description";
/*     */   
/*     */   public void save(Params transientInstance)
/*     */   {
/*  30 */     log.debug("saving Params instance");
/*     */     try {
/*  32 */       getSession().save(transientInstance);
/*  33 */       log.debug("save successful");
/*     */     } catch (RuntimeException re) {
/*  35 */       log.error("save failed", re);
/*  36 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(Params persistentInstance) {
/*  41 */     log.debug("deleting Params instance");
/*     */     try {
/*  43 */       getSession().delete(persistentInstance);
/*  44 */       log.debug("delete successful");
/*     */     } catch (RuntimeException re) {
/*  46 */       log.error("delete failed", re);
/*  47 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Params findById(Long id) {
/*  52 */     log.debug("getting Params instance with id: " + id);
/*     */     try {
/*  54 */       return (Params)getSession().get(
/*  55 */         "com.viettel.ipcc.agentserver.dao.Params", id);
/*     */     }
/*     */     catch (RuntimeException re) {
/*  58 */       log.error("get failed", re);
/*  59 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByExample(Params instance) {
/*  64 */     log.debug("finding Params instance by example");
/*     */     try {
/*  66 */       List results = getSession().createCriteria(
/*  67 */         "com.viettel.ipcc.agentserver.dao.Params").add(
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
/*  79 */     log.debug("finding Params instance with property: " + propertyName + 
/*  80 */       ", value: " + value);
/*     */     try {
/*  82 */       String queryString = "from Params as model where model." + 
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
/*     */   public List findByParamName(Object paramName) {
/*  94 */     return findByProperty("paramName", paramName);
/*     */   }
/*     */   
/*     */   public List findByDescription(Object description) {
/*  98 */     return findByProperty("description", description);
/*     */   }
/*     */   
/*     */   public List findAll() {
/* 102 */     log.debug("finding all Params instances");
/*     */     try {
/* 104 */       String queryString = "from Params";
/* 105 */       Query queryObject = getSession().createQuery(queryString);
/* 106 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 108 */       log.error("find all failed", re);
/* 109 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public Params merge(Params detachedInstance) {
/* 114 */     log.debug("merging Params instance");
/*     */     try {
/* 116 */       Params result = (Params)getSession().merge(detachedInstance);
/* 117 */       log.debug("merge successful");
/* 118 */       return result;
/*     */     } catch (RuntimeException re) {
/* 120 */       log.error("merge failed", re);
/* 121 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachDirty(Params instance) {
/* 126 */     log.debug("attaching dirty Params instance");
/*     */     try {
/* 128 */       getSession().saveOrUpdate(instance);
/* 129 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 131 */       log.error("attach failed", re);
/* 132 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachClean(Params instance) {
/* 137 */     log.debug("attaching clean Params instance");
/*     */     try {
/* 139 */       getSession().lock(instance, LockMode.NONE);
/* 140 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 142 */       log.error("attach failed", re);
/* 143 */       throw re;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\ParamsDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */