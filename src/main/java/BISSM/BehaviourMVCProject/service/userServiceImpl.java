package BISSM.BehaviourMVCProject.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BISSM.BehaviourMVCProject.model.UserInfoModel;
import BISSM.BehaviourMVCProject.repository.userRepositoryImpl;
@Service
public class userServiceImpl  implements userService{
	@Autowired
	userRepositoryImpl userRepo;
	public int isgetregisterid(String username,String pass) {
	return userRepo.isgetregisterid(username, pass);	
	}
	public UserInfoModel isgetAllDatauser(int registerid) {
		return userRepo.isgetAllDatauser(registerid);
	}

public List<UserInfoModel> isgetFollowerList(int registerid ) {
	return userRepo.isgetFollowerList(registerid);
	}
	
public boolean isRemoveFollower(int registerid,int userid) {
	return userRepo.isRemoveFollower(registerid,userid);
}	

public int  getFollowingCount(int registerid) {
	return userRepo.getFollowingCount(registerid);
}

public int  getFollowerCount(int registerid) {
	return userRepo.getFollowerCount(registerid);
}

public List <UserInfoModel>isgetFollowingList(int registerid ) {
	return userRepo.isgetFollowingList(registerid);
}
@Override
public boolean isUnfollowUser(int registerid, int userid) {
	return userRepo.isUnfollowUser(registerid, userid);
}
public boolean UpdateUserData(UserInfoModel model,int rid) {
	return userRepo.UpdateUserData(model, rid);
}

@Override
public List<UserInfoModel> isgetAllUsers() {
	return userRepo.isgetAllUsers();
}


@Override
public List<UserInfoModel> isgetAllUsersInfoByName(String name) {
	return userRepo.isgetAllUsersInfoByName(name);
}

/*	
public boolean isAddUser(UserInfoModel model) {
		return userRepo.isAddUser(model);
	}
    

public List isSearchUser(String username) {
	return userRepo.isSearchUser(username);
}
public int gerUserRegistrationid(String username) {
	return userRepo.gerUserRegistrationid(username);
}

public boolean isFollowUser(int userid,int registerid) {
	return userRepo.isFollowUser(userid, registerid);
}
 

public boolean forgetPassward(String oldPass,String newPass,int rid) {
	return userRepo.forgetPassward(oldPass, newPass, rid);
}

public boolean isDeleteAccount(int registerid) {
	return userRepo.isDeleteAccount(registerid);
}
	
public boolean isCheckDeleteReuest(int registerid) {
	return userRepo.isCheckDeleteReuest(registerid);
}
public boolean cancelRequest(int registerid) {
	return userRepo.cancelRequest(registerid);
}




		
*/	
	
	
}

