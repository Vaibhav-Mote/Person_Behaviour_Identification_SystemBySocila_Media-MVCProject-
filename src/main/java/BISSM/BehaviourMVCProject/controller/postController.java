package BISSM.BehaviourMVCProject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import BISSM.BehaviourMVCProject.model.PostLikeCount;
import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.PostService;
import BISSM.BehaviourMVCProject.service.userServiceImpl;

@Controller
public class postController {
	@Autowired
	userServiceImpl userService;
	@Autowired
	PostService postService;

	@RequestMapping("deletePost")
	public String isDeletedPost(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		int registerid = (Integer) session.getAttribute("registerid");
		int postid = Integer.parseInt(request.getParameter("postid"));
		boolean b = postService.isdeletePost(postid);
		int followingCount = userService.getFollowingCount(registerid);
		int followerCount = userService.getFollowerCount(registerid);
		List<PostLikeCount> likecountlist = postService.ViewAllPosts(registerid);
		UserInfoModel umodel = userService.isgetAllDatauser(registerid);
		List<Integer> likedPostList = postService.getAlredyLikedPost(registerid);
		model.addAttribute("likedpost", likedPostList);
		model.addAttribute("likecountlist", likecountlist);
		model.addAttribute("FollowerCount", followerCount);
		model.addAttribute("FollowingCount", followingCount);
		model.addAttribute("userInfoModel", umodel);

		return "profile";
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
		out.println("<a href='#' class='commenticon' id='commenticon" + postid + "'><i class='bi bi-chat-right'></i>"
				+ commentCount + " <input id='comment" + postid
				+ "' type='text' name='comment' value='' placeholder='Comment Here' style='width:7em;height:0px padding:13px'/></a>");
	}

}
