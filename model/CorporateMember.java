package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CorporateMember extends Member {
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
	}	
	
	public IntegerProperty corporateIDProperty() {
		return corporateID;
	}
}
