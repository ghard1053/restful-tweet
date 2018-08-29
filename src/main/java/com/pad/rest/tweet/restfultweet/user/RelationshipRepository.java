package com.pad.rest.tweet.restfultweet.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {

	List<Relationship> findByFollowingId(int id);
	List<Relationship> findAllByFollowingId(int id);
	List<Relationship> findAllByFollowedId(int id);

}