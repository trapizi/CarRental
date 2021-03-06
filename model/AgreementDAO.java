package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.*;

/**
 * @author Sarina Lee (z5020069)
 */

public class AgreementDAO implements TableDAO<Agreement> {

	/**
	 * Returns a list of all agreements in the agreement table
	 */
	public ObservableList<Agreement> findAll() throws SQLException {    			
		try {
			/* Query database*/
			ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "AGREEMENT", ""));

			ObservableList<Agreement> list = this.getAgreementList(rs);

			return list;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns a list of all agreements in the agreement table
	 */
	public ObservableList<Agreement> findMemberAgreements(int mID) throws SQLException {    			
		try {
			/* Query database*/			
			ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "AGREEMENT", "SEEKER=" + mID + " OR " + "OFFERER=" + mID));
			ObservableList<Agreement> list = this.getAgreementList(rs);

			return list;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Inserts an agreement into the database.
	 */
	public void insert(Agreement agmt) throws SQLException, ClassNotFoundException {
		String sqlStmt = new InsertSQLBuilder().addTable("AGREEMENT")
				.addFieldValue("SEEKER", agmt.getSeeker())
				.addFieldValue("OFFERER", agmt.getOfferer())
				.addFieldValue("PAYAMT", agmt.getPayAmt())
				.addFieldValue("AGREEDATE", agmt.getAgreeDate())
				.addFieldValue("CREATEDAY", agmt.getCreateDay())
				.addFieldValue("TOPOSTCODE", agmt.getToPostcode())
				.addFieldValue("FROMPOSTCODE", agmt.getFromPostcode())
				.toString();
		try {
			DBUtil.dbExecuteUpdate(sqlStmt);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
			throw e;
		}   
	}

	/**
	 * Updates the details of an agreement in the database
	 */
	public void update(Agreement agmt) throws SQLException, ClassNotFoundException {
		String sqlStmt = new UpdateSQLBuilder().addTable("AGREEMENT")
				.addFieldValue("SEEKER", agmt.getSeeker())
				.addFieldValue("OFFERER", agmt.getOfferer())
				.addFieldValue("PAYAMT", agmt.getPayAmt())
				.addFieldValue("AGREEDATE", agmt.getAgreeDate())
				.addFieldValue("CREATEDAY", agmt.getCreateDay())
				.addFieldValue("TOPOSTCODE", agmt.getToPostcode())
				.addFieldValue("FROMPOSTCODE", agmt.getFromPostcode())
				.where("AGREEMENT_ID=" + agmt.getAgreement_id())
				.toString();

		System.out.println(sqlStmt);
		try {
			DBUtil.dbExecuteUpdate(sqlStmt);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
			throw e;
		}   
	}

	/**
	 * Deletes an item from the database given a condition
	 */
	public void delete(String condition) throws SQLException, ClassNotFoundException {
		try {
			DBUtil.dbExecuteUpdate(SQLBuilder.deleteFromCondition("AGREEMENT", condition));
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
			throw e;
		}    	
	}

	/**
	 * Helper function
	 * Converts agreement records from database into agreement objects for java to play with
	 */
	private ObservableList<Agreement> getAgreementList (ResultSet rs) throws SQLException {
		ObservableList<Agreement> list = FXCollections.observableArrayList();

		while (rs.next()) {
			try {
				Agreement agmt = new Agreement();    		
				agmt.setAgreement_id(rs.getInt("AGREEMENT_ID"));
				agmt.setSeeker(rs.getInt("SEEKER"));
				agmt.setOfferer(rs.getInt("OFFERER"));
				agmt.setPayAmt(rs.getFloat("PAYAMT"));
				agmt.setAgreeDate(rs.getDate("AGREEDATE"));
				agmt.setCreateDay(rs.getDate("CREATEDAY"));
				agmt.setToPostcode(rs.getLong("TOPOSTCODE"));
				agmt.setFromPostcode(rs.getLong("FROMPOSTCODE"));

				list.add(agmt);
			} catch (SQLException e) {
				System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
				throw e;
			}
		}
		return list;
	}

	/**
	 * Finds an agreement given its unique agreement ID
	 */
	public Agreement findById(int agmt_id) throws SQLException {
		try {
			/* Query database for agreement */
			ResultSet rs = DBUtil.dbExecuteQuery(SQLBuilder.selectTable("*", "AGREEMENT", "AGREEMENT_ID=" + agmt_id));

			ObservableList<Agreement> list = this.getAgreementList(rs);

			/* only try to return if list is not empty to prevent out of bounds exception */
			if (list.size() > 0) {
				return list.get(0);
			}

			return null;

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " failed.");
			e.printStackTrace();
		}

		return null;
	}
}