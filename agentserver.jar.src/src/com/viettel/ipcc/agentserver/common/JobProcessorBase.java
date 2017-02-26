/*     */ package com.viettel.ipcc.agentserver.common;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.log4j.Logger;
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
/*     */ 
/*     */ 
/*     */ public abstract class JobProcessorBase
/*     */   implements IThreadProcessor
/*     */ {
/*  26 */   protected Set<JobBase> setJob = null;
/*     */   
/*  28 */   private long sleepTime = 100L;
/*  29 */   private Logger logger = Logger.getLogger(JobProcessorBase.class);
/*     */   
/*     */   public JobProcessorBase() {
/*  32 */     this.setJob = Collections.synchronizedSet(new TreeSet(
/*  33 */       new JobComparator()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void process()
/*     */   {
/*  40 */     Object[] allJob = this.setJob.toArray();
/*     */     
/*  42 */     List<JobBase> listJob = new ArrayList();
/*     */     Object[] arrayOfObject1;
/*  44 */     int j = (arrayOfObject1 = allJob).length; for (int i = 0; i < j; i++) { Object obj = arrayOfObject1[i];
/*  45 */       JobBase j = (JobBase)obj;
/*  46 */       if (this.setJob.remove(j)) {
/*  47 */         listJob.add(j);
/*     */         
/*  49 */         if (System.currentTimeMillis() - j.getStartTime() > 5000L) {
/*  50 */           this.logger.warn("Job: " + j + " in queue too long time: " + 
/*  51 */             (System.currentTimeMillis() - j.getStartTime()) / 
/*  52 */             1000L + "s");
/*     */         }
/*     */       }
/*     */     }
/*  56 */     if (listJob.size() > 0) {
/*     */       try {
/*  58 */         processJob(listJob);
/*     */       } catch (Exception ex) {
/*  60 */         ex.printStackTrace();
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/*  65 */       Thread.sleep(getSleepTime());
/*     */     }
/*     */     catch (InterruptedException e) {
/*  68 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void addJob(JobBase j)
/*     */   {
/*  74 */     this.setJob.add(j);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract void processJob(List<JobBase> paramList);
/*     */   
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  84 */     Set<testjob> ss = new TreeSet(new Comparator()
/*     */     {
/*     */ 
/*     */       public int compare(JobProcessorBase.testjob o1, JobProcessorBase.testjob o2)
/*     */       {
/*  89 */         if (o1.id.equals(o2.id)) {
/*  90 */           return 0;
/*     */         }
/*  92 */         return o1.id.longValue() > o2.id.longValue() ? 1 : -1;
/*     */       }
/*     */       
/*  95 */     });
/*  96 */     testjob j1 = new testjob();
/*  97 */     testjob j2 = new testjob();
/*  98 */     j1.id = Long.valueOf(1L);
/*  99 */     j2.id = Long.valueOf(2L);
/* 100 */     ss.add(j1);
/* 101 */     ss.add(j2);
/* 102 */     for (testjob j : ss) {
/* 103 */       System.out.println(j.id);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSleepTime(long sleepTime) {
/* 108 */     this.sleepTime = sleepTime;
/*     */   }
/*     */   
/*     */   public long getSleepTime() {
/* 112 */     return this.sleepTime;
/*     */   }
/*     */   
/*     */   static class testjob
/*     */   {
/*     */     public Long id;
/*     */   }
/*     */ }


/* Location:              D:\Source code\share source code\Share source\ipcc\IPCC_19_09_2011\agentserver\agentserver-01\lib\agentserver.jar!\com\viettel\ipcc\agentserver\common\JobProcessorBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */