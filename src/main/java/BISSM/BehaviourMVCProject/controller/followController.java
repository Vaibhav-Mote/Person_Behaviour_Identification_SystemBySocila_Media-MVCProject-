package BISSM.BehaviourMVCProject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import BISSM.BehaviourMVCProject.service.userServiceImpl;

@Controller
public class followController {
	@Autowired
	userServiceImpl userService;
	/*
	@RequestMapping("followController")
	public void followUnfollow(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    int registerid = Integer.parseInt(request.getParameter("registerid"));
	    int userid = Integer.parseInt(request.getParameter("userid"));
	    boolean isFollowing = userService.isFollowUser(userid, registerid); // Update logic here
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    System.out.println("followcontrolloer");
	    if (isFollowing) {
	        out.println("<a class='ff' id='follow" + userid + "' onclick='unfollow(" + registerid + ", " + userid + ")'>Following</a>");
	    } 
	    out.close();
	}

*/
	@RequestMapping("followController")
	public void followUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    int registerid = Integer.parseInt(request.getParameter("registerid"));
	    int userid = Integer.parseInt(request.getParameter("userid"));
	    
	    boolean isFollowing = userService.isFollowUser(userid, registerid);;
	    
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    if (isFollowing) {
	        out.println("Following");
	    }
	    out.close();
	}
}
