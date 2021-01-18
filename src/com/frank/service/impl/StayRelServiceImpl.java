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
import com.frank.dao.StayRelDao;
import com.frank.entity.StayRel;
import com.frank.service.StayRelService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class StayRelServiceImpl extends BaseServiceImpl<StayRel> implements StayRelService{
	 
	@Autowired
	private StayRelDao stayRelDao;
	@Override
	public BaseDao<StayRel> getBaseDao() {
		return stayRelDao;
	}

}
