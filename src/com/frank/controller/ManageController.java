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
import com.frank.entity.Bed;
import com.frank.entity.Building;
import com.frank.entity.College;
import com.frank.entity.Domitory;
import com.frank.entity.Grade;
import com.frank.entity.Major;
import com.frank.entity.Manage;
import com.frank.entity.MbRel;
import com.frank.entity.StayRel;
import com.frank.entity.Student;
import com.frank.entity.Team;
import com.frank.service.BedService;
import com.frank.service.BuildingService;
import com.frank.service.CollegeService;
import com.frank.service.DomitoryService;
import com.frank.service.GradeService;
import com.frank.service.MajorService;
import com.frank.service.ManageService;
import com.frank.service.MbRelService;
import com.frank.service.StayRelService;
import com.frank.service.StudentService;
import com.frank.service.TeamService;

/**
 * @author Tommy
 * @date 2021年1月18日下午4:56:40
 */
@Controller
@RequestMapping("/manage")
public class ManageController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private ManageService manageService;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private CollegeService collegeService;
	
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private DomitoryService domitoryService;
	
	@Autowired
	private MbRelService mbRelService;
	
	
	@Autowired
	private BedService bedService;
	
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
	public String findByObj(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Manage> list = manageService.listAllByEntity(manage);
		//查看管理员是否分配管理宿舍，已经分配的不能删除,0:未分配，1：已分配
		for(Manage ma : list){
			 MbRel m = new MbRel();
			 m.setManageId(ma.getId());
			 List<MbRel> ms = mbRelService.listAllByEntity(m);
			 if(ms.size() > 0 ){
				 ma.setIfManage("1");
			 }
		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", manage);
		return "manage/manage";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		List<Major> majorList = majorService.listAll();
		model.addAttribute("majorList", majorList);
		return "manage/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(Manage manage,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 manage.setId(uuid);
    	 manageService.insert(manage);
    	 return "redirect:/manage/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		List<Major> majorList = majorService.listAll();
		model.addAttribute("majorList", majorList);
		
		Manage obj = manageService.load(id);
		model.addAttribute("obj",obj);
		return "manage/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		manageService.update(manage);
		return "redirect:/manage/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		manageService.deleteById(id);
		return "redirect:/manage/findByObj.do";
	}
	
	/**
	 * 跳至个人信息修改页面
	 * @return
	 */
	@RequestMapping(value = "/personInfo.do")
	public String personInfo(String id,Model model,HttpServletRequest request) {
		List<Major> majorList = majorService.listAll();
		model.addAttribute("majorList", majorList);
		
		Manage manage = (Manage) request.getSession().getAttribute("user");
		Manage obj = manageService.load(manage.getId());
		model.addAttribute("obj",obj);
		return "manage/personInfo";
	}
	
	/**
	 * 个人信息修改
	 * @return
	 */
	@RequestMapping(value = "/exPersonInfo.do")
	public String exPersonInfo(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		manageService.update(manage);
		return  "student/success";
	}
	
	/**
	 * 当前管理员管理的宿舍楼
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/myBuilding.do")
	public String myBuilding(Building building, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		if(building.getBuildingNo() != null && building.getBuildingNo().equals("")){
			building.setBuildingNo(null);
		}
		List<Building> list = new ArrayList<Building>();
		//获取当前登录管理员
		Manage manage = (Manage) request.getSession().getAttribute("user");
		MbRel rel = new MbRel();
		rel.setManageId(manage.getId());
		List<MbRel> rels = mbRelService.listAllByEntity(rel);
		if(rels.size() > 0){
			rel = rels.get(0);
			building.setId(rel.getBuildingId());
			
			list = buildingService.listAllByEntity(building);
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

		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", building);
		return "manage/myBuilding";
	}
	
	/**
	 * 当前管理员管理的宿舍
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/myDomitory.do")
	public String myDomitory(Domitory domitory, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(domitory.getBuildingNo() != null && domitory.getBuildingNo().equals("")){
			domitory.setBuildingNo(null);
		}
		List<Domitory> list = new ArrayList<Domitory>();
		//获取当前登录管理员
		Manage manage = (Manage) request.getSession().getAttribute("user");
		MbRel rel = new MbRel();
		rel.setManageId(manage.getId());
		List<MbRel> rels = mbRelService.listAllByEntity(rel);
		if(rels.size() > 0){
			rel = rels.get(0);
			domitory.setBuildingId(rel.getBuildingId());

			list = domitoryService.listAllByEntity(domitory);
			for(Domitory m : list){
				Building building = buildingService.load(m.getBuildingId());
				m.setBuildingNo(building.getBuildingNo());
				
				//设置已入住人数
				int ruzuNum = 0;//默认为0
				Bed b = new Bed();
				b.setDomitoryId(m.getId());
				List<Bed> beds =bedService.listAllByEntity(b);
				for(Bed be : beds){
					StayRel re = new StayRel();
					re.setBedId(be.getId());
					List<StayRel> rs = stayRelService.listAllByEntity(re);
					if(rs.size() > 0 ){
						ruzuNum ++;
					}
				}
				m.setRuzhuNum(ruzuNum);
			}
		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", domitory);
		return "manage/myDomitory";
	}
	
	/**
	 * 当前管理员管理的宿舍床位
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/myBed.do")
	public String myBed(Bed bed, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Bed> bedList = new ArrayList<Bed>();
		
		List<Domitory> list = new ArrayList<Domitory>();
		//获取当前登录管理员
		Manage manage = (Manage) request.getSession().getAttribute("user");
		MbRel rel = new MbRel();
		rel.setManageId(manage.getId());
		List<MbRel> rels = mbRelService.listAllByEntity(rel);
		if(rels.size() > 0){
			rel = rels.get(0);
			Domitory domitory = new Domitory();
			domitory.setBuildingId(rel.getBuildingId());

			list = domitoryService.listAllByEntity(domitory);
			for(Domitory m : list){
				Building building = buildingService.load(m.getBuildingId());
				m.setBuildingNo(building.getBuildingNo());
				
				//设置已入住人数
				int ruzuNum = 0;//默认为0
				Bed b = new Bed();
				b.setDomitoryId(m.getId());
				List<Bed> beds =bedService.listAllByEntity(b);
				for(Bed be : beds){
					StayRel re = new StayRel();
					re.setBedId(be.getId());
					List<StayRel> rs = stayRelService.listAllByEntity(re);
					if(rs.size() > 0 ){
						ruzuNum ++;
					}
				}
				m.setRuzhuNum(ruzuNum);
			}
		}
		if(list.size() > 0){
			for(Domitory d : list){
				Bed bb = new Bed();
				bb.setDomitoryId(d.getId());
				List<Bed> bs = bedService.listAllByEntity(bb);
				bedList.addAll(bs);
			}
			for(Bed b : bedList){
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
		}
		model.addAttribute("bedList", bedList);
		//存储查询条件
		model.addAttribute("obj", bed);
		return "manage/myBed";
	}
	
	/**
	 * 跳至管理员学生入住登记页面
	 * @return
	 */
	@RequestMapping(value = "/ruzhu.do")
	public String ruzhu(Model model, HttpServletRequest request) {
		//各管理员只能选择自己管辖的宿舍楼入住
		//获取当前登录管理员
		List<Building> buildingList = new ArrayList<Building>();
		Manage manage = (Manage) request.getSession().getAttribute("user");
		MbRel rel = new MbRel();
		rel.setManageId(manage.getId());
		List<MbRel> rels = mbRelService.listAllByEntity(rel);
		if(rels.size() > 0){
			rel = rels.get(0);
			Building building = new Building();
			building.setId(rel.getBuildingId());
			buildingList = buildingService.listAllByEntity(building);
		}
		model.addAttribute("buildingList", buildingList);
		return "manage/ruzhu";
	}
	
	/**
	 * 退房管理列表,当前管理员管理的宿舍楼下的
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tuifang.do")
	public String tuifang(Student student, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Student> newList = new ArrayList<Student>();
		List<Student> list = studentService.listAllByEntity(student);
		for(Student m : list){
			StayRel rel = new StayRel();
			rel.setStudentId(m.getId());
			List<StayRel> rels = stayRelService.listAllByEntity(rel);
			//过滤已入住的学生，通过判断学生id是否存在关系表中
			if(rels.size() > 0){
				Major major = majorService.load(m.getMajorId());
				College college = collegeService.load(m.getCollegeId());
				Grade grade = gradeService.load(m.getGradeId());
				Team team = teamService.load(m.getTeamId());
				m.setGradeName(grade.getName());
				m.setMajorName(major.getName());
				m.setCollegeName(college.getName());
				m.setTeamName(team.getName());

				newList.add(m);
			}
			
		}
		model.addAttribute("list", newList);
		//存储查询条件
		model.addAttribute("obj", student);
		return "manage/tuifang";
	}
	
	/**
	 * 退房
	 * @return
	 */
	@RequestMapping(value = "/exTuifang.do")
	public String exTuifang(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//退房既删除学生和床位的对应关系
		StayRel rel = new StayRel();
		rel.setStudentId(id);
		List<StayRel> rels = stayRelService.listAllByEntity(rel);
		if(rels.size() > 0){
			rel = rels.get(0);
			stayRelService.deleteById(rel.getId());
		}
		return "redirect:/manage/tuifang.do";
	}
}
