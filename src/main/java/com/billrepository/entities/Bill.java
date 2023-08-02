// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.entities;

public class Bill {
	
	private String billID;
	private int billNo;
	private String dealerCode;
	private String distributorCode;
	private double billAmount;
	private String billDate;
	private int dueDays;
	private String billStatus;
	private String billPDFPath;
	private String paidReceiptPath;
	private String description;
	
	public String getBillID() {
		return billID;
	}
	public void setBillID(String billID) {
		this.billID = billID;
	}
	
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	
	public String getDealerCode() {
		return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	
	public int getDueDays() {
		return dueDays;
	}
	public void setDueDays(int dueDays) {
		this.dueDays = dueDays;
	}
	
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	
	public String getBillPDFPath() {
		return billPDFPath;
	}
	public void setBillPDFPath(String billPDFPath) {
		this.billPDFPath = billPDFPath;
	}
	
	public String getPaidReceiptPath() {
		return paidReceiptPath;
	}
	public void setPaidReceiptPath(String paidReceiptPath) {
		this.paidReceiptPath = paidReceiptPath;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
