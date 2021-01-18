/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 12时25分55秒
 */
package com.frank.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.base.BaseDao;
import com.frank.base.BaseServiceImpl;
import com.frank.dao.AdminDao;
import com.frank.entity.Admin;
import com.frank.service.AdminService;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 */

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{
	 
	@Autowired
	private AdminDao adminDao;
	@Override
	public BaseDao<Admin> getBaseDao() {
		return adminDao;
	}

}
