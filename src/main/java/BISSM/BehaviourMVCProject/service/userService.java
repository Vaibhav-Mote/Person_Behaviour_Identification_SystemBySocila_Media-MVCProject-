package BISSM.BehaviourMVCProject.service;

import java.util.List;

import BISSM.BehaviourMVCProject.model.UserInfoModel;

public interface userService {
public int isgetregisterid(String username,String pass) ;
public UserInfoModel isgetAllDatauser(int registerid);
public List<UserInfoModel> isgetFollowerList(int registerid ) ;
public boolean isRemoveFollower(int registerid,int userid) ;
public int  getFollowingCount(int registerid);
public int  getFollowerCount(int registerid) ;
public List <UserInfoModel>isgetFollowingList(int registerid );
public boolean isUnfollowUser(int registerid,int userid) ;
public boolean UpdateUserData(UserInfoModel model,int rid) ;
public List<UserInfoModel> isgetAllUsers();
public List<UserInfoModel> isgetAllUsersInfoByName(String name);
public boolean isFollowUser(int userid,int registerid) ;
public boolean isAddUser(UserInfoModel model) ;
/*


public List isSearchUser(String username);
public int gerUserRegistrationid(String username) ;

public boolean forgetPassward(String oldPass,String newPass,int rid) ;
public boolean isDeleteAccount(int registerid) ;
public boolean isCheckDeleteReuest(int registerid) ;
public boolean cancelRequest(int registerid);


*/
}
    


