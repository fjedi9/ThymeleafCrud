package com.vepilef.springboothibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vepilef.springboothibernate.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
