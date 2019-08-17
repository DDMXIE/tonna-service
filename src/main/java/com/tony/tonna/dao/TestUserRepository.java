package com.tony.tonna.dao;

import com.tony.tonna.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<User, Integer> {


}
