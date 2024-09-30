package BISSM.BehaviourMVCProject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import BISSM.BehaviourMVCProject.service.PostServiceImpl;

@Controller()
public class HomeController {


	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		return new ModelAndView("home");
	}
	
	
}
