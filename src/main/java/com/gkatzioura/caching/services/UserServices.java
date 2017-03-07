package com.gkatzioura.caching.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gkatzioura.caching.model.User;
import com.gkatzioura.caching.repository.UserRepository;

@Service
@Profile("simple-cache")
public class UserServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServices.class);
	@Autowired
	private UserRepository userRepository;

	@Cacheable("alluserscache")
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		LOGGER.info("fetching all users");
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Cacheable(cacheNames = "usercache", key = "#root.id")
	public User getUserById(String id) {
		LOGGER.info("fetching user information for user" + id);
		return userRepository.findOne(id);
	}

	@CachePut(cacheNames = "usercache", key = "#root.id")
	public void addUser(User userPayload) {
		LOGGER.info("adding new user");
		userRepository.save(userPayload);
	}

	@CachePut(cacheNames = "usercache", key = "#root.id")
	public void updateUser(String id, User userPayLoad) {
		LOGGER.info("updating user" + id);
		userRepository.save(userPayLoad);
	}

	@CacheEvict(cacheNames = "usercache", key = "#root.id")
	public void removeUser(String id) {
		LOGGER.info("deleting user " + id);
		userRepository.delete(id);
	}
}
