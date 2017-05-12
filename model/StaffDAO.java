package model;
import java.sql.SQLException;

import javafx.collections.ObservableList;

public interface StaffDAO {
    public ObservableList<Staff> findAll() throws SQLException;
    public ObservableList<Staff> findById(String staff_id) throws SQLException;
    public ObservableList<Staff> findByName(String first_name, String last_name) throws SQLException;
    
    public void insertStaff(Staff staff) throws SQLException;
    public void updateStaff(Staff staff) throws SQLException;
    public void deleteStaff(String condition) throws SQLException, ClassNotFoundException;
}
