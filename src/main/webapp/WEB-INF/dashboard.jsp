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
<title>Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>

</head>
<body>
	<div class="content">
		<h1>Welcome <c:out value="${username}"/></h1>
		<a href="/books/new"><button>+ Add a book to my shelf</button></a> | <a href="/logout"><button>Logout</button></a>
	</div>
	<div class="container mt-5">	
		<h1>Available Books to Borrow</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author Name</th>
						<th>Owner</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="eachBook" items="${bookList}">
						<c:if test="${eachBook.borrower.id== null}">
							<tr>
								<td> <c:out value="${eachBook.id}"/></td>
								<td><a href="/books/${eachBook.id}"><c:out value="${eachBook.bookName}"/></a></td>
								<td><c:out value="${eachBook.authorName}"/></td>
								<td><c:out value="${eachBook.reader.userName}"/></td>
								<!-- Conditional Rendering of the actions available -->
								<td>
									<c:choose>
										<c:when test="${userId.equals(eachBook.reader.id)}">							
											<a href="/books/edit/${eachBook.id}"><button style="margin-bottom: 10px" class="btn btn-success">Edit Book</button></a>			
											<form action="/books/delete/${eachBook.id}" method="post">
											<input type="hidden" name="_method" value="delete" />
											<button class="btn btn-danger" value="delete">Delete</button>
											</form>
										</c:when>
					
										<c:otherwise>
											<a href="books/borrow/${eachBook.id}"><button>Borrow</button></a>
										</c:otherwise>
								</c:choose>		
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			
			<h1>Books I'm borrowing</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author Name</th>
						<th>Owner</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="eachBook" items="${bookList}">
						<c:if test="${userId==eachBook.borrower.id}">
							<tr>
								<td> ${eachBook.id} </td>
								<td><a href="/books/${eachBook.id}"><c:out value="${eachBook.bookName}"/></a></td>
								<td><c:out value="${eachBook.authorName}"/></td>
								<td><c:out value="${eachBook.reader.userName}"/></td>
								<td><a href="/books/return/${eachBook.id}"><button>Return</button></a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
	</div>
</body>
</html>