package com.strikers.mediclaim.service;

import java.util.List;

import com.strikers.mediclaim.dto.UserDto;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.entity.User;

public interface UserService {

	User userLogin(UserDto userDto);

	List<PolicyClaim> policyClaims(Integer userId);

}
