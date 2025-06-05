package it.nextre.nextcart.service;



import java.time.LocalDate;

import org.jboss.logging.Logger;

import it.nextre.nextcart.dto.ListaSpesaRequestDTO;
import it.nextre.nextcart.dto.ListaSpesaResponseDTO;
import it.nextre.nextcart.dto.UserListaSpesaDTO;
import it.nextre.nextcart.util.ValidatorCampi;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListaSpesaServiceImpl implements ListaSpesaService {

    private Logger log; 


	@Override
	public UserListaSpesaDTO createLista(ListaSpesaRequestDTO dto) {

		
		if ((dto == null)) {
			throw new IllegalArgumentException("Il dto è null");
		}
		ValidatorCampi.validaCampoObbligatorio(dto.getNomeLista(), "Nome lista");
		if (dto.getNomeLista().length() > 100) {
			throw new IllegalArgumentException("Il campo nome è maggiore di 100 caratteri");
		}
		ValidatorCampi.validaCampoObbligatorio(dto.getDataPrevista(), "Data lista");
		LocalDate dataInserita = ValidatorCampi.validaData(dto.getDataPrevista(), "Data lista");
		
		if (dataInserita.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("La data è antecedente ad oggi.");
		}
		
		// to entity
		// salvataggio 
		// test

		return null;
	}

	@Override
	public UserListaSpesaDTO getListeByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaSpesaResponseDTO getListaByIdAndUser(Long listaId, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteLista(Long listaId) {
		// TODO Auto-generated method stub
		return false;
	}

    

    }