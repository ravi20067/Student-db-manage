package com.first.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.project.model.CustomUserDetails;


@Repository
public interface SignInRepo extends JpaRepository<CustomUserDetails, Integer> {

    CustomUserDetails findByUserName(String userName);
}
