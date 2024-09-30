package BISSM.BehaviourMVCProject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.PostServiceImpl;
import BISSM.BehaviourMVCProject.service.userServiceImpl;

@Controller
public class IndexController {
	@Autowired
	userServiceImpl userSer;
	@Autowired
	PostServiceImpl postService;
	
	@RequestMapping("index")
	public String getIndexPage(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int registerid=(Integer)session.getAttribute("registerid");
		UserInfoModel model = userSer.isgetAllDatauser(registerid);
		session.setAttribute("userInfoModel2", model);
		session.setAttribute("registerid", registerid);
        List<List<Object>> posts = postService.getForYou(); 
        List<Integer>likedPostList=postService.getAlredyLikedPost(registerid);
        List<List<Object>>FriendsFollowerPostsList = postService.getFriendsFollowerPosts(registerid);
       session.setAttribute("posts", posts);
       session.setAttribute("likedPostList",likedPostList);
       session.setAttribute("FriendsFollowerPostsList", FriendsFollowerPostsList);
		return "index";
	}

}
