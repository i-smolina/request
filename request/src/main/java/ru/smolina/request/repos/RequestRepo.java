package ru.smolina.request.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import antlr.collections.List;
import ru.smolina.request.domain.Request;
import ru.smolina.request.domain.User;

public interface RequestRepo extends JpaRepository<Request, Long>{
	
	public Iterable<Request> findByAuthor(User author);
	public Request findByStatusLike(String status);
	
	

}
