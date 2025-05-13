package it.nextre.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseDao {
	
	private static final Logger log = LogManager.getLogger(BaseDao.class);
	
	public Connection openConnection() throws SQLException{
		
		String username = "root";
		String password = "mysql";
		String jdbcUrl = "jdbc:mysql://localhost:3336/lista_spesa_db";
		
		log.trace("jdbcUrl [{}}", jdbcUrl);
		log.debug("Apro la connessione al db");
		Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
		log.trace("Connection [{}}", conn);
		log.debug("Connessione aperta");
		return conn;
	}

}
