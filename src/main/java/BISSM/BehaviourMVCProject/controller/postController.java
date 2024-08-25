package BISSM.BehaviourMVCProject.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import BISSM.BehaviourMVCProject.model.PostLikeCount;
import BISSM.BehaviourMVCProject.service.PostService;
import BISSM.BehaviourMVCProject.service.userServiceImpl;
@Controller
public class postController {
@Autowired
userServiceImpl userService;
@Autowired
PostService postService;
	@RequestMapping("deletePost")
	public String isDeletedPost(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int registerid=(Integer)session.getAttribute("registerid");
		int postid=Integer.parseInt(request.getParameter("postid"));
		 boolean b=postService.isdeletePost(postid);
		int followingCount = userService.getFollowingCount(registerid);
		  int followerCount = userService.getFollowerCount(registerid);
		List<PostLikeCount> likecountlist = postService.ViewAllPosts(registerid);
		session.setAttribute("likecountlist", likecountlist);
		session.setAttribute("FollowerCount", followerCount);
		session.setAttribute("FollowingCount", followingCount);
			return "profile";	
	}
	
	@RequestMapping("likepost")
	public void isLikePost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 HttpSession session = request.getSession(false);  // Get the current session
		int registerid=(Integer)session.getAttribute("registerid");
		int postid=Integer.parseInt(request.getParameter("postid"));
		boolean b = postService.islikePost(registerid, postid);
		PrintWriter out=response.getWriter();
		if (b) {
			int likeCount = postService.isgetLikeCount(postid);
	        out.print("<a href='#' class='likeicon' id='likeicon" + postid + "'><i class='bi bi-heart-fill text-danger' onclick='likeUnlikePost(" + postid + ")'></i>" + likeCount + "</a>");
	    } else {
	    	int likeCount = postService.isgetLikeCount(postid);
	        out.print("<a href='#' class='likeicon' id='likeicon" + postid + "'><i class='bi bi-heart-fill text-white' onclick='likeUnlikePost(" + postid + ")'></i>" + likeCount + "</a>");
	    }

			
		
	}
	
	@RequestMapping(value = "comment")
	public void isCommentPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    HttpSession session = request.getSession();
	    int registerid = (Integer) session.getAttribute("registerid");
	    int postid = Integer.parseInt(request.getParameter("postid"));
	    String comment = request.getParameter("comment");
	    PrintWriter out = response.getWriter();
	    boolean b = postService.isCommenPost(registerid, postid, comment);
	    int commentCount = postService.isgetCommentCount(postid);
	    out.println("<a href='#' class='commenticon' id='commenticon" + postid + "'><i class='bi bi-chat-right'></i>" + commentCount + " <input id='comment" + postid + "' type='text' name='comment' value='' placeholder='Comment Here' style='width:15em;height:20px'/></a>");
	}

	
	
}
