package model;


import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;



public class Seek {
	private IntegerProperty seekID;
    private StringProperty memberID;
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
    
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
    
    public StringProperty usernameProperty(){
        return username;
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