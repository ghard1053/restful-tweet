package com.pad.rest.tweet.restfultweet.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

//@ApiModel(description="All details about the user.")
@Entity
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min=3, message="username should hove atleast 3 characters")
	//@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String username;

	@Size(min=3, message="Name should hove atleast 3 characters")
	//@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String name;
	
	@Size(min=2, message="Name should hove atleast 2 characters")
	//@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String mail;
	
	private String bio;	

	@OneToMany(mappedBy="user")
	private List<Tweet> tweets;

//    @ManyToMany
//    @JoinTable(name = "Relationship",
//            joinColumns = @JoinColumn(name = "followingId", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "followedId", referencedColumnName = "id"))
//    public List following;
	
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "Relationship",
//    	joinColumns = @JoinColumn(name = "followingId", referencedColumnName = "id"),
//    	inverseJoinColumns = @JoinColumn(name = "followedId", referencedColumnName = "id"))
//    private List<User> following;
	
//    @OneToMany
//    @JoinColumn(name="followingId")
//    private List<Relationship> FollowingRelationships;
//	    
//    @OneToMany
//    @JoinColumn(name="followedId")
//    private List<Relationship> FollowerRelationships;

//    @ManyToMany()
//    private List<Relationship> following;
//    
//    @ManyToMany()
//    private List<Relationship> followers;
    
    
	protected User() {
		
	}
	
	public User(Integer id, String username, String name, String mail, String bio) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.mail = mail;
		this.bio = bio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, name=%s, mail=%s, bio=%s]", id, username, name, mail, bio);
	}
	
}
