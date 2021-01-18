package com.frank.dao.impl;
import org.springframework.stereotype.Repository;
import com.frank.base.BaseDaoImpl;
import com.frank.dao.StudentDao;
import com.frank.entity.Student;
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao{
}
