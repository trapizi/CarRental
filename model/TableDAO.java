package model;

import java.sql.SQLException;

import javafx.collections.ObservableList;

public interface TableDAO<E> {
	ObservableList<E> findAll() throws SQLException;
	E findById(int id) throws SQLException;
	void insert(E e) throws SQLException, ClassNotFoundException;
	void update(E e) throws SQLException, ClassNotFoundException;
	void delete(String condition) throws SQLException, ClassNotFoundException;
}
