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
import com.frank.dao.DomitoryDao;
import com.frank.entity.Domitory;
import com.frank.service.DomitoryService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class DomitoryServiceImpl extends BaseServiceImpl<Domitory> implements DomitoryService{
	 
	@Autowired
	private DomitoryDao domitoryDao;
	@Override
	public BaseDao<Domitory> getBaseDao() {
		return domitoryDao;
	}

}
