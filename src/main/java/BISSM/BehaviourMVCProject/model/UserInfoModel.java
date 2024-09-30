package BISSM.BehaviourMVCProject.model;

public class UserInfoModel {
	private String name;
	private String email;
	private String username;
	private String pass;
	private int id;
	private int followerCount;
	private int followingCount;
	
	public int getFollowerCount() {
		return followerCount;
	}


	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}


	public int getFollowingCount() {
		return followingCount;
	}


	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	


}
