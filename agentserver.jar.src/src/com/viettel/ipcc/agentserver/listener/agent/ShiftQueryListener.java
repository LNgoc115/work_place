/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.dao.Shifts;
/*    */ import com.viettel.ipcc.agentserver.dao.ShiftsDAO;
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.List;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class ShiftQueryListener
/*    */ {
/*    */   public String[] queryShift()
/*    */   {
/* 13 */     ShiftsDAO shiftdao = (ShiftsDAO)Utils.getCtx().getBean("ShiftsDAO");
/* 14 */     List listAllShifts = shiftdao.findAll2();
/*    */     
/* 16 */     String[] res = new String[listAllShifts.size()];
/*    */     
/* 18 */     for (int i = 0; i < listAllShifts.size(); i++) {
/* 19 */       Shifts s = (Shifts)listAllShifts.get(i);
/* 20 */       res[i] = 
/* 21 */         (s.getShiftId() + ";" + s.getShiftName() + ";" + s.getShiftFrom() + ";" + s.getShiftTo());
/*    */     }
/*    */     
/*    */ 
/* 25 */     return res;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\ShiftQueryListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */