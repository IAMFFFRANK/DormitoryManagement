package com.frank.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frank.base.BaseController;
import com.frank.entity.Admin;
import com.frank.entity.Manage;
import com.frank.entity.Student;
import com.frank.service.AdminService;
import com.frank.service.ManageService;
import com.frank.service.StudentService;


/**
 * @author Tommy
 * @date 2021年1月18日下午4:56:17
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ManageService manageService;
	
	@Autowired
	private StudentService studentService;
	
	
	/**
	 * 跳转登陆
	 * @return
	 */
	@RequestMapping(value = "/login.do")
	public String login(){
		return "login/login";
		
	}
	
	/**
	 * 欢迎页面跳转
	 * @return
	 */
	@RequestMapping(value = "/welcome.do")
	public String welcome(){
		return "login/welcome";
		
	}
	
	@RequestMapping(value = "/toLogin.do")
	public String findByObj(String userName,String passWord, String type, Model model, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		 
		session.removeAttribute("user");
		session.removeAttribute("userId");
		//1.管理员 2.宿舍楼管理员 3.学生
		if(type.equals("1")){
			Admin admin = new Admin();
			admin.setUserName(userName);
			admin.setPassWord(passWord);
			List<Admin> list = adminService.listAllByEntity(admin);
			if(list.size() > 0 ){
				//登录成功,跳到系统管理员页面
				request.getSession().setAttribute("user", admin);
				return "login/aIndex";
			}else{
				//登录失败
				return "login/login";
			}
		}else if(type.equals("2")){
			Manage manage = new Manage();
			manage.setUserName(userName);
			manage.setPassWord(passWord);
			List<Manage> list = manageService.listAllByEntity(manage);
			if(list.size() > 0 ){
				//登录成功,跳到宿舍管理员页面
				request.getSession().setAttribute("user", list.get(0));
				return "login/mIndex";
			}else{
				//登录失败
				return "login/login";
			}
		}else{
			Student student = new Student();
			student.setUserName(userName);
			student.setPassWord(passWord);
			List<Student> list = studentService.listAllByEntity(student);
			if(list.size() > 0 ){
				//登录成功,跳到学生页面
				request.getSession().setAttribute("user", list.get(0));
				return "login/sIndex";
			}else{
				//登录失败
				return "login/login";
			}
		}
		
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/tuichu.do")
	  public String tuichu(HttpSession session){
        session.invalidate();
        return "login/login";
    }
	
	@RequestMapping(value = "/utu.do")
	  public String utu(HttpSession session){
      session.invalidate();
      return "redirect:/login/index.do";
  }
	

}
