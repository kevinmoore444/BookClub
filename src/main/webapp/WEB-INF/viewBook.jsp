<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>

</head>
<body>
	<div class="content">
		<h1>Welcome <c:out value="${username}"/></h1>
		<!--Header Buttons  -->
		<a href="/books"><button>Back to the shelves</button></a> <a href="/logout"><button>Logout</button></a>
		<!--Book Details  -->
		<h1 style="margin-top: 20px; text-decoration: underline">${book.bookName}</h1>
		
		<!-- Conditionally Render content based upon logged in User -->
		<!--Edit and Delete Buttons for Logged in User only -->
		<!-- If logged in user did not post this book -->
<c:choose>
		<c:when test="${userId.equals(book.reader.id)}">
			<h4>You read ${book.bookName} by ${book.authorName}</h4>
			<h4>Here are your thoughts:</h4>
			<br/>
			<p>${book.myThoughts}</p>

			<a href="/books/edit/${book.id}"><button style="margin-bottom: 10px" class="btn btn-success">Edit Book</button></a>			
			<form action="/books/delete/${book.id}" method="post">
			<input type="hidden" name="_method" value="delete" />
			<button class="btn btn-danger" value="delete">Delete</button>
			</form>
		</c:when>

		<c:otherwise>
			<h4>${book.reader.userName} read ${book.bookName} by ${book.authorName}</h4>
			<h4>Here are ${book.reader.userName}'s thoughts:</h4>
			<br/>
			<p>${book.myThoughts}</p>
		</c:otherwise>
</c:choose>		
		



	
	</div>	

</body>
</html>