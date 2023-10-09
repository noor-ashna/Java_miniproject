package com.miniproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproj.entity.UserAccount;
import com.miniproj.repository.UserAccountRepo;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	UserAccountRepo userAccountRepo;

	@Override
	public String saveOrUpdateUserAcc(UserAccount userAcc) {

		Integer userId = userAcc.getUserId();
		// Upsert (insert or update)
		userAccountRepo.save(userAcc);
		if (userId == null) {
			return "Record is saved";
		} else {
			return "Record is updated";
		}
	}

	@Override
	public List<UserAccount> getAllUserAccounts() {
//		List<UserAccount> accounts = userAccountRepo.findAll();
//		return accounts;
		return userAccountRepo.findAll(); // we can also retrieve all this way
	}

	@Override
	public UserAccount getUserAcc(Integer userId) {
		// we use optional to handle null point exception
		Optional<UserAccount> findById = userAccountRepo.findById(userId);
		if (findById.isPresent()) {
			return findById.get();

		}
		return null;
	}

	@Override
	public boolean deleteUserAcc(Integer userId) {
		boolean existsById = userAccountRepo.existsById(userId);
		if (existsById) {
			userAccountRepo.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserAccStatus(Integer userId, String status) {
		try {
			userAccountRepo.updateUserAccountStatus(userId, status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
