package com.nic.aishe.master.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.aishe.master.entity.Course;
import com.nic.aishe.master.entity.CourseEnrolledStudentCount;

public interface CourseEnrolledRepo extends JpaRepository<CourseEnrolledStudentCount, Integer> {



	CourseEnrolledStudentCount findByCourseAndCourseCollegeInfoIdAndCourseCollegeInfoSurveyYearAndCourseDiscipline(
			Course course, Integer id, Integer year, String discipline);

}
