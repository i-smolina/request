package ru.smolina.request.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.smolina.request.domain.User;
import ru.smolina.request.repos.UserRepo;

@RestController
//@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/user")
	public User getOne(@AuthenticationPrincipal User user, @RequestParam Long id) {
		return user;
	}
	
	
}
