<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*" %>
    <%@page import="java.io.File" %>
    <%@page import="BISSM.BehaviourMVCProject.model.*" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ProfilePage</title>
<link rel="stylesheet" href="resources/CSS/profile.css"/>
<script src="resources/JS/profile.js"></script>
<!-- Bootstrap Icons CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
</head>
<body>
<div class="profileMainDiv">
    <div class="profileLeftMenuDiv">
        <jsp:include page="menus.jsp" />
    </div>
    
    <div class="profileLeftMenuDivMobile">
        <jsp:include page="mobileMenus.jsp" />
    </div>
    <%
    UserInfoModel model = (UserInfoModel)request.getAttribute("userInfoModel");
    int registerid=model.getId();
    List<PostLikeCount> likecountlist =(List<PostLikeCount>)request.getAttribute("likecountlist");
   
    %>
    
    <div class="profilemainContaint">
    <div class="containt">
    <div class="profilecs">
    <div class="profilePhotoLogocs">
    <img src="" style="width:100%;height:100%; border-radius: 50%" >
  
    </div>
   <div class="usernamecs">
  <h2><%= model.getUsername() %></h2>
  <h5><%= model.getName() %></h5>
  <p><%= model.getEmail() %></p>
  <%
  int followingCount = (Integer)request.getAttribute("FollowingCount");
  int followerCount =(Integer)request.getAttribute("FollowerCount");
  
  int postcount = 0;
  if (likecountlist != null) {
      for (PostLikeCount p : likecountlist) {
          if (p.getPost() != null) {
              ++postcount;
          }
      }
      
  %>
  
  
  <a class= "ff"href="followr?registerid=<%= registerid %>">Followers: <%= followerCount %></a>
  <a class="ff" href="following?registerid=<%= registerid %>">Followings: <%= followingCount %></a>
  <a href="#">Posts: <%= postcount %></a>
  <%
   int registerids=(Integer)session.getAttribute("registerid");
   if(registerids==registerid){
	   %>
  <a href="editP" id="editProfilebtn">EditProfile</a>
 <%
   }
 %>
   </div>
    
    </div>
   <div class="postdiv">
   <%
   for (PostLikeCount p : likecountlist) {
   %>
   <div class="viewPost">
   <div class="mainHeaderPost">
   <div class="logo">
   <img  style="width:100%;height:100%; border-radius: 50%" >
   </div>
   <div class="usernamee">
   <h5><%= model.getName() %></h5>
 <%List<Integer>likepostlist=(List)request.getAttribute("likedpost"); %>
   <div class="post">
   <p><%= p.getPost() %></p>
   
   
  <%
  String uploadDir = getServletContext().getRealPath("")+"uploads"; 
   int postid = p.getPostid();
   String filename=p.getFilename();
   String path=uploadDir+"\\"+filename;
   
   %>
   </div>
   
   </div>
   <%
   if(registerids==registerid){
	   %>
  <div class="deletePost">
 <a href="deletePost?postid=<%= postid %>"><i class="bi bi-trash" style="color:white"></i></a>
  </div> 
 <%
   }
 %>
   
   </div>   <!-- mainHeaderPost -->
  <div class="postImage">
   <img alt="<%= filename %>" src="<%= request.getContextPath() %>/resources/uploads/<%= filename %>">
</div>
  <div class="likeComment">   
   <form  method="post">
    <input type="hidden" name="postid" value="<%= postid%>"/>
    <input type="hidden" name="registerid" value="<%= registerid %>"/>
    <%
    int flag=0;
    for(Integer l:likepostlist){
    if(l==postid){
    	flag=1;
    	break;
 }
    }
    if(flag==1){
    	%>
      <a href="#" class="likeicon" id="likeiconprofile<%=postid%>">
    <i class="bi bi-heart-fill text-danger" onclick="likeUnlikePostprofile(<%=postid%>)"></i><%= p.getCount() %>
</a>

 <% 
    }
    else{
    	%>
              <a href="#" class="likeicon" id="likeiconprofile<%=postid%>">
    <i class="bi bi-heart-fill text-white" onclick="likeUnlikePostprofile(<%=postid%>)"></i><%= p.getCount() %>
</a>
  <% 
    }
    %> 
   
<a href="#" class="commenticon" id="commenticon<%=postid%>"><i class="bi bi-chat-right"></i><%= p.getCommentCount() %>
<input id="comment<%=postid%>" class="comment" type="text" name="comment" value="" placeholder="Comment Here" style="width:7em;height:0px;padding:13px"/></a>
<input type="button" name="comments" value="Comment" onclick="iscommentpost(<%=postid%>)" />
 </form>

    </div>
   
   </div>
   
   <%
   }
   %>
   
  </div>
  <%						
      } else {
  %>
   <a href="#">Posts: <%= postcount %></a>
   <a href="followr?registerid=<%= registerid %>">followers: <%= followerCount %></a>
  <a href="following?registerid=<%= registerid %>">followings: <%= followingCount %></a>
    <%
   int registerids=(Integer)session.getAttribute("registerid");
   if(registerids==registerid){
	   %>
  <a href="editProfile?registerid=<%= registerid %>" id="editProfilebtn">EditProfile</a>
 <%
   }
 %>
 
  
   <div>
   <h5 style="color:white;padding-top:10px">No Post</h5>
   </div>
  <% 
      }
  %>
    
    </div>
    
    </div>
    
</div>
</body>
</html>
