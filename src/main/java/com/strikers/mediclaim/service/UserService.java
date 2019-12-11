package com.strikers.mediclaim.service;

import java.util.List;

import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.entity.User;

public interface UserService {

	List<PolicyClaim> userLogin(User user);

}
