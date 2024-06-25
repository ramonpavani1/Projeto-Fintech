package fintech.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionManager {

	private static ConnectionManager connectionManager;

	private ConnectionManager() {
	}

	public static ConnectionManager getInstance() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "USUARIO",
					"SENHA");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void close(Connection connection, PreparedStatement stmt, ResultSet rs) {
		// TODO Auto-generated method stub
		
	}

	public static void close(Connection connection, PreparedStatement stmt) {
		// TODO Auto-generated method stub
		
	}

}