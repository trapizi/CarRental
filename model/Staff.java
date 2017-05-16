package model;

public class Staff extends User {
	private int staff_id;
	private double salary;

	public String toString() {
		return "first_name: " + this.getFirstName() + " last_name: " + this.getLastName() + " username: " + this.getUserName();
	}
	
	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
}
