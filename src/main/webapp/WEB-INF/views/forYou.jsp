<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.*" %>
    <%@page import="BISSM.BehaviourMVCProject.model.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/followinFriendsPost.css">
<link rel="stylesheet" href="resources/CSS/profile.css"/>
<script type="text/javascript" src="resources/JS/forYou.js"></script>
<!-- Bootstrap Icons CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">

</head>
<body>
<div class="followingFriendsPost">
<%
int registerid3 = (Integer)session.getAttribute("registerid");
List<List<Object>> post3 = (List<List<Object>>) session.getAttribute("posts");
List<Integer>likepostlist3=(List)session.getAttribute("likedPostList"); 
if (post3 != null) {

        for (List<Object> innerList : post3) {
            PostLikeCount postLikeCount = null;
            UserInfoModel userInfoModel = null;

            for (Object obj : innerList) {
                if (obj instanceof PostLikeCount) {
                    postLikeCount = (PostLikeCount) obj;
                } else if (obj instanceof UserInfoModel) {
                    userInfoModel = (UserInfoModel) obj;
                }
            }

            if (postLikeCount != null && userInfoModel != null) {
%>
    <div class="postdiv">
        <div class="viewPost">
            <div class="mainHeaderPost">
                <div class="logo">
                  <img alt ="not "src="resources/asset/dp.jpg" style="width:100%;height:100%; border-radius: 50%" >
                </div>
                <div class="usernamee">
                    <h5><%= userInfoModel.getUsername() %></h5>
                    <div class="post">
                        <p><%= postLikeCount.getPost() %></p>
                        
                    </div>
                </div>
                
            </div>
            
            <%
            String uploadDir = getServletContext().getRealPath("")+"uploads";
            String filename=postLikeCount.getFilename();
            String path=uploadDir+"\\"+filename;
            int postid=postLikeCount.getPostid();
            %>
            <div class="postImage">
   <img alt="<%= filename %>" src="<%= request.getContextPath() %>/resources/uploads/<%= filename %>" >
 
</div>
  
            <div class="likeComment"> 
             
                 <form  method="post">
    <input type="hidden" name="postid" value="<%= postLikeCount.getPostid() %>"/>
    <input type="hidden" name="registerid" value="<%= registerid3%>"/>
    <%
    int flag=0;
    for(Integer l:likepostlist3){
    if(l==postid){
    	flag=1;
    	break;
 }
    }
    if(flag==1){
    	%>
      <a href="#" class="likeicon1234" id="likeicon1234<%=postid%>">
    <i class="bi bi-heart-fill text-danger" onclick="likeUnlikePost1234(<%=postid%>)"></i><%= postLikeCount.getCount() %>
</a>

 <% 
    }
    else{
    	%>
              <a href="#" class="likeicon" id="likeicon1234<%=postid%>">
    <i class="bi bi-heart-fill text-white" onclick="likeUnlikePost1234(<%=postid%>)"></i><%= postLikeCount.getCount() %>
</a>
  <% 
    }
    %> 
   
<a href="#" class="commenticon" id="commenticon1234<%=postid%>"><i class="bi bi-chat-right"></i><%= postLikeCount.getCommentCount() %>
<input id="comment1234<%=postid%>" type="text" name="comment" value="" placeholder="Comment Here" style="width:7em;height:0px;padding:13px"/></a>
<input type="button" name="comments" value="Comment" onclick="iscommentpost1234(<%=postid%>)" />
 </form>
             
            </div>
        </div>
    </div>
<%
            }
        }
    } else {
%>
    <h5>No Post</h5>
<%
    }
%>
</div>


</body>
</html>