package com.frank.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frank.base.BaseController;
import com.frank.entity.Grade;
import com.frank.service.GradeService;
import com.frank.utils.Pager;


/**
 * @author Tommy
 * @date 2021年1月18日下午4:56:11
 */
@Controller
@RequestMapping("/grade")
public class GradeController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private GradeService gradeService;
	
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Grade grade, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Grade> list = gradeService.listAllByEntity(grade);
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", grade);
		return "grade/grade";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(Grade grade, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(grade.getName())){
        	params.put("name", grade.getName());
		}
		//分页查询
		Pager<Grade> pagers = gradeService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", grade);
		return "grade/grade";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "grade/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(Grade grade,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 grade.setId(uuid);
    	 gradeService.insert(grade);
    	 return "redirect:/grade/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		Grade obj = gradeService.load(id);
		model.addAttribute("obj",obj);
		return "grade/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Grade grade, Model model, HttpServletRequest request, HttpServletResponse response) {
		gradeService.update(grade);
		return "redirect:/grade/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		gradeService.deleteById(id);
		return "redirect:/grade/findByObj.do";
	}
}
