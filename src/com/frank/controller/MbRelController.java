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
import com.frank.entity.MbRel;
import com.frank.service.CollegeService;
import com.frank.service.MajorService;
import com.frank.service.MbRelService;

/**
 * @author Tommy
 * @date 2021年1月18日下午4:56:47
 */
@Controller
@RequestMapping("/mbRel")
public class MbRelController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private MbRelService mbRelService;
	
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
	public String findByObj(MbRel mbRel, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<MbRel> list = mbRelService.listAllByEntity(mbRel);
		/*for(MbRel m : list){
			Major major = majorService.load(m.getMajorId());
			College college = collegeService.load(major.getCollegeId());
			m.setMajorName(major.getName());
			m.setCollegeName(college.getName());
		}*/
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", mbRel);
		return "mbRel/mbRel";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		List<Major> majorList = majorService.listAll();
		model.addAttribute("majorList", majorList);
		return "mbRel/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(MbRel mbRel,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 mbRel.setId(uuid);
    	 mbRelService.insert(mbRel);
    	 return "redirect:/mbRel/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		List<Major> majorList = majorService.listAll();
		model.addAttribute("majorList", majorList);
		
		MbRel obj = mbRelService.load(id);
		model.addAttribute("obj",obj);
		return "mbRel/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(MbRel mbRel, Model model, HttpServletRequest request, HttpServletResponse response) {
		mbRelService.update(mbRel);
		return "redirect:/mbRel/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		mbRelService.deleteById(id);
		return "redirect:/mbRel/findByObj.do";
	}
	
}
