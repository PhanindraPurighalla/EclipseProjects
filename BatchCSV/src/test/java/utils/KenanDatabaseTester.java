package utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;

public class KenanDatabaseTester {

	public void fetchConnectionDetailsForCustomer1Database() throws DatabaseUnitException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		//Connect to the database
	    Class.forName("com.mysql.jdbc.Driver");
	    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DVT", "root", "mysqladminpwd");
	    IDatabaseConnection connection = new DatabaseConnection(conn);
		
		
	}

	public void rollbackAndCloseDatabaseConnections() {
		// TODO Auto-generated method stub
		
	}

	public IDatabaseConnection getConnection() throws ClassNotFoundException, SQLException, DatabaseUnitException {
	    Class.forName("com.mysql.jdbc.Driver");
	    java.sql.Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DVT", "root", "mysqladminpwd");
	    IDatabaseConnection connection = new MySqlConnection(jdbcConnection, "DVT");
	    return connection;
	}

}
