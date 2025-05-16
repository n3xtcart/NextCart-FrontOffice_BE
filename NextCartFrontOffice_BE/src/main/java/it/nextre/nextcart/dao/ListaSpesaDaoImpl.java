package it.nextre.nextcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import it.nextre.nextcart.entity.ListaSpesa;
import it.nextre.nextcart.exception.DaoException;

public class ListaSpesaDaoImpl extends BaseDao implements ListaSpesaDao{
	
	private static final Logger log = Logger.getLogger(ListaSpesaDaoImpl.class);

	@Override
	public List<ListaSpesa> findAllByUserId(Long id){
		
		if(id == null) {
			throw new IllegalArgumentException("L'id non può essere null");
		}
		
		String sql = """
				SELECT id, nome, data_prevista, id_utente
				FROM liste_spesa
				WHERE id_utente = ?
				""";
		
		List<ListaSpesa> risultato = new ArrayList<>();
		
		try (Connection conn = getConnection()){
			
			log.debug("sql [" + sql + "]");
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, id);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				ListaSpesa lista = new ListaSpesa();
				lista.setId(rs.getLong("id"));
				lista.setNome(rs.getString("nome"));
				lista.setDataPrevista(rs.getDate("data_prevista").toLocalDate());
				lista.setIdUtente(rs.getLong("id_utente"));
				
				risultato.add(lista);
			}
						
		}catch (SQLException e) {
			throw new DaoException("Errore durante la connessione al db", e);
		}
		
		return risultato;
	}

	@Override
	public ListaSpesa save(ListaSpesa entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ListaSpesa entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}



}
