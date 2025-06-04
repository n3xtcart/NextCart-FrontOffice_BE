package it.nextre.nextcart.dto;

import java.math.BigDecimal;
import it.nextre.nextcart.entity.ProdottoListaSpesa;

public class ProdottoListaSpesaRequestDTO {
	
	private Long idProdottoShop;
    //private String tipologiaProdotto;
    private BigDecimal quantitaProdotto;
    private String noteProdotto;
    private Boolean checkedProdotto;

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
    	ProdottoListaSpesa prodotto = new ProdottoListaSpesa();
        prodotto.setIdProdottoShop(this.idProdottoShop);
        prodotto.setQuantita(this.quantitaProdotto);
        prodotto.setNote(this.noteProdotto);
        prodotto.setChecked(this.checkedProdotto);
        return prodotto;
    }
}
