package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        // TypedQuery<Student> theQuery = entityManager.createQuery("from Student order by lastName asc", Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where lastName=:lastName", Student.class);

        // set query parameters
        theQuery.setParameter("lastName", theLastName);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        // retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        // delete the student
        entityManager.remove(theStudent);
    }

//    @Override
//    @Transactional
//    public void deleteById(Integer id) {
//        // create query
//        TypedQuery<Student> theQuery = entityManager.createQuery("delete from Student where id=:studentId", Student.class);
//
//        // set query parameters
//        theQuery.setParameter("studentId", id);
//
//        // execute query
//        theQuery.executeUpdate();
//    }

    @Override
    @Transactional
    public int deleteAll() {
        // create query
        Query theQuery = entityManager.createQuery("delete from Student");

        // execute query
        int numberOfDeletedRows = theQuery.executeUpdate();
        return numberOfDeletedRows;
    }

}
