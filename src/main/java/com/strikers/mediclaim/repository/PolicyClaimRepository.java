package com.strikers.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.mediclaim.entity.PolicyClaim;

@Repository
public interface PolicyClaimRepository extends JpaRepository<PolicyClaim, Integer>{

}
