
import model.Member;
import java.util.Date;
import java.sql.Timestamp;

public class carSeek {
	private int seekID;
    private Member username;
    private Timestamp bookDay, bookTime;

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

	public void setBookDay(Timestamp bookDay) {
		this.bookDay = bookDay;
	}

	public Timestamp getBookTime() {
		return bookTime;
	}

	public void setBookTime(Timestamp bookTime) {
		this.bookTime = bookTime;
	}
}