/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.common.Area;
/*    */ import com.viettel.ipcc.agentdesktop.protocol.response.GetAreaResponse;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.dao.Zoon;
/*    */ import com.viettel.ipcc.agentserver.dao.ZoonDAO;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ 
/*    */ public class QueryZoonProcessor
/*    */   extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 20 */     Agents agent = null;
/* 21 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null)
/*    */     {
/* 23 */       ZoonDAO dao = (ZoonDAO)Utils.getCtx().getBean(
/* 24 */         "ZoonDAO");
/* 25 */       List list = dao.findAll2();
/*    */       
/* 27 */       List<Area> listArea = new ArrayList();
/*    */       
/* 29 */       for (Object o : list) {
/* 30 */         Zoon z = (Zoon)o;
/*    */         
/* 32 */         Area a = new Area(z.getZoonId(), z.getZoonName());
/*    */         
/* 34 */         listArea.add(a);
/*    */       }
/*    */       
/* 37 */       GetAreaResponse response = new GetAreaResponse(listArea);
/* 38 */       Utils.sendMessageToAgent(job.getSession(), response);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\QueryZoonProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */