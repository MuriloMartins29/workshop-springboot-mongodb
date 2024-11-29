package com.murilodebarros.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murilodebarros.workshopmongo.domain.User;
import com.murilodebarros.workshopmongo.dto.UserDTO;
import com.murilodebarros.workshopmongo.repository.UserRepository;
import com.murilodebarros.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
	    Optional<User> user = repo.findById(id);
	    if (!user.isPresent()) {
	        throw new ObjectNotFoundException("Objeto não encontrado");
	    }	    
	    return user.get();
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = repo.findById(obj.getId())
		        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		    updateData(newObj, obj);
		    return repo.save(newObj);
		
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
 