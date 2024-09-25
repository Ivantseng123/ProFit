package com.ProFit.service.courseService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.coursesBean.CourseBean;
import com.ProFit.dao.coursesCRUD.IHcourseDao;
import com.ProFit.dto.courseDTO.CoursesDTO;

@Service
@Transactional
public class CoursesService implements IcourseService {
	
	@Autowired
	private IHcourseDao hcourseDao;
	
	@Override
	public CourseBean insertCourse(CourseBean course) {
		return hcourseDao.insertCourse(course);
	}

	@Override
	public boolean deleteCourseById(String courseId) {
		return hcourseDao.deleteCourseById(courseId);
	}

	@Override
	public boolean updateCourseById(CourseBean newCourse) {
		return hcourseDao.updateCourseById(newCourse);
	}

	@Override
	public CoursesDTO searchOneCourseById(String courseId) {
		CourseBean singleCourseById = hcourseDao.searchOneCourseById(courseId);

	    // 使用 DTO 包裝 CourseBean 的數據
	    CoursesDTO coursesDTO = new CoursesDTO(singleCourseById);
		
		return coursesDTO;
	}

	@Override
	public List<CourseBean> searchCourses() {
		return hcourseDao.searchCourses();
	}

	@Override
	public List<CoursesDTO> searchCourses(String courseName, String userName, String status, String userId,String category) {
		List<CourseBean> searchCourses = hcourseDao.searchCourses(courseName, userName, status, userId, category);
		
		for(int i=0;i<searchCourses.size();i++) {
			System.out.println(searchCourses.get(i));
		}

		
		// 將 CourseBean 轉換為 CoursesDTO
		List<CoursesDTO> searchCoursesDTO = (List<CoursesDTO>) searchCourses.stream()
			.map(CoursesDTO::new).collect(Collectors.toList()); // 使用 DTO 的構造函數
		return searchCoursesDTO;
	}

}
