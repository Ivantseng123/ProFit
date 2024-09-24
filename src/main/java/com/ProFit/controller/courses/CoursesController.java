package com.ProFit.controller.courses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ProFit.bean.coursesBean.CourseBean;
import com.ProFit.dao.coursesCRUD.IHcourseDao;
import com.ProFit.dto.courseDTO.CoursesDTO;

@Controller
public class CoursesController {
	
	@Autowired
	private IHcourseDao hcourseDao;
	
	@GetMapping("/courses")
	public String coursesPage() {
		return "coursesVIEW/courseView";
	}
	
	@GetMapping("/courses/addCourse")
	public String addCoursePage() {
		return "coursesVIEW/createCourseView";
	}
	
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	// 搜尋全部的方法
	@GetMapping("/courses/search")
	@ResponseBody
	public List<CoursesDTO> searchAllCourses(
	    @RequestParam(required = false) String courseName,
	    @RequestParam(required = false) String courseCreateUserName,
	    @RequestParam(required = false) String courseStatus,
	    @RequestParam(required = false) String courseCreateUserId,
	    @RequestParam(required = false) String courseMajor
	){
		
		List<CourseBean> searchCourses = hcourseDao.searchCourses(courseName, courseCreateUserName, courseStatus, courseCreateUserId, courseMajor);
		
		for(int i=0;i<searchCourses.size();i++) {
			System.out.println(searchCourses.get(i));
		}

		
		// 將 CourseBean 轉換為 CoursesDTO
		List<CoursesDTO> searchCoursesDTO = (List<CoursesDTO>) searchCourses.stream()
			.map(CoursesDTO::new).collect(Collectors.toList()); // 使用 DTO 的構造函數
			
		
		return searchCoursesDTO;
		
	}	
	
	// 搜尋單筆的方法
	@GetMapping("/courses/search/{courseId}")
	@ResponseBody
	public CoursesDTO searchOneCourse(@PathVariable String courseId){
	    
	    CourseBean singleCourseById = hcourseDao.searchOneCourseById(courseId);

	    // 使用 DTO 包裝 CourseBean 的數據
	    CoursesDTO coursesDTO = new CoursesDTO(singleCourseById);

	    return coursesDTO;
	}
	
    // 更新課程的方法，使用路徑變數和 POST 方法
    @PostMapping("/courses/update/{oldCourseId}")
    @ResponseBody
    public boolean updateCourseById(
        @PathVariable("oldCourseId") String courseId,
        @RequestParam String courseName,
        @RequestParam String courseCategory,
        @RequestParam String courseCreateUserId,
        @RequestParam String courseInformation,
        @RequestParam String courseDescription,
        @RequestParam String courseEnrollmentDate,
        @RequestParam String courseStartDate,
        @RequestParam String courseEndDate,
        @RequestParam String coursePrice,
        @RequestParam String courseStatus
    ) {
        // 定義日期格式
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        
        // 解析日期
        LocalDate enrollmentDateParsed = (courseEnrollmentDate != null && !courseEnrollmentDate.isEmpty()) 
            ? LocalDate.parse(courseEnrollmentDate, dateFormatter) : null;
        LocalDateTime startDateParsed = (courseStartDate != null && !courseStartDate.isEmpty()) 
            ? LocalDateTime.parse(courseStartDate, dateTimeFormatter) : null;
        LocalDateTime endDateParsed = (courseEndDate != null && !courseEndDate.isEmpty()) 
            ? LocalDateTime.parse(courseEndDate, dateTimeFormatter) : null;
        
        // 創建更新的 CourseBean 對象
        CourseBean updateCourse = new CourseBean(
            courseId, 
            courseName, 
            courseCreateUserId, 
            courseCategory, 
            courseInformation, 
            courseDescription, 
            enrollmentDateParsed, 
            startDateParsed, 
            endDateParsed, 
            coursePrice, 
            courseStatus
        );
        
        // 調用 DAO 層更新課程
        boolean isUpdated = hcourseDao.updateCourseById(updateCourse);
        
        return isUpdated;
    }
    
    // 添加一個方法來轉發到 updateCourseView.jsp
    @GetMapping("/courses/viewUpdate")
    public String viewUpdateCourse(@RequestParam String courseId, Model model) {
        CourseBean course = hcourseDao.searchOneCourseById(courseId);
        model.addAttribute("course", new CoursesDTO(course)); // 使用 DTO
        return "coursesVIEW/updateCourseView"; // 假設 view resolver 配置為 /WEB-INF/views/
    }
	
}
