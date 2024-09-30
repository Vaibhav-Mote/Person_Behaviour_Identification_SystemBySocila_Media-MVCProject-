package BISSM.BehaviourMVCProject.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import BISSM.BehaviourMVCProject.model.PostLikeCount;
import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.PostServiceImpl;
import BISSM.BehaviourMVCProject.service.userServiceImpl;

@Controller
public class loginServletController {
	@Autowired
	userServiceImpl userSer;
	@Autowired
	PostServiceImpl postService;

	@RequestMapping("loginpage")
	public String getLoginPage() {
		return "login";
	}

	

	

	@RequestMapping(value = "/loginn")
	public String getlogin(HttpServletRequest request, Map<String, Object> map) throws ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int registerid = userSer.isgetregisterid(username, password);
		if (registerid != -1) {
			
			HttpSession session = request.getSession(true);
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
		else {
			map.put("msg", "login failed");
			return "login";
			
		}
		
	}

}
