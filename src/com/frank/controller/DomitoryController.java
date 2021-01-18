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
import com.frank.entity.Bed;
import com.frank.entity.Building;
import com.frank.entity.Domitory;
import com.frank.entity.Major;
import com.frank.entity.StayRel;
import com.frank.service.BedService;
import com.frank.service.BuildingService;
import com.frank.service.CollegeService;
import com.frank.service.DomitoryService;
import com.frank.service.MajorService;
import com.frank.service.StayRelService;
/**
 * @author Tommy
 * @date 2021年1月18日下午4:56:04
 */
@Controller
@RequestMapping("/domitory")
public class DomitoryController extends BaseController {
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private DomitoryService domitoryService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private BedService bedService;
	@Autowired
	private StayRelService stayRelService;
	// --------------------------------------- 华丽分割线 ------------------------------
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Domitory domitory, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(domitory.getBuildingNo() != null && domitory.getBuildingNo().equals("")){
			domitory.setBuildingNo(null);
		}
		List<Domitory> list = domitoryService.listAllByEntity(domitory);
		for(Domitory m : list){
			Building building = buildingService.load(m.getBuildingId());
			m.setBuildingNo(building.getBuildingNo());
			//设置已入住人数
			int ruzuNum = 0;//默认为0
			Bed b = new Bed();
			b.setDomitoryId(m.getId());
			List<Bed> beds =bedService.listAllByEntity(b);
			for(Bed be : beds){
				StayRel rel = new StayRel();
				rel.setBedId(be.getId());
				List<StayRel> rs = stayRelService.listAllByEntity(rel);
				if(rs.size() > 0 ){
					ruzuNum ++;
				}
			}
			m.setRuzhuNum(ruzuNum);
		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", domitory);
		return "domitory/domitory";
	}
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		List<Building> buildingList = buildingService.listAll();
		model.addAttribute("buildingList", buildingList);
		return "domitory/add";
	}
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(Domitory domitory,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 domitory.setId(uuid);
    	 domitoryService.insert(domitory);
		 //添加宿舍时根据宿舍类型自动生成相应的床位
		 String str = "abcdef";
		for(int i = 0; i < domitory.getType(); i++){
			Bed bed = new Bed();
			String uuidd = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
	    	bed.setId(uuidd);
	    	bed.setDomitoryId(domitory.getId());//宿舍号
			char ch = str.charAt(i);
			bed.setBedNo(String.valueOf(ch));//床位编号
			bedService.insert(bed);
		}
    	 return "redirect:/domitory/findByObj.do";
	}
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		List<Major> majorList = majorService.listAll();
		model.addAttribute("majorList", majorList);
		Domitory obj = domitoryService.load(id);
		model.addAttribute("obj",obj);
		return "domitory/update";
	}
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Domitory domitory, Model model, HttpServletRequest request, HttpServletResponse response) {
		domitoryService.update(domitory);
		return "redirect:/domitory/findByObj.do";
	}
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		domitoryService.deleteById(id);
		return "redirect:/domitory/findByObj.do";
	}
}
