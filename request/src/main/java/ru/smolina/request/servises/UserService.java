package ru.smolina.request.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import ru.smolina.request.domain.User;
import ru.smolina.request.exceptions.InvalidOperationException;
import ru.smolina.request.repos.UserRepo;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username);
	}

	public Iterable<User> findAll() {
		return userRepo.findAll();
	}

	public User allow(User userFromDb, User user) throws InvalidOperationException {
		if (user.getRoles() == null ||  user.getRoles().size() == 0)
			throw new InvalidOperationException("Ошибка : получен пустой список прав!");
		userFromDb.setRoles(user.getRoles());
		return userRepo.save(userFromDb);
	}
}
