package ru.smolina.request.repos;

import org.springframework.data.repository.CrudRepository;

import ru.smolina.request.domain.User;

public interface UserRepo extends CrudRepository<User, Long>{
	public User findByUsername(String username);
	

}
