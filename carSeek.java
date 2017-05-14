
import model.Member;
import java.util.Date;

public class carSeek {
	private int seekID;
    private Member username;
    private Date bookDay, bookTime;

	public int getSeekID() {
		return seekID;
	}

	public void setSeekID(int seekID) {
		this.seekID = seekID;
	}
    
    public Member getUsername() {
        return username;
    }

    public void setUsername(Member username) {
        this.username = username;
    }

	public Date getBookDay() {
		return bookDay;
	}

	public void setBookDay(Date bookDay) {
		this.bookDay = bookDay;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
}