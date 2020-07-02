package com.demmage.urlshorter.dao;

import com.demmage.urlshorter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);



}
