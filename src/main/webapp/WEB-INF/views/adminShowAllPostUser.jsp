<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/adminIndex.css">
<link rel="stylesheet" href="resources/CSS/adminShowAllPostUser.css">
  <%@page import="BISSM.BehaviourMVCProject.model.*" %>
</head>
<body>
<div class="maindiv">

<div class="leftMenudiv">
<%@include file="adminMenus.jsp" %>
</div>
<div class="rightMenuDiv">
<div class="maininfodiv">
<%

int registerid = Integer.parseInt(request.getParameter("registerid"));
UserInfoModel model = (UserInfoModel) request.getAttribute("model");
List<PostLikeCount> likecountlist = (List<PostLikeCount>) request.getAttribute("likecountlist");

if(likecountlist!=null){
for (PostLikeCount p : likecountlist) {
%>
<div class="postdiv">
<div class="logo">
<div class="pphoto">

</div>
</div>
<div class="username">
<h2><%=model.getUsername() %></h2>
</div>
<div class="post">

<a href="adminPrediction?post=<%=p.getPost() %>">

<p><%=p.getPost() %></p>
</div>
</a>
</div>
<% 	
}
%>
</div>
<div class="allPosts">
    <input type="button" name="btn" value="All Post prediction" style="padding:10px;margin-left:880px;margin-top:20px;margin-bottom:20px" 
           onclick="location.href='allpostPrediction?registerid=<%=registerid%>'" />
</div>

<% 
}
else{
	
	%>
	<h1 style="color:white; text-align:center">Post Not Avaliable</h1>
	</div>
	<% 
}

%>




</div>

</div>
</body>
</html>