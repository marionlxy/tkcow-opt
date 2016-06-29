/**
 * 
 */
package com.taikang.udp.sys.util.sequence;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.taikang.udp.framework.common.util.logger.LoggerFactory;
import com.taikang.udp.sys.model.ComBO;
import com.taikang.udp.sys.service.impl.ComServiceImpl;

/**
 * 〈功能简述〉
 * @author duyq06
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年1月28日 下午3:06:20
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class SeqGeneratorTest /* extends AbstractTest*/ {
	public static final Logger logger = LoggerFactory.getLogger();
	@Test
	public void testBusinessSeqGenerator(){
		String str = BusinessSeqGenerator.getInstance("Biz_Seq").nextId();
		
		logger.debug("****************************************");
		logger.info("取得的 业务流水号为：" + str);
	}
}
