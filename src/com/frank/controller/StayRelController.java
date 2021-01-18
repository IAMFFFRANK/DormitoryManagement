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
import com.frank.entity.StayRel;
import com.frank.service.CollegeService;
import com.frank.service.MajorService;
import com.frank.service.StayRelService;


/**
 * @author Tommy
 * @date 2021年1月18日下午4:56:52
 */
@Controller
@RequestMapping("/stayRel")
public class StayRelController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private StayRelService stayRelService;
	
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
	public String findByObj(StayRel stayRel, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<StayRel> list = stayRelService.listAllByEntity(stayRel);
		/*for(StayRel m : list){
			Major major = majorService.load(m.getMajorId());
			College college = collegeService.load(major.getCollegeId());
			m.setMajorName(major.getName());
			m.setCollegeName(college.getName());
		}*/
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", stayRel);
		return "stayRel/stayRel";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		List<Major> majorList = majorService.listAll();
		model.addAttribute("majorList", majorList);
		return "stayRel/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(StayRel stayRel,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 stayRel.setId(uuid);
    	 stayRelService.insert(stayRel);
    	 return "redirect:/stayRel/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		List<Major> majorList = majorService.listAll();
		model.addAttribute("majorList", majorList);
		
		StayRel obj = stayRelService.load(id);
		model.addAttribute("obj",obj);
		return "stayRel/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(StayRel stayRel, Model model, HttpServletRequest request, HttpServletResponse response) {
		stayRelService.update(stayRel);
		return "redirect:/stayRel/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		stayRelService.deleteById(id);
		return "redirect:/stayRel/findByObj.do";
	}
	
}
