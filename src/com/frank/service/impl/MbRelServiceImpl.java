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
import com.frank.dao.MbRelDao;
import com.frank.entity.MbRel;
import com.frank.service.MbRelService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class MbRelServiceImpl extends BaseServiceImpl<MbRel> implements MbRelService{
	 
	@Autowired
	private MbRelDao mbRelDao;
	@Override
	public BaseDao<MbRel> getBaseDao() {
		return mbRelDao;
	}

}
