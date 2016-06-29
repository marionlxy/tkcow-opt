package com.taikang.udp.threadpool.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.threadpool.service.IRunnerTaskService;
import com.taikang.udp.threadpool.service.ITkTaskService;

@Scope("prototype")
@Service(IRunnerTaskService.SERVICE_ID)
public class RunnerTaskServiceImpl extends BaseServiceImpl implements Runnable,IRunnerTaskService {
	
	private ITkTaskService tkTaskService;
    
	public Dto dto;

	@Override
	public void run() {
		logger.debug(" is running.");
		if (tkTaskService != null) {
			tkTaskService.execute(dto);
		}
		logger.debug("*******************************" + Thread.currentThread().getName());
		logger.debug(" is running end.");
	}

	/**
	 * @return the tkTaskService
	 */
	public ITkTaskService getTkTaskService() {
		return tkTaskService;
	}

	/**
	 * @return the dto
	 */
	public Dto getDto() {
		return dto;
	}

	/**
	 * @param tkTaskService the tkTaskService to set
	 */
	public void setTkTaskService(ITkTaskService tkTaskService) {
		this.tkTaskService = tkTaskService;
	}

	/**
	 * @param dto the dto to set
	 */
	public void setDto(Dto dto) {
		this.dto = dto;
	}
	
	
	
}
