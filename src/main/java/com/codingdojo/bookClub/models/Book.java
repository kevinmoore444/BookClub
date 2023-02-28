package com.codingdojo.bookClub.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book {

	
	//Attributes
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
	    @NotNull
	    @Size(min = 3, max = 50)
	    private String bookName;
	
	    @NotNull
	    @Size(min = 3, max = 50)
	    private String authorName;
	    
	    @NotNull
	    @Size(min = 5, max = 200)
	    private String myThoughts;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="reader_id") //foreign key
	    private User reader;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="borrower_id") //foreign key
	    private User borrower;
	    
	    
	    @Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
		
		@PrePersist
		protected void onCreate(){
		   this.createdAt = new Date();
		}
		@PreUpdate
		protected void onUpdate(){
		   this.updatedAt = new Date();
		}
	    
	
		
		
		
		//Constructor
		public Book() {}
		
		
		
		//Generate Getters and Setters
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		public String getMyThoughts() {
			return myThoughts;
		}
		public void setMyThoughts(String myThoughts) {
			this.myThoughts = myThoughts;
		}
		public User getReader() {
			return reader;
		}
		public void setReader(User reader) {
			this.reader = reader;
		}
		
		public User getBorrower() {
			return borrower;
		}
		public void setBorrower(User borrower) {
			this.borrower = borrower;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

}
