package com.codingdojo.bookClub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bookClub.models.Book;
import com.codingdojo.bookClub.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;
	
	
	//all donations
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	
	//Create one
	public Book createBook(Book book) {
		return bookRepo.save(book);
		
	}
	
	
	//find One
	public Book oneBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}
		else {
			return null;
		}
	}
			
		
	//edit
	public Book editBook(Book book) {
		return bookRepo.save(book);
	}
		
		
	//delete
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
	
	
	
	
	
	
}
