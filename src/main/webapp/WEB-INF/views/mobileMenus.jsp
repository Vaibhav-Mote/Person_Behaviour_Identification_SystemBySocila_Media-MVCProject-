<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="BISSM.BehaviourMVCProject.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Mobile Menus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.6.0/css/fontawesome.min.css" integrity="sha384-NvKbDTEnL+A8F/AA5Tc5kmMLSJHUO868P+lDtTpJIeQdGYaUIuLr4lVGOEA1OcMy" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script><meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="resources/CSS/mobileMenus.css">
</head>
<body>
      <%
      UserInfoModel model2 = (UserInfoModel)session.getAttribute("userInfoModel2");
      String name2 = model2.getName();
      String username2=model2.getUsername();
      String email2 = model2.getEmail();
      char firstLetter2=username2.charAt(0);
  
      
int registerid=(Integer)session.getAttribute("registerid");
%>
<div class="mobileMenus ">
    <nav class="navbar navbar-expand-lg navbar-dark menubackground" id="mobilemenus">
   
    <button class="navbar-toggler menuicon" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa-solid fa-bars text-white menuicon"></i>
    </button>
    <h3 class="text-white name" ><%=name2%></h3>
     
    <div class="collapse navbar-collapse " id="navbarNav">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
<a class="nav-link hk text-white margin-leftmenus mt-4 " id="v-pills-home-tab"  href="index?registerid=<%=registerid%>"> <i class="fa-solid fa-house fa-lg p-2"></i>Home</a>
<a class="nav-link hk text-white margin-leftmenus" id="v-pills-profile-tab" href="profileController?registerid=<%=registerid%>" ><i class="fa-solid fa-user fa-lg p-2"></i>Profile</a> 
<a class="nav-link hk text-white margin-leftmenus " id="v-pills-messages-tab"  href="searchPage" ><i class="fa-solid fa-search p-2"></i>Search</a>
<a class="nav-link hk text-white margin-leftmenus" id="v-pills-messages-tab"  href="nPost" ><i class="fa-solid fa-plus fa-lg p-2"></i>Create New Post</a>
<a class="nav-link hk text-white margin-leftmenus" id ="logout" href="loginn" >Logout</a>
    </div>
 </div>
</nav>
</div>

</body>
</html>
