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
import com.frank.dao.BedDao;
import com.frank.entity.Bed;
import com.frank.service.BedService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class BedServiceImpl extends BaseServiceImpl<Bed> implements BedService{
	 
	@Autowired
	private BedDao bedDao;
	@Override
	public BaseDao<Bed> getBaseDao() {
		return bedDao;
	}

}
