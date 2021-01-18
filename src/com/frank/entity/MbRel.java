package com.frank.entity;

import java.io.Serializable;

/**
 * 记录管理员和宿舍楼的关系,多对一个关系，多个管理管理一个宿舍
 * @author 
 *
 */
public class MbRel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String manageId;//学生id
	private String buildingId;//床位id
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getManageId()
	{
		return manageId;
	}
	public void setManageId(String manageId)
	{
		this.manageId = manageId;
	}
	public String getBuildingId()
	{
		return buildingId;
	}
	public void setBuildingId(String buildingId)
	{
		this.buildingId = buildingId;
	}
	
	
}
