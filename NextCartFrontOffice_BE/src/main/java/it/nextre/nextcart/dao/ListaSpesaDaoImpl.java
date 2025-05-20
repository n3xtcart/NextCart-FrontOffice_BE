package it.nextre.nextcart.dao;

import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import it.nextre.nextcart.entity.ListaSpesa;
import it.nextre.nextcart.exception.DaoException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

@ApplicationScoped
public class ListaSpesaDaoImpl implements ListaSpesaDao{
	
	private static final Logger log = Logger.getLogger(ListaSpesaDaoImpl.class);
	
	@Inject
    private EntityManager entityManag;

	@Override
	public List<ListaSpesa> findByUserId(Long idUtente){
		//Metodo da implementare con query personalizzata
		return null;
	}

	@Override
	public ListaSpesa save(ListaSpesa entity) {
		
		log.debug("Inizio salvataggio ListaSpesa: " + entity);
		
	    try {
	    	entityManag.persist(entity);
	        log.debug("ListaSpesa salvata");
	        return entity;
	    } catch (ConstraintViolationException e ) {
	    	throw new DaoException("Violazione di un vincolo del database", e);
	    }catch (PersistenceException e) {
	        throw new DaoException("Errore durante il salvataggio nel DB", e);
	    }
	     
	}

	@Override
	public void update(ListaSpesa entity) {
		log.debug("Inizio aggiornamento ListaSpesa: " + entity);
		
		try {
			entityManag.merge(entity);
			log.debug("ListaSpesa aggiornata");
		}catch (PersistenceException e) {
	        throw new DaoException("Errore durante l'aggiornamento nel DB", e);
	    }	
	}

	@Override
	public void delete(ListaSpesa entity) {
		
		log.debug("Inizio rimozione ListaSpesa: " + entity);
		
		try {
			entityManag.remove(entity);
			log.debug("ListaSpesa rimossa");
		}catch (PersistenceException e) {
	        throw new DaoException("Errore durante la rimozione nel DB", e);
	    }
		
	}

}
