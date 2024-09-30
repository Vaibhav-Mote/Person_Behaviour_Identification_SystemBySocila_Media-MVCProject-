package BISSM.BehaviourMVCProject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import BISSM.BehaviourMVCProject.model.PostLikeCount;
import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.PostServiceImpl;
import BISSM.BehaviourMVCProject.service.userServiceImpl;


@Controller
public class adminSearchController {

	@Autowired
	userServiceImpl userService;
	
	@Autowired
	PostServiceImpl postService;
	
	@RequestMapping("adminSearch")
	public ModelAndView isgetAdminSearchPage() {
		List <UserInfoModel>list=userService.isgetAllUsers();
		ModelAndView model=new ModelAndView();
		model.addObject("list",list);
		model.setViewName("adminSearch");
		return model;
	}
	

	@RequestMapping("adminSearchUserController")
	public void isgetAdminSearchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String name = request.getParameter("n");
		List<UserInfoModel> list = userService.isgetAllUsersInfoByName(name);
		for (UserInfoModel m : list) {
			out.println("<div id='pointedDiv'>");
			out.println("<a id='anker' href='adminShowAllPostUser?registerid="+ m.getId()+ "'>");
			out.println("<div class='followerInfo'>");
			out.println("<div class='logo'></div>");
			out.println("<div class='username'>");
			out.println("<span>" + m.getUsername() + "</span><br>");
			out.println(m.getName());
			out.println("</div>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
		
	}
	
}
	
	@RequestMapping("adminShowAllPostUser")
	public ModelAndView adminShowAllPosts(HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		int registerid = Integer.parseInt(request.getParameter("registerid"));
		List<PostLikeCount> likecountlist = postService.ViewAllPosts(registerid);
		UserInfoModel model1 = userService.isgetAllDatauser(registerid);
		model.addObject("model", model1);
		model.addObject("likecountlist", likecountlist);
		model.setViewName("adminShowAllPostUser");
		return model;
	}
	
	
	
}
