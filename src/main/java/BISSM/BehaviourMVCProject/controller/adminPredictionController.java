package BISSM.BehaviourMVCProject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import BISSM.BehaviourMVCProject.model.PredictionModel;
import BISSM.BehaviourMVCProject.service.PredictioinSeriviceImpl;

@Controller
public class adminPredictionController {

	@Autowired
	PredictioinSeriviceImpl predictSer;

    @RequestMapping("adminPrediction")
    public String getAdminPrediction(HttpServletRequest request, Model model) {
        String post = request.getParameter("post");
        // This should be a single behavior label
        String personBehavior = predictSer.predictPersonBehavior(post);
        model.addAttribute("personBehavior", personBehavior);
        int dataValue = 10; // Example data value for the chart
        model.addAttribute("dataValue", dataValue);

        return "adminPredictionSinglePost"; // Ensure this matches the JSP file name
    }
    
    @RequestMapping("allpostPrediction")
    public String getAdminPredictionFromAllPosts(HttpServletRequest request, Model model) {
        int predictUserID = Integer.parseInt(request.getParameter("registerid"));
        
        String[] unlabelledInformation = predictSer.getAllPostsCommentsLikes(predictUserID);
        
        if (unlabelledInformation != null) {
            List<PredictionModel> personBehavior = predictSer.predictOverAllPersonBehavior(unlabelledInformation);
            int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
            for (PredictionModel pm : personBehavior) {
                switch (pm.getCluster()) {
                    case 1:
                        p1++;
                        break;
                    case 2:
                        p2++;
                        break;
                    case 3:
                        p3++;
                        break;
                    case 4:
                        p4++;
                        break;
                    case 5:
                        p5++;
                        break;
                }
            }
            
            model.addAttribute("p1", p1);
            model.addAttribute("p2", p2);
            model.addAttribute("p3", p3);
            model.addAttribute("p4", p4);
            model.addAttribute("p5", p5);
        } else {
        	int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
        }

        return "adminPredictionAllPost"; // Ensure this matches the JSP file name
    }


}
