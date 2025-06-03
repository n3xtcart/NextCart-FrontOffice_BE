package it.nextre.nextcart.dto;

import java.math.BigDecimal;
import it.nextre.nextcart.entity.ProdottoListaSpesa;

public class ProdottoListaSpesaUpdateDTO {
	
	//private String tipologiaProdotto;
    private BigDecimal quantitaProdotto;
    private String noteProdotto;
    private Boolean checkedProdotto;

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

    public void toEntity(ProdottoListaSpesa prodotto) {
    	
		prodotto.setQuantita(this.quantitaProdotto);
		prodotto.setNote(this.noteProdotto);
		prodotto.setChecked(this.checkedProdotto);
		
    }
}
