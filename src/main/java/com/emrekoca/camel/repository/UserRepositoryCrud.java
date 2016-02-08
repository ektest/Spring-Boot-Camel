package com.emrekoca.camel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.emrekoca.camel.domain.User;

@Transactional
public interface UserRepositoryCrud extends CrudRepository<User, Integer> {
	
}