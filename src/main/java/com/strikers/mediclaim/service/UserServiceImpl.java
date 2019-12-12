package com.strikers.mediclaim.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.mediclaim.dto.UserDto;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.entity.User;
import com.strikers.mediclaim.repository.PolicyClaimRepository;
import com.strikers.mediclaim.repository.UserRepository;
import com.strikers.mediclaim.util.StringConstant;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PolicyClaimRepository policyClaimRepository;

	/**
	 * This method is used to login for the Approver and senior approver
	 * @param userId
	 * @return List<PolicyClaim>
	 */
	@Override
	public User userLogin(UserDto userDto) {
		User user1 = userRepository.findByUsernameAndPasswordAndStatus(userDto.getUsername(), userDto.getPassword(),
				StringConstant.ACTIVE_STATUS);
		if (user1 != null)
			return user1;
		return null;
	}

	/**
	 * This method is used to filter the mediclaim
	 * based on Policy Limit of Approver
	 * @param userId
	 * @return List<PolicyClaim>
	 */
	@Override
	public List<PolicyClaim> policyClaims(Integer userId) {
		List<PolicyClaim> policyClaimList = null;
		Optional<User> optionaluser = userRepository.findById(userId);
		if (optionaluser.isPresent()) {
			List<PolicyClaim> policyClaims = policyClaimRepository.findAll();
			if (!policyClaims.isEmpty()) {
				if (optionaluser.get().getRole().equalsIgnoreCase(StringConstant.APPROVER_ROLE)) {
					policyClaimList = policyClaims.stream()
							.filter(policyClaim -> !policyClaim.getClaimStatus().equalsIgnoreCase(StringConstant.ASSIGN_STATUS))
							.map(policyClaim -> {
								policyClaim.setRemarks(StringConstant.IN_LIMIT);
								return policyClaim;
							}).collect(Collectors.toList());
				} else if (optionaluser.get().getRole().equalsIgnoreCase(StringConstant.SENIOR_APPROVER_ROLE)) {
					policyClaimList = policyClaims.stream()
							.filter(policyClaim -> policyClaim.getClaimStatus().equalsIgnoreCase(StringConstant.ASSIGN_STATUS))
							.map(policyClaim -> {
								policyClaim.setRemarks(StringConstant.EXCEED_LIMIT);
								return policyClaim;
							}).collect(Collectors.toList());
				} else {
					policyClaimList = Collections.emptyList();
				}
			} else {
				policyClaimList = Collections.emptyList();
			}
		}
		return policyClaimList;
	}

}
