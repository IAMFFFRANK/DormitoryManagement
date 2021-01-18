package com.frank.entity;

import java.io.Serializable;

/**
 * 班级
 * @author 
 *
 */
public class Building implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String buildingNo; //宿舍楼号
	private String position;//位置
	private String layerNumber;//宿舍层数
	private String roomNumber;//每层房间数
	private String manageId;//管理员
	private String manageName;//管理员
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getBuildingNo()
	{
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo)
	{
		this.buildingNo = buildingNo;
	}
	public String getPosition()
	{
		return position;
	}
	public void setPosition(String position)
	{
		this.position = position;
	}
	public String getLayerNumber()
	{
		return layerNumber;
	}
	public void setLayerNumber(String layerNumber)
	{
		this.layerNumber = layerNumber;
	}
	public String getRoomNumber()
	{
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber)
	{
		this.roomNumber = roomNumber;
	}
	public String getManageId()
	{
		return manageId;
	}
	public void setManageId(String manageId)
	{
		this.manageId = manageId;
	}
	public String getManageName()
	{
		return manageName;
	}
	public void setManageName(String manageName)
	{
		this.manageName = manageName;
	}
	
	
}
