package it.nextre.nextcart.dto;

import java.math.BigDecimal;
import it.nextre.nextcart.entity.ProdottoListaSpesa;

public class ProdottoListaSpesaRequestDTO {
	
	private Long idProdottoLista;
	private	Long idProdottoShop;
	private BigDecimal quantitaProdotto;
	private String noteProdotto;
	private Boolean checkedProdotto;
	
	public Long getIdProdottoLista() {
		return idProdottoLista;
	}
	public void setIdProdottoLista(Long idProdottoLista) {
		this.idProdottoLista = idProdottoLista;
	}
	public Long getIdProdottoShop() {
		return idProdottoShop;
	}
	public void setIdProdottoShop(Long idProdottoShop) {
		this.idProdottoShop = idProdottoShop;
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

    public ProdottoListaSpesa toEntity() {
    	
    	ProdottoListaSpesa entity = new ProdottoListaSpesa();
        entity.id = this.idProdottoLista;
        entity.setIdProdotto(this.idProdottoShop);
        entity.setQuantita(this.quantitaProdotto);
        entity.setNote(this.noteProdotto);
        entity.setChecked(this.checkedProdotto);

        return entity;
    }
	
}
