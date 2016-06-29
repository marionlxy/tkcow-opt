package com.taikang.udp.threadpool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.threadpool.service.IRunnerTaskService;
import com.taikang.udp.threadpool.service.ITkTaskService;

public class AppThreadTest {
	public static void main(String[] args) {
		// ApplicationContext ctx = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"classpath:META-INF/app_config/context/context-*.xml");
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ctx
				.getBean("taskExecutor");
		for (int i = 0; i <= 100; i++) {
			IRunnerTaskService printTask1 = (IRunnerTaskService) ctx
					.getBean("runnerTaskService");
			printTask1.setDto(new BaseDto());
			printTask1.setTkTaskService((ITkTaskService) ctx
					.getBean("testThreadAction"));
			try {
				taskExecutor.execute((Runnable) printTask1);
			} catch(TaskRejectedException e){
				// 只要发现有异常就等待一段时间，直到没有异常为止，这样就能模仿阻塞队列
		        try{
		            Thread.sleep(1000);
		        }catch(Exception e2){}
		    }

			while (true) {
				int count = taskExecutor.getActiveCount();
				System.out.println("Active Threads : " + count);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (count == 0) {
					taskExecutor.shutdown();
					break;
				}
			}
		}
	}
}
