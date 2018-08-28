package com.pad.rest.tweet.restfultweet.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	TweetRepository tweetRepository;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

//		if (!user.isPresent())
//			throw new UserNotFoundException("id-" + id);

		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		Resource<User> resource = new Resource<User>(user.get());

		ControllerLinkBuilder linkTo =
			linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		//HATEOAS

		return resource;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder
		  .fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping("/users/{id}/tweets")
	public List<Tweet> retrieveAllUsers(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);

//		if(!userOptional.isPresent())
//			throw new UserNotFoundException("id-" + id);
		
		return userOptional.get().getTweets();
	}
	
	@PostMapping("/users/{id}/tweets")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Tweet tweet) {

		Optional<User> userOptional = userRepository.findById(id);

//		if(!userOptional.isPresent())
//			throw new UserNotFoundException("id-" + id);
		
		User user = userOptional.get();
		
		tweet.setUser(user);
		
		tweetRepository.save(tweet);

		URI location = ServletUriComponentsBuilder
		  .fromCurrentRequest().path("/{id}").buildAndExpand(tweet.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
}
