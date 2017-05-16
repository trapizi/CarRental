package model;


import model.Member;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;



public class Seek {
	private IntegerProperty seekID;
    private StringProperty username;
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
        
        public SimpleObjectProperty<Date> bookTime(){
            return bookTime;
        }
}