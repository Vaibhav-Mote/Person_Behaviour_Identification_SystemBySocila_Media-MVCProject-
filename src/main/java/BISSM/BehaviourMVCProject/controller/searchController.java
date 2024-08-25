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
import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.userService;


@Controller
public class searchController {

	@Autowired
	userService userService;
	
	@RequestMapping("searchPage")
	public  String getSearchPage(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession(false);
		int registerid=(Integer)session.getAttribute("registerid");
		List <UserInfoModel>list=userService.isgetAllUsers();
		model.addAttribute("list", list);
		model.addAttribute("registerid", registerid);
	    return "search";	
	}
	
	@RequestMapping("search")
	public void searchByName(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String name = request.getParameter("n");
		List<UserInfoModel> list = userService.isgetAllUsersInfoByName(name);
		for (UserInfoModel m : list) {
			out.println("<div id='pointedDiv'>");
			out.println("<a id='anker' href='profile.jsp?registerid="+ m.getId()+ "'>");
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
}
