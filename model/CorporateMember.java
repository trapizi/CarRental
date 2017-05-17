package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Bing Wen (z3463269)
 */
public class CorporateMember extends Member {
	private Corporate corporation;
	private IntegerProperty corporateID;

	@Override
	public String toString() {
		return "CorporateID: " + this.corporation.getCorporateID() + " | " + super.toString();
	}
	
	public CorporateMember() {
		super();
		this.corporateID = new SimpleIntegerProperty();
	}
	
	public Corporate getCorporation() {
		return corporation;
	}
	
	public void setCorporation(Corporate corporation) {
		this.corporation = corporation;
	}
	
	public int getCorporateID() {
		return corporateID.get();
	}

	public void setCorporateID(int corporateID) {
		this.corporateID.set(corporateID);
	}	
	
	public IntegerProperty corporateIDProperty() {
		return corporateID;
	}
}
