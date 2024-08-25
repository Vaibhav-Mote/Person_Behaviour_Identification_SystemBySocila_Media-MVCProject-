package BISSM.BehaviourMVCProject.controller;

import java.util.List;
import java.util.Map;

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
public class ProfileConrtoller {
	
	@Autowired
	PostServiceImpl postService;
	
	@Autowired
	userServiceImpl userService;
	//call to profile page
	@RequestMapping("/profileController")
	public String getProfilePage(HttpServletRequest request,Model model) {
	    int registerid = Integer.parseInt(request.getParameter("registerid"));
	    int followingCount = userService.getFollowingCount(registerid);
	    int followerCount = userService.getFollowerCount(registerid);
	    UserInfoModel umodel = userService.isgetAllDatauser(registerid);
	    List<PostLikeCount> likecountlist = postService.ViewAllPosts(registerid);
	    List<Integer>likedPostList=postService.getAlredyLikedPost(registerid);
		model.addAttribute("likedpost", likedPostList);
	    model.addAttribute("likecountlist", likecountlist);
	    model.addAttribute("FollowerCount", followerCount);
	    model.addAttribute("FollowingCount", followingCount);
	    model.addAttribute("userInfoModel", umodel);
	    return "profile";
	}



//accept request on profile page
@RequestMapping("editP")
public String getEditProfilePage(HttpServletRequest request,Model m) {
	HttpSession session = request.getSession(false);
	int registerid=(Integer)session.getAttribute("registerid");
	UserInfoModel model = userService.isgetAllDatauser(registerid);
	m.addAttribute("userInfoModel", model);
	return "editProfile";
}

@RequestMapping("editProfileController")
public ModelAndView getChangeDataEditProfiePage(HttpServletRequest request,Map map) {
	HttpSession session = request.getSession(false);
	int registerid=(Integer)session.getAttribute("registerid");
    String username = request.getParameter("username");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
        UserInfoModel model = new UserInfoModel();
        model.setName(name);
        model.setEmail(email);
        model.setUsername(username);
        boolean b=userService.UpdateUserData(model, registerid);
        if(b) {
        	map.put("msg","Data Updated Successfully");
        }
        else {
        	map.put("msg","Data Not Updated Successfully");
        }
      model = userService.isgetAllDatauser(registerid);
        ModelAndView mv=new ModelAndView();
    	mv.addObject("userInfoModel",model);
    	mv.setViewName("editProfile");
    	return mv;

}

}
