package ru.smolina.request.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smolina.request.domain.Request;
import ru.smolina.request.domain.User;
import ru.smolina.request.servises.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public Iterable<User> list(@AuthenticationPrincipal User user) {		
		return userService.findAll();
	}
	
}
