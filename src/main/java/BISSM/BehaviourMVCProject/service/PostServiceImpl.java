package BISSM.BehaviourMVCProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BISSM.BehaviourMVCProject.model.PostLikeCount;
import BISSM.BehaviourMVCProject.repository.PostRepositoryImpl;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepositoryImpl postRepo;

	@Override
	public List<PostLikeCount> ViewAllPosts(int registerIDSearchUser) {
		return postRepo.ViewAllPosts(registerIDSearchUser);
	}

	@Override
	public boolean isdeletePost(int postid) {
		return postRepo.isdeletePost(postid);

	}

	public boolean islikePost(int userid,int postid) {
		return postRepo.islikePost(userid,postid); 
		}

	@Override
	public List<Integer> getAlredyLikedPost(int registerid) {
		return postRepo.getAlredyLikedPost(registerid);
	}

	@Override
	public int isgetLikeCount(int postid) {
		// TODO Auto-generated method stub
		return postRepo.isgetLikeCount(postid);
	}
	
	public boolean isCommenPost(int userid,int postid,String comment) { 
		return postRepo.isCommenPost(userid, postid,comment); }
	

	@Override
	public int isgetCommentCount(int postid) {
		// TODO Auto-generated method stub
		return postRepo.isgetCommentCount(postid);
	}
	
	@Override
	public boolean isAddPost(String post, int registerid, String fileName) { 
		// TODO Auto-generated method stub return
			 return postRepo.isAddPost(post, registerid, fileName); }
	
	@Override 
	public List<List<Object>> getForYou() { 
		return postRepo.getForYou(); 
		}
	
	@Override 
	public List<List<Object>> getFriendsFollowerPosts(int registerid) {
	 return postRepo.getFriendsFollowerPosts(registerid); 
	 }
	
	/*
	 * public List viewTrendingpost() { return postRepo.viewTrendingpost(); }
	 * 
	 * public List viewPersonalpost(int rid) { return
	 * postRepo.viewPersonalpost(rid); }
	 * 
	 * public int getPostId(String post) { return postRepo.getPostId(post); } 
	 * 
	 * 
	 * public List countLikeOfPersonlPost(int userid) { return
	 * postRepo.countLikeOfPersonlPost(userid); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */


}
