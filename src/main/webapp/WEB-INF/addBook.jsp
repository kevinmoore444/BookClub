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
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>

</head>
<body>
	<div class="content">
		<h1>Welcome <c:out value="${username}"/></h1>
		<a href="/books"><button>Back to the shelves</button></a>|<a href="/logout"><button>Logout</button></a>
	</div>
	<div class="container mt-5">
			<h1>New Book</h1>
			<form:form action="/books/new" method="post" modelAttribute="newBook" class="form" >
			    <p>
			        <form:label path="bookName">Title</form:label>
			        <form:input path="bookName" class="form-control"/>
			        <form:errors path="bookName"/>
	
			    </p>
			    <p>
			        <form:label path="authorName">Author</form:label>
			        <form:input path="authorName" class="form-control"/>
			        <form:errors path="authorName"/>
	
			    </p>
			    <p>
			        <form:label path="myThoughts">Description</form:label> 
			        <form:textarea path="myThoughts" class="form-control"/>
			 		<form:errors path="myThoughts"/>    
			    </p>
					<form:hidden path="reader" value="${userId}"/>
			    <button class="btn btn-primary" type="submit" value="Submit">Submit</button>
			</form:form>
		</div>  
</body>
</html>