package com.strikers.mediclaim.service;

import java.util.List;

import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.entity.User;

public interface UserService {

	User userLogin(User user);

	List<PolicyClaim> policyClaims(Integer userId);

}
