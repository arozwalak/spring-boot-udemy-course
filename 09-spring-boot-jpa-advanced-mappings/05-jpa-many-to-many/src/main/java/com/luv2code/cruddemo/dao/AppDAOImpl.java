package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                + "JOIN FETCH c.reviews "
                + "where c.id=:courseId", Course.class);

        // set parameter
        query.setParameter("courseId", theId);

        // execute query and get result
        Course course = query.getSingleResult();

        // return result
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                + "JOIN FETCH c.students "
                + "where c.id=:courseId", Course.class);

        // set parameter
        query.setParameter("courseId", theId);

        // execute query and get result
        Course course = query.getSingleResult();

        // return result
        return course;
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        // create query
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s "
                + "JOIN FETCH s.courses "
                + "where s.id=:studentId", Student.class);

        // set parameter
        query.setParameter("studentId", theId);

        // execute query
        Student student = query.getSingleResult();

        // return result
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        // get the course
        Course tempCourse = entityManager.find(Course.class, theId);

        // delete the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // get the student
        Student tempStudent = entityManager.find(Student.class, theId);

        // delete the student
        entityManager.remove(tempStudent);
    }
}
