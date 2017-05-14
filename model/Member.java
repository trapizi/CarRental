package model;
import java.util.ArrayList;
import java.util.Date;

public class Member extends User {
	private int memberID;
	private Date lastMatchDate;
	private Date accountExpiry;
	private float commissionRate;
	private String creditCard;
	private String paymentMedia;
		
	/*
	 * TODO: uncomment when other classes made
	 */
	
	//private ArrayList<CarOffer> offerList;
	//private ArrayList<CarSeek> seekList;
	//private ArrayList<Agreement> agreementList;
	//private CorporateMember corporateMem;

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public Date getLastMatchDate() {
		return lastMatchDate;
	}
	
	public void setLastMatchDate(Date lastMatchDate) {
		this.lastMatchDate = lastMatchDate;
	}
	
	public Date getAccountExpiry() {
		return accountExpiry;
	}
	
	public void setAccountExpiry(Date accountExpiry) {
		this.accountExpiry = accountExpiry;
	}
	
	public float getCommissionRate() {
		return commissionRate;
	}
	
	public void setCommissionRate(float commissionRate) {
		this.commissionRate = commissionRate;
	}
	
	public String getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	public String getPaymentMedia() {
		return paymentMedia;
	}
	
	public void setPaymentMedia(String paymentMedia) {
		this.paymentMedia = paymentMedia;
	}
}
