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
import com.frank.entity.StayRel;
import com.frank.entity.Student;
import com.frank.service.BedService;
import com.frank.service.BuildingService;
import com.frank.service.CollegeService;
import com.frank.service.DomitoryService;
import com.frank.service.MajorService;
import com.frank.service.MbRelService;
import com.frank.service.StayRelService;
import com.frank.service.StudentService;
/**
 * @author Tommy
 * @date 2021年1月18日下午4:55:32
 */
@Controller
@RequestMapping("/bed")
public class BedController extends BaseController {
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private BedService bedService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private DomitoryService domitoryService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private MbRelService mbRelService;
	@Autowired
	private StayRelService stayRelService;
	@Autowired
	private StudentService studentService;
	// --------------------------------------- 华丽分割线 ------------------------------
	/**
	 * 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Bed bed, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(bed.getBedNo() != null && bed.getBedNo().equals("")){
			bed.setBedNo(null);
		}
		List<Bed> list = bedService.listAllByEntity(bed);
		for(Bed b : list){
			Domitory domitory = domitoryService.load(b.getDomitoryId());
			b.setDomitoryNo(domitory.getDomitoryNo());
			Building building = buildingService.load(domitory.getBuildingId());
			b.setBuildingNo(building.getBuildingNo());
			StayRel mb = new StayRel();
			mb.setBedId(b.getId());
			List<StayRel> mbs = stayRelService.listAllByEntity(mb);
			if(mbs.size() > 0 ){
				StayRel m = mbs.get(0);
				b.setStudentId(m.getStudentId());
				Student s = studentService.load(m.getStudentId());
				b.setStudentName(s.getNickName());
			}
		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", bed);
		return "bed/bed";
	}
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		List<Domitory> domitoryList = domitoryService.listAll();
		model.addAttribute("domitoryList", domitoryList);
		return "bed/add";
	}
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(Bed bed,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 bed.setId(uuid);
    	 bedService.insert(bed);
    	 return "redirect:/bed/findByObj.do";
	}
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		List<Domitory> domitoryList = domitoryService.listAll();
		model.addAttribute("domitoryList", domitoryList);
		Bed obj = bedService.load(id);
		model.addAttribute("obj",obj);
		return "bed/update";
	}
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Bed bed, Model model, HttpServletRequest request, HttpServletResponse response) {
		bedService.update(bed);
		return "redirect:/bed/findByObj.do";
	}
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		bedService.deleteById(id);
		return "redirect:/bed/findByObj.do";
	}
}
