package model;

public class CorporateMember extends Member {
	private Corporate corporation;

	public Corporate getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporate corporation) {
		this.corporation = corporation;
	}
}
