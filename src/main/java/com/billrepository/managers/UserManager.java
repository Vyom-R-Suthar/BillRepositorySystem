// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.managers;

import java.util.List;

import com.billrepository.dao.UserDao;
import com.billrepository.entities.User;

public class UserManager {
	private static UserManager instance = new UserManager();
	private static UserDao userDao = new UserDao();
	private UserManager() {
	}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	public User createUser(String userName,String firmName,String mobileNo,String address,String password,String uniqueCode) {
		User user = new User();
		
		user.setAddress(address);
		user.setFirmName(firmName);
		user.setMobileNo(mobileNo);
		user.setPassword(password);
		user.setPhotoPath(uniqueCode);
		user.setUniqueCode(uniqueCode);
		user.setUserName(userName);
		
		return user;
	}
	
	public boolean verifyUniquenessOfCode(String code) {
		boolean isUnique = userDao.verifyUniquenessOfCode(code);
		return isUnique;
	}

	public boolean saveUserToDB(User user) {
		boolean inserted = userDao.saveUserToDB(user);
		return inserted;
	}

	public User loginCheck(String uniqueCode, String password) {
		User user = userDao.loginCheck(uniqueCode,password);
		return user;
	}

	public boolean isUserExists(String code) {
		boolean userExists = userDao.isUserExists(code);
		return userExists;
	}

	public User fetchUser(String dealerCode) {
		User user = userDao.fetchUser(dealerCode);
		return user;
	}

	public List<User> fetchDistributorsList(String uniqueCode) {
		List<User> distributorsList = userDao.fetchDistributorsList(uniqueCode);
		return distributorsList;
	}

	public List<User> getDealersList(User user) {
		List<User> dealerList = userDao.fetchDealersList(user);
		return dealerList;
	}
}
