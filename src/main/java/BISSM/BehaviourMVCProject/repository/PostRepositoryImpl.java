package BISSM.BehaviourMVCProject.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.Statement;

import BISSM.BehaviourMVCProject.model.PostLikeCount;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<PostLikeCount> ViewAllPosts(int registerIDSearchUser) {
        List<PostLikeCount> list = new LinkedList<PostLikeCount>();

        // Fetch post IDs for the user
        String postIdQuery = "SELECT pm.postid " +
                             "FROM postmaster pm " +
                             "INNER JOIN postregistrationjoin prj ON pm.postid = prj.postid " +
                             "INNER JOIN userregistrationmaster rm ON rm.registerid = prj.registerid " +
                             "WHERE rm.registerid = ? " +
                             "ORDER BY pm.date DESC";

        List<Integer> postIds = template.query(postIdQuery, new Object[]{registerIDSearchUser}, 
                                                   new RowMapper<Integer>() {
													@Override
													public Integer mapRow(ResultSet rs, int rowNum)
															throws SQLException {
														return rs.getInt("postid");
													}
												});

        // Process each post
        int postCount = 0;
        for (Integer postId : postIds) {
            final PostLikeCount pmodel = new PostLikeCount();
            pmodel.setPostid(postId);

            // Fetch post details
            String postDetailsQuery = "SELECT postname, filename,postid FROM postmaster WHERE postid = ?";
             Void post = template.queryForObject(postDetailsQuery, new Object[]{postId}, new RowMapper<Void>() {

				@Override
				public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
					PostLikeCount post=new PostLikeCount();
					pmodel.setPost(rs.getString(1));
					pmodel.setFilename(rs.getString(2));
					pmodel.setPostid(rs.getInt(3));
					return null;
			        
				}
            	
            });

            // Fetch like count
            String likeCountQuery = "SELECT COUNT(lm.likeid) " +
                                    "FROM likemaster lm " +
                                    "INNER JOIN likepostjoin lpj ON lpj.likeid = lm.likeid " +
                                    "INNER JOIN postmaster pm ON pm.postid = lpj.postid " +
                                    "WHERE pm.postid = ?";
            template.queryForObject(likeCountQuery, new Object[]{postId}, new RowMapper<Void>() {

				@Override
				public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
					pmodel.setCount(rs.getInt(1));
					return null;
				}
            	
            });

            // Fetch comment count
            String commentCountQuery = "SELECT COUNT(cm.commentid) " +
                                       "FROM commentmaster cm " +
                                       "INNER JOIN postcommentjoin pcj ON pcj.commentid = cm.commentid " +
                                       "INNER JOIN postmaster pm ON pm.postid = pcj.postid " +
                                       "WHERE pm.postid = ?";
            template.queryForObject(commentCountQuery, new Object[]{postId}, new RowMapper<Void>() {

				@Override
				public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
					pmodel.setCommentCount( rs.getInt(1));
					return null;

				}
            	
            });
            
            pmodel.setPostCount(++postCount);
            list.add(pmodel);
        }

        return list.isEmpty() ? null : list;
    }

    
    //is deletepost
    @Override
	public boolean isdeletePost(int postid) {
		try {
			int v=template.update("delete from postregistrationjoin where postid=?",postid);
			if (v>0) {
				v=template.update(" delete from postmaster where postid=?",postid);
				return (v>0)?true:false;
			} else {
				return false;
			}

		} catch (Exception ex) {
			return false;
		}

	}

    
 // like the post
 	public boolean islikePost(int userid, int postid) {
 		try {
 			Integer likeid=null;
 			// Check if the user has already liked the post
 			try {
 			 likeid=template.queryForObject("SELECT likeid from likepostjoin WHERE likeid IN (SELECT likeid FROM likemaster WHERE registerid=?) AND postid=?",new Object[] {userid,postid},new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
             return rs.getInt(1)!=0?rs.getInt(1):null;
				}
 			});
 			 
 			}catch(Exception ex) {
 				likeid=null;
 			}
 			
 			if (likeid!=null) {
 				
 				int v=template.update("delete from likepostjoin where likeid=? and postid=?",likeid,postid);
 				if(v>0) {
 					v=template.update("delete from likemaster where likeid=?",likeid);
 				}
 				
 				return false;

 			} else {
                KeyHolder keyHolder = new GeneratedKeyHolder();
                template.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO likemaster (registerid) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, userid);
                        return ps;
                    }
                }, keyHolder);
                // Check if the like insertion was successful
                if (keyHolder.getKey() != null) {
                    int generatedLikeId = keyHolder.getKey().intValue();
                    // Insert into likepostjoin
                    String insertJoinQuery = "INSERT INTO likepostjoin (likeid, postid) VALUES (?, ?)";
                    int updateCount = template.update(insertJoinQuery, generatedLikeId, postid);
                    return updateCount > 0;
                } else {
                    return false;
                }
 			}

 		} catch (Exception ex) {
 			return false;
 		}

 	}

 	//fetch alredy liked all posts
 	
 	public List<Integer> getAlredyLikedPost(int registerid){
 		List<Integer>list=template.query("SELECT postid FROM likepostjoin WHERE likeid IN (SELECT likeid FROM likemaster WHERE registerid=?)",new Object[] {registerid},new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
 		});
 		return list;
 	}

	//fetch like count of perticular post
	public int isgetLikeCount(int postid) {
		try {
			Integer likecount=template.queryForObject("SELECT COUNT(lm.likeid) FROM likemaster lm INNER JOIN likepostjoin lpj ON lpj.likeid = lm.likeid INNER JOIN postmaster pm ON pm.postid = lpj.postid WHERE pm.postid =?", new Object[] {postid},new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
				
			});
			return (likecount>0)?likecount:0;
			
			
		}catch(Exception ex) {
			return 0;
		}
	}
    
	//fetch comment count of perticular post
	public int isgetCommentCount(int postid) {
		try {
			Integer commentCount=template.queryForObject("SELECT COUNT(cm.commentid) FROM commentmaster cm INNER JOIN postcommentjoin pcj ON pcj.commentid = cm.commentid INNER JOIN postmaster pm ON pm.postid = pcj.postid WHERE pm.postid =?", new Object[] {postid},new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
				
			});
			return (commentCount>0)?commentCount:0;
			
			
		}catch(Exception ex) {
			return 0;
		}
	}
	
	// comment the post
		public boolean isCommenPost(int userid, int postid, String comment) {

			try {
				
				KeyHolder keyHolder = new GeneratedKeyHolder();
                int v=template.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO commentmaster values('0',?,(curdate()),?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        ps.setString(1,comment);
                        ps.setInt(2, userid);
                        return ps;
                    }
                }, keyHolder);
					
				if (v > 0) {
					
					 if (keyHolder.getKey() != null) {
		                    int commentid = keyHolder.getKey().intValue();
					 
					 v=template.update("insert into postcommentjoin values(?,?)",postid,commentid);
					 return v > 0 ? true : false;
					 }
					 else {
						return false;
					}
				} else {
					return false;
				}

			} catch (Exception ex) {
				return false;
			}

		}

		
		// Add the new post
		 public boolean isAddPost(String post, int registerid, String fileName) {
			try {
				KeyHolder keyHolder = new GeneratedKeyHolder();
				int v=template.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement("insert into postmaster values('0',?,(curdate()),(curtime()),?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        ps.setString(1,post);
                        ps.setString(2, fileName);
                        return ps;
                    }
                }, keyHolder);
				
						if (v > 0) {
							
							 if (keyHolder.getKey() != null) {
				                    int postid = keyHolder.getKey().intValue();
							 
							 v=template.update("insert into postregistrationjoin values(?,?)",postid,registerid);
							 return v > 0 ? true : false;
							 }
							 else {
								return false;
							}
						} else {
							return false;
						}

					} catch (Exception ex) {
						return false;
					}

		}
		
	
	/*
	 

	// view Trending post
	public List viewTrendingpost() {
		List list = new ArrayList();
		try {
			stmt = conn.prepareStatement("select postname from postmaster order by date desc");
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

	// view personal post
	public List viewPersonalpost(int rid) {
		List list = new ArrayList();
		try {
			stmt = conn.prepareStatement(
					"select p.postname,p.date,p.time  from postmaster p inner join postregistrationjoin prj on p.postid=prj.postid where prj.registerid=?");
			stmt.setInt(1, rid);
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

	// get perticular post id
	public int getPostId(String post) {
		try {
			stmt = conn.prepareStatement("select postid from postmaster where postname=?");
			stmt.setString(1, post);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}

		} catch (Exception ex) {
			return 0;
		}
	}

	
	
	// count like of personal post (count like of post according to user id)
	public List countLikeOfPersonlPost(int userid) {
		try {
			stmt = conn.prepareStatement(
					"SELECT p.postid, p.postname, GROUP_CONCAT(cm.comment SEPARATOR ', ') AS comments,count(lpj.likeid)as likeCount,count(pcj.commentid)as commentCount FROM postmaster p INNER JOIN postcommentjoin pcj ON p.postid = pcj.postid INNER JOIN commentmaster cm ON pcj.commentid = cm.commentid inner join likepostjoin lpj on p.postid=lpj.postid inner join likemaster lm on lpj.likeid=lm.likeid WHERE cm.registerid =? GROUP BY p.postid");
			stmt.setInt(1, userid);
			rs = stmt.executeQuery();
			List<PostLikeCount> likecountlist = new ArrayList();

			while (rs.next()) {
				PostLikeCount plc = new PostLikeCount();
				plc.setPostid(rs.getInt(1));
				plc.setPost(rs.getString(2));
				plc.setComment(rs.getString(3));
				plc.setCount(rs.getInt(4));
				plc.setCommentCount(rs.getInt(5));
				likecountlist.add(plc);
			}
			return likecountlist.size() > 0 ? likecountlist : null;
		} catch (Exception ex) {
			return null;
		}

	}

	


//get all friends follower posts
	@Override
	public List<List<Object>> getFriendsFollowerPosts(int registerid) {

	    List<List<Object>> list = new ArrayList<>();
	    
	    try {
	        stmt = conn.prepareStatement(
	                " SELECT r.username, r.name, p.postid, p.postname, p.date, p.time,p.filename FROM postmaster p INNER JOIN postregistrationjoin prj ON p.postid = prj.postid INNER JOIN userregistrationmaster r ON r.registerid = prj.registerid WHERE prj.registerid in (select r.registerid from userregistrationmaster r inner join followingmaster fwer on r.registerid = fwer.registerid inner join followerfollowingjoin ff on fwer.followingid = ff.followingid where ff.registerid =?)");
	        stmt.setInt(1, registerid);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            UserInfoModel umodel = new UserInfoModel();
	            PostLikeCount pmodel = new PostLikeCount();
	            umodel.setUsername(rs.getString(1));
	            umodel.setName(rs.getString(2));
	            pmodel.setPostid(rs.getInt(3));
	            pmodel.setPost(rs.getString(4));
	            pmodel.setFilename(rs.getString(7));

	            // Fetch like count of post
	            PreparedStatement likeStmt = conn.prepareStatement(
	                    "select count(lm.likeid) from likemaster lm " +
	                    "inner join likepostjoin lpj on lpj.likeid = lm.likeid " +
	                    "inner join postmaster pm on pm.postid = lpj.postid " +
	                    "where pm.postid = ?");
	            likeStmt.setInt(1, rs.getInt(3));
	            ResultSet likeRs = likeStmt.executeQuery();
	            if (likeRs.next()) {
	                pmodel.setCount(likeRs.getInt(1));
	            }
	            likeRs.close();
	            likeStmt.close();

	            // Fetch comment count of post
	            PreparedStatement commentStmt = conn.prepareStatement(
	                    "select count(cm.commentid) from commentmaster cm " +
	                    "inner join postcommentjoin pcj on pcj.commentid = cm.commentid " +
	                    "inner join postmaster pm on pm.postid = pcj.postid " +
	                    "where pm.postid = ?");
	            commentStmt.setInt(1, rs.getInt(3));
	            ResultSet commentRs = commentStmt.executeQuery();
	            if (commentRs.next()) {
	                pmodel.setCommentCount(commentRs.getInt(1));
	            }
	            commentRs.close();
	            commentStmt.close();

	            List<Object> upmodellist = new ArrayList<>();
	            upmodellist.add(pmodel);
	            upmodellist.add(umodel);
	            list.add(upmodellist);
	        }
	        rs.close();
	        stmt.close();
	    } catch (Exception ex) {
	        ex.printStackTrace(); // Consider logging the exception properly
	        return null;
	    }

	    return list;
	}

	public List<List<Object>> getForYou() {

	    List<List<Object>> list = new ArrayList<>();
	    
	    try {
	        stmt = conn.prepareStatement(
	                "select r.username, r.name, p.postid, p.postname, p.date, p.time,p.filename from postmaster p inner join postregistrationjoin prj on p.postid = prj.postid inner join userregistrationmaster r on r.registerid = prj.registerid");
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            UserInfoModel umodel = new UserInfoModel();
	            PostLikeCount pmodel = new PostLikeCount();
	            umodel.setUsername(rs.getString(1));
	            umodel.setName(rs.getString(2));
	            pmodel.setPostid(rs.getInt(3));
	            pmodel.setPost(rs.getString(4));
	            pmodel.setFilename(rs.getString(7));

	            // Fetch like count of post
	            PreparedStatement likeStmt = conn.prepareStatement(
	                    "select count(lm.likeid) from likemaster lm " +
	                    "inner join likepostjoin lpj on lpj.likeid = lm.likeid " +
	                    "inner join postmaster pm on pm.postid = lpj.postid " +
	                    "where pm.postid = ?");
	            likeStmt.setInt(1, rs.getInt(3));
	            ResultSet likeRs = likeStmt.executeQuery();
	            if (likeRs.next()) {
	                pmodel.setCount(likeRs.getInt(1));
	            }
	            likeRs.close();
	            likeStmt.close();

	            // Fetch comment count of post
	            PreparedStatement commentStmt = conn.prepareStatement(
	                    "select count(cm.commentid) from commentmaster cm " +
	                    "inner join postcommentjoin pcj on pcj.commentid = cm.commentid " +
	                    "inner join postmaster pm on pm.postid = pcj.postid " +
	                    "where pm.postid = ?");
	            commentStmt.setInt(1, rs.getInt(3));
	            ResultSet commentRs = commentStmt.executeQuery();
	            if (commentRs.next()) {
	                pmodel.setCommentCount(commentRs.getInt(1));
	            }
	            commentRs.close();
	            commentStmt.close();

	            List<Object> upmodellist = new ArrayList<>();
	            upmodellist.add(pmodel);
	            upmodellist.add(umodel);
	            list.add(upmodellist);
	        }
	        rs.close();
	        stmt.close();
	    } catch (Exception ex) {
	        return null;
	    }

	    return list;
	}

*/
}
