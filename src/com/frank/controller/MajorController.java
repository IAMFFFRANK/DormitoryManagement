package com.frank.controller;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frank.base.BaseController;
import com.frank.entity.College;
import com.frank.entity.Major;
import com.frank.service.CollegeService;
import com.frank.service.MajorService;

/**
 * @author Tommy
 * @date 2021年1月18日下午4:56:30
 */
@Controller
@RequestMapping("/major")
public class MajorController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private CollegeService collegeService;
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Major major, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Major> list = majorService.listAllByEntity(major);
		for(Major m : list){
			College college = collegeService.load(m.getCollegeId());
			m.setCollegeName(college.getName());
		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", major);
		return "major/major";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		List<College> collegeList = collegeService.listAll();
		model.addAttribute("collegeList", collegeList);
		return "major/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(Major major,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 major.setId(uuid);
    	 majorService.insert(major);
    	 return "redirect:/major/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		List<College> collegeList = collegeService.listAll();
		model.addAttribute("collegeList", collegeList);
		
		Major obj = majorService.load(id);
		model.addAttribute("obj",obj);
		return "major/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Major major, Model model, HttpServletRequest request, HttpServletResponse response) {
		majorService.update(major);
		return "redirect:/major/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		majorService.deleteById(id);
		return "redirect:/major/findByObj.do";
	}
	
}
