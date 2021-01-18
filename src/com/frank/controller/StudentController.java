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
import org.springframework.web.bind.annotation.ResponseBody;

import com.frank.base.BaseController;
import com.frank.entity.Bed;
import com.frank.entity.Building;
import com.frank.entity.College;
import com.frank.entity.Domitory;
import com.frank.entity.Grade;
import com.frank.entity.Major;
import com.frank.entity.StayRel;
import com.frank.entity.Student;
import com.frank.entity.Team;
import com.frank.service.BedService;
import com.frank.service.BuildingService;
import com.frank.service.CollegeService;
import com.frank.service.DomitoryService;
import com.frank.service.GradeService;
import com.frank.service.MajorService;
import com.frank.service.StayRelService;
import com.frank.service.StudentService;
import com.frank.service.TeamService;


/**
 * @author Tommy
 * @date 2021年1月18日下午4:56:56
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private CollegeService collegeService;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private DomitoryService domitoryService;
	
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
	public String findByObj(Student student, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Student> list = studentService.listAllByEntity(student);
		for(Student m : list){
			Major major = majorService.load(m.getMajorId());
			College college = collegeService.load(m.getCollegeId());
			Grade grade = gradeService.load(m.getGradeId());
			Team team = teamService.load(m.getTeamId());
			m.setGradeName(grade.getName());
			m.setMajorName(major.getName());
			m.setCollegeName(college.getName());
			m.setTeamName(team.getName());
		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", student);
		return "student/student";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		List<Team> teamList = teamService.listAll();
		model.addAttribute("teamList", teamList);
		List<Grade> gradeList = gradeService.listAll();
		model.addAttribute("gradeList", gradeList);
		return "student/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
    @RequestMapping(value = "/exAdd.do")
    public String manageMain(Student student,Integer id,HttpServletRequest request){
    	 String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
    	 student.setId(uuid);
    	 Team team = teamService.load(student.getTeamId());
    	 Major major = majorService.load(team.getMajorId());
    	 student.setMajorId(major.getId());
    	 College college = collegeService.load(major.getCollegeId());
    	 student.setCollegeId(college.getId());
    	 
    	 student.setIfRuzhu(0);//默认未入住
    	 studentService.insert(student);
    	 return "redirect:/student/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(String id,Model model) {
		List<Team> teamList = teamService.listAll();
		model.addAttribute("teamList", teamList);
		List<Grade> gradeList = gradeService.listAll();
		model.addAttribute("gradeList", gradeList);
		
		Student obj = studentService.load(id);
		model.addAttribute("obj",obj);
		return "student/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Student student, Model model, HttpServletRequest request, HttpServletResponse response) {
		Team team = teamService.load(student.getTeamId());
    	Major major = majorService.load(team.getMajorId());
    	student.setMajorId(major.getId());
    	College college = collegeService.load(major.getCollegeId());
    	student.setCollegeId(college.getId());
		studentService.update(student);
		return "redirect:/student/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		studentService.deleteById(id);
		return "redirect:/student/findByObj.do";
	}
	
	/**
	 * 跳至学生入住登记页面
	 * @return
	 */
	@RequestMapping(value = "/ruzhu.do")
	public String ruzhu(Model model) {
		List<Building> buildingList = buildingService.listAll();
		model.addAttribute("buildingList", buildingList);
		return "student/ruzhu";
	}
	
	/**
	 * 根据宿舍楼号获取宿舍楼
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDomitory.do")
	public List<Domitory> getDomitory(Model model, String buildingId) {
		Domitory bu = new Domitory();
		bu.setBuildingId(buildingId);
		List<Domitory> list = domitoryService.listAllByEntity(bu);
		return list;
	}
	
	/**
	 * 根据宿舍号获取所有可用床位
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBed.do")
	public List<Bed> getBed(Model model, String domitoryId) {
		Bed b = new Bed();
		b.setDomitoryId(domitoryId);
		List<Bed> list =bedService.listAllByEntity(b);
		//过滤可用的床位
		List<Bed> newList = new ArrayList<Bed>();
		for(Bed be : list){
			StayRel rel = new StayRel();
			rel.setBedId(be.getId());
			List<StayRel> rels = stayRelService.listAllByEntity(rel);
			if(rels.size() == 0){
				newList.add(be);
			}
		}
		return newList;
	}
	
	/**
	 * 选择未入住学生弹框页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectStudent.do")
	public String selectStudent(Student student, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Student> list = studentService.listAllByEntity(student);
		List<Student> newList = new ArrayList<Student>();
		for(Student m : list){
			StayRel rel = new StayRel();
			rel.setStudentId(m.getId());
			List<StayRel> rels = stayRelService.listAllByEntity(rel);
			//过滤未入住的学生，通过判断学生id是否存在关系表中
			
			if(rels.size() == 0){
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
		return "student/selectStudent";
	}
	
	/**
	 * 入住保存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exRuzhu.do")
	public String exRuzhu(Model model, String id,String buildingId,String domitoryId,String bedId) {
		//保存学生和床位的对应关系，成功返回0，失败返回1
		try{
			StayRel rel = new StayRel();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");//生成唯一主键
			rel.setId(uuid);
			rel.setStudentId(id);
			rel.setBedId(bedId);
			stayRelService.insert(rel);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			return "1";
		}

	}
	
	/**
	 * 退房管理列表（所有已入住的学生）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tuifang.do")
	public String tuifang(Student student, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Student> list = studentService.listAllByEntity(student);
		List<Student> newList = new ArrayList<Student>();
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
		return "student/tuifang";
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
		return "redirect:/student/tuifang.do";
	}
	
	
	
	/**
	 * 跳至个人信息修改页面
	 * @return
	 */
	@RequestMapping(value = "/personInfo.do")
	public String personInfo(String id,Model model,HttpServletRequest request) {
		
		List<Team> teamList = teamService.listAll();
		model.addAttribute("teamList", teamList);
		List<Grade> gradeList = gradeService.listAll();
		model.addAttribute("gradeList", gradeList);
		
		Student student = (Student) request.getSession().getAttribute("user");
		Student obj = studentService.load(student.getId());
		model.addAttribute("obj",obj);
		return "student/personInfo";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exPersonInfo.do")
	public String exPersonInfo(Student student, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		studentService.update(student);
		return  "student/success";
	}
	
	/**
	 * 学生入住床位查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/myRuzhu.do")
	public String findByObj(Bed bed, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(bed.getBedNo() != null && bed.getBedNo().equals("")){
			bed.setBedNo(null);
		}
		List<Bed> list = new ArrayList<Bed>();
		//获取当前登录学生
		Student student = (Student) request.getSession().getAttribute("user");
		
		StayRel rel = new StayRel();
		rel.setStudentId(student.getId());
		List<StayRel> rels =stayRelService.listAllByEntity(rel);
		if(rels.size() > 0){
			String bedId = rels.get(0).getBedId();
			bed.setId(bedId);
			list = bedService.listAllByEntity(bed);
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
		}
		model.addAttribute("list", list);
		//存储查询条件
		model.addAttribute("obj", bed);
		return "student/myRuzhu";
	}
}
