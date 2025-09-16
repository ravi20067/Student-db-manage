package com.first.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.first.project.model.Student;

public interface StudentRepo extends JpaRepository<Student,Integer> {
}
