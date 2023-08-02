// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.billrepository.entities.Bill;
import com.billrepository.managers.BillManager;
import com.billrepository.utilities.ConnectionProvider;

public class BillDao {

	public boolean isBillExists(String billID) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from bills where billID=?");
			pstmt.setString(1, billID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.isBeforeFirst()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean saveBillToDB(Bill bill) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into bills values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, bill.getBillID());
			pstmt.setInt(2, bill.getBillNo());
			pstmt.setString(3,bill.getDealerCode());
			pstmt.setString(4, bill.getDistributorCode());
			pstmt.setDouble(5, bill.getBillAmount());
			pstmt.setString(6,bill.getBillDate());
			pstmt.setInt(7,bill.getDueDays());
			pstmt.setString(8,bill.getBillStatus());
			pstmt.setString(9,bill.getBillPDFPath());
			pstmt.setString(10,bill.getPaidReceiptPath());
			pstmt.setString(11,	bill.getDescription());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public List<Bill> fetchBills(String uniqueCode) {
		Connection con = ConnectionProvider.getConnection();
		List<Bill> billList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from bills where dealerCode=?");
			pstmt.setString(1, uniqueCode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String billID = rs.getString("billID");
				int billNo = rs.getInt("billNo");
				String dealerCode = rs.getString("dealerCode");
				String distributorCode = rs.getString("distributorCode");
				double billAmount = rs.getDouble("billAmount");
				String billDate = rs.getString("billDate");
				int dueDays = rs.getInt("dueDays");
				String billStatus = rs.getString("billStatus");
				String billPDFPath = rs.getString("billPDFPath");
				String paidReceiptPath = rs.getString("paidReceiptPath");
				String description = rs.getString("description");
				
				Bill bill = BillManager.getInstance().createBill(billID, billNo, dealerCode, distributorCode, billAmount, billDate, dueDays, billStatus, billPDFPath, paidReceiptPath, description);
				billList.add(bill);
			}
			return billList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Bill fetchBillThroughID(String billID) {
		Connection con = ConnectionProvider.getConnection();
		Bill bill = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from bills where billID = ?");
			pstmt.setString(1, billID);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int billNo = rs.getInt("billNo");
				String dealerCode = rs.getString("dealerCode");
				String distributorCode = rs.getString("distributorCode");
				double amount = rs.getDouble("billAmount");
				String billDate = rs.getString("billDate");
				int dueDays = rs.getInt("dueDays");
				String billStatus = rs.getString("billStatus");
				String billPDFPath = rs.getString("billPDFPath");
				String paidReceiptPath = rs.getString("paidReceiptPath");
				String description = rs.getString("description");
				
				bill = BillManager.getInstance().createBill(billID, billNo, dealerCode, distributorCode, amount, billDate, dueDays, billStatus, billPDFPath, paidReceiptPath, description);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bill;
	}

	public boolean addPaidReceiptPathtoDB(String billID,String paidReceiptPath) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("update bills set billStatus = 'paid' , paidReceiptPath = ? where billID = ? ");
			pstmt.setString(1, paidReceiptPath);
			pstmt.setString(2, billID);
			int result = pstmt.executeUpdate();
			if(result > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Bill> getUnpaidBillsList(String uniqueCode) {
		Connection con = ConnectionProvider.getConnection();
		List<Bill> billList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from bills where dealerCode=? AND billStatus='unpaid'");
			pstmt.setString(1, uniqueCode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String billID = rs.getString("billID");
				int billNo = rs.getInt("billNo");
				String dealerCode = rs.getString("dealerCode");
				String distributorCode = rs.getString("distributorCode");
				double amount = rs.getDouble("billAmount");
				String billDate = rs.getString("billDate");
				int dueDays = rs.getInt("dueDays");
				String billStatus = rs.getString("billStatus");
				String billPDFPath = rs.getString("billPDFPath");
				String paidReceiptPath = rs.getString("paidReceiptPath");
				String description = rs.getString("description");
				
				Bill bill = BillManager.getInstance().createBill(billID, billNo, dealerCode, distributorCode, amount, billDate, dueDays, billStatus, billPDFPath, paidReceiptPath, description);
				billList.add(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return billList;
	}

	public List<Bill> getPaidBillsList(String uniqueCode) {
		Connection con = ConnectionProvider.getConnection();
		List<Bill> billList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from bills where dealerCode=? AND billStatus='paid'");
			pstmt.setString(1, uniqueCode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String billID = rs.getString("billID");
				int billNo = rs.getInt("billNo");
				String dealerCode = rs.getString("dealerCode");
				String distributorCode = rs.getString("distributorCode");
				double amount = rs.getDouble("billAmount");
				String billDate = rs.getString("billDate");
				int dueDays = rs.getInt("dueDays");
				String billStatus = rs.getString("billStatus");
				String billPDFPath = rs.getString("billPDFPath");
				String paidReceiptPath = rs.getString("paidReceiptPath");
				String description = rs.getString("description");
				
				Bill bill = BillManager.getInstance().createBill(billID, billNo, dealerCode, distributorCode, amount, billDate, dueDays, billStatus, billPDFPath, paidReceiptPath, description);
				billList.add(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return billList;
	}

	public List<Bill> fetchBillsOfParticularDistributor(String uniqueCode, String distributorCriteria) {
		Connection con = ConnectionProvider.getConnection();
		List<Bill> particularDistributorBills = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from bills where dealerCode=? AND distributorCode=?");
			pstmt.setString(1, uniqueCode);
			pstmt.setString(2, distributorCriteria);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String billID = rs.getString("billID");
				int billNo = rs.getInt("billNo");
				String dealerCode = rs.getString("dealerCode");
				String distributorCode = rs.getString("distributorCode");
				double amount = rs.getDouble("billAmount");
				String billDate = rs.getString("billDate");
				int dueDays = rs.getInt("dueDays");
				String billStatus = rs.getString("billStatus");
				String billPDFPath = rs.getString("billPDFPath");
				String paidReceiptPath = rs.getString("paidReceiptPath");
				String description = rs.getString("description");
				
				Bill bill = BillManager.getInstance().createBill(billID, billNo, dealerCode, distributorCode, amount, billDate, dueDays, billStatus, billPDFPath, paidReceiptPath, description);
				particularDistributorBills.add(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return particularDistributorBills;
	}

	public List<Bill> fetchDealerBills(String userCode, String dealerCode) {
		Connection con = ConnectionProvider.getConnection();
		List<Bill> dealerBillList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from bills where dealerCode=? AND distributorCode=?");
			pstmt.setString(1, dealerCode);
			pstmt.setString(2, userCode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String billID = rs.getString("billID");
				int billNo = rs.getInt("billNo");
				double amount = rs.getDouble("billAmount");
				String billDate = rs.getString("billDate");
				int dueDays = rs.getInt("dueDays");
				String billStatus = rs.getString("billStatus");
				String billPDFPath = rs.getString("billPDFPath");
				String paidReceiptPath = rs.getString("paidReceiptPath");
				String description = rs.getString("description");
				Bill bill = BillManager.getInstance().createBill(billID, billNo, dealerCode, userCode, amount, billDate, dueDays, billStatus, billPDFPath, paidReceiptPath, description);
				dealerBillList.add(bill);
			}
			return dealerBillList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
