package com.codingdojo.bookClub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.bookClub.models.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
	List<Book> findAll();
}
