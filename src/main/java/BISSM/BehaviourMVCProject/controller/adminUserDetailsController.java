package BISSM.BehaviourMVCProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import BISSM.BehaviourMVCProject.model.PostLikeCount;
import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.PostServiceImpl;
import BISSM.BehaviourMVCProject.service.userServiceImpl;

@Controller
public class adminUserDetailsController {

	@Autowired
	userServiceImpl userService;
	
	@Autowired
	PostServiceImpl postService;
	
	
	@RequestMapping("adminUserDetails")
	public ModelAndView getAdminIndexLoginPage() {
		ModelAndView model=new ModelAndView();
		List<UserInfoModel> userAllInfo = userService.isgetAllUsers();
		List <List<PostLikeCount> >list=new ArrayList<List<PostLikeCount>>();
		for(UserInfoModel m:userAllInfo) {
			int followingCount = userService.getFollowingCount(m.getId());
			  int followerCount = userService.getFollowerCount(m.getId());
			  m.setFollowerCount(followerCount);
			  m.setFollowingCount(followingCount);
			  List<PostLikeCount> likecountlist = postService.ViewAllPosts(m.getId());
			  list.add(likecountlist);  
		}
		model.addObject("userAllInfo", userAllInfo);
		model.addObject("list", list);
		model.setViewName("adminUserDetails");
		
		
		return model;
	}
	
}
