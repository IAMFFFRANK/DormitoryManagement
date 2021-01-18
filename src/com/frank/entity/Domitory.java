package com.frank.entity;

import java.io.Serializable;

/**
 * 宿舍
 * @author 
 *
 */
public class Domitory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String domitoryNo; //宿舍号
	private String buildingId;//所属的宿舍楼id
	private String buildingNo;
	private String tel;//宿舍电话
	private Integer type;//宿舍类型 1：单人间 2 ：双人间 4：四人间 6：六人间
	private Integer ruzhuNum;//入住人数

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getDomitoryNo()
	{
		return domitoryNo;
	}
	public void setDomitoryNo(String domitoryNo)
	{
		this.domitoryNo = domitoryNo;
	}

	public String getBuildingId()
	{
		return buildingId;
	}
	public void setBuildingId(String buildingId)
	{
		this.buildingId = buildingId;
	}
	public String getBuildingNo()
	{
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo)
	{
		this.buildingNo = buildingNo;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public Integer getType()
	{
		return type;
	}
	public void setType(Integer type)
	{
		this.type = type;
	}

	public Integer getRuzhuNum() {
		return ruzhuNum;
	}

	public void setRuzhuNum(Integer ruzhuNum) {
		this.ruzhuNum = ruzhuNum;
	}
	
}
