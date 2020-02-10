package com.energizeglobal.com.internship.angular.repository;

import com.energizeglobal.com.internship.angular.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}

