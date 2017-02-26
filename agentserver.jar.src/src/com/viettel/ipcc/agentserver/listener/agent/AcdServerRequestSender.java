/*    */ package com.viettel.ipcc.agentserver.listener.agent;
/*    */ 
/*    */ import com.viettel.ipcc.agentserver.utils.Utils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AcdServerRequestSender
/*    */ {
/*    */   private String listAcdServerUrl;
/*    */   private String updateUserStatusMethodName;
/*    */   private String getNumberCallInQueueMethodName;
/*    */   private volatile List<String> _listAcdServerUrl;
/*    */   private Random ran;
/* 22 */   private Logger logger = Logger.getLogger(AcdServerRequestSender.class);
/*    */   
/*    */   public AcdServerRequestSender() {
/* 25 */     this.ran = new Random();
/*    */   }
/*    */   
/*    */   public void initList() {
/* 29 */     this._listAcdServerUrl = new ArrayList();
/* 30 */     this._listAcdServerUrl.addAll(Arrays.asList(getListAcdServerUrl().split(";")));
/*    */   }
/*    */   
/*    */   private String selectUrl()
/*    */   {
/* 35 */     return (String)this._listAcdServerUrl.get(this.ran.nextInt(this._listAcdServerUrl.size()));
/*    */   }
/*    */   
/*    */   public Object sendUpdateUserStatus(String agent, String status) {
/* 39 */     String url = selectUrl();
/* 40 */     this.logger.info("send update user status to acdserver: " + url + " agent: " + 
/* 41 */       agent + " status: " + status);
/* 42 */     return Utils.sendXmlrpc(url, getUpdateUserStatusMethodName(), 
/* 43 */       new Object[] { agent, status });
/*    */   }
/*    */   
/*    */   public void setUpdateUserStatusMethodName(String updateUserStatusMethodName) {
/* 47 */     this.updateUserStatusMethodName = updateUserStatusMethodName;
/*    */   }
/*    */   
/*    */   public String getUpdateUserStatusMethodName() {
/* 51 */     return this.updateUserStatusMethodName;
/*    */   }
/*    */   
/*    */   public void setListAcdServerUrl(String listAcdServerUrl) {
/* 55 */     this.listAcdServerUrl = listAcdServerUrl;
/*    */   }
/*    */   
/*    */   public String getListAcdServerUrl() {
/* 59 */     return this.listAcdServerUrl;
/*    */   }
/*    */   
/*    */   public Map<String, Integer> sendGetNumCallInEachQueue() {
/* 63 */     Map<String, Integer> mapAllQueue = new HashMap();
/*    */     
/* 65 */     for (int i = 0; i < this._listAcdServerUrl.size(); i++) {
/* 66 */       String url = (String)this._listAcdServerUrl.get(i);
/*    */       
/* 68 */       Object[] listCallQueue = (Object[])Utils.sendXmlrpc(url, "AcdQueueServer.getNumberCallPerQueue", null);
/* 69 */       Object[] arrayOfObject1; int j = (arrayOfObject1 = listCallQueue).length; for (int i = 0; i < j; i++) { Object o = arrayOfObject1[i];
/* 70 */         String callQueue = o.toString();
/* 71 */         String[] strArr = callQueue.split(" , ");
/* 72 */         String key = strArr[0];
/* 73 */         int val = Integer.valueOf(strArr[1]).intValue();
/* 74 */         int oldValue = 0;
/*    */         
/* 76 */         if (mapAllQueue.containsKey(key)) {
/* 77 */           oldValue = ((Integer)mapAllQueue.get(key)).intValue();
/*    */         }
/*    */         
/* 80 */         mapAllQueue.put(key, Integer.valueOf(oldValue + val));
/*    */       }
/*    */     }
/*    */     
/* 84 */     return mapAllQueue;
/*    */   }
/*    */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\listener\agent\AcdServerRequestSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */