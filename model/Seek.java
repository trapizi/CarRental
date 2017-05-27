package model;


import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;



public class Seek {
	private IntegerProperty seekID, memberID;
    private SimpleObjectProperty<Date> bookDay, bookTime;

	public int getSeekID() {
		return seekID.get();
	}

	public void setSeekID(int seekID) {
		this.seekID.set(seekID);
	}
        
        public IntegerProperty seekIDProperty(){
            return seekID;
        }
    
    public int getMemberID() {
        return memberID.get();
    }

    public void setMemberID(int memberID) {
        this.memberID.set(memberID);
    }
    
    public IntegerProperty memberIDProperty(){
        return memberID;
    }

	public Object getBookDay() {
		return bookDay.get();
	}

	public void setBookDay(Date bookDay) {
		this.bookDay.set(bookDay);
	}
        
        public SimpleObjectProperty<Date> bookDayProperty(){
            return bookDay;
        }

	public Object getBookTime() {
		return bookTime.get();
	}

	public void setBookTime(Date bookTime) {
		this.bookTime.set(bookTime);
	}
        
        public SimpleObjectProperty<Date> bookTimeProperty(){
            return bookTime;
        }
}