package com.gkatzioura.caching.repository;

import org.springframework.data.repository.CrudRepository;

import com.gkatzioura.caching.model.User;

public interface UserRepository extends CrudRepository<User,String>{


}