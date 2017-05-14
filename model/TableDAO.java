package model;

import java.sql.SQLException;

import javafx.collections.ObservableList;

public interface TableDAO<E> {
	public ObservableList<E> findAll() throws SQLException, ClassNotFoundException;
	public E findById(int id) throws SQLException, ClassNotFoundException;
	public void insert(E e) throws SQLException, ClassNotFoundException;
	public void update(E e) throws SQLException, ClassNotFoundException;
	public void delete(String condition) throws SQLException, ClassNotFoundException;
}
