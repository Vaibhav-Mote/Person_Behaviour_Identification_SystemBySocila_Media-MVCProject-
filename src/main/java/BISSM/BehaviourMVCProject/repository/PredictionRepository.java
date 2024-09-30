package BISSM.BehaviourMVCProject.repository;

import java.util.List;

import BISSM.BehaviourMVCProject.model.PredictionModel;




public interface PredictionRepository {
	public int getPostId(int postid,int predictUserID) ;
	public int predictPersonBehavior(String post);
	public List<PredictionModel> predictOverAllPersonBehavior(String[] unlabelledInformation);
	public String[] getAllPostsCommentsLikes(int predictUserID);

}
