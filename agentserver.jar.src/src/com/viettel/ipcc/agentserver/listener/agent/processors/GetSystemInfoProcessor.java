/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.common.SystemInfo;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.SystemInfoResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.dao.HibernateSessionFactory;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AcdServerRequestSender;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.hibernate.SQLQuery;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class GetSystemInfoProcessor extends ProcessorBase
/*    */ {
/* 21 */   private Logger logger = Logger.getLogger(GetSystemInfoProcessor.class);
/*    */   
/*    */ 
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 26 */     Agents agent = null;
/* 27 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 28 */       this.logger.info("process : get total answered call on each called");
/* 29 */       LinkedList<String> callInfoList = new LinkedList();
/*    */       
/* 31 */       Session session = HibernateSessionFactory.getSessionFactory().openSession();
/* 32 */       String sqlCallStat = 
/* 33 */         " select a.description, a.queue_id, b.total from queues a,  (select queue_id, count(*) as total  from (select a.agent_id, qa.queue_id from agents a, queue_agent qa  where a.agent_id= qa.agent_id and a.system_status ='NOT AVAILABLE' and a.user_status <> 'LOGOUT')  group by queue_id) b  where a.queue_id = b.queue_id ";
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 40 */       SQLQuery sqlQueryCallStat = session.createSQLQuery(sqlCallStat);
/* 41 */       List listRes = sqlQueryCallStat.list();
/* 42 */       if (listRes.size() > 0) {
/* 43 */         for (Object obj : listRes) {
/* 44 */           Object[] row = (Object[])obj;
/* 45 */           String callInfo = row[0].toString() + "," + row[1].toString() + "," + row[2].toString();
/* 46 */           callInfoList.add(callInfo);
/*    */         }
/*    */       } else {
/* 49 */         String sqlQueueStat = "select q.description, q.queue_id from queues q";
/* 50 */         SQLQuery sqlQueryQueueStat = session.createSQLQuery(sqlQueueStat);
/* 51 */         List listQueueRes = sqlQueryQueueStat.list();
/* 52 */         for (Object obj : listQueueRes) {
/* 53 */           Object[] row = (Object[])obj;
/* 54 */           String callInfo = row[0].toString() + "," + row[1].toString() + "," + "0";
/* 55 */           callInfoList.add(callInfo);
/*    */         }
/*    */       }
/*    */       
/* 59 */       session.close();
/*    */       
/* 61 */       this.logger.info("process : get total waiting call in each queue");
/* 62 */       AcdServerRequestSender acdSender = (AcdServerRequestSender)Utils.getCtx().getBean("AcdServerRequestSender");
/* 63 */       Object queueMap = acdSender.sendGetNumCallInEachQueue();
/*    */       
/* 65 */       this.logger.debug("results  queueMap : " + queueMap + " && callMap : " + callInfoList.toString());
/* 66 */       SystemInfo sysInfo = new SystemInfo((Map)queueMap, callInfoList);
/* 67 */       Utils.sendMessageToAgent(job.getSession(), new SystemInfoResponse(sysInfo));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\GetSystemInfoProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */