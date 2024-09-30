package BISSM.BehaviourMVCProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import BISSM.BehaviourMVCProject.model.PredictionModel;
import BISSM.BehaviourMVCProject.repository.PredictionRepositoryImpl;


@Controller
public class PredictioinSeriviceImpl implements PredictionService {
	@Autowired
	PredictionRepositoryImpl predRepo;
	
	public int getPostId(int postid,int predictUserID) {
		return predRepo.getPostId(postid, predictUserID);
	}
	
	public String predictPersonBehavior(String post) {
		int result= predRepo.predictPersonBehavior(post);
		if(result==1) {
			return "Openess to Experience";
		}else if(result==2) {
			return "Conscientiousness";
		}
		else if(result==3) {
			return "Extraversion";
		}
		else if(result==4) {
			return "Agreeableness";
		}
		else {
			return "Neuroticism";
		}
			
		}
	public List<PredictionModel> predictOverAllPersonBehavior(String[] unlabelledInformation){
		return predRepo.predictOverAllPersonBehavior(unlabelledInformation);
	}
	public String[] getAllPostsCommentsLikes(int predictUserID) {
		return predRepo.getAllPostsCommentsLikes(predictUserID);
	}
}
