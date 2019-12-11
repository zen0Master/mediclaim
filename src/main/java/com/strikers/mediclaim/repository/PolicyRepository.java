package com.strikers.mediclaim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.mediclaim.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	Optional<Policy> findByPolicyNumber(String policyNumber);

}
