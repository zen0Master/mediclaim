package com.strikers.mediclaim.service;

import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strikers.mediclaim.dto.RequestClaimApproverDto;
import com.strikers.mediclaim.dto.ResponseClaimApproverDto;
import com.strikers.mediclaim.entity.ClaimApprover;
import com.strikers.mediclaim.entity.PolicyClaim;
import com.strikers.mediclaim.entity.User;
import com.strikers.mediclaim.repository.ClaimApproverRepository;
import com.strikers.mediclaim.repository.PolicyClaimRespository;
import com.strikers.mediclaim.repository.UserRepository;
import com.strikers.mediclaim.util.StringConstant;

/**
 * @author Vasavi
 * @since 2019-12-11
 * @description ->this class is used for to approve claim operation
 */
@Service
public class ClaimApproverServiceImpl implements ClaimApproverService {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ClaimApproverServiceImpl.class);
	/**
	 * The claimApproverRepository.
	 */
	@Autowired
	private ClaimApproverRepository claimApproverRepository;

	@Autowired
	private PolicyClaimRespository policyClaimRespository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * @description -> this method is used for to approve claim
	 * @param -> requestClaimApproverDto : which contains policyClaimId and message.
	 * @return -> responseClaimApproverDto
	 * @throws -> CommonException
	 */
	public ResponseClaimApproverDto approveClaim(Integer approvedId, RequestClaimApproverDto requestClaimApproverDto) {
		ResponseClaimApproverDto responseClaimApproveDto = new ResponseClaimApproverDto();
		String flag="";
		
		Optional<User> optionalUser = userRepository.findById(approvedId);
		//logger.debug(optionalUser.get().getUserName());
		if (optionalUser.isPresent()) {
			Optional<PolicyClaim> optionalPolicyClaim = policyClaimRespository
					.findById(requestClaimApproverDto.getPolicyClaimId());
			if (optionalPolicyClaim.isPresent()) {
				PolicyClaim policyClaim = optionalPolicyClaim.get();
				if (policyClaim.getClaimStatus().equalsIgnoreCase(StringConstant.PENDING_STATUS)
						&& requestClaimApproverDto.getStatus().equalsIgnoreCase(StringConstant.APPROVE_STATUS)) {
					if(policyClaim.getClaimAmount()>StringConstant.POLICY_LIMIT) {
					logger.debug(policyClaim.getClaimStatus());
						policyClaim.setClaimStatus(StringConstant.ASSIGN_STATUS);
						flag=StringConstant.ASSIGN_STATUS;
					}else {
						policyClaim.setClaimStatus(StringConstant.APPROVE_STATUS);
					}
				}else if(requestClaimApproverDto.getStatus().equalsIgnoreCase(StringConstant.ASSIGN_STATUS)){
					policyClaim.setClaimStatus(StringConstant.ASSIGN_STATUS);
				}
				else {
					policyClaim.setClaimStatus(StringConstant.REJECT_STATUS);
					flag=StringConstant.REJECT_STATUS;
				}
			 	PolicyClaim policyClaim2=policyClaimRespository.save(policyClaim);
				if(policyClaim2!=null) {
					ClaimApprover claimApprover=new ClaimApprover();
					claimApprover.setPolicyClaim(policyClaim2);
					claimApprover.setApproverId(optionalUser.get());
					claimApprover.setStatus(StringConstant.ACTIVE_STATUS);
					ClaimApprover claimApprover2= claimApproverRepository.save(claimApprover);
					if(claimApprover2!=null) {
						if(flag.equalsIgnoreCase(StringConstant.ASSIGN_STATUS))
							responseClaimApproveDto.setMessage(StringConstant.ASSIGN_STATUS);
						else if(flag.equalsIgnoreCase(StringConstant.REJECT_STATUS))
							responseClaimApproveDto.setMessage(StringConstant.REJECT_STATUS);
						else
							responseClaimApproveDto.setMessage(StringConstant.SUCCESS);
					}
				}
			}
		}
		
		return responseClaimApproveDto;
	}

}