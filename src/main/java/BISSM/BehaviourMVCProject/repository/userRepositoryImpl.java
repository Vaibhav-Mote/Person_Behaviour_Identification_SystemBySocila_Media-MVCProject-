package BISSM.BehaviourMVCProject.repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import BISSM.BehaviourMVCProject.model.UserInfoModel;




@Repository
public class userRepositoryImpl implements userRepository {
	
@Autowired
JdbcTemplate template;



//get registerid 
List <Integer>list; 
@Override
public int isgetregisterid(final String username, final String pass) {
	 String sql = "SELECT registerid FROM userregistrationmaster WHERE username = ? AND passward = ?";
     List<Integer> resultList = template.query(sql, new PreparedStatementSetter() {
         @Override
         public void setValues(java.sql.PreparedStatement ps) throws SQLException {
             ps.setString(1, username);
             ps.setString(2, pass);
         }
     }, new RowMapper<Integer>() {
         @Override
         public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
             return rs.getInt("registerid");
         }
     });

     if (resultList.isEmpty()) {
         return -1; 
     } else {
         return resultList.get(0); 
     }
}


//get all data user
@Override
public UserInfoModel isgetAllDatauser(final int registerid) {
		List<UserInfoModel> list= template.query("select *from userregistrationmaster where registerid=?",new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, registerid);
			}
			
		},new RowMapper<UserInfoModel>() {

			@Override
			public UserInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfoModel model = new UserInfoModel();
				model.setId(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setUsername(rs.getString(4));
				model.setPass(rs.getString(5));
				return model;
			}
			
		});
	
	if(list.isEmpty()) {
		return null;
	}
	else {
		return list.get(0);
	}
	
	
}


//remove follower
	@Override
	public boolean isRemoveFollower(int registerid, int userid) {
	    try {
	        Integer followerid = template.queryForObject(
	            "SELECT fwer.followerid FROM userregistrationmaster r " +
	            "INNER JOIN followermaster fwer ON r.registerid=fwer.registerid " +
	            "INNER JOIN followerfollowingjoin ff ON fwer.followerid=ff.followerid " +
	            "WHERE ff.registerid=? AND r.registerid=?", 
	            new Object[]{userid, registerid},
	            new RowMapper<Integer>() {
	                @Override
	                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
	                    return rs.getInt(1);
	                }
	            }
	        );

	        if (followerid != null) {
	            int v = template.update("DELETE FROM followerfollowingjoin WHERE registerid = ? AND followerid = ?", userid, followerid);
	            if (v > 0) {
	                v = template.update("DELETE FROM followermaster WHERE registerid = ? AND followerid = ?", registerid, followerid);
	                return v > 0;
	            } else {
	                return false;
	            }
	        } else {
	            return false;
	        }
	    } catch (EmptyResultDataAccessException e) {
	        return false;
	    }
	}

					
	//get following count 
		public int  getFollowingCount(int registerid) {
			try {
				Integer followingCount=template.queryForObject("select count(ff.followingid) from userregistrationmaster r inner join followingmaster fwing on r.registerid=fwing.registerid inner join followerfollowingjoin ff on fwing.followingid=ff.followingid group by ff.registerid having ff.registerid=?",new Object[] {registerid},new RowMapper<Integer>() {

					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getInt(1);
					}
				});
			   return(followingCount!=null)?followingCount:0;
	
			}catch(Exception ex) {
				System.out.println("Error is:"+ex);
				return 0;
			}
			
			
		}			
			
				

		//is get followerCount
		public int  getFollowerCount(int registerid) {
			try {
				Integer followerCount=template.queryForObject("select count(ff.followerid) from userregistrationmaster r inner join followermaster fwer on r.registerid=fwer.registerid inner join followerfollowingjoin ff on fwer.followerid=ff.followerid group by ff.registerid having ff.registerid=?",new Object[] {registerid},new RowMapper<Integer>() {

					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getInt(1);
					}
					
				});
			    return (followerCount!=null)?followerCount:0;
			
			}catch(Exception ex) {
				return 0;
			}
			
			
		}
		

		//get follower list
			
			public List<UserInfoModel> isgetFollowerList(final int registerid ) {
				String query="select r.registerid ,r.name,r.username from userregistrationmaster r inner join followermaster fwer on r.registerid=fwer.registerid inner join followerfollowingjoin ff on fwer.followerid=ff.followerid where ff.registerid=?";
			List<UserInfoModel> model = template.query(query, new Object[]{registerid}, 
		            new RowMapper<UserInfoModel>() {
						@Override
						public UserInfoModel mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserInfoModel model1=new UserInfoModel();
							model1.setId(rs.getInt(1));
							model1.setName(rs.getString(2));
							model1.setUsername(rs.getString(3));
							return model1;
						}
					});
			return model;
			
			}
		
		//get following list
		public List <UserInfoModel>isgetFollowingList(int registerid ) {
			String query="select r.registerid, r.name,r.username from userregistrationmaster r inner join followingmaster fwing on r.registerid=fwing.registerid inner join followerfollowingjoin ff on fwing.followingid=ff.followingid where ff.registerid=?";
			List<UserInfoModel> modelList = template.query(query, new Object[]{registerid}, 
		            new RowMapper<UserInfoModel>() {
						@Override
						public UserInfoModel mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserInfoModel model1=new UserInfoModel();
							model1.setId(rs.getInt(1));
							model1.setName(rs.getString(2));
							model1.setUsername(rs.getString(3));
							return model1;
						}
					});
			return modelList;
		}
		
		//is unfollow user
		public boolean isUnfollowUser(final int registerid, final int userid) {
		    try {
		        Integer followingId = template.queryForObject(
		            "SELECT fwing.followingid FROM followingmaster fwing " +
		            "INNER JOIN followerfollowingjoin ff ON fwing.followingid = ff.followingid " +
		            "WHERE fwing.registerid = ? AND ff.registerid = ?",
		            new Object[] {registerid, userid},
		            new RowMapper<Integer>() {
		                @Override
		                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		                    return rs.getInt(1);
		                }
		            }
		        );

		        if (followingId != null) {
		            int rowsDeleted = template.update("DELETE FROM followerfollowingjoin WHERE registerid = ? AND followingid = ?", userid, followingId);		
		            if (rowsDeleted > 0) {
		                rowsDeleted = template.update("DELETE FROM followingmaster WHERE registerid = ? AND followingid = ?", registerid, followingId);
		                return rowsDeleted > 0;
		            }
		        }
		        return false;
		    } catch (Exception ex) {
		        return false;
		    }
		}


		



//update user data
	public boolean UpdateUserData(UserInfoModel model, int rid) {
		try {
			int v=template.update("update userregistrationmaster set name=?,email=?,username=? where registerid=?",model.getName(),model.getEmail(),model.getUsername(),rid);
			return (v > 0) ? true : false;
		} catch (Exception ex) {
			return false;
		}
	}
	
	
	//get all users
		@Override
		public List<UserInfoModel> isgetAllUsers() {
			try {
				List<UserInfoModel>list=template.query("select *From userregistrationmaster",new RowMapper<UserInfoModel>() {

					@Override
					public UserInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserInfoModel m=new UserInfoModel();
						m.setId(rs.getInt(1));
						m.setName(rs.getString(2));
						m.setEmail(rs.getString(3));
						m.setUsername(rs.getString(4));
						m.setPass(rs.getString(5));
						return m;
					}
					
				});
				return(!list.isEmpty())?list:null;
				
			}catch(Exception ex) {
				return null;
			}
			
		}
	
		
		

		//is getall users by name
			@Override
			public List<UserInfoModel> isgetAllUsersInfoByName(String name) {
				try {
					List<UserInfoModel>list=template.query("select *From userregistrationmaster where name like '%"+name+"%' ",new RowMapper<UserInfoModel>() {

						@Override
						public UserInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
							UserInfoModel model=new UserInfoModel();
							model.setId(rs.getInt(1));
							model.setName(rs.getString(2));
							model.setEmail(rs.getString(3));
							model.setUsername(rs.getString(4));
							
							return model;
						}
					});
					
					return (!list.isEmpty())?list:null;
			
				}catch(Exception ex) {
					return null;
				}
		
	
	
			}
}
	
/*
 
	//add new registration in table
	public boolean isAddUser(UserInfoModel model) {
		
		
		try {
			stmt = conn.prepareStatement("insert into userregistrationmaster value('0',?,?,?,?)");
			stmt.setString(1, model.getName());
			stmt.setString(2, model.getEmail());
			stmt.setString(3, model.getUsername());
			stmt.setString(4, model.getPass());
			int a = stmt.executeUpdate();
			return (a > 0) ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return false;
		}
	}

	
	//get All data for user
	public UserInfoModel isgetAllDatauser(int registerid) {
		UserInfoModel model = new UserInfoModel();
		try {
			stmt = conn.prepareStatement("select *from userregistrationmaster where registerid=?");
			stmt.setInt(1, registerid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				model.setName(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setUsername(rs.getString(4));
				model.setPass(rs.getString(5));

			}
			return model;
		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return null;
		}
	}

	
	
//search the user
	public List<String> isSearchUser(String username) {
		List<String> list = new ArrayList<String>();
		try {
			stmt = conn.prepareStatement("SELECT username FROM userregistrationmaster WHERE username LIKE ?");
			stmt.setString(1, username + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			return list;

		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return null;
		}

	}
//get user registration id
	public int gerUserRegistrationid(String username) {
		try {
			stmt = conn.prepareStatement("select registerid from userregistrationmaster where username=?");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return -1;
		}
	}
	
	//follow the another user 
	int oldfollowerid=0,oldfollowingid=0,olduserid=0;
	public boolean isFollowUser(int userid, int registerid) {
		try {
			int followerid;
			int followingid;
			//granted key is return the current data 
			stmt = conn.prepareStatement("insert into followingmaster values('0',?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, userid);
			int g = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				followerid = rs.getInt(1);
				
				if (g > 0) {
					stmt = conn.prepareStatement("insert into followermaster values('0',?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
					stmt.setInt(1, registerid);
					int v = stmt.executeUpdate();
					rs = stmt.getGeneratedKeys();
					if (rs.next()) {
						followingid = rs.getInt(1);
						
						if (v > 0) {
							
							stmt=conn.prepareStatement("select *from followerfollowingjoin where registerid=? and followingid=?");
							stmt.setInt(1,registerid);
							stmt.setInt(2, oldfollowerid);
							rs=stmt.executeQuery();
							
							if(rs.next()) {
								int flowingid=rs.getInt(3);
								stmt=conn.prepareStatement("select *from followerfollowingjoin where registerid=? and followerid=?");
								stmt.setInt(1, userid);
								stmt.setInt(2, oldfollowingid);
								rs=stmt.executeQuery();
								
								if(rs.next()) {
									System.out.println("===>>you are alredy following<<=====");
									System.out.println("1:Unfollow");
									System.out.println("Please 1 For Unfollow");
									Scanner sc=new Scanner(System.in);
									int choice=sc.nextInt();
									switch(choice) {
									case 1:
										stmt=conn.prepareStatement("delete from followerfollowingjoin where registerid=? and followingid=?");
										stmt.setInt(1,registerid);
										stmt.setInt(2, oldfollowerid);
						
										if(stmt.executeUpdate()==1) {
											System.out.println("Unfollowed");
										}else {
											System.out.println("Some problem is there.....");
										}
										
										default:
											System.out.println("wrong choice");
									}
									
								
									return false;
									
								}
								
								else {
									stmt = conn.prepareStatement("insert into followerfollowingjoin (registerid ,followingid)values(?,?)");
									stmt.setInt(1, registerid);
									stmt.setInt(2, followerid);
									stmt.executeUpdate();
									stmt = conn.prepareStatement("insert into followerfollowingjoin (registerid ,followerid)values(?,?)");
									stmt.setInt(1,userid );
									stmt.setInt(2, followingid);
									int value = stmt.executeUpdate();
									if (value > 0) {
										oldfollowerid=followerid;
										oldfollowingid=followingid;
										olduserid=userid;
										
										return true;
									} 
									else {
										return false;
									}
									
								}
							}else {
				
								stmt = conn.prepareStatement("insert into followerfollowingjoin (registerid ,followingid)values(?,?)");
								stmt.setInt(1, registerid);
								stmt.setInt(2, followerid);
								stmt.executeUpdate();
								stmt = conn.prepareStatement("insert into followerfollowingjoin (registerid ,followerid)values(?,?)");
								stmt.setInt(1,userid );
								stmt.setInt(2, followingid);
								int value = stmt.executeUpdate();
								if (value > 0) {
									oldfollowerid=followerid;
									oldfollowingid=followingid;
									olduserid=userid;
									
									return true;
								} 
								else {
									return false;
								}
							}
							
							

						} else {
							return false;
						}
					}
					else {
						return false;
					}

				} else {
					return false;
				}
			} 
			else {
				return false;
			}

		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return false;

		}
	}
	
	
	
	
	
	
	
	
	//forget the password
	public boolean forgetPassward(String oldPass,String newPass,int rid) {
		try {
			stmt=conn.prepareStatement("select passward from userregistrationmaster where registerid=?");
			stmt.setInt(1, rid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(oldPass)) {
					System.out.println("Old Passward match");
					try {
						stmt=conn.prepareStatement("update userregistrationmaster set passward=? where registerid=?");
						stmt.setString(1, newPass);
						stmt.setInt(2, rid);
						int v=stmt.executeUpdate();
						if(v!=0)return true;
						else return false;
						
					}catch(Exception ex) {
						System.out.println("Error is"+ex);
						return false;
						
					}
				}
				else {
					System.out.println("Old passward not Match");
					return false;
				}
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error is:"+e);
			return false;
		}
		
		
	}
	
	//Delete Account 
	public boolean isDeleteAccount(int registerid) {
		try {
			stmt=conn.prepareStatement("insert into deleterequest values(?,(curdate()))");
			stmt.setInt(1, registerid);
			return stmt.executeUpdate()>0?true:false;
		}
		catch(Exception ex) {
			return false;
		}
		
	}
	
	//check the delete request to admin panal
	public boolean isCheckDeleteReuest(int registerid) {
		try {
			System.out.println("registerid :"+registerid);
			stmt=conn.prepareStatement("select *from deleterequest where registerid=?");
			stmt.setInt(1, registerid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception ex) {
			return false;
			
		}
	}
	
	
	//cancel delete account request
	public boolean cancelRequest(int registerid) {
		try {
			stmt=conn.prepareStatement("delete from deleterequest where registerid=?");
			stmt.setInt(1, registerid);
			int v=stmt.executeUpdate();
		    return v>0?true:false;
			
		}catch(Exception ex) {
			return  false;
		}
	}

	

		
	}
 
 */


	

			

