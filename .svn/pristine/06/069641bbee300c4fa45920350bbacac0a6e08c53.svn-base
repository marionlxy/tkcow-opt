package com.taikang.udp.sys.action;
import java.util.List;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.model.UserBO;


/**
  * IUserAction
  */

public interface IUserAction extends IBaseAction { 

	final String ACTION_ID = "userAction"; 	
	
	public CurrentPage queryByCondition(CurrentPage page);
	
	public List<UserBO> findAll(UserBO param);
	
	public List<UserBO> verifyAlreadyExists(UserBO user);

}
