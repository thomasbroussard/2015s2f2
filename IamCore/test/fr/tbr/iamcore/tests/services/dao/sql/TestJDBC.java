package fr.tbr.iamcore.tests.services.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1527/sample;create=true";
		
		try {
			Connection dbConnection = DriverManager.getConnection(url, "tom", "tom");
			dbConnection.prepareStatement("select * from IDENTITIES").executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
