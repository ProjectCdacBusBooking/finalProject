package com.sandip.bus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandip.bus.pojo.User;

public interface UserDao extends JpaRepository<User, Long>{

}
