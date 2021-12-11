package ru.smolina.request.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smolina.request.domain.Request;
import ru.smolina.request.domain.Status;
import ru.smolina.request.exceptions.AccessDeniedException;
import ru.smolina.request.exceptions.InvalidRequestStatusException;
import ru.smolina.request.servises.RequestService;

@RestController
@RequestMapping("/operator/request")
public class OperatorController {
	@Autowired
	private RequestService requestService;

	@GetMapping
	public Iterable<Request> list() {
		return requestService.findByStatus(Status.SENT);
	}

	// Показать одну заявку (добавляем символ '-' между символами в тексте заявки!)
	@GetMapping("/{id}")
	public Request getOne(@PathVariable("id") Request request)
			throws InvalidRequestStatusException {
		return requestService.readRequestAndChangeText(request);
	}

	@PatchMapping("/accept/{id}")
	public Request accept(@PathVariable("id") Request request) throws InvalidRequestStatusException {
		return requestService.accept(request);
	}

	@PatchMapping("/reject/{id}")
	public Request reject(@PathVariable("id") Request request) throws InvalidRequestStatusException {
		return requestService.reject(request);
	}

}
