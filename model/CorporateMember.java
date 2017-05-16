package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CorporateMember extends Member {
<<<<<<< HEAD
	private Corporate corporation;

	@Override
	public String toString() {
		return "CorporateID: " + this.corporation.getCorporateID() + " | " + super.toString();
	}
	
	public Corporate getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporate corporation) {
		this.corporation = corporation;
=======
	private IntegerProperty corporateID;

	public CorporateMember() {
		super();
		this.corporateID = new SimpleIntegerProperty();
	}
	
	public int getCorporateID() {
		return corporateID.get();
	}

	public void setCorporateID(int corporateID) {
		this.corporateID.set(corporateID);
>>>>>>> fcbeb30bb80e045ee2add7652dab01f6f14882e3
	}	
	
	public IntegerProperty corporateIDProperty() {
		return corporateID;
	}
}
