package com.strikers.mediclaim.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public User userLogin(User user) {
		User user1 = userRepository.findByUserNameAndPasswordAndStatus(user.getUserName(), user.getPassword(),
				StringConstant.ACTIVE_STATUS);
		if (user1 != null)
			return user1;
		return null;
	}

	@Override
	public List<PolicyClaim> policyClaims(Integer userId) {
		List<PolicyClaim> policyClaimList = null;
		Optional<User> optionaluser = userRepository.findById(userId);
		if (optionaluser.isPresent()) {
			List<PolicyClaim> policyClaims = policyClaimRepository.findAll();
			if (policyClaims != null && !policyClaims.isEmpty()) {
				if (optionaluser.get().getRole().equalsIgnoreCase(StringConstant.APPROVER_ROLE)) {

					policyClaimList = policyClaims.stream()
							.filter(policyClaim -> policyClaim.getClaimAmount() < StringConstant.POLICY_LIMIT)
							.map(policyClaim -> {
								policyClaim.setRemarks(StringConstant.IN_LIMIT);
								return policyClaim;
							}).collect(Collectors.toList());
				} else {
					policyClaimList = policyClaims.stream()
							.filter(d -> d.getClaimAmount() >= StringConstant.POLICY_LIMIT).map(policyClaim -> {
								policyClaim.setRemarks(StringConstant.EXCEED_LIMIT);
								return policyClaim;
							}).collect(Collectors.toList());
				}
			} else {
				policyClaimList = Collections.emptyList();
			}
		}
		return policyClaimList;
	}

}
