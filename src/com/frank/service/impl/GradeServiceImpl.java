/**
 * @ClassName:  
 * @Description: 
 * @author gradeistrator
 * @date - 2017年02月18日 12时25分55秒
 */
package com.frank.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.base.BaseDao;
import com.frank.base.BaseServiceImpl;
import com.frank.dao.GradeDao;
import com.frank.entity.Grade;
import com.frank.service.GradeService;

/**
 * @ClassName:  
 * @Description: 
 * @author gradeistrator
 */

@Service
public class GradeServiceImpl extends BaseServiceImpl<Grade> implements GradeService{
	 
	@Autowired
	private GradeDao gradeDao;
	@Override
	public BaseDao<Grade> getBaseDao() {
		return gradeDao;
	}

}
