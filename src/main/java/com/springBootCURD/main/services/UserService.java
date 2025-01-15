package com.springBootCURD.main.services;

import java.util.List;
import java.util.Optional;

import com.springBootCURD.main.entities.UserEntity;

public interface UserService {
	public UserEntity insertUser(UserEntity userData);

	public List<UserEntity> userList();

	public UserEntity updateUser(UserEntity userData);

	public Optional<UserEntity> getUserById(int id);

	public List<UserEntity> getUserByEmail(String id);

	public boolean deleteUser(int id);


}
