package BISSM.BehaviourMVCProject.controller;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.service.userServiceImpl;




@Controller
public class RegistrationController {

	@Autowired
	userServiceImpl userService;
	
@RequestMapping("registrationPage")
public String getRegistrationPage() {
	return "registrationPage";
}

@RequestMapping("registr")
public String isRegister(HttpServletRequest request,Map map) {
	
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	UserInfoModel model=new UserInfoModel();
	model.setName(name);
	model.setEmail(email);
	model.setUsername(username);
	model.setPass(password);
	boolean b = userService.isAddUser(model);
	if(b) {
		map.put("message", "Register Successfully");
	}
	else {
		map.put("message", "Registration failed");
	}
return "registrationPage";
}

}

