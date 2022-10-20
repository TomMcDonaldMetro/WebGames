<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
<style>
	table.center{
		margin-left:auto;
		margin-right: auto;
	}
	
	.container{
		width: 300px;
		height: 200px;
		position:absolute;
		top: 50%;
		left: 50%;
		margin-top: -100px;
		margin-left: -150px;
	}
</style>
</head>
<body>
<%@ include file = "header.html" %>
	<div class = "container">

  <h1 style = "color: #01B0F1;">Sign up</h1>
  <form action="signup" method="POST">
    <div class="form-group">
      <label for="fname">First Name</label>
      <input type="textarea" class="form-control" id="fname" name="fname" aria-describedby="emailHelp" placeholder="Enter first name">
    </div>
    <div class="form-group">
      <label for="lname">Last Name</label>
      <input type="textarea" class="form-control" id="lname" name="lname" aria-describedby="emailHelp" placeholder="Enter last name">
    </div>
    <div class="form-group">
      <label for="email">Email address</label>
      <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email">
      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" id="password" name="password" placeholder="Password">
    </div>
    <div class="form-group">
      <label for="repeat_password">Repeat Password</label>
      <input type="password" class="form-control" id="repeat_password" name="repeat_password" placeholder="Repeat password">
    </div>
    <div class="form-group form-check">
      <input type="checkbox" class="form-check-input" id="exampleCheck1">
      <label class="form-check-label" for="exampleCheck1">Remember me</label>
    </div>
    <button type="submit" class="btn btn-primary">Sign Up</button>
  </form>
</div>

<%@ include file = "footer.html" %>
</body>
</html>