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

	public String getMenus() {
		return "menus";
	}

	@RequestMapping("newPost")
	public String getNewPostPage() {
		this.getMenus();
		return "newPost";
	}

	@RequestMapping(value = "/loginn")
	public String getlogin(HttpServletRequest request, Map<String, Object> map) throws ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int registerid = userSer.isgetregisterid(username, password);
		if (registerid != -1) {
			HttpSession session = request.getSession(true);
			UserInfoModel model = userSer.isgetAllDatauser(registerid);
			session.setAttribute("userInfoModel", model);
			session.setAttribute("registerid", registerid);
			
//			List<Integer>likedPostList=postService.getAlredyLikedPost(registerid);
//			session.setAttribute("likedpost", likedPostList);
			
			return "index";

		} else {
			map.put("msg", "login failed");
			return "login";
		}
	}

}
