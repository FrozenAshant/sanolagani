package com.bitflip.sanolagani.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitflip.sanolagani.models.Company;
import com.bitflip.sanolagani.service.AdminService;
import com.bitflip.sanolagani.serviceimpl.RecommendationInitializer;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	@Autowired
    RecommendationInitializer recommedationinit;
	@Autowired
	AdminService adminservice;

	@GetMapping({"/","/home"})
	public String homePage(Model model) {
		recommedationinit.getAuthenticUser();
		List<Company> companylist = adminservice.getAllCompany();
		Optional<List<Company>> result = Optional.ofNullable(companylist);
		if (result != null) {
		List<Company> companybasedoncapital=adminservice.listingBasedonRaisedCapital(companylist);
		model.addAttribute("companybasedoncapital", companybasedoncapital);
			for (Company company : companylist) {
				
			}
				 		 
						 
						 
				return "index";
		}
		return "index";
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
			//HttpStatusReturningLogoutSuccessHandler hs=new HttpStatusReturningLogoutSuccessHandler();
			request.getSession(false).invalidate();

			return "redirect:/home";
		}

}
