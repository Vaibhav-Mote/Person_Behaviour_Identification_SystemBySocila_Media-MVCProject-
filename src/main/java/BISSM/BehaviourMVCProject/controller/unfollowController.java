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
public class unfollowController {
	@Autowired
	userServiceImpl userService;
	
	
	@RequestMapping("unfollowController")
	public void unfollowUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    int registerid = Integer.parseInt(request.getParameter("registerid"));
	    int userid = Integer.parseInt(request.getParameter("userid"));
	    
	    boolean isUnfollowed = userService.isRemoveFollower(registerid,userid);
	    
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    if (isUnfollowed) {
	        // Return the 'Follow' button with the updated onclick
	        out.println("Follow");
	    }
	    out.close();
	}

}
