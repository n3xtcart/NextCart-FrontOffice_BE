package it.nextre.nextcart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jboss.logging.Logger;

public abstract class BaseDao {
	
	private static final Logger log = Logger.getLogger(BaseDao.class);
	
	public Connection getConnection() throws SQLException{
		
		String username = "root";
		String password = "mysql";
		String jdbcUrl = "jdbc:mysql://localhost:3336/lista_spesa_db";
		
		log.trace("jdbcUrl [" + jdbcUrl + "]");
		log.debug("Apro la connessione al db");
		Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
		log.trace("Connection [" + conn +  "]");
		log.debug("Connessione aperta");
		return conn;
	}

}
