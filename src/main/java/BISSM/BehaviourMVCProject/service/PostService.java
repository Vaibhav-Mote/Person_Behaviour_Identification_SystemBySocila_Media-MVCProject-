package BISSM.BehaviourMVCProject.service;

import java.util.List;

import BISSM.BehaviourMVCProject.model.PostLikeCount;

public interface PostService {
	public List<PostLikeCount> ViewAllPosts(int registerIDSearchUser);
	public boolean isdeletePost(int postid);
	public boolean islikePost(int userid,int postid);
	public List<Integer> getAlredyLikedPost(int registerid);
	public int isgetLikeCount(int postid);
	public boolean isCommenPost(int userid ,int postid,String comment) ;
	public int isgetCommentCount(int postid);
	boolean isAddPost(String post, int registerid, String fileName);
	/*

public List viewTrendingpost() ;
public List viewPersonalpost(int rid) ;

public int getPostId(String post);

public boolean isCommenPost(int userid ,int postid,String comment) ;
public List countLikeOfPersonlPost(int userid);


public List<List<Object>> getFriendsFollowerPosts(int registerid);
public List<List<Object>> getForYou() ;
*/
}
