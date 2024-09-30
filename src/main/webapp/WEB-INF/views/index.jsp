<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>indexpage</title>
    <link rel="stylesheet" href="resources/CSS/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

    <div class="main">

        <div class="leftdiv">
            <%@include file="menus.jsp"%> 
        </div>
        	
        <div class="togglemenus">
            <%@include file="mobileMenus.jsp"%> 
        </div>

        <div class="middlediv">
            <div class="menu">
                <ul class="nav nav-pills nav-fill" id="pills-tab" role="tablist"
                    style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-primary); --bs-nav-pills-link-active-bg: var(--bs-white);">
                    <li id="li">
                        <a class="nav-link active hk bg-transparent text-white"  id="pills-home-tab " href="#pills-home"
                           role="tab" aria-controls="pills-home" aria-selected="true" data-toggle="pill">For You</a>
                    </li>
                    <li id="li">
                        <a class="nav-link hk bg-transparent text-white" id="pills-profile-tab " href="#pills-profile"
                           role="tab" aria-controls="pills-profile" aria-selected="false" data-toggle="pill">Following</a>
                    </li>
                </ul>
            </div>

            <div class="maincontent" style="height:100%">
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home"
                        role="tabpanel" aria-labelledby="pills-home-tab">
                        <%@include file="forYou.jsp"%> 
                    </div>
                    <div class="tab-pane fade" id="pills-profile" role="tabpanel"
                        aria-labelledby="pills-profile-tab">
                        <%@include file="ff.jsp"%>
                    </div>
                </div>
            </div>

        </div>

        <div class="rightdiv">
            <!-- Any content for the right division can be placed here -->
            kkkk
        </div>

    </div>

</body>
</html>
