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
/*     */ public class AgentZoonDAO
/*     */   extends BaseHibernateDAO
/*     */ {
/*  25 */   private static final Logger log = LoggerFactory.getLogger(AgentZoonDAO.class);
/*     */   
/*     */ 
/*     */   public void save(AgentZoon transientInstance)
/*     */   {
/*  30 */     log.debug("saving AgentZoon instance");
/*     */     try {
/*  32 */       getSession().save(transientInstance);
/*  33 */       log.debug("save successful");
/*     */     } catch (RuntimeException re) {
/*  35 */       log.error("save failed", re);
/*  36 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void delete(AgentZoon persistentInstance) {
/*  41 */     log.debug("deleting AgentZoon instance");
/*     */     try {
/*  43 */       getSession().delete(persistentInstance);
/*  44 */       log.debug("delete successful");
/*     */     } catch (RuntimeException re) {
/*  46 */       log.error("delete failed", re);
/*  47 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public AgentZoon findById(AgentZoonId id) {
/*  52 */     log.debug("getting AgentZoon instance with id: " + id);
/*     */     try {
/*  54 */       return (AgentZoon)getSession().get(
/*  55 */         "com.viettel.ipcc.agentserver.dao.AgentZoon", id);
/*     */     }
/*     */     catch (RuntimeException re) {
/*  58 */       log.error("get failed", re);
/*  59 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findByExample(AgentZoon instance) {
/*  64 */     log.debug("finding AgentZoon instance by example");
/*     */     try {
/*  66 */       List results = getSession().createCriteria(
/*  67 */         "com.viettel.ipcc.agentserver.dao.AgentZoon").add(
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
/*  79 */     log.debug("finding AgentZoon instance with property: " + propertyName + 
/*  80 */       ", value: " + value);
/*     */     try {
/*  82 */       String queryString = "from AgentZoon as model where model." + 
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
/*     */   public List findAll() {
/*  94 */     log.debug("finding all AgentZoon instances");
/*     */     try {
/*  96 */       String queryString = "from AgentZoon";
/*  97 */       Query queryObject = getSession().createQuery(queryString);
/*  98 */       return queryObject.list();
/*     */     } catch (RuntimeException re) {
/* 100 */       log.error("find all failed", re);
/* 101 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public List findAll2() {
/* 106 */     log.debug("finding all AgentZoon instances");
/*     */     try {
/* 108 */       Session session = HibernateSessionFactory.getSessionFactory()
/* 109 */         .openSession();
/*     */       
/* 111 */       String queryString = "from AgentZoon";
/*     */       
/* 113 */       Query queryObject = session.createQuery(queryString);
/*     */       
/* 115 */       List listRes = queryObject.list();
/* 116 */       session.close();
/*     */       
/* 118 */       return listRes;
/*     */     } catch (RuntimeException re) {
/* 120 */       log.error("find all failed", re);
/* 121 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public AgentZoon merge(AgentZoon detachedInstance) {
/* 126 */     log.debug("merging AgentZoon instance");
/*     */     try {
/* 128 */       AgentZoon result = (AgentZoon)getSession().merge(detachedInstance);
/* 129 */       log.debug("merge successful");
/* 130 */       return result;
/*     */     } catch (RuntimeException re) {
/* 132 */       log.error("merge failed", re);
/* 133 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachDirty(AgentZoon instance) {
/* 138 */     log.debug("attaching dirty AgentZoon instance");
/*     */     try {
/* 140 */       getSession().saveOrUpdate(instance);
/* 141 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 143 */       log.error("attach failed", re);
/* 144 */       throw re;
/*     */     }
/*     */   }
/*     */   
/*     */   public void attachClean(AgentZoon instance) {
/* 149 */     log.debug("attaching clean AgentZoon instance");
/*     */     try {
/* 151 */       getSession().lock(instance, LockMode.NONE);
/* 152 */       log.debug("attach successful");
/*     */     } catch (RuntimeException re) {
/* 154 */       log.error("attach failed", re);
/* 155 */       throw re;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\AgentZoonDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */