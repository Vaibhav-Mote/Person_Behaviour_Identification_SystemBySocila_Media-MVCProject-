package BISSM.BehaviourMVCProject.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.userServiceImpl;

@Controller
public class followingController {

	
	
	@Autowired
	userServiceImpl userSer;
	
	@RequestMapping("following")
	public ModelAndView getFollowings(HttpServletRequest request) {
		HttpSession session =request.getSession(false);
		int registerid=(Integer)session.getAttribute("registerid");
		List <UserInfoModel>list=userSer.isgetFollowingList(registerid);
		ModelAndView mv=new ModelAndView();
         mv.addObject("followingInfoList", list);
         mv.setViewName("following");
         return mv;
		
	}
	
	@RequestMapping("unfollow")
	public ModelAndView getUnfollow(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    int userid = (Integer) session.getAttribute("registerid"); 
	    int registerid = Integer.parseInt(request.getParameter("registerid")); 
	    boolean unfollowSuccess = userSer.isUnfollowUser(registerid, userid);
	    List<UserInfoModel> list = userSer.isgetFollowingList(userid);
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("followingInfoList", list);
	    mv.setViewName("following");
	    return mv;
	}

	
	
}
