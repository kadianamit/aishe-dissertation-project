package com.nic.aishe.master.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.nic.aishe.master.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

List<Course> findByCollegeInfoIdAndCollegeInfoSurveyYear(Integer id,Integer surveyYear);

Course findByIdAndCollegeInfoIdAndCollegeInfoSurveyYear(Integer id,Integer collegeId,Integer surveyYear);

@Query(value="select max(id) from course",nativeQuery = true)
Integer maxId();
}
