// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.managers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.billrepository.dao.BillDao;
import com.billrepository.entities.Bill;

public class BillManager {
	private static BillManager instance = new BillManager();
	private static BillDao billDao = new BillDao();
	private BillManager() {
	}
	public static BillManager getInstance() {
		return instance;
	}
	public boolean isBillExists(String billID) {
		boolean billExists = billDao.isBillExists(billID);
		return billExists;
	}
	public String saveBillPDF(String uniqueCode,String billID, InputStream in) {
		try {
			FileOutputStream fout = new FileOutputStream("C:\\JDBC\\BillRepositorySystem\\src\\main\\webapp\\pdf\\"+uniqueCode+"\\"+billID+".pdf");
			byte[] b = new byte[100];
			while(in.read(b) != -1) {
				fout.write(b);
			}
			return "C:\\JDBC\\BillRepositorySystem\\src\\main\\webapp\\pdf\\"+uniqueCode+"\\"+billID+".pdf";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	public Bill createBill(String billID, int billNo, String dealerCode, String distributorCode, double billAmount,
			String billDate, int dueDays, String billStatus, String billPDFPath, String billReceiptPath, String description) {
		Bill bill = new Bill();
		bill.setBillAmount(billAmount);
		bill.setBillDate(billDate);
		bill.setBillNo(billNo);
		bill.setBillPDFPath(billPDFPath);
		bill.setBillStatus(billStatus);
		bill.setDealerCode(dealerCode);
		bill.setDescription(description);
		bill.setDistributorCode(distributorCode);
		bill.setDueDays(dueDays);
		bill.setPaidReceiptPath(billReceiptPath);
		bill.setBillID(billID);
		
		return bill;
	}
	public boolean saveBillToDB(Bill bill) {
		boolean savedToDB = billDao.saveBillToDB(bill);
		return savedToDB;
	}
	public List<Bill> fetchBills(String uniqueCode) {
		List<Bill> billList = billDao.fetchBills(uniqueCode);
		return billList;
	}
	public InputStream getPDFInputStream(String billID) {
		FileInputStream in = null;
		Bill bill = fetchBillThroughID(billID);
		try {
			in = new FileInputStream(bill.getBillPDFPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
	
	public InputStream getReceiptInputStream(String billID) {
		FileInputStream in = null;
		Bill bill = fetchBillThroughID(billID);
		try {
			in = new FileInputStream(bill.getPaidReceiptPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
	public Bill fetchBillThroughID(String billID) {
		Bill bill = billDao.fetchBillThroughID(billID);
		return bill;
	}
	public boolean savePaidReceiptPDF(String billID, InputStream in) {
		Bill bill = fetchBillThroughID(billID);
		try {
			FileOutputStream fout = new FileOutputStream("C:\\JDBC\\BillRepositorySystem\\src\\main\\webapp\\pdf\\"+bill.getDealerCode()+"\\"+billID+"_paymentReceipt.pdf");
			byte[] b = new byte[100];
			while(in.read(b) != -1) {
				fout.write(b);
			}
			String paidReceiptPath = "C:\\JDBC\\BillRepositorySystem\\src\\main\\webapp\\pdf\\"+bill.getDealerCode()+"\\"+billID+"_paymentReceipt.pdf";
			boolean added = billDao.addPaidReceiptPathtoDB(billID,paidReceiptPath);
			if(added) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false; 
	}
	public List<Bill> getUnpaidBillsList(String uniqueCode) {
		List<Bill> billList = billDao.getUnpaidBillsList(uniqueCode);
		return billList;
	}
	public List<Bill> getPaidBillsList(String uniqueCode) {
		List<Bill> billList = billDao.getPaidBillsList(uniqueCode);
		return billList;
	}
	public void sortBills(String sortingCriteria,List<Bill> billList) {
		if(sortingCriteria.equalsIgnoreCase("accordingToDate")) {
			billList.sort(new BillDateComparator());
		}else if(sortingCriteria.equalsIgnoreCase("accordingToLeftDays")) {
			billList.sort(new BillLeftDaysComparator());
		}else if(sortingCriteria.equalsIgnoreCase("accordingToAmount")) {
			billList.sort(new BillAmountComparator());
		}
	}
	public List<Bill> fetchBillsOfParticularDistributor(String uniqueCode, String distributorCriteria) {
		List<Bill> particularDistributorBills = billDao.fetchBillsOfParticularDistributor(uniqueCode,distributorCriteria);
		return particularDistributorBills;
	}
	public List<Bill> fetchDealerBills(String userCode, String dealerCode) {
		List<Bill> dealerBillList = billDao.fetchDealerBills(userCode,dealerCode);
		return dealerBillList;
	}
	
	
}

class BillAmountComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		
		return Double.valueOf(((Bill)o1).getBillAmount()).compareTo(Double.valueOf(((Bill)o2).getBillAmount()));
	}
	
}

class BillLeftDaysComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		long bill1leftDays = 0;
		long bill2leftDays = 0;
		try {
		String bill1Date = ((Bill)o1).getBillDate();
		String bill2Date = ((Bill)o2).getBillDate();
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
    	Date d1bill1 = sdf.parse(bill1Date);
    	Date d2 = sdf.parse(java.time.LocalDate.now().toString());
    	long difference = d2.getTime() - d1bill1.getTime();
    	long days =  difference/(1000*24*3600);
    	bill1leftDays = ((Bill)o1).getDueDays() - days;
    	if(((Bill)o1).getBillStatus().equalsIgnoreCase("paid") && bill1leftDays<0){
    		bill1leftDays = 0;
    	}
		
    	Date d1bill2 = sdf.parse(bill2Date);
    	long difference2 = d2.getTime() - d1bill2.getTime();
    	long days2 =  difference2/(1000*24*3600);
    	bill2leftDays = ((Bill)o2).getDueDays() - days2;
    	if(((Bill)o2).getBillStatus().equalsIgnoreCase("paid") && bill2leftDays<0){
    		bill2leftDays = 0;
    	}
		}catch(Exception e) {
			e.printStackTrace();
		}
    	return Long.valueOf(bill1leftDays).compareTo(Long.valueOf(bill2leftDays));
	}
	
}

class BillDateComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		String[] bill1Date = ((Bill)o1).getBillDate().split("-");
		String[] bill2Date = ((Bill)o2).getBillDate().split("-");
		
		int bill1Year = Integer.parseInt(bill1Date[0]);
		int bill1Month = Integer.parseInt(bill1Date[1]);
		int bill1Day = Integer.parseInt(bill1Date[2]);
		
		int bill2Year = Integer.parseInt(bill2Date[0]);
		int bill2Month = Integer.parseInt(bill2Date[1]);
		int bill2Day = Integer.parseInt(bill2Date[2]);
		
		if(bill1Year == bill2Year) {
			if(bill1Month == bill2Month) {
				return Integer.valueOf(bill1Day).compareTo(Integer.valueOf(bill2Day));
			}else {
				return Integer.valueOf(bill1Month).compareTo(Integer.valueOf(bill2Month));
			}
		}else {
			return Integer.valueOf(bill1Year).compareTo(Integer.valueOf(bill2Year));
		}
		
	}
	
}