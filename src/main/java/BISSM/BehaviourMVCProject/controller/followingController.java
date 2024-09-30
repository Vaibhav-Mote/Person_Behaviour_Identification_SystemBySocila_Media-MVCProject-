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
	    int registerid = Integer.parseInt(request.getParameter("registerid"));
	    List<UserInfoModel> list = userSer.isgetFollowingList(registerid);
	    ModelAndView mv = new ModelAndView();
	    System.out.println(registerid);
	    mv.addObject("followingInfoList", list);
	    mv.addObject("registerid", registerid); // Store registerid here
	    mv.setViewName("following");
	    return mv;
	}

	@RequestMapping("unfollow")
	public ModelAndView getUnfollow(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    int registerid = (Integer) session.getAttribute("registerid");
	    int userid = Integer.parseInt(request.getParameter("registerid"));
	    boolean unfollowSuccess = userSer.isRemoveFollower(registerid,userid);
	    // After unfollowing, get the updated list of followings for userid
	    List<UserInfoModel> list = userSer.isgetFollowingList(registerid);
	    ModelAndView mv1 = new ModelAndView();
	    mv1.addObject("followingInfoList", list);
	    mv1.addObject("registerid", userid); // Store userid instead of registerid after unfollow
	    mv1.setViewName("following");
	    return mv1;
	}


	
	
}
