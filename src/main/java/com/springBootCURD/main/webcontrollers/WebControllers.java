package com.springBootCURD.main.webcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springBootCURD.main.entities.UserEntity;
import com.springBootCURD.main.services.UserServiceImpl;

@Controller
public class WebControllers {
//	@Autowired
	private UserEntity userEntity;
	@Autowired
	private UserServiceImpl userServices;

//	Render the starting Page

	@RequestMapping("/")
	public ModelAndView indexPage() {
		ModelAndView views = new ModelAndView();
		views.setViewName("index");
		return views;
	}

//  Home User
	@RequestMapping("/home")
	public ModelAndView homePage() {
		ModelAndView views = new ModelAndView();
		views.setViewName("index");
		return views;
	}

//	Insert Data Form return

	@GetMapping("/insertdataform")
	public ModelAndView formInsertDataPage(Model model) {
		ModelAndView views = new ModelAndView();

		model.addAttribute("user", new UserEntity());
		views.setViewName("submitForm");
		return views;
	}

// List Return 

	@GetMapping("/datalist")
	public String dataListPage(Model model) {
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
		model.addAttribute("listUser", userEntities);
		model.addAttribute("i", 0);
		return "list";
	}

//	Update Page return with a single data and get Id 

	@GetMapping("/updateById")
	public String updateByIdPage(@RequestParam("id") int id, Model model, RedirectAttributes redirectAttributes) {
		Optional<UserEntity> userEntitiesOptinal;
		userEntity = null;
		if (id >= 0) {
			userEntitiesOptinal = userServices.getUserById(id);
			if (userEntitiesOptinal.isPresent()) {
				userEntity = userEntitiesOptinal.orElse(new UserEntity());
			}
		} else if (userEntity == null || id <= 0) {

			redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong. Unable to delete record.");
			return "redirect:/datalist";
		}
		model.addAttribute("user", userEntity);
		return "edit";
	}

// Return search page

	@GetMapping("/searchbyemail")
	public ModelAndView searchByimailPage(Model model) {

		model.addAttribute("user", new UserEntity());
		ModelAndView views = new ModelAndView();
		views.setViewName("searchByEmail");
		return views;
	}

//	Return User data get By Email ID

	@GetMapping("/serachbyemailresultrequest")
	public String searchByimailResultRequest(@ModelAttribute("user") UserEntity userEntity, Model model) {

		System.out.println(userEntity.getEmail());

		List<UserEntity> userEntities = userServices.getUserByEmail(userEntity.getEmail());
		if (userEntities != null && !userEntities.isEmpty()) {
			for (UserEntity user : userEntities) {
				System.out.println("ID: " + user.getId());
				System.out.println("Name: " + user.getName());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Mobile: " + user.getMobile());
				System.out.println("-----------------------------");
			}

			model.addAttribute("successMsg", "Search successful!");
		} else {
			model.addAttribute("errorMsg", "No records found for the provided email.");
		}

		model.addAttribute("listUser", userEntities);
		model.addAttribute("i", 0);

		return "list";
	}
// Delete Data by Id

	@GetMapping("/deletevalue")
	public String deleteRescordRequest(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {

		System.out.println(id);
		boolean status = false;
		if (id >= 0) {
			status = userServices.deleteUser(id);
		}

		if (status) {
			redirectAttributes.addFlashAttribute("successMsg", "Record successfully deleted.");
		} else {
			redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong. Unable to delete record.");
		}

		return "redirect:/datalist"; // Redirect to dataListPage
	}

// Submit Entry Data by Submit form
	@PostMapping("/entryFormSubmit")
	public String submitFormData(@ModelAttribute("user") UserEntity userEntity, RedirectAttributes redirectAttributes) {
		System.out.println(userEntity.getEmail() + " " + userEntity.getName() + " " + userEntity.getMobile());
		if (userEntity.getEmail() != "" && userEntity.getName() != "" && userEntity.getMobile() != ""
				&& userEntity != null) {
			userEntity = userServices.insertUser(userEntity);
			if (userEntity != null) {
				redirectAttributes.addFlashAttribute("successMsg", "Form successfully submitted");
			} else {
				redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong");
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong");
		}

		return "redirect:/insertdataform";
	}

//Submit Form Updated Data

	@PostMapping("/entryformupdate")
	public String upfateFormData(@ModelAttribute UserEntity userUpdate, RedirectAttributes redirectAttributes) {
//		System.out.println(userEntity.getEmail() + " " + userEntity.getName() + " " + userEntity.getMobile());

		if (userEntity.getEmail() != "" && userEntity.getName() != "" && userEntity.getMobile() != ""
				&& userEntity != null) {

			userEntity = userServices.updateUser(userUpdate);
			if (userEntity != null) {
				redirectAttributes.addFlashAttribute("successMsg", "Record successfully update.");
			} else {
				redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong. Unable to update record.");
			}

		} else {
			redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong. Unable to update record.");
		}

		return "redirect:/datalist"; // Redirect to dataListPage
	}
}
