package it.nextre.corsojava.dto;

public class ProdottoListaSpesaDTO  {
	
	private Long idProdottoLista;
	public String nomeProdotto;
	private String categoriaProdotto; 
	public String tipologiaProdotto;	//se sono grammi o se è una confezione
	public Double quantitaProdotto;
	public String noteProdotto;
	public Boolean checkedProdotto;
	
	public Long getIdProdottoLista() {
        return idProdottoLista;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public String getCategoriaProdotto() {
        return categoriaProdotto;
    }

    public String getTipologiaProdotto() {
        return tipologiaProdotto;
    }

    public void setTipologiaProdotto(String tipologiaProdotto) {
        this.tipologiaProdotto = tipologiaProdotto;
    }

    public Double getQuantitaProdotto() {
        return quantitaProdotto;
    }

    public void setQuantitaProdotto(Double quantitaProdotto) {
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
	
}
