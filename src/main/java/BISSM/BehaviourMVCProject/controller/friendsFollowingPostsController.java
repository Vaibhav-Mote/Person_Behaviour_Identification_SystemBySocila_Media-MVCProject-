package BISSM.BehaviourMVCProject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import BISSM.BehaviourMVCProject.service.PostService;

@Controller
public class friendsFollowingPostsController {

    @Autowired
    private PostService postService;


	@RequestMapping("p")
	public void isLikePostt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false); // Get the current session
		int registerid = (Integer) session.getAttribute("registerid");
		int postid = Integer.parseInt(request.getParameter("postid"));
		boolean b = postService.islikePost(registerid, postid);
		PrintWriter out = response.getWriter();
		if (b) {
			int likeCount = postService.isgetLikeCount(postid);
			out.print("<a href='#' class='likeicon' id='likeicon12" + postid
					+ "'><i class='bi bi-heart-fill text-danger' onclick='likeUnlikePost12(" + postid + ")'></i>"
					+ likeCount + "</a>");
		} else {
			int likeCount = postService.isgetLikeCount(postid);
			out.print("<a href='#' class='likeicon' id='likeicon12" + postid
					+ "'><i class='bi bi-heart-fill text-white' onclick='likeUnlikePost12(" + postid + ")'></i>"
					+ likeCount + "</a>");
		}

	}
    
    @RequestMapping("comment12")
    public void isCommentPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int registerid = (Integer) session.getAttribute("registerid");
        int postid = Integer.parseInt(request.getParameter("postid"));
        String comment = request.getParameter("comment");

        PrintWriter out = response.getWriter();
        boolean commented = postService.isCommenPost(registerid, postid, comment);
        int commentCount = postService.isgetCommentCount(postid);
        out.println("<a href='#' class='commenticon' id='commenticon12" + postid + "'>" +
                    "<i class='bi bi-chat-right'></i>" +
                    commentCount + " <input id='comment12" + postid + "' type='text' name='comment' value='' placeholder='Comment Here' style='width:7em;height:0px;padding:13px'/></a>");
    }
}
