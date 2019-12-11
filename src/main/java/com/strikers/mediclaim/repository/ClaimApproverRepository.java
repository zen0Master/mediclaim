package com.strikers.mediclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strikers.mediclaim.entity.ClaimApprover;

@Repository
public interface ClaimApproverRepository extends JpaRepository<ClaimApprover, Integer> {

}
