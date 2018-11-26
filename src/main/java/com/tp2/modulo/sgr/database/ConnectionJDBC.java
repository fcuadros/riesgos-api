package com.tp2.modulo.sgr.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;

public class ConnectionJDBC {

	public Connection getConnection() {
		Connection connection = null;
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://104.154.86.66:3306/dbriesgo?useUnicode=true&"
//			+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
//			+ "serverTimezone=UTC&useSSL=false",
//			"root","@AccesoTP3");
			
			String url = null;
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				// Load the class that provides the new "jdbc:google:mysql://" prefix.
				Class.forName("com.mysql.jdbc.GoogleDriver");
				url = System.getProperty("cloudsql.url");
			} else {
			    Class.forName("com.mysql.jdbc.Driver");
			    url = System.getProperty("cloudsql.url.dev");
			}
			
			connection = DriverManager.getConnection(url,"root","@AccesoIndraa03");
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
