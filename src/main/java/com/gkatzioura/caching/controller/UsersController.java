package com.gkatzioura.caching.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gkatzioura.caching.model.User;
import com.gkatzioura.caching.services.UserServices;

@RestController
public class UsersController {


	@Autowired
	private UserServices userServices;

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public List<User> fetchUsers() {
		return userServices.getUsers();

	}

	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable String id) {

		return userServices.getUserById(id);
	}

	@RequestMapping(path = "/users", method = RequestMethod.POST)
	public String addUser(@RequestBody User userPayLoad) {
		userServices.addUser(userPayLoad);
		return "user added to DB";
	}

	@RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable String id, @RequestBody User userPayLoad) {
		userServices.updateUser(id, userPayLoad);
		return "user updated";
	}

	@RequestMapping(path = "/users/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable String id) {
		userServices.removeUser(id);
		return "User Deleted";
	}

}