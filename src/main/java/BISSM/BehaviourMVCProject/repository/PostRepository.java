package BISSM.BehaviourMVCProject.repository;

import java.util.List;
import BISSM.BehaviourMVCProject.model.PostLikeCount;
public interface PostRepository {
	public List<PostLikeCount> ViewAllPosts(int registerIDSearchUser);
	public boolean isdeletePost(int postid);
	public boolean islikePost(int userid, int postid);
	public List<Integer>getAlredyLikedPost(int reigisterid);
	public int isgetLikeCount(int postid);
	public boolean isCommenPost(int userid,int postid,String comment);
	public int isgetCommentCount(int postid);
	boolean isAddPost(String post, int registerid, String fileName);
	public List<List<Object>> getForYou();
	public List<List<Object>> getFriendsFollowerPosts(int registerid);
	/*


	public List viewTrendingpost();

	public List viewPersonalpost(int rid);

	public int getPostId(String post);

	public boolean isCommenPost(int userid, int postid, String comment);

	public List countLikeOfPersonlPost(int userid);

	



*/
}
