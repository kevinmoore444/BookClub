package com.codingdojo.bookClub.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.bookClub.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
