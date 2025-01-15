package com.springBootCURD.main.restapicontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<UserEntity> submitFormData(@RequestBody UserEntity userEntity) {

		if (userEntity != null) {

			userEntity = userServices.insertUser(userEntity);
		}

		if (userEntity != null) {

			return ResponseEntity.status(HttpStatus.CREATED).header("Form submitted successfully", "Thanks!")
					.body(userEntity);

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Something went wrong. Unable to submit record.", "Thanks!").body(userEntity);
		}
		}

	@PutMapping("/updatedata")
	public ResponseEntity<UserEntity> updateData(@RequestBody UserEntity userEntity) {

		if (userEntity != null) {

			userEntity = userServices.updateUser(userEntity);

		}

		if (userEntity != null) {

			return ResponseEntity.status(HttpStatus.OK).header("Form Updatted successfull", "Thanks!").body(userEntity);

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Something went wrong. Unable to find record.", "Thanks!").body(userEntity);

		}
	}

	@DeleteMapping("/deletedata")
	public ResponseEntity<UserEntity> deleteData(@RequestParam int id) {
		System.out.println(id);
		Optional<UserEntity> userEntitiesOptinal;
		UserEntity userEntity = null;
		if (id >= 0) {
			userEntitiesOptinal = userServices.getUserById(id);
			if (userEntitiesOptinal.isPresent()) {
				userEntity = userEntitiesOptinal.orElse(new UserEntity());
			}
			boolean status = false;
			if (id >= 0) {
				status = userServices.deleteUser(id);
			}

			if (status && userEntity != null) {

				return ResponseEntity.status(HttpStatus.OK).header("Record successfully deleted.", "Thanks!")
						.body(userEntity);

			}

		} 
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Something went wrong. Unable to delete record.", "Thanks!").body(userEntity);

		
	}
}
