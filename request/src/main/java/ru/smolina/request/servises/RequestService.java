package ru.smolina.request.servises;


import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.smolina.request.domain.Request;
import ru.smolina.request.domain.Status;
import ru.smolina.request.domain.User;
import ru.smolina.request.exceptions.AccessDeniedException;
import ru.smolina.request.exceptions.InvalidRequestStatusException;
import ru.smolina.request.repos.RequestRepo;

@Service
public class RequestService {

	@Autowired
	private RequestRepo requestRepo;

	public Iterable<Request> findByAuthor(User user) {
		return requestRepo.findByAuthor(user);
	}
	
	public Iterable<Request> findByStatus(Status status) {
		return requestRepo.findByStatusOrderByCreatdate(status);
	}
	
	public Request createRequest(User user, Request request) {
		request.setAuthor(user);
		request.setStatus(Status.DRAFT);
		request.setCreatdate(LocalDateTime.now());
		return requestRepo.save(request);
	}

	public Request updateRequest(User user, Request requestFromDb, Request request)
			throws AccessDeniedException, InvalidRequestStatusException {
		if  (!user.getUser_id().equals(request.getAuthor().getUser_id())) 
			throw new AccessDeniedException("Ошибка доступа : Нельзя редактировать чужие заявки!");

		if (requestFromDb.getStatus() != Status.DRAFT)
			throw new InvalidRequestStatusException(
					" Ошибка : Запрещено редактировать заявку, которая находится в статусе " + requestFromDb.getStatus());

		BeanUtils.copyProperties(request, requestFromDb, "request_id", "author", "status");
		return requestRepo.save(requestFromDb);
	}
	
	public Request readRequestAndChangeText(Request request) throws InvalidRequestStatusException {
		if (request.getStatus() != Status.SENT)
			throw new InvalidRequestStatusException(
					"Ошибка : Оператор не может просмотреть заявку, которая находится в статусе "
							+ request.getStatus());
		
		if (request.getText().length() == 0) return request;

		StringBuilder builder = new StringBuilder();
		for (char ch : request.getText().toCharArray()) {
			builder.append(ch);
			builder.append('-');
		}
		request.setText(builder.substring(0, builder.length()-1));
		return request;
	}
	
	public Request readRequest(User user, Request request) throws AccessDeniedException {
		if (!user.getUser_id().equals(request.getAuthor().getUser_id())) 
			throw new AccessDeniedException("Ошибка доступа : Нельзя просматривать чужие заявки!"); 
		return request;
	}

	public Request send(User user, Request request) throws InvalidRequestStatusException, AccessDeniedException  {
		if (!user.getUser_id().equals(request.getAuthor().getUser_id())) 
			throw new AccessDeniedException("Ошибка доступа : Нельзя отправить чужую заявку!"); 
		
		if (request.getStatus() != Status.DRAFT)
			throw new InvalidRequestStatusException(
					"Ошибка : Пользователь не может отправить заявку, которая находится в статусе "
							+ request.getStatus());
		request.setStatus(Status.SENT);
		return requestRepo.save(request);
	}

	public Request accept(Request request) throws InvalidRequestStatusException {
		if (request.getStatus() != Status.SENT)
			throw new InvalidRequestStatusException(
					"Ошибка : Оператор не может принять заявку, которая находится в статусе " + request.getStatus());

		request.setStatus(Status.ACCEPTED);
		return requestRepo.save(request);
	}

	public Request reject(Request request) throws InvalidRequestStatusException {
		if (request.getStatus() != Status.SENT)
			throw new InvalidRequestStatusException(
					"Ошибка : Оператор не может отклонить заявку, которая находится в статусе " + request.getStatus());

		request.setStatus(Status.REJECTED);
		return requestRepo.save(request);
	}

	
}
