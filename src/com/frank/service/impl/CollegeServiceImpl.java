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
import com.frank.dao.CollegeDao;
import com.frank.entity.College;
import com.frank.service.CollegeService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class CollegeServiceImpl extends BaseServiceImpl<College> implements CollegeService{
	 
	@Autowired
	private CollegeDao collegeDao;
	@Override
	public BaseDao<College> getBaseDao() {
		return collegeDao;
	}

}
