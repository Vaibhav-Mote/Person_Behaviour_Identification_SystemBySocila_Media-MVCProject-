package BISSM.BehaviourMVCProject.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import BISSM.BehaviourMVCProject.service.PostServiceImpl;



@Controller
public class NewPostController {
	@Autowired
	PostServiceImpl postService;
	
	@RequestMapping("nPost")
	public String isgetNewProfilePage() {
		return "newPost";
	}
	

@RequestMapping("newpost")
public String newPost(@RequestParam("file") MultipartFile file,
                      @RequestParam("post") String post,
                      HttpServletRequest request,
                      RedirectAttributes redirectAttributes) throws IOException {
    HttpSession session = request.getSession(false);
    Integer registerid = (Integer) session.getAttribute("registerid");

    if (registerid == null) {
        // Handle the case where the user is not logged in
        redirectAttributes.addFlashAttribute("message", "You need to log in to create a post.");
        return "redirect:/login";
    }
   
    if (!file.isEmpty()) {
        // Define the upload path
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + "resources" + File.separator + "uploads";
        
        // Ensure the upload directory exists
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directories if they don't exist
        }
        
        // Create a new file in the specified directory
        String fileName = file.getOriginalFilename();
        File destinationFile = new File(uploadPath + File.separator + fileName);
        
        // Save the file
        file.transferTo(destinationFile);
        
        
        
        boolean b = postService.isAddPost(post, registerid, fileName); // Pass fileName to service
        if(b)
        	redirectAttributes.addFlashAttribute("message", "Posted Successfully");
        else 
        	redirectAttributes.addFlashAttribute("message", "Posted not Successfully");
        
    } else {
        // Provide failure feedback
        redirectAttributes.addFlashAttribute("message", "Posted not Successfully");
    }

    // Redirect to the form page
    return "redirect:/newPost";
}

	
}
