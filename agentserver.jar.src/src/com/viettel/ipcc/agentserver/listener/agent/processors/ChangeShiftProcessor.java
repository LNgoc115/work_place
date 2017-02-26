/*    */ package com.viettel.ipcc.agentserver.listener.agent.processors;
/*    */ 
/*    */ import com.viettel.ipcc.agentdesktop.protocol.request.ShiftInfoRequest;
/*    */ import com.viettel.ipcc.agentserver.common.Agents;
/*    */ import com.viettel.ipcc.agentserver.listener.agent.AgentDesktopJob;
/*    */ 
/*    */ public class ChangeShiftProcessor extends ProcessorBase
/*    */ {
/*    */   public void process(AgentDesktopJob job)
/*    */   {
/* 11 */     Agents agent = null;
/* 12 */     if ((agent = checkLogInAndDisconnect(job.getSession())) != null) {
/* 13 */       ShiftInfoRequest request = (ShiftInfoRequest)job
/* 14 */         .getAgentDesktopMsg();
/*    */       
/* 16 */       String shiftid = request.getShiftId();
/*    */       
/* 18 */       this.logger
/* 19 */         .info("change shiftid for: " + agent + " shiftid: " + 
/* 20 */         shiftid);
/*    */       
/* 22 */       if (agent != null) {
/* 23 */         agent.setShiftid(shiftid);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\processors\ChangeShiftProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */