package com.ling.translator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ling.translator.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findById(int id);
}
