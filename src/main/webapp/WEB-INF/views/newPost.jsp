<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a New Post</title>
<script src="resources/JS/newPost.js"></script>
<link rel="stylesheet" href="resources/CSS/newPostt.css"/>
</head>
<body>
<div class="mainContent">
    <div class="leftprofile">
        <%@ include file="menus.jsp" %>
    </div>
    <div class="profileLeftMenuDivMobile">
        <jsp:include page="mobileMenus.jsp" />
    </div> 
    <div class="newpost">
        <div class="postImgdiv">
            <img id="postimg" src="">
        </div>
        <div class="post">
            <h2 id="h2">Create Post</h2>
           <form name="frm" action="l" method="post" enctype="multipart/form-data" onsubmit="return isValidPost()">
                
                 <textarea id="newpost" name="post" rows="4" cols="50" placeholder="Create the New Post" value=""></textarea>
                <input type="file" name="file" id="file" onchange="previewImage()">
                <input type="submit" name="btn" value="Post" id="btn" class="btn btn-primary">
            </form>
            <% 
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <h4 class="msg">${message}</h4>
            <% 
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
