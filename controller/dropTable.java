package controller;

import test.BingTest;
import util.DBUtil;

public class dropTable {
	public static void main(String[] args) throws Exception {
		BingTest.clearTables();
		DBUtil.dbShutdown();
	
}
}