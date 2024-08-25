package BISSM.BehaviourMVCProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

@RequestMapping("registrationPage")
public String getRegistrationPage() {
	return "registrationPage";
}

}

