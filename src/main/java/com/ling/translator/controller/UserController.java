package com.ling.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ling.translator.model.User;
import com.ling.translator.repository.UserRepo;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping
    public ResponseEntity<User> getUser(){
		User user = userRepo.findById(1);		
    	return ResponseEntity.ok().body(user);
    }
	
	@PostMapping
    public ResponseEntity<User> loadUser(@RequestBody User user){
		userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
	
	@PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
		if(user.getId()==0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User loadedUser = userRepo.findById(user.getId());
		if(loadedUser==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		loadedUser.setEmail(user.getEmail());
		loadedUser.setFullName(user.getFullName());
		loadedUser.setLogin(user.getLogin());
		userRepo.save(loadedUser);
		return ResponseEntity.status(HttpStatus.OK).body(loadedUser);
    }
	
    
    

}
