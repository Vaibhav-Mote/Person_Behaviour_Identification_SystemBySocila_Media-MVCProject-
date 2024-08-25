<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/editProfilee.css">
<script src="resources/JS/editProfile.js" ></script>
</head>
<body>

<div class="maindiv">

<div class="leftmenu">
<%@include file="menus.jsp" %>

</div>
<div class="maincontaint">
<div class="editprofile">
<h2 id="editheader">Edit Profile</h2>
<%

UserInfoModel model1 = (UserInfoModel)request.getAttribute("userInfoModel");
%>
<form method="get" action="editProfileController"  >
    <div class="logo">
    </div>
    <label>Username</label><br>
    <input id="username" type="text" name="username" value="<%=model1.getUsername()%>" required/><br>
    <label>Name</label><br>
    <input id="name" type="text" name="name" value="<%=model1.getName()%>"/><br>
    <label>Email</label><br>
    <input id="email" type="text" name="email" value="<%=model1.getEmail()%>"/>
    <input id="btn" type="submit" name="btn" value="Update"/>
</form>
<div id="messagee">${msg}</div>
</div>

</div>


</div>

</body>
</html>