// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.billrepository.entities.User;
import com.billrepository.managers.UserManager;
import com.billrepository.utilities.ConnectionProvider;

public class UserDao {

	public boolean verifyUniquenessOfCode(String code) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from users where uniqueCode=?");
			pstmt.setString(1,code);
			ResultSet rs = pstmt.executeQuery();
			if(!rs.isBeforeFirst()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean saveUserToDB(User user) {
		Connection con = ConnectionProvider.getConnection();
		boolean inserted = false;
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
			pstmt.setString(1, user.getUniqueCode());
			pstmt.setString(2, user.getFirmName());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getMobileNo());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getPhotoPath());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				inserted = true;
				return inserted;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return inserted;
	}

	public User loginCheck(String uniqueCode, String password) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from users where uniqueCode=? and password=?");
			pstmt.setString(1, uniqueCode);
			pstmt.setString(2,password);
			ResultSet rs = pstmt.executeQuery();
			if(!rs.next()) {
				return null;
			}else {
				String firmName = rs.getString("firmName");
				String userName = rs.getString("userName");
				String mobileNo = rs.getString("mobileNo");
				String address = rs.getString("address");
				String photoPath = rs.getString("photoPath");
				
				User user = UserManager.getInstance().createUser(userName, firmName, mobileNo, address, password, uniqueCode);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isUserExists(String code) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from users where uniqueCode=?");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			if(rs.isBeforeFirst()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public User fetchUser(String dealerCode) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from users where uniqueCode=?");
			pstmt.setString(1, dealerCode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String firmName = rs.getString("firmName");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String mobileNo = rs.getString("mobileNo");
				String address = rs.getString("address");
				String photoPath = rs.getString("photoPath");
				
				User user = UserManager.getInstance().createUser(userName, firmName, mobileNo, address, password, dealerCode);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> fetchDistributorsList(String uniqueCode) {
		Connection con = ConnectionProvider.getConnection();
		List<String> distributorCodes = new ArrayList<>();
		List<User> distributorList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select distributorCode from relationship where dealerCode=?");
			pstmt.setString(1, uniqueCode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String distributorCode = rs.getString("distributorCode");
				distributorCodes.add(distributorCode);
			}
			for(String distributorCode : distributorCodes) {
				User user = fetchUser(distributorCode);
				distributorList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return distributorList;
	}

	public List<User> fetchDealersList(User user) {
		List<String> dealerCodes = new ArrayList<>();
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select dealerCode from relationship where distributorCode=?");
			pstmt.setString(1,user.getUniqueCode());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				dealerCodes.add(rs.getString("dealerCode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<User> dealers = new ArrayList<>();
		for(int i=0; i< dealerCodes.size(); i++) {
			dealers.add(UserManager.getInstance().fetchUser(dealerCodes.get(i)));
		}
		
		return dealers;
	}

}
