package com.xxl.job.admin;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 
 * misfire test
 * 
 * @author Dorae
 *
 */
public class MyQuartzJobBean extends QuartzJobBean {

	@SuppressWarnings("unused")
	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		Trigger trigger = jobexecutioncontext.getTrigger();
		JobDataMap map = jobexecutioncontext.getJobDetail().getJobDataMap();
		// String triggerName = trigger.getName();
		// String group = trigger.getGroup();
		Date lastalivetime = new Date();
		System.out.println("________________________testMethod1.......1" + lastalivetime);
	}
}
