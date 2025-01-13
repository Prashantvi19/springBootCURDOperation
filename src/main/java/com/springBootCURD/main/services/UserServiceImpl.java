package com.springBootCURD.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootCURD.main.entities.UserEntity;
import com.springBootCURD.main.repositroy.CurdRepo;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CurdRepo curdRepo;

	private List<UserEntity> userList = null;

	@Override
	public UserEntity insertUser(UserEntity userData) {

		System.out.println(userData.getEmail() + userData.getMobile() + userData.getName());
		try {

			System.out.println(userData.getEmail() + userData.getMobile() + userData.getName() + curdRepo);
			return curdRepo.save(userData);

//			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserEntity updateUser(UserEntity userData) {

		System.out.println(userData.getEmail() + userData.getMobile() + userData.getName());
		try {
			return curdRepo.save(userData);			 

//			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteUser(int id) {
		boolean status= false;
		try {

			if (id != 0) {
				curdRepo.deleteById(id);
				status = true;
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return status;

	}

	@Override
	public Optional<UserEntity> getUserById(int id) {
		try {


			return curdRepo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserEntity> getUserByEmail(String email) {
		try {
			
			userList =curdRepo.findByEmail(email);
			return userList != null ? userList : new ArrayList<>();
		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	@Override
	public List<UserEntity> userList() {
		try  {

			
			userList = curdRepo.findAll();

			return userList != null ? userList : new ArrayList<>();
		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
