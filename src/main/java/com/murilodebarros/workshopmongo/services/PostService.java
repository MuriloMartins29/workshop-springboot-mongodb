package com.murilodebarros.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murilodebarros.workshopmongo.domain.Post;
import com.murilodebarros.workshopmongo.repository.PostRepository;
import com.murilodebarros.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
	    Optional<Post> user = repo.findById(id);
	    if (!user.isPresent()) {
	        throw new ObjectNotFoundException("Objeto não encontrado");
	    }	    
	    return user.get();
	}
	
	public List<Post> findByTitle(String text) {
		return repo.findByTitleContainingIgnoreCase(text);
	}
}
 