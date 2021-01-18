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
import com.frank.dao.MajorDao;
import com.frank.entity.Major;
import com.frank.service.MajorService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class MajorServiceImpl extends BaseServiceImpl<Major> implements MajorService{
	 
	@Autowired
	private MajorDao majorDao;
	@Override
	public BaseDao<Major> getBaseDao() {
		return majorDao;
	}

}
