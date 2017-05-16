package model;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;

public class Agreement {
	
	private int agreement_id;
	private Member seeker;
	private Member offerer;
	private String status;
	private String payAmt;
	private String agreeDate;
	private String uniqueNo;
	private String createDay;
	private String initiateBy;
	//private CarOffer offer;
	private double offerReceivable;
	private long toPin;
	private long fromPin;
	private long pUpFrom;
	private long pUpTo;	
	private String day;
	
	private ArrayList<Adjustment> adjustList;
	
	public int getAgreement_id() {
		return agreement_id;
	}

	public void setAgreement_id(int agreement_id) {
		this.agreement_id = agreement_id;
	}

	public Member getSeeker() {
		return seeker;
	}

	public void setSeeker(Member seeker) {
		this.seeker = seeker;
	}

	public Member getOfferer() {
		return offerer;
	}

	public void setOfferer(Member offerer) {
		this.offerer = offerer;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}

	public String getAgreeDate() {
		return agreeDate;
	}

	public void setAgreeDate(String agreeDate) {
		this.agreeDate = agreeDate;
	}

	public String getUniqueNo() {
		return uniqueNo;
	}

	public void setUniqueNo(String uniqueNo) {
		this.uniqueNo = uniqueNo;
	}

	public String getCreateDay() {
		return createDay;
	}

	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}

	public String getInitiateBy() {
		return initiateBy;
	}

	public void setInitiateBy(String initiateBy) {
		this.initiateBy = initiateBy;
	}

	public double getOfferReceivable() {
		return offerReceivable;
	}

	public void setOfferReceivable(double offerReceivable) {
		this.offerReceivable = offerReceivable;
	}

	public long getToPin() {
		return toPin;
	}

	public void setToPin(long toPin) {
		this.toPin = toPin;
	}

	public long getFromPin() {
		return fromPin;
	}

	public void setFromPin(long fromPin) {
		this.fromPin = fromPin;
	}

	public long getpUpFrom() {
		return pUpFrom;
	}

	public void setpUpFrom(long pUpFrom) {
		this.pUpFrom = pUpFrom;
	}

	public long getpUpTo() {
		return pUpTo;
	}

	public void setpUpTo(long pUpTo) {
		this.pUpTo = pUpTo;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public ArrayList<Adjustment> getAdjustList() {
		return adjustList;
	}

	public void setAdjustList(ArrayList<Adjustment> adjustList) {
		this.adjustList = adjustList;
	}
}
