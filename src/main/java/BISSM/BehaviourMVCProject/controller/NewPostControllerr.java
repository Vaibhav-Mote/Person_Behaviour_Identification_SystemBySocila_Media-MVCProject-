package BISSM.BehaviourMVCProject.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import BISSM.BehaviourMVCProject.service.PostServiceImpl;

@Controller
public class NewPostControllerr {
	

	
	@Autowired
	PostServiceImpl postService;
	
	@RequestMapping("nPost")
	public String isgetNewProfilePage() {
		return "newPost";
	}
	
	

	
	@RequestMapping("l")
    public String newPost(@RequestParam("file") MultipartFile file,
                          @RequestParam("post") String post,
                          HttpServletRequest request,
                          RedirectAttributes redirectAttributes,Map map) throws IOException {
        HttpSession session = request.getSession(false);
        Integer registerid = (Integer) session.getAttribute("registerid");

        if (registerid == null) {
            redirectAttributes.addFlashAttribute("message", "You need to log in to create a post.");
            return "redirect:/login";
        }
       
        if (!file.isEmpty()) {
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + "resources" + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            File destinationFile = new File(uploadPath + File.separator + fileName);
            file.transferTo(destinationFile);
            
            boolean b = postService.isAddPost(post, registerid, fileName);
            if (b)
                map.put("message", "Posted Successfully");
            else 
                map.put("message", "Posted not Successfully");
        } else {
           map.put("message", "Posted not Successfully");
        }

        return "newPost";
    }
	
}
