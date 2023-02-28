package com.codingdojo.bookClub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.bookClub.models.LoginUser;
import com.codingdojo.bookClub.models.User;
import com.codingdojo.bookClub.repositories.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository userRepo;
		
		
		public User register(User newUser, BindingResult result) {
			
			//Find user in the database by email
			Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
			
			//If the email is present, reject
			if(optionalUser.isPresent()) {
				result.rejectValue("email", "unique", "This email is already registered");
			}
			//Reject if password doesn't match confirmation
			if(!newUser.getConfirm().equals(newUser.getPassword())) {
				result.rejectValue("email", "matches", "This confirm password does not match");
			}
			//If Result has errors, return
			if(result.hasErrors()) {
				return null;
			}
			//Hash and set password, save user to database
			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashed);
			userRepo.save(newUser);
			
			return newUser;
		}
		
		
		
		
		public User login(LoginUser newLogin, BindingResult result) {

			
		//Find user in the database by email
		Optional<User> optionalUser = userRepo.findByEmail(newLogin.getEmail());	
		
		//If the email is not present reject and return
		if(!optionalUser.isPresent()) {
			result.rejectValue("email", "unique", "This email is not registered");
			return null;
		}
		//Grab the user from potential user
		User user = optionalUser.get();
		
		//If Bcrypt password match fails
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
		    result.rejectValue("password", "Matches", "Invalid Password!");
		}	
			
		//If result has errors, return	
		if(result.hasErrors()) {
			return null;
		}
			
			return user;
		}
		
		//find One
		public User findById(Long id) {
			Optional<User> optionalUser = userRepo.findById(id);
			if(optionalUser.isPresent()) {
				return optionalUser.get();
			}
			else {
				return null;
			}
		}
		
		
		
}
