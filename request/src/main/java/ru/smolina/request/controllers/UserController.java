package ru.smolina.request.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.smolina.request.domain.Request;
import ru.smolina.request.domain.User;
import ru.smolina.request.exceptions.AccessDeniedException;
import ru.smolina.request.servises.RequestService;

@RestController
@RequestMapping("/request")
public class UserController {
	@Autowired
	private RequestService requestService;

	@GetMapping
	public Iterable<Request> list(@AuthenticationPrincipal User user) {
		return requestService.findByAuthor(user);
	}

	@GetMapping("{id}")
	public Request getOne(@AuthenticationPrincipal User user, @PathVariable("id") Request request)
			throws AccessDeniedException {
		if (user.getUser_id() != request.getAuthor().getUser_id())
			throw new AccessDeniedException("Ошибка доступа : Нельзя просматривать чужие заявки!");
		return request;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	Request create(@AuthenticationPrincipal User user, @Validated @RequestBody Request request) {
		return requestService.saveNewRequest(user, request);
	}

	@PutMapping("{id}")
	public Request update(@AuthenticationPrincipal User user, @PathVariable("id") Request requestFromDb, @RequestBody Request request) throws AccessDeniedException {
		return requestService.updateRequest(user, requestFromDb, request);
	}

	@PatchMapping("/sent/{id}")
	public Request sent(@PathVariable("id") Request requestFromDb, @RequestBody Request request) {
		return requestService.sent(requestFromDb, request);
	}
}
