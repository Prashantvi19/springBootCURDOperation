package com.springBootCURD.main.restapicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBootCURD.main.entities.UserEntity;
import com.springBootCURD.main.services.UserService;
@RestController
public class RestfulWebServicesController {
	@Autowired
	private UserService userServices;

	@GetMapping(value = "/getdata", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserEntity> getDataRequest() {

		List<UserEntity> userEntities = userServices.userList();
		if (userEntities != null && !userEntities.isEmpty()) {
			for (UserEntity user : userEntities) {
				System.out.println("ID: " + user.getId());
				System.out.println("Name: " + user.getName());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Mobile: " + user.getMobile());
				System.out.println("-----------------------------");
			}

		}

		return userEntities;
	}

	@GetMapping(value = "/serachbyemail", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserEntity> searchByimailResultRequest(@RequestParam String email) {

		System.out.println(email);

		List<UserEntity> userEntities = userServices.getUserByEmail(email);
		if (userEntities != null && !userEntities.isEmpty()) {
			for (UserEntity user : userEntities) {
				System.out.println("ID: " + user.getId());
				System.out.println("Name: " + user.getName());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Mobile: " + user.getMobile());
				System.out.println("-----------------------------");
			}

		}

		return userEntities;
	}

	@PostMapping("/submitForm")
	public UserEntity submitFormData(@RequestBody UserEntity userEntity) {

//	    userEntity = userServices.insertUser(userEntity);
	    
	    return userServices.insertUser(userEntity);
//		if (userEntity != null) {
//			return "Form submitted successfully" + "String :";
//		} else {
//			return "Somthing went wrong" + "String :";
//		}
	}

	@PutMapping("/updatedata")
	public String updateData(@RequestBody UserEntity userEntity) {

		boolean status = false;

		if (userEntity != null) {
			
			userEntity = userServices.updateUser(userEntity);
		}

		if (userEntity != null) {

			return "Form Updatted successfully" + "String :" + status;
		} else {
			return "Something went wrong. Unable to find record." + status;

		}
	}

	@DeleteMapping("/deletedata")
	public String deleteData(@RequestParam int id) {
		System.out.println(id);

		boolean status = false;
		if (id != 0) {
			status = userServices.deleteUser(id);
		}

		if (status) {
			return "Record successfully deleted.";
		} else {
			return "Something went wrong. Unable to delete record.";
		}

	}
}
