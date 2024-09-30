<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*" %>
    <%@ page import="BISSM.BehaviourMVCProject.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/adminIndex.css">
<link rel="stylesheet" href="resources/CSS/searchh.css">
<link rel="stylesheet" href="resources/CSS/adminSearch.css">
<script src="resources/JS/search.js"></script>
<script type="text/javascript">

function isSearchUseradmin(str){
	let xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
	if(this.readyState==4 && this.status==200){
		document.getElementById("pointedDiv").innerHTML=this.responseText;
	}
	};
	xhttp.open("Get","adminSearchUserController?n="+str,true);
	xhttp.send();

	
}

</script>
</head>
<body>
<div class="maindiv">

<div class="leftMenudiv">
<%@include file="adminMenus.jsp" %>
</div>
<div class="rightMenuDiv">

<div class="searchdiv">
<input type="text" id="searchinput"name="search" placeholder="Search Here" onkeyup="isSearchUseradmin(this.value)"/>


<div id="pointedDiv" >
<%
int registerid=(Integer)session.getAttribute("userid");

List <UserInfoModel>list=(List)request.getAttribute("list");
if(list!=null){
for(UserInfoModel m:list){
	int userid=m.getId();
	%>
	
	
<div class="followerInfo">
<div class="logo">
</div>
<a id ="anker"href="adminShowAllPostUser?registerid=<%=m.getId()%>">
<div class="username">
 <span><%=m.getUsername() %></span><br>
<%=m.getName()%>
</div>
</a>


 </div>

	<% 
}
}
else{
	%>
	<h2>No Data Found</h2>
	<% 
}
%>
</div>

</div>


</div>

</div>
</body>
</html>