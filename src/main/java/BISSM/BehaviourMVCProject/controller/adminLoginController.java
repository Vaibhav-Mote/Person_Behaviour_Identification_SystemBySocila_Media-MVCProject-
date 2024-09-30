package BISSM.BehaviourMVCProject.controller;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class adminLoginController {
	
	@RequestMapping("adminLoginn")
	public String  getadminLoginPage() {
		return "adminLogin";
	
	}
	

	
	@RequestMapping("adlogin")
	public String getadminLoginIndexPage(HttpServletRequest request,Map map) {	
		String name=request.getParameter("username");
		String pass=request.getParameter("password");
		if(name.compareTo("vaibhav")==0 &&(pass.compareTo("1234")==0)) {
			HttpSession session = request.getSession(true);
            session.setAttribute("name", name);  
            return "adminIndex";
		}
		else {
			map.put("msg", "Login Failed");
			return "adminLogin";
		}
		
		
	}

}
