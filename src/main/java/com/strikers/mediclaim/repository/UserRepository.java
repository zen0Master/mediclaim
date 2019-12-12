package com.strikers.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.mediclaim.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * @description This method is used to find user based on username and status
	 * @param username
	 * @param password
	 * @param status
	 * @return
	 */
	User findByUsernameAndPasswordAndStatus(String username, String password, String status);

}
