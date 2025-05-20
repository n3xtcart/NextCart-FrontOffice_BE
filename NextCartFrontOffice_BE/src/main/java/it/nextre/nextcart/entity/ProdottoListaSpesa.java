package it.nextre.nextcart.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "prodotti_lista_spesa")
public class ProdottoListaSpesa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "id_prodotto", nullable = false)
	private Long idProdotto;
	
	@Column(name = "quantita", precision = 10, scale = 2, nullable = false)
	private BigDecimal quantita;
	
	@Column(name = "id_tipologia", nullable = false)
	private Long idTipologia;
	
	@Column(name = "note", length = 255)
	private String note;   
	
	@Column(name = "checked", nullable = false)
	private Boolean checked;
	
	@Transient
	private ListaSpesa listaSpesa;
	
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
	
	public BigDecimal getQuantita() {
		return quantita;
	}
	
	public void setQuantita(BigDecimal quantita) {
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
