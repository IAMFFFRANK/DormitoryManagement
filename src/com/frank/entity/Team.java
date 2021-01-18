package com.frank.entity;

import java.io.Serializable;

/**
 * 班级
 * @author 
 *
 */
public class Team implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;//班级名称
	private String majorId;//所属专业id
	private String majorName;//所属专业名称
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
	public String getMajorId()
	{
		return majorId;
	}
	public void setMajorId(String majorId)
	{
		this.majorId = majorId;
	}
	public String getMajorName()
	{
		return majorName;
	}
	public void setMajorName(String majorName)
	{
		this.majorName = majorName;
	}
	public String getCollegeName()
	{
		return collegeName;
	}
	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}
	public String getCollegeId()
	{
		return collegeId;
	}
	public void setCollegeId(String collegeId)
	{
		this.collegeId = collegeId;
	}
	
}
