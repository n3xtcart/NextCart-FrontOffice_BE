package it.nextre.nextcart.dto;

import java.math.BigDecimal;
import it.nextre.nextcart.entity.ProdottoListaSpesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
 
public class ProdottoListaSpesaRequestDTO {
	
	@NotNull
	@Positive
	private Long idProdottoShop;
	
	@NotNull
	@Positive
    private BigDecimal quantitaProdotto;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$")
    private String tipologiaProdotto;
	
	@Size(max = 255)
	@Pattern(regexp = "^(?!\\s*$).+")
    private String noteProdotto;
    
	@NotNull
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
	
	public String getTipologiaProdotto() {
		return tipologiaProdotto;
	}

	public void setTipologiaProdotto(String tipologiaProdotto) {
		this.tipologiaProdotto = tipologiaProdotto;
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
