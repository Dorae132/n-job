package com.xxl.job.admin;

import java.util.concurrent.TimeUnit;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * misfire test
 * 
 * @author Dorae
 *
 */
public class MisFireTest {

	public ApplicationContext app;
	public Scheduler sched;

	public String jobName = "trigger22";

	public String jobGroup = "nsb_test_test";

	public void run() throws Exception {
		System.out.println("------- Initializing -------------------");
		System.out.println("------- Initialization Complete -----------");
		System.out.println("------- Scheduling Jobs -----------");

		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		JobKey jobKey = new JobKey(jobName, jobGroup);

		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * ? * * *")
				.withMisfireHandlingInstructionDoNothing();
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder)
				.build();
		// trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);

		Class<? extends Job> jobClass_ = MyQuartzJobBean.class; // Class.forName(jobInfo.getJobClass());
		JobDetail jobDetail = JobBuilder.newJob(jobClass_).withIdentity(jobKey).build();

		sched.scheduleJob(jobDetail, trigger);

		System.out.println("------- Starting Scheduler ----------------");
		sched.start();
		System.out.println("------- Started Scheduler -----------------");

		try {
			Thread.sleep(10L * 1000L);
		} catch (Exception e) {

		}
		System.out.println("------- Shutting Down ---------------------");
		sched.pauseTrigger(triggerKey);

		try {
			Thread.sleep(10L * 1000L);// 暂停10秒
		} catch (Exception e) {

		}
		sched.resumeTrigger(triggerKey);
		System.out.println("------- Shutdown Complete -----------------");

		try {
			Thread.sleep(10L * 1000L);
		} catch (Exception e) {

		}
		sched.shutdown(true);

	}

	public static void main(String[] args) throws Exception {
		MisFireTest example = new MisFireTest();
		example.app = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/applicationcontext-base.xml",
				"classpath:spring/applicationcontext-xxl-job-admin.xml" });
		example.sched = (Scheduler) example.app.getBean("quartzScheduler");
		example.run();
		TimeUnit.HOURS.sleep(1);
	}
}
