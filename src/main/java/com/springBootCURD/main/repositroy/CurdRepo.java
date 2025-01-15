package com.springBootCURD.main.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBootCURD.main.entities.UserEntity;

public interface CurdRepo extends JpaRepository<UserEntity,Integer>{


	List<UserEntity> findByEmail(String email);
}
