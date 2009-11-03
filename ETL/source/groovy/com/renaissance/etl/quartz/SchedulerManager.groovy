package com.renaissance.etl.quartz
import org.quartz.*
import org.quartz.helpers.*

def groupName = "Stock-ETL"
SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
Scheduler sched = schedFact.getScheduler();
sched.start();

//DownloadTransactionRecordJob
JobDetail downloadTransactionRecordJob = 
	new JobDetail("DownloadTransactionRecord", groupName, DownloadTransactionRecordJob.class)

//Daily Trigger 
Trigger dailyTrigger = TriggerUtils.makeDailyTrigger(22,40);
dailyTrigger.setGroup(groupName);
dailyTrigger.setName("DailyTrigger");
dailyTrigger.setStartTime(new Date());

sched.scheduleJob(downloadTransactionRecordJob, dailyTrigger);

//sched.shutdown()