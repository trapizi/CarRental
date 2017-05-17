package model;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.property.*;

public class Agreement {
	
	private IntegerProperty agreement_id;
	private IntegerProperty seeker_id;
	private IntegerProperty offerer_id;
	private StringProperty status;
	private StringProperty payAmt;
	private SimpleObjectProperty<Date> agreeDate;
	private SimpleObjectProperty<Date> createDay;
	private LongProperty postcode;
	private StringProperty initiateBy;
	
	
	private long toPin;
	private long fromPin;
	private long pUpFrom;
	private long pUpTo;	
	//private String day;
	
	public Agreement() {
		this.agreement_id = new SimpleIntegerProperty();
		this.seeker_id = new SimpleIntegerProperty();
		this.offerer_id = new SimpleIntegerProperty();
		this.status = new SimpleStringProperty(); 
		this.payAmt = new SimpleStringProperty(); 
		this.agreeDate = new SimpleObjectProperty<Date>();
		this.createDay = new SimpleObjectProperty<Date>();
		this.postcode = new SimpleLongProperty();
		this.initiateBy = new SimpleStringProperty();
	}
	
	@Override
	public String toString() {
		return this.status.get();
	}
	
	public int getAgreement_id() {
		return agreement_id.get();
	}

	public void setAgreement_id(int agreement_id) {
		this.agreement_id.set(agreement_id);;
	}
	
	public IntegerProperty agreementIDProperty() {
		return agreement_id;
	}

	public int getSeeker() {
		return seeker_id.get();
	}

	public void setSeeker(int seeker_id) {
		this.seeker_id.set(seeker_id);;
	}
	
	public IntegerProperty seekerIDProperty() {
		return seeker_id;
	}

	public int getOfferer() {
		return offerer_id.get();
	}

	public void setOfferer(int offerer_id) {
		this.offerer_id.set(offerer_id);
	}
	
	public IntegerProperty offererIDProperty() {
		return offerer_id;
	}
	
	public String getStatus() {
		return status.get();
	}

	public void setStatus(String status) {
		this.status.set(status);;
	}

	public StringProperty statusProperty() {
		return status;
	}
	
	public String getPayAmt() {
		return payAmt.get();
	}

	public void setPayAmt(String payAmt) {
		this.payAmt.set(payAmt);
	}
	
	public StringProperty payAmtProperty() {
		return payAmt;
	}

	public Object getAgreeDate() {
		return agreeDate.get();
	}

	public void setAgreeDate(Date agreeDate) {
		this.agreeDate.set(agreeDate);
	}
	
	public SimpleObjectProperty<Date> agreeDateProperty() {
		return agreeDate;
	}

	
	public Object getCreateDay() {
		return createDay.get();
	}

	public void setCreateDay(Date createDay) {
		this.createDay.set(createDay);
	}
	
	public SimpleObjectProperty<Date> createDayProperty() {
		return createDay;
	}

	public String getInitiateBy() {
		return initiateBy.get();
	}

	public void setInitiateBy(String initiateBy) {
		this.initiateBy.set(initiateBy);
	}
	
	public StringProperty initiateByProperty() {
		return initiateBy;
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
	
	public Long getPostcode() {
		return postcode.get();
	}

	public void setPostcode(Long postcode) {
		this.postcode.set(postcode);
	}
	
	public LongProperty postcodeProperty() {
		return postcode;
	}

/*	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
*/

}
