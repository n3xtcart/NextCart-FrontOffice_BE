package it.nextre.nextcart.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "prodotti_lista_spesa")
public class ProdottoListaSpesa extends PanacheEntity {
	
	@NotNull
	@Min(value = 1)
	@Column(name = "id_prodotto_shop", nullable = false)
	private Long idProdottoShop;
	
	@NotNull
	@DecimalMin(value = "0.01")
	@Column(name = "quantita", precision = 10, scale = 2, nullable = false)
	private BigDecimal quantita;
	
//	@NotNull
//	@Column(name = "id_tipologia", nullable = false)
//	private Long idTipologia;
	
	@Size(max = 255)
	@Column(name = "note", length = 255)
	private String note;   
	
	@NotNull
	@Column(name = "checked", nullable = false)
	private Boolean checked;
	
	@CreationTimestamp
	@PastOrPresent
	@Column(name = "creation_time", nullable = false)
	public LocalDateTime creationTime;
	
	@UpdateTimestamp
	@PastOrPresent
	@Column(name = "update_time", nullable = false)
	public LocalDateTime updateTime;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "lista_spesa_id", nullable = false)
	private ListaSpesa listaSpesa;
	
	public Long getIdProdottoShop() {
		return idProdottoShop;
	}

	public void setIdProdottoShop(Long idProdottoShop) {
		this.idProdottoShop = idProdottoShop;
	}

	public BigDecimal getQuantita() {
		return quantita;
	}

	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
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

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public ListaSpesa getListaSpesa() {
		return listaSpesa;
	}

	public void setListaSpesa(ListaSpesa listaSpesa) {
		this.listaSpesa = listaSpesa;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(checked, creationTime, idProdottoShop, listaSpesa, note, quantita, updateTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdottoListaSpesa other = (ProdottoListaSpesa) obj;
		return Objects.equals(checked, other.checked) && Objects.equals(creationTime, other.creationTime)
				&& Objects.equals(idProdottoShop, other.idProdottoShop) && Objects.equals(listaSpesa, other.listaSpesa)
				&& Objects.equals(note, other.note) && Objects.equals(quantita, other.quantita)
				&& Objects.equals(updateTime, other.updateTime);
	}
	
	@Override
	public String toString() {
		return "ProdottoListaSpesa [idProdottoShop=" + idProdottoShop + ", quantita=" + quantita + ", note=" + note
				+ ", checked=" + checked + ", creationTime=" + creationTime + ", updateTime=" + updateTime
				+ ", listaSpesa=" + listaSpesa + "]";
	}

}
