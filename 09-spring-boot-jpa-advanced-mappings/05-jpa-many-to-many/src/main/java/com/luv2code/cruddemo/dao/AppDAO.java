package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.transaction.annotation.Transactional;

public interface AppDAO {

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    @Transactional
    void save(Course theCourse);

    Student findStudentAndCoursesByStudentId(int theId);

    void update(Student tempStudent);

    void deleteCourseById(int theId);

    void deleteStudentById(int theId);
}
