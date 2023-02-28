package com.codingdojo.bookClub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.bookClub.models.Book;
import com.codingdojo.bookClub.models.User;
import com.codingdojo.bookClub.services.BookService;
import com.codingdojo.bookClub.services.UserService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	
    //Render the dash-board, return to login screen if session is null
    @GetMapping("/books")
    public String dashboard(HttpSession session, Model model) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	model.addAttribute("bookList", bookService.allBooks());
    	return "dashboard.jsp";
    }    
    
    //Create Book - display form
    @GetMapping("/books/new")
    public String displayNewBookForm(@ModelAttribute("newBook") Book newBook, HttpSession session) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	return "addBook.jsp";
    }
    
    
    //Create Book - process form
	@PostMapping("/books/new")
	public String processBookForm(@Valid @ModelAttribute("newBook") Book newBook, BindingResult result, HttpSession session) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		if(result.hasErrors()) {
			return "addBook.jsp";
		}
		else {
			newBook.setBorrower(null);
			bookService.createBook(newBook);
			return "redirect:/books";
		}
	}
    
    
	//Edit Book - display form
	@GetMapping("/books/edit/{id}")
	public String displayEditBookForm(@PathVariable("id") Long id, Model model, HttpSession session) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		Book foundBook = bookService.oneBook(id);
		model.addAttribute("foundBook", foundBook);
		return "editBook.jsp";
	}
    
	//Edit Donation - process form
	@PutMapping("/books/edit/{id}")
	public String processEditBook(@Valid @ModelAttribute("foundBook") Book book, 
			BindingResult result, @PathVariable("id") Long id, HttpSession session) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		if(result.hasErrors()) {
			return "editBook.jsp";
		}
		else {
			bookService.editBook(book);
			return "redirect:/books";
		}
	}
	
	
	//Delete Book
	@DeleteMapping("/books/delete/{id}")
	public String processDeleteBook(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		bookService.deleteBook(id);
		return "redirect:/books";
	}
	
	//Display one book
	@GetMapping("/books/{id}")
    public String donationDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		Book book = bookService.oneBook(id);
		model.addAttribute("book", book);
		return "viewBook.jsp";
	}
    
    
	//Borrow Book
	@GetMapping("/books/borrow/{id}")
	public String borrowBook(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		
		Book book = bookService.oneBook(id);
		User user = userService.findById((Long) session.getAttribute("userId"));
		book.setBorrower(user);
		bookService.editBook(book);
		
		
		return "redirect:/books";
	}
	
	//Return Book
	@GetMapping("/books/return/{id}")
	public String returnBook(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		Book book = bookService.oneBook(id);
		book.setBorrower(null);
		bookService.editBook(book);
		
		
		return "redirect:/books";
	}
	
    
    
}
