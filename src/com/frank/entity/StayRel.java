package com.frank.entity;

import java.io.Serializable;

/**
 * 入住，记录学生和床位的关系,一对一关系，一个学生只能入住一个床位
 * @author 
 *
 */
public class StayRel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String studentId;//学生id
	private String bedId;//床位id
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getStudentId()
	{
		return studentId;
	}
	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}
	public String getBedId()
	{
		return bedId;
	}
	public void setBedId(String bedId)
	{
		this.bedId = bedId;
	}
	
}
