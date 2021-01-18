package com.frank.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.frank.base.BaseController;
import com.frank.entity.Building;
import com.frank.entity.Manage;
import com.frank.entity.MbRel;
import com.frank.service.BuildingService;
import com.frank.service.ManageService;
import com.frank.service.MbRelService;
/**
 * @author Tommy
 * @date 2021年1月18日下午4:55:41
 */
@Controller
@RequestMapping("/building")
public class BuildingController extends BaseController {
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private MbRelService mbRelService;
	@Autowired
	private ManageService manageService;
	// --------------------------------------- 华丽分割线 ------------------------------
	/**
	 * 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Building building, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(building.getBuildingNo() != null && building.getBuildingNo().equals("")){
			building.setBuildingNo(null);
		}
		List<Building> list = buildingService.listAllByEntity(building);
		//查询宿舍管理员
		for(Building b : list ){
			MbRel m = new MbRel();
			m.setBuildingId(b.getId());
			List<MbRel> ms = mbRelService.listAllByEntity(m);
			if(ms.size() > 0){
				MbRel mb = ms.get(0);
				Manage mange = manageService.load(mb.getManageId());
				b.setManageName(mange.getNickName());
			}
		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", building);
		return "building/building";
	}
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		return "building/add";
	}
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(Building building,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 building.setId(uuid);
    	 buildingService.insert(building);
    	 return "redirect:/building/findByObj.do";
	}
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		Building obj = buildingService.load(id);
		model.addAttribute("obj",obj);
		return "building/update";
	}
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Building building, Model model, HttpServletRequest request, HttpServletResponse response) {
		buildingService.update(building);
		return "redirect:/building/findByObj.do";
	}
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		buildingService.deleteById(id);
		return "redirect:/building/findByObj.do";
	}
	/**
	 * 跳至分配管理员页面
	 * @return
	 */
	@RequestMapping(value = "/setManage.do")
	public String setManage(String id, Model model) {
		Manage manage = new Manage();
		List<Manage> list = manageService.listAllByEntity(manage);
		List<Manage> newList = new ArrayList<Manage>();
		//过滤已经分配的管理员
		for(Manage ma : list){
			 MbRel m = new MbRel();
			 m.setManageId(ma.getId());
			 List<MbRel> ms = mbRelService.listAllByEntity(m);
			 if(ms.size() == 0 ){
				 newList.add(ma);
			 }
		}
		model.addAttribute("manageList", newList);
		Building obj = buildingService.load(id);
		model.addAttribute("obj",obj);
		return "building/setManage";
	}
	/**
	 * 保存分配管理员
	 * @return
	 */
    @RequestMapping(value = "/exSetManage.do")
    public String exSetManage(Building building,String manageId,HttpServletRequest request){
    	 MbRel m = new MbRel();
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 m.setId(uuid);
    	 m.setBuildingId(building.getId());
    	 m.setManageId(manageId);
    	 mbRelService.insert(m);
    	 return "redirect:/building/findByObj.do";
	}
}
