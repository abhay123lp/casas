
package com.renaissance.etl.quartz
import org.quartz.*
import org.quartz.helpers.*

SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
Scheduler sched = schedFact.getScheduler();
sched.start();

JobDetail jobDetail = new JobDetail("printJob", "Test", DemoJob.class)

Trigger trigger = TriggerUtils.makeDailyTrigger(22,40);
trigger.setGroup("Test");
trigger.setName("secondTrigger");
trigger.setStartTime(new Date());

sched.scheduleJob(jobDetail, trigger);

//sched.shutdown()