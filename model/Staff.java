package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Staff extends User {	
	private IntegerProperty staff_id;
	private DoubleProperty salary;
	
	public Staff() {
		super();
		this.staff_id = new SimpleIntegerProperty();
		this.salary = new SimpleDoubleProperty();
	}
	
	public String toString() {
		return "first_name: " + this.getFirstName() + " last_name: " + this.getLastName() + " username: " + this.getUserName();
	}
	
	public int getStaff_id() {
		return staff_id.get();
	}

	public void setStaff_id(int staff_id) {
		this.staff_id.set(staff_id);
	}
	
	public IntegerProperty staff_idProperty() {
		return staff_id;
	}
	
	public double getSalary() {
		return salary.get();
	}
	
	public void setSalary(double salary) {
		this.salary.set(salary);
	}
	
	public DoubleProperty salaryProperty() {
		return salary;
	}
}
