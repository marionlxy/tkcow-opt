package com.taikang.udp.threadpool.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.common.util.logger.LoggerFactory;
import com.taikang.udp.sys.util.CommonUtil;
import com.taikang.udp.sys.util.sequence.BusinessSeqGenerator;
import com.taikang.udp.sys.util.sequence.SequenceGenerator;
import com.taikang.udp.threadpool.service.IRunnerTaskService;
import com.taikang.udp.threadpool.service.ITkTaskService;

public class AppThreadTest extends Thread {
	public static final Logger logger = LoggerFactory.getLogger();
	
	@Test
	public void testThreadPool() {
		// ApplicationContext ctx = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"classpath:META-INF/app_config/context/context-*.xml");
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ctx
				.getBean("taskExecutor");
		
		//logger.debug("&&&&&&&&&&&:   " + i);
		IRunnerTaskService printTask1 = (IRunnerTaskService) ctx.getBean("runnerTaskService");
		printTask1.setDto(new BaseDto());
		printTask1.setTkTaskService((ITkTaskService)ctx.getBean("testThreadAction") );
		taskExecutor.execute((Runnable) printTask1);
		
		try {
			CommonUtil.closeTheadPool(taskExecutor);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private int count = 0;

	public void run() {
		/*long userid = SequenceGenerator.getInstance("userId", 6).nextId();
		System.out.println("第" + count + "次调用，userId=" + userid);*/
		String userid = BusinessSeqGenerator.getInstance("Biz_Seq").nextId();
		System.out.println("第" + count + "次调用，userId=" + userid);
		/*for (int i = 0; i < 10000; i++) {
			if (count > 0) {
				System.out.println("count= " + count--);
			}
		}*/
		
	}
	@Test
	public void testmain() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"classpath:META-INF/app_config/context/context-*.xml");
		AppThreadTest g = null;
		for (int i = 1; i <= 100; i++) {
			/*String userid = BusinessSeqGenerator.getInstance("Biz_Seq").nextId();
			System.out.println("第" + i + "次调用，userId=" + userid);*/
			long userid = SequenceGenerator.getInstance("userId", 6).nextId();
			System.out.println("第" + i + "次调用，userId=" + userid);
			/*g = new AppThreadTest();
			g.count = i;
			g.start();*/
		}

	}
}
