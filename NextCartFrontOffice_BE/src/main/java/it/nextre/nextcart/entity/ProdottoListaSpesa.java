package it.nextre.nextcart.entity;

public class ProdottoListaSpesa {
	
	private Long id;
	private ListaSpesa listaSpesa;
	private Long idProdotto;
	private Double quantita;
	private Long idTipologia;         
	private String note;      
	private Boolean checked;
	
	
	public Long getId() {
		return id;
	}
		
	public ListaSpesa getListaSpesa() {
		return listaSpesa;
	}
	
	public void setListaSpesa(ListaSpesa listaSpesa) {
		this.listaSpesa = listaSpesa;
	}
	
	public Long getIdProdotto() {
		return idProdotto;
	}
	
	public void setIdProdotto(Long idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public Double getQuantita() {
		return quantita;
	}
	
	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}
	
	public Long getIdTipologia() {
		return idTipologia;
	}
	
	public void setIdTipologia(Long idTipologia) {
		this.idTipologia = idTipologia;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public Boolean getChecked() {
		return checked;
	}
	
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
