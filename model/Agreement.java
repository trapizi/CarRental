package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import javafx.beans.property.*;
import util.DBUtil;
import util.InvalidInputException;

public class Agreement {
	
	private IntegerProperty agreement_id;
	private IntegerProperty seeker_id;
	private IntegerProperty offerer_id;
	private StringProperty status;
	private FloatProperty payAmt;
	private SimpleObjectProperty<Date> agreeDate;
	private SimpleObjectProperty<Date> createDay;
	private StringProperty initiateBy;
	private LongProperty toPostcode;
	private LongProperty fromPostcode;
	
	public Agreement() {
		this.agreement_id = new SimpleIntegerProperty();
		this.seeker_id = new SimpleIntegerProperty();
		this.offerer_id = new SimpleIntegerProperty();
		this.status = new SimpleStringProperty(); 
		this.payAmt = new SimpleFloatProperty(); 
		this.agreeDate = new SimpleObjectProperty<Date>();
		this.createDay = new SimpleObjectProperty<Date>();
		this.initiateBy = new SimpleStringProperty();
		this.toPostcode = new SimpleLongProperty();
		this.fromPostcode = new SimpleLongProperty();
	}
	
	@Override
	public String toString() {
		return "id: " + this.getAgreement_id() + " status: " + this.getStatus() + " price: " + 
				this.getPayAmt() + " date: " + this.getAgreeDate() + "date created: " + this.getCreateDay() +
					"initiated by: " + this.getInitiateBy() + "postcode from: " + this.getFromPostcode() +
						"postcode to: " + this.getToPostcode();
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
	
	public float getPayAmt() {
		return payAmt.get();
	}

	public void setPayAmt(float payAmt) {
		this.payAmt.set(payAmt);
	}
	
	public FloatProperty payAmtProperty() {
		return payAmt;
	}

	public Object getAgreeDate() {
		return agreeDate.get();
	}

	public void setAgreeDate(Date agreeDate) {
		this.agreeDate.set(agreeDate);
	}
	
	public void setAgreeDate(String date) {
	    try {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	java.util.Date temp = format.parse(date);
			this.setAgreeDate(new Date(temp.getTime()));
	    } catch (ParseException e) {
	    	// exception should never be triggered as we validated it before
	    }
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
	
	public void setCreateDay(String date) {
	    try {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	java.util.Date temp = format.parse(date);
			this.setCreateDay(new Date(temp.getTime()));
	    } catch (ParseException e) {
	    	// exception should never be triggered as we validated it before
	    }
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
	
	public long getToPostcode() {
		return toPostcode.get();
	}

	public void setToPostcode(long toPostcode) {
		this.toPostcode.set(toPostcode);
	}

	public LongProperty toPostcodeProperty() {
		return toPostcode;
	}

	public Long getFromPostcode() {
		return fromPostcode.get();
	}

	public void setFromPostcode(Long fromPostcode) {
		this.fromPostcode.set(fromPostcode);
	}
	
	public LongProperty fromPostcodeProperty() {
		return fromPostcode;
	}
}
