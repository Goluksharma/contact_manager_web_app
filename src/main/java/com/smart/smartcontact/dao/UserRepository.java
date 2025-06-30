package com.smart.smartcontact.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.smartcontact.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("select u From User u Where u.email=:email")
    public User getByUserName(@Param("email") String email);

}
