package com.frank.entity;

import java.io.Serializable;

/**
 * 床位
 * @author 
 *
 */
public class Bed implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String bedNo;//床位编号，例如a,b,c,d,e,f
	private String domitoryId;//所属宿舍id
	private String domitoryNo;//所属宿舍号
	private String buildingNo;//所属宿舍楼
	private String studentId;//学生id
	private String studentName;//入住学生姓名

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getBedNo()
	{
		return bedNo;
	}
	public void setBedNo(String bedNo)
	{
		this.bedNo = bedNo;
	}
	public String getDomitoryId()
	{
		return domitoryId;
	}
	public void setDomitoryId(String domitoryId)
	{
		this.domitoryId = domitoryId;
	}
	public String getStudentId()
	{
		return studentId;
	}
	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
	public String getDomitoryNo()
	{
		return domitoryNo;
	}
	public void setDomitoryNo(String domitoryNo)
	{
		this.domitoryNo = domitoryNo;
	}
	public String getBuildingNo()
	{
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo)
	{
		this.buildingNo = buildingNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
