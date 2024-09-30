package BISSM.BehaviourMVCProject.service;

import java.util.List;

import BISSM.BehaviourMVCProject.model.PredictionModel;



public interface PredictionService {

	public int getPostId(int postid,int predictUserID) ;
	public String predictPersonBehavior(String post);
	public List<PredictionModel> predictOverAllPersonBehavior(String[] unlabelledInformation);
	public String[] getAllPostsCommentsLikes(int predictUserID);
}
