package com.strikers.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.mediclaim.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
