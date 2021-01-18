package com.frank.controller;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.frank.base.BaseController;
import com.frank.entity.College;
import com.frank.service.CollegeService;
import com.frank.utils.JsonUtil2;
import com.frank.utils.Pager;
/**
 * @author Tommy
 * @date 2021年1月18日下午4:54:55
 */
@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController {
	/**
	 * 依赖注入 start dao/service/===
	 */
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
	public String findByObj(College college, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<College> list = collegeService.listAllByEntity(college);
		model.addAttribute("list", list);
		//分页查询
		/*Pager<College> pagers = collegeService.findByEntity(college);
		model.addAttribute("pagers", pagers);*/
		//存储查询条件
		model.addAttribute("obj", college);
		return "college/college";
	}
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(College college, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(college.getName())){
        	params.put("content", college.getName());
		}
        /*if(!isEmpty(college.getAddUserId())){
        	params.put("addUserId", college.getAddUserId());
		}*/
		//分页查询
		Pager<College> pagers = collegeService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", college);
		return "college/college";
	}
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "college/add";
	}
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(College college,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 college.setId(uuid);
    	 collegeService.insert(college);
    	 return "redirect:/college/findByObj.do";
	}
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		College obj = collegeService.load(id);
		model.addAttribute("obj",obj);
		return "college/update";
	}
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(College college, Model model, HttpServletRequest request, HttpServletResponse response) {
		collegeService.update(college);
		return "redirect:/college/findByObj.do";
	}
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		collegeService.deleteById(id);
		return "redirect:/college/findByObj.do";
	}
	// --------------------------------------- 华丽分割线 ------------------------------
	/**
	 * 分页查询 返回list json(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.json", method = RequestMethod.GET)
	@ResponseBody
	public String findByObjByEntity(College college, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<College> pagers = collegeService.findByEntity(college);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", college);
		return jsonObject.toString();
	}
	/**
	 * 分页查询 返回list json(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.json", method = RequestMethod.GET)
	@ResponseBody
	public String findByMapMap(College college, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(college.getName())){
        	params.put("content", college.getName());
		}
		//分页查询
		Pager<College> pagers = collegeService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", college);
		return jsonObject.toString();
	}
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(College college, Model model, HttpServletRequest request, HttpServletResponse response) {
		collegeService.insert(college);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "添加成功");
		return jsonObject.toString();
	}
	/**
	 * ajax 修改
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.json",method = RequestMethod.POST)
	@ResponseBody
	public String exUpdateJson(College college, Model model, HttpServletRequest request, HttpServletResponse response) {
		collegeService.update(college);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "修改成功");
		return jsonObject.toString();
	}
	/**
	 * ajax 删除
	 * @return
	 */
	@RequestMapping(value = "/delete.json", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String exDeleteJson(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		collegeService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//collegeService.deleteBySqId("deleteBySql", params);
		//状态删除
		//College load = collegeService.load(id);
		//load.setIsDelete(1);
		//collegeService.update(load);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "删除成功");
		return jsonObject.toString();
	}
	/**
	 * 单文件上传
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/saveFile")  
    public String saveFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("/upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
}
