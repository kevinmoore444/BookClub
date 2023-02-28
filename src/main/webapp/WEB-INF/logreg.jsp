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
<title>Login and Registration</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>

</head>
<body>
	<div class="content">
		<h1>Register</h1>
			<form:form action="/register" metho="POST" modelAttribute="newUser">
			
				<div>
					<form:label path="userName">User Name:</form:label>
					<form:input style="text-align: center" path="userName" class="form-control"/>
					<form:errors path="userName" class="text-danger"/>
				</div>
				<div>
					<form:label path="email">Email:</form:label>
					<form:input style="text-align: center" path="email" class="form-control"/>
					<form:errors path="email" class="text-danger"/>
				</div>
				<div>
					<form:label path="password">Password:</form:label>
					<form:input style="text-align: center" path="password" class="form-control" type="password"/>
					<form:errors path="password" class="text-danger"/>
				</div>
				<div>
					<form:label path="confirm">Confirm password:</form:label>
					<form:input style="text-align: center" path="confirm" class="form-control" type="password"/>
					<form:errors path="confirm" class="text-danger"/>
				</div>
				<button style="margin-top: 20px; margin-bottom: 20px" type="submit" class="btn btn-primary">Register</button>
			</form:form>
			
			
		<h1>Login</h1>
		<form:form action="/login" method="POST" modelAttribute="newLogin">
				<div>
					<form:label path="email">Email:</form:label>
					<form:input style="text-align: center" path="email" class="form-control"/>
					<form:errors path="email" class="text-danger"/>
				</div>
				<div>
					<form:label path="password">Password:</form:label>
					<form:input style="text-align: center" path="password" class="form-control" type="password"/>
					<form:errors path="password" class="text-danger"/>
				</div>
				<button style="margin-top: 20px" type="submit" class="btn btn-primary">Log In</button>
		</form:form>
		
	</div>
</body>
</html>