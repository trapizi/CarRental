package model;

public class CorporateMember extends Member {
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
	}	
}
