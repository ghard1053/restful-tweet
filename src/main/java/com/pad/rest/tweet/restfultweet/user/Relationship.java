package com.pad.rest.tweet.restfultweet.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Relationship {

	@Id
	@GeneratedValue
	private Integer id;

	// フォローする側
	private Integer followingId;

	// フォローされる側
	private Integer followedId;
	
	protected Relationship() {
		
	}
	
	public Relationship(Integer id, Integer followingId, Integer followedId) {
		super();
		this.id = id;
		this.followingId = followingId;
		this.followedId = followedId;
	}
	
//	insert into relationship(id, following_id, followed_id) values(101, 10001, 10002);
//	insert into relationship(id, following_id, followed_id) values(102, 10001, 10003);
//	insert into relationship(id, following_id, followed_id) values(103, 10002, 10001);
//	insert into relationship(id, following_id, followed_id) values(104, 10003, 10002);

	
//  @ManyToOne(targetEntity=User.class)
//  @JoinColumn(name="followingId", referencedColumnName = "id")
//  private User FollowingUser;
//  
//  @ManyToOne(targetEntity=User.class)
//  @JoinColumn(name="followedId", referencedColumnName = "id")
//  private User FollowedUser;

//  @ManyToMany(mappedBy = "following")
//  private List<User> following;
//  
//  @ManyToMany(mappedBy = "followed")
//  private List<User> followers;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFollowingId() {
		return followingId;
	}

	public void setFollowingId(Integer followingId) {
		this.followingId = followingId;
	}

	public Integer getFollowedId() {
		return followedId;
	}

	public void setFollowedId(Integer followedId) {
		this.followedId = followedId;
	}
	
}
