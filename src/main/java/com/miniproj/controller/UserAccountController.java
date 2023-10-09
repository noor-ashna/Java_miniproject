package com.miniproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miniproj.entity.UserAccount;
import com.miniproj.service.UserAccountService;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountService service;

	@GetMapping("/")
	public String index(Model model) { // it sends the data to UI

		model.addAttribute("user", new UserAccount());

		return "index";

	}

	@PostMapping("/save-user")
	public String handleSubmitAccountBtn(@ModelAttribute("user") UserAccount user, Model model) {
		String msg = service.saveOrUpdateUserAcc(user);

		model.addAttribute("msg", msg);
		return "index";
	}

	@GetMapping("/users")
	public String getUsers(Model model) {

		List<UserAccount> usersList = service.getAllUserAccounts();
		model.addAttribute("users", usersList);
		return "view-users";

	}
	   @GetMapping("/edit")
	public String editUser(@RequestParam ("id")Integer id, Model model) {
		  UserAccount userAccount = service.getUserAcc(id);
		   model.addAttribute("user", userAccount);
		return "index";
		 
	}
	    @GetMapping("/delete")
	   public String deleteUser(@RequestParam("id")Integer uid) {
	    	boolean deleteUserAcc = service.deleteUserAcc(uid);
		return "redirect:/users";
		   
	   }
	     // we are not using Model because this method does not send direct response to the ui
	    @GetMapping("/update")
	public String statusUpdate(@RequestParam("id")Integer uid, 
			                  @RequestParam("status") String status) {
		         service.updateUserAccStatus(uid, status);
		         return "redirect:/users";
		
	}
	
	
}
