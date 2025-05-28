package it.nextre.nextcart.dto;

import java.math.BigDecimal;
import it.nextre.nextcart.entity.ProdottoListaSpesa;

public class ProdottoListaSpesaDTO  {

	private Long idProdottoLista;
	private	Long idProdottoShop;
	private String nomeProdotto;
	private String categoriaProdotto;
	private Long idTipologia;
	private String tipologiaProdotto;	//se sono grammi o se è una confezione
	private BigDecimal quantitaProdotto;
	private String noteProdotto;
	private Boolean checkedProdotto;
	
	public Long getIdProdottoLista() {
		return idProdottoLista;
	}

	public void setIdProdottoLista(Long idProdottoLista) {
		this.idProdottoLista = idProdottoLista;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public String getCategoriaProdotto() {
		return categoriaProdotto;
	}

	public void setCategoriaProdotto(String categoriaProdotto) {
		this.categoriaProdotto = categoriaProdotto;
	}

	public String getTipologiaProdotto() {
		return tipologiaProdotto;
	}

	public void setTipologiaProdotto(String tipologiaProdotto) {
		this.tipologiaProdotto = tipologiaProdotto;
	}

	public BigDecimal getQuantitaProdotto() {
		return quantitaProdotto;
	}

	public void setQuantitaProdotto(BigDecimal quantitaProdotto) {
		this.quantitaProdotto = quantitaProdotto;
	}

	public String getNoteProdotto() {
		return noteProdotto;
	}

	public void setNoteProdotto(String noteProdotto) {
		this.noteProdotto = noteProdotto;
	}

	public Boolean getCheckedProdotto() {
		return checkedProdotto;
	}

	public void setCheckedProdotto(Boolean checkedProdotto) {
		this.checkedProdotto = checkedProdotto;
	}
	
	public Long getIdProdottoShop() {
		return idProdottoShop;
	}

	public void setIdProdottoShop(Long idProdottoShop) {
		this.idProdottoShop = idProdottoShop;
	}
	
	public Long getIdTipologia() {
		return idTipologia;
	}

	public void setIdTipologia(Long idTipologia) {
		this.idTipologia = idTipologia;
	}

    public static ProdottoListaSpesaDTO toDTO(ProdottoListaSpesa entity, ProdottoDTO prodottoDTO) {

		if (entity == null) {
			return null;
		}
		
		if (prodottoDTO == null) {
			return null;
		}

		ProdottoListaSpesaDTO dto = new ProdottoListaSpesaDTO();
		dto.setIdProdottoLista(entity.id);
		dto.setNomeProdotto(prodottoDTO.getNome());
		dto.setCategoriaProdotto(prodottoDTO.getCategoriaDTO().getNome());
		dto.setTipologiaProdotto(prodottoDTO.getTipologiaProdotto());
		dto.setQuantitaProdotto(entity.getQuantita());
		dto.setNoteProdotto(entity.getNote());
		dto.setCheckedProdotto(entity.getChecked());
			
		return dto;
    } 
    
	public ProdottoListaSpesa toEntity() {

	    
		ProdottoListaSpesa entity = new ProdottoListaSpesa();
		
		entity.setIdProdotto(this.getIdProdottoShop());
		entity.setIdTipologia(this.getIdTipologia());
		//Da terminare
		
		

		return entity;
	}
    
    
    
    
    
    
}
