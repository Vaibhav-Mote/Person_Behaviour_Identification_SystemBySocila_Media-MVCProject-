package BISSM.BehaviourMVCProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class adminIndexController {

	
	@RequestMapping("adminIndex")
	public String getAdminIndexLoginPage() {
		return "adminIndex";
	}
	
}
