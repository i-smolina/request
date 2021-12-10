package ru.smolina.request.servises;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sun.jdi.request.InvalidRequestStateException;

import ru.smolina.request.domain.Request;
import ru.smolina.request.domain.Role;
import ru.smolina.request.domain.Status;
import ru.smolina.request.domain.User;
import ru.smolina.request.exceptions.AccessDeniedException;
import ru.smolina.request.repos.RequestRepo;

@Service
public class RequestService {

	@Autowired
	private RequestRepo requestRepo;

	public Iterable<Request> findByAuthor(User user) {
		return requestRepo.findByAuthor(user);
	}

	public Request saveNewRequest(User user, Request request) {
		request.setAuthor(user);
		return requestRepo.save(request);
	}

	public Request updateRequest(User user, Request requestFromDb, Request request) throws AccessDeniedException {
		if (user.getUser_id() != requestFromDb.getAuthor().getUser_id())
			throw new AccessDeniedException("Ошибка доступа : Нельзя редактировать чужие заявки!");
		
		if (requestFromDb.getStatus() != Status.DRAFT)
			throw new InvalidRequestStateException("Редактирование запрещено! Заявка находится в статусе " + requestFromDb.getStatus());
		
		BeanUtils.copyProperties(request, requestFromDb, "request_id", "author", "status");
		return requestRepo.save(requestFromDb);
	}

	public Request sent(Request requestFromDb, Request request) {
		if (requestFromDb.getStatus() == Status.DRAFT && request.getStatus() == Status.SENT) {
			requestFromDb.setStatus(request.getStatus());
			return requestRepo.save(requestFromDb);
		} else
			throw new InvalidRequestStateException("Ошибка : невозможно изменить статус заявки с "
					+ requestFromDb.getStatus() + "на " + request.getStatus());
	}
}
