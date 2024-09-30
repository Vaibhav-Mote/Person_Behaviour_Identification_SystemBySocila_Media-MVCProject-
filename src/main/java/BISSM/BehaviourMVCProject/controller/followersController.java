package BISSM.BehaviourMVCProject.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.userServiceImpl;

@Controller
public class followersController {
		
	@Autowired
	userServiceImpl userSer;	
	
	

	@RequestMapping("followr")
	public String getFollowerPage(HttpServletRequest request,Model model) {
		int registerid=Integer.parseInt(request.getParameter("registerid"));
		List <UserInfoModel>list=userSer.isgetFollowerList(registerid);
		model.addAttribute("followerList", list);
		model.addAttribute("registerid", registerid);
		return "followers";
	}
	
	
	
	@RequestMapping("/removef")
	public String removeFollower(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false); 
		int userid=(Integer)session.getAttribute("registerid");
		int registerid=Integer.parseInt(request.getParameter("registerid"));
		boolean b=userSer.isRemoveFollower(registerid, userid); 
		List <UserInfoModel>list=userSer.isgetFollowerList(userid);
		model.addAttribute("followerList", list);
		return "followers";
		
	}
	
	
	
}
