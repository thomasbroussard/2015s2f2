package fr.tbr.iamcore.tests.services.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestJDBC {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1527/sample;create=true";
		
		try {
			Connection dbConnection = DriverManager.getConnection(url, "tom", "tom");
			ResultSet rs = dbConnection.prepareStatement("select * from IDENTITIES").executeQuery();
			while (rs.next()){
				System.out.println(rs.getString(2));
			}
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
