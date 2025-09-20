package com.first.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.project.model.User;
@Repository
public interface SignUpRepo extends JpaRepository<User,Integer>{

}
