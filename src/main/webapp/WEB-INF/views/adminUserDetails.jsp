<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*" %>
<%@ page import="BISSM.BehaviourMVCProject.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Details</title>
    <link rel="stylesheet" href="resources/CSS/adminIndex.css">
    <link rel="stylesheet" href="resources/CSS/adminUserDetails.css">
</head>
<body>
    <div class="maindiv">
        <div class="leftMenudiv">
            <%@ include file="adminMenus.jsp" %>
        </div>
        <div class="rightMenuDiv">
            <%
                // Fetch the lists from request attributes
                List<UserInfoModel> userList = (List<UserInfoModel>) request.getAttribute("userAllInfo");
                List<List<PostLikeCount>> postLikeCountsList = (List<List<PostLikeCount>>) request.getAttribute("list");
                
                if (userList != null) {
   
                    int index = 0;
                    for (UserInfoModel user : userList) {
                        List<PostLikeCount> postLikeCounts = postLikeCountsList.get(index);
                        int postCount = 0;
                        
                        // Count the number of posts using a for-each loop
                        if (postLikeCounts != null) {
                            for (PostLikeCount postLikeCount : postLikeCounts) {
                                if (postLikeCount.getPost() != null) {
                                    postCount++;
                                }
                            }
                        }
            %>
            <div class="profilemainContaint">
                <div class="containt">
                    <div class="profilecs">
                        <div class="profilePhotoLogocs">
                            <!-- Profile photo section can be added here -->
                        </div>
                        <div class="usernamecs">
                            <h2><%= user.getUsername() %></h2>
                            <h5><%= user.getName() %></h5>
                            <h5><%= user.getPass() %></h5>
                            <p><%= user.getEmail() %></p>
                            <a href="#">Posts: <%= postCount %></a>
                            <a href="#">Followers: <%= user.getFollowerCount() %></a>
                            <a href="#">Followings: <%= user.getFollowingCount() %></a>
                        </div>
                    </div>
                </div>
            </div>
            <%
                        index++;
                    }
                } else {
            %>
            <h2 style="color:white">Data not found</h2>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
