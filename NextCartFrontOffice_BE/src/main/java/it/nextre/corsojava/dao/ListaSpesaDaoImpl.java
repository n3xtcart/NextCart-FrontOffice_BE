package it.nextre.corsojava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import it.nextre.corsojava.entity.ListaSpesa;
import it.nextre.corsojava.exception.DaoException;

public class ListaSpesaDaoImpl extends BaseDao implements ListaSpesaDao {
	
	private static final Logger log = LogManager.getLogger(ListaSpesaDaoImpl.class);
	
	@Override
	public void saveList(ListaSpesa entity) {
		
		String sql = """
				INSERT INTO liste_spesa
				(nome, data_prevista, id_utente)
				VALUES
				(?,?,?)
				""";
		
		try (Connection conn = openConnection()) {
			
			log.debug("sql [{}]", sql);
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			int i = 1;
			pstm.setString(i++, entity.getNome());
			pstm.setDate(i++, java.sql.Date.valueOf(entity.getDataPrevista()));
			pstm.setLong(i++, entity.getIdUtente());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException("Errore durante la connessione", e);
		}
				
		
	}

	@Override
	public void updateList(ListaSpesa entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteList(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
