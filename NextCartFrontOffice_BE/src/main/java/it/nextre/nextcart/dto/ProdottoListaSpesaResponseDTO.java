package it.nextre.nextcart.dto;

import java.math.BigDecimal;

import it.nextre.nextcart.entity.ProdottoListaSpesa;

public class ProdottoListaSpesaResponseDTO {
	
	private Long idProdottoLista;
    private Long idProdottoShop;
    private String nomeProdotto;
    private String categoriaProdotto;
    private String tipologiaProdotto;
    private BigDecimal quantitaProdotto;
    private String noteProdotto;
    private Boolean checkedProdotto;
    private BigDecimal quantitaDisponibileStock;
	
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

	public BigDecimal getQuantitaDisponibileStock() {
		return quantitaDisponibileStock;
	}

	public void setQuantitaDisponibileStock(BigDecimal quantitaDisponibileStock) {
		this.quantitaDisponibileStock = quantitaDisponibileStock;
	}

  public String getTipologiaProdotto() {
		return tipologiaProdotto;
	}

	public void setTipologiaProdotto(String tipologiaProdotto) {
		this.tipologiaProdotto = tipologiaProdotto;
	}

public static ProdottoListaSpesaResponseDTO fromEntity(ProdottoListaSpesa prodotto, ProdottoDTO prodottoDTO) {
	  
	  if (prodotto == null) return null;
	  if (prodottoDTO == null) return null;
	  
	  ProdottoListaSpesaResponseDTO dto = new ProdottoListaSpesaResponseDTO();
      dto.setIdProdottoLista(prodotto.id);
      dto.setIdProdottoShop(prodotto.getIdProdottoShop());
      dto.setQuantitaProdotto(prodotto.getQuantita());
      dto.setNoteProdotto(prodotto.getNote());
      dto.setCheckedProdotto(prodotto.getChecked());
      dto.setTipologiaProdotto(prodotto.getTipologiaProdotto());
      dto.setNomeProdotto(prodottoDTO.getNome());
      dto.setCategoriaProdotto(prodottoDTO.getCategoriaDTO().getNome());
      dto.setQuantitaDisponibileStock(prodottoDTO.getQuantita());

      return dto;
    }

}
