package com.taikang.udp.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.taikang.udp.framework.common.util.logger.LoggerFactory;

public class CommonUtil {
	public static final Logger logger = LoggerFactory.getLogger();

	public static boolean closeTheadPool(ThreadPoolTaskExecutor taskExecutor)
			throws InterruptedException {

		if (taskExecutor == null) {
			return false;
		} else {
			while (true) {
				int count = taskExecutor.getActiveCount();
				logger.debug("目前的线程数为：" + count);
				Thread.sleep(1000);
				if (count == 0) {
					taskExecutor.shutdown();
					break;
				}
			}
		}
		return true;
	}
	
	/**
	 * 生成随机字符串.
	 * @param length 字符串长度
	 * @return 随机字符串
	 */
	public static String getRandomStr (final int length) {
		int strLength = 4;
		if (length > 0) {
			strLength = length;
		}
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
	    StringBuffer sb = new StringBuffer(); 
	    Random random = new Random(); 
	    int range = buffer.length(); 
	    for (int i = 0; i < strLength; i ++) { 
	        sb.append(buffer.charAt(random.nextInt(range))); 
	    } 
	    return sb.toString(); 
	}
	
	/**
	 * 生成服务器保存的文件名。
	 * @param suffix 文件后缀
	 * @return 随机文件名
	 */
	public static String generateFileName (final String suffix) {
		if (suffix == null || "".equals(suffix)) {
			return "";
		}
		StringBuffer name = new StringBuffer();
		Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        String base = format.format(date);
        name.append(base).append(getRandomStr(4)).append(".").append(suffix);
        return name.toString();
	}
}
