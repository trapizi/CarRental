import java.util.ArrayList;
import java.util.Calendar;

public class Member {
	private Calendar lastMatchDate;
	private Calendar accountExpiry;
	private float commisionRate;
	private String creditCard;
	private String accountOwnerName;
	private String paymentMedia;
	private String password;
	
	/*
	 * TODO: uncomment when other classes made
	 */
	
	//private ArrayList<CarOffer> offerList;
	//private ArrayList<CarSeek> seekList;
	//private ArrayList<Agreement> agreementList;
	//private CorporateMember corporateMem;
	
	public Calendar getLastMatchDate() {
		return lastMatchDate;
	}
	public void setLastMatchDate(Calendar lastMatchDate) {
		this.lastMatchDate = lastMatchDate;
	}
	public Calendar getAccountExpiry() {
		return accountExpiry;
	}
	public void setAccountExpiry(Calendar accountExpiry) {
		this.accountExpiry = accountExpiry;
	}
	public float getCommisionRate() {
		return commisionRate;
	}
	public void setCommisionRate(float commisionRate) {
		this.commisionRate = commisionRate;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public String getAccountOwnerName() {
		return accountOwnerName;
	}
	public void setAccountOwnerName(String accountOwnerName) {
		this.accountOwnerName = accountOwnerName;
	}
	public String getPaymentMedia() {
		return paymentMedia;
	}
	public void setPaymentMedia(String paymentMedia) {
		this.paymentMedia = paymentMedia;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
