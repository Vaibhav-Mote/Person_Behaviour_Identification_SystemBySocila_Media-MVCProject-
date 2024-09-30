<%-- 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/searchh.css">
<script src="resources/JS/search.js"></script>
</head>
<body>
<div class="allcontaint">
<div class="leftdiv">
<%@include file="menus.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
</div>
<div class="maincontaint">
<div class="searchdiv">
<input type="text" id="searchinput"name="search" placeholder="Search Here" onkeyup="isSearchUser(this.value)"/>
<div id="pointedDiv">
<%
int registerid=(Integer)request.getAttribute("registerid");
List <UserInfoModel>list=(List)request.getAttribute("list");
List <UserInfoModel>followingList=(List)request.getAttribute("followingList");
for(UserInfoModel m:list){
	int userid=m.getId();
	%>
<div class="followerInfo">
<div class="logo">

</div>
<a id ="anker"href="profileController?registerid=<%=m.getId()%>">
<div class="username">
 <span><%=m.getUsername() %></span><br>
<%=m.getName()%>
</div>
</a>
<%
boolean flag=false;
for(UserInfoModel f:followingList){
	if(m.getId()==f.getId()){
		flag=true;
		break;	
	}
}
if(flag){
		%>
		<div class="follows">
    <a class="ff" id="follow<%=m.getId() %>"  onclick="unfollow(<%=registerid%>, <%=userid%>)">Following</a>
</div>
		 </div>
		<% 
	}
	else{
		%>
		<div class="follows">
    <a  class="ff" id="follow<%=m.getId() %>"  onclick="follow(<%=registerid%>, <%=userid%>)">Follow</a>
</div>
</div>
		<% 
	}
}
%>

</div>
</div>
</div>
</div>


</body>
</html> --%>






<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="resources/CSS/searchh.css">
    <script src="resources/JS/search.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="allcontaint">
    <div class="leftdiv">
        <%@ include file="menus.jsp" %>
    </div>

    <div class="maincontaint">
        <div class="searchdiv">
            <input type="text" id="searchinput" name="search" placeholder="Search Here" onkeyup="isSearchUser(this.value)" />
            <div id="pointedDiv">
                <%
                int registerid = (Integer) request.getAttribute("registerid");
                List<UserInfoModel> list = (List) request.getAttribute("list");
                List<UserInfoModel> followingList = (List) request.getAttribute("followingList");

                for (UserInfoModel m : list) {
                    int userid = m.getId();
                    boolean isFollowing = false;

                    for (UserInfoModel f : followingList) {
                        if (m.getId() == f.getId()) {
                            isFollowing = true;
                            break;
                        }
                    }
                %>
                <div class="followerInfo">
                    <div class="logo">
                        <!-- Add logo here if needed -->
                    </div>

                    <a id="anker" href="profileController?registerid=<%= m.getId() %>">
                        <div class="username">
                            <span><%= m.getUsername() %></span><br>
                            <%= m.getName() %>
                        </div>
                    </a>

                    <div class="follows">
                        <a class="ff" id="follow<%= m.getId() %>" 
                           onclick="<%= isFollowing ? "unfollow(" + registerid + ", " + userid + ")" : "follow(" + registerid + ", " + userid + ")" %>">
                            <%= isFollowing ? "Following" : "Follow" %>
                        </a>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>

</body>
</html>


