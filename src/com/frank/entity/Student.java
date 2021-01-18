package com.frank.entity;

import java.io.Serializable;

/**
 * 学生
 * @author 
 *
 */
public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String userName;//账号用户名
	private String passWord;//账号密码
	private String nickName;//姓名
	private Integer sex;//性别 
	private String studentId;//学号
	private String jiguan;//籍贯
	private String phone;//手机号
	private Integer ifRuzhu;//是否入住，0：未入住，1：已入住
	private String collegeId;//学院id
	private String majorId;//专业id
	private String gradeId;//年级id
	private String teamId;//班级id
	private String collegeName;//学院名称
	private String majorName;//专业名称
	private String gradeName;//年级名称
	private String teamName;//班级名称
	
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
	public String getStudentId()
	{
		return studentId;
	}
	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
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
	public Integer getIfRuzhu()
	{
		return ifRuzhu;
	}
	public void setIfRuzhu(Integer ifRuzhu)
	{
		this.ifRuzhu = ifRuzhu;
	}
	public String getCollegeId()
	{
		return collegeId;
	}
	public void setCollegeId(String collegeId)
	{
		this.collegeId = collegeId;
	}
	public String getMajorId()
	{
		return majorId;
	}
	public void setMajorId(String majorId)
	{
		this.majorId = majorId;
	}
	public String getGradeId()
	{
		return gradeId;
	}
	public void setGradeId(String gradeId)
	{
		this.gradeId = gradeId;
	}
	public String getTeamId()
	{
		return teamId;
	}
	public void setTeamId(String teamId)
	{
		this.teamId = teamId;
	}
	public String getCollegeName()
	{
		return collegeName;
	}
	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}
	public String getMajorName()
	{
		return majorName;
	}
	public void setMajorName(String majorName)
	{
		this.majorName = majorName;
	}
	public String getGradeName()
	{
		return gradeName;
	}
	public void setGradeName(String gradeName)
	{
		this.gradeName = gradeName;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
	
}
