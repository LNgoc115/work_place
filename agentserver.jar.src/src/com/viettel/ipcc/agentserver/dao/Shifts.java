/*    */ package com.viettel.ipcc.agentserver.dao;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Shifts
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4611346714569943200L;
/*    */   private String shiftId;
/*    */   private String shiftName;
/*    */   private String shiftFrom;
/*    */   private String shiftTo;
/*    */   
/*    */   public Shifts() {}
/*    */   
/*    */   public Shifts(String shiftId, String shiftName, String shiftFrom, String shiftTo)
/*    */   {
/* 29 */     this.shiftId = shiftId;
/* 30 */     this.shiftName = shiftName;
/* 31 */     this.shiftFrom = shiftFrom;
/* 32 */     this.shiftTo = shiftTo;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getShiftId()
/*    */   {
/* 38 */     return this.shiftId;
/*    */   }
/*    */   
/*    */   public void setShiftId(String shiftId) {
/* 42 */     this.shiftId = shiftId;
/*    */   }
/*    */   
/*    */   public String getShiftName() {
/* 46 */     return this.shiftName;
/*    */   }
/*    */   
/*    */   public void setShiftName(String shiftName) {
/* 50 */     this.shiftName = shiftName;
/*    */   }
/*    */   
/*    */   public String getShiftFrom() {
/* 54 */     return this.shiftFrom;
/*    */   }
/*    */   
/*    */   public void setShiftFrom(String shiftFrom) {
/* 58 */     this.shiftFrom = shiftFrom;
/*    */   }
/*    */   
/*    */   public String getShiftTo() {
/* 62 */     return this.shiftTo;
/*    */   }
/*    */   
/*    */   public void setShiftTo(String shiftTo) {
/* 66 */     this.shiftTo = shiftTo;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\dao\Shifts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */