package BISSM.BehaviourMVCProject.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.userServiceImpl;

@Controller
public class followersController {
		
	@Autowired
	userServiceImpl userSer;	
	
	

	@RequestMapping("followers")
	public String getFollowerPage(HttpServletRequest request,Model model) {
		int registerid=Integer.parseInt(request.getParameter("registerid"));
		List <UserInfoModel>list=userSer.isgetFollowerList(registerid);
		model.addAttribute("followerList", list);
		return "followers";
	}
	
	
	
	@RequestMapping("/removef")
	public String removeFollower(HttpServletRequest request) {
		HttpSession session = request.getSession(false); 
		UserInfoModel model=(UserInfoModel)session.getAttribute("userInfoModel");
		int userid=model.getId();
		int registerid=Integer.parseInt(request.getParameter("registerid"));
		boolean b=userSer.isRemoveFollower(registerid, userid); 
		List <UserInfoModel>list=userSer.isgetFollowerList(userid);
		session.setAttribute("followerList", list);
		return "followers";
		
	}
	
	
	
}
