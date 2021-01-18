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
import com.frank.dao.StudentDao;
import com.frank.entity.Student;
import com.frank.service.StudentService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService{
	 
	@Autowired
	private StudentDao studentDao;
	@Override
	public BaseDao<Student> getBaseDao() {
		return studentDao;
	}

}
