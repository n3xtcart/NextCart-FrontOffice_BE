package it.nextre.nextcart.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "liste_spesa")
public class ListaSpesa extends PanacheEntity {

	@Size(max = 100)
	@NotNull
	@Pattern(regexp = "^[A-Za-zÀ-ÿ0-9_ ]+$")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@NotNull
	@FutureOrPresent
	@Column(name = "data_prevista", nullable = false)
	private LocalDate dataPrevista;
	
	@NotNull
	@Min(value = 1)
	@Column(name = "id_utente", nullable = false)
	private Long idUtente;
	
	@CreationTimestamp
	@PastOrPresent
	@Column(name = "creation_time", nullable = false)
	public LocalDateTime creationTime;
	
	@UpdateTimestamp
	@PastOrPresent
	@Column(name = "update_time", nullable = false)
	public LocalDateTime updateTime;
	
    @OneToMany(mappedBy = "listaSpesa", fetch = FetchType.EAGER)
    private List<ProdottoListaSpesa> prodotti;
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public List<ProdottoListaSpesa> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ProdottoListaSpesa> prodotti) {
		this.prodotti = prodotti;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(creationTime, dataPrevista, idUtente, nome, updateTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaSpesa other = (ListaSpesa) obj;
		return Objects.equals(creationTime, other.creationTime) && Objects.equals(dataPrevista, other.dataPrevista)
				&& Objects.equals(idUtente, other.idUtente) && Objects.equals(nome, other.nome)
				&& Objects.equals(updateTime, other.updateTime);
	}

	@Override
	public String toString() {
		return "ListaSpesa [nome=" + nome + ", dataPrevista=" + dataPrevista + ", idUtente=" + idUtente
				+ ", creationTime=" + creationTime + ", updateTime=" + updateTime + "]";
	}	
}
