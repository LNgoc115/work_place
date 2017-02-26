/*    */ package com.viettel.ipcc.agentserver.dao;
/*    */ 
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaseHibernateDAO
/*    */   implements IBaseHibernateDAO
/*    */ {
/*    */   public Session getSession()
/*    */   {
/* 13 */     return HibernateSessionFactory.getSession();
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\BaseHibernateDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */