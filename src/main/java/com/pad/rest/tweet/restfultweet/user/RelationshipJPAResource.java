package com.pad.rest.tweet.restfultweet.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class RelationshipJPAResource {

	@Autowired
	private RelationshipRepository relationshipRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/relationships")
	public List<Relationship> relationships() {
		return relationshipRepository.findAll();
	}
	
	@GetMapping("/users/{id}/following")
	public List<User> following(@PathVariable int id) {

		List<Relationship> relationships = relationshipRepository.findAllByFollowingId(id);

		List<Integer> userIds = new ArrayList<Integer>();
		for (Relationship relationship : relationships) {
			userIds.add(relationship.getFollowedId());
		}

		System.out.println("-------------------------------");
		System.out.println("serIds" + userIds);
		
		return userRepository.findAllById(userIds);
	}
	
	@GetMapping("/users/{id}/followers")
	public List<User> followers(@PathVariable int id) {

		List<Relationship> relationships = relationshipRepository.findAllByFollowedId(id);

		// そのfollowedId をリストにしてUser検索
		List<Integer> userIds = new ArrayList<Integer>();
		for (Relationship relationship : relationships) {
			userIds.add(relationship.getFollowingId());
		}

		System.out.println("-------------------------------");
		System.out.println("serIds" + userIds);
		
		// User一覧を返す
		return userRepository.findAllById(userIds);
	}
	
	// followする
	@DeleteMapping("/users/{followingId}/following/{followedId}")
	public void deleteUser(@PathVariable int followingId, @PathVariable int followedId) {
		//userRepository.deleteById(id);
	}

	@PostMapping("/users/{followingId}/following/{followedId}")
	public void createFollowing(@PathVariable int followingId, @PathVariable int followedId) {
		
		Optional<User> followingUser = userRepository.findById(followingId);
		Optional<User> followedUser = userRepository.findById(followedId);

		int count = userRepository.findAll().size();
		
		Relationship relationship = new Relationship(count++, followingUser.get().getId(), followedUser.get().getId());
		relationshipRepository.save(relationship);
		
		//URI location = ServletUriComponentsBuilder
		  //.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

		//return ResponseEntity.created(location).build();
	}
}
