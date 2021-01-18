/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 * @date - 2017年02月18日 12时25分55秒
 */
package com.frank.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.base.BaseDao;
import com.frank.base.BaseServiceImpl;
import com.frank.dao.ManageDao;
import com.frank.entity.Manage;
import com.frank.service.ManageService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService{
	 
	@Autowired
	private ManageDao manageDao;
	@Override
	public BaseDao<Manage> getBaseDao() {
		return manageDao;
	}

}
