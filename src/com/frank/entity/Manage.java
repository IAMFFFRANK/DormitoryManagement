package com.frank.entity;

import java.io.Serializable;

/**
 * 宿舍楼管理员
 * @author 
 *
 */
public class Manage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String userName;//账号用户名
	private String passWord;//账号密码
	private String nickName;//姓名
	private Integer sex;//性别 
	private String teacherId;//教工号
	private String jiguan;//籍贯
	private String phone;//手机号
	private String ifManage;//是否分配管理宿舍，已分配的不能删除,0:未分配，1：已分配
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassWord()
	{
		return passWord;
	}
	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}
	public String getNickName()
	{
		return nickName;
	}
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}
	public Integer getSex()
	{
		return sex;
	}
	public void setSex(Integer sex)
	{
		this.sex = sex;
	}
	public String getTeacherId()
	{
		return teacherId;
	}
	public void setTeacherId(String teacherId)
	{
		this.teacherId = teacherId;
	}
	public String getJiguan()
	{
		return jiguan;
	}
	public void setJiguan(String jiguan)
	{
		this.jiguan = jiguan;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
	public String getIfManage()
	{
		return ifManage;
	}
	public void setIfManage(String ifManage)
	{
		this.ifManage = ifManage;
	}
	
}
