package com.frank.entity;

import java.io.Serializable;

/**
 * 系统管理员
 * @author 
 *
 */
public class Major implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;//学院名称
	private String collegeId;//所属学院id
	private String collegeName;//所属学院名称
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getCollegeId()
	{
		return collegeId;
	}
	public void setCollegeId(String collegeId)
	{
		this.collegeId = collegeId;
	}
	public String getCollegeName()
	{
		return collegeName;
	}
	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}
	
}
