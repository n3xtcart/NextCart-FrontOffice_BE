package it.nextre.nextcart.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import it.nextre.nextcart.entity.ListaSpesa;

public class ListaSpesaResponseDTO {
	
	private Long idLista;
    private String nomeLista; 
    private LocalDate dataPrevista;
    private List<ProdottoListaSpesaResponseDTO> prodotti; 
	
    public Long getIdLista() {
		return idLista;
	}
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	public String getNomeLista() {
		return nomeLista;
	}
	public void setNomeLista(String nomeLista) {
		this.nomeLista = nomeLista;
	}
	public LocalDate getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public List<ProdottoListaSpesaResponseDTO> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<ProdottoListaSpesaResponseDTO> prodotti) {
		this.prodotti = prodotti;
	}

	
    public static ListaSpesaResponseDTO fromEntity(ListaSpesa entity, List<ProdottoDTO> prodottiShop) {
    	
        if (entity == null) return null;
        if (prodottiShop == null) return null;
        
        Map<Long, ProdottoDTO> prodottiShopMap = prodottiShop.stream()
        		.collect(Collectors.toMap(ProdottoDTO::getId, Function.identity()));

        ListaSpesaResponseDTO dto = new ListaSpesaResponseDTO();
        dto.setIdLista(entity.id);
        dto.setNomeLista(entity.getNome());
        dto.setDataPrevista(entity.getDataPrevista());

        dto.setProdotti(
        		
        		entity.getProdotti().stream()
        		.map(prodotto -> {
        			
        			ProdottoDTO prodottoDTO = prodottiShopMap.get(prodotto.getIdProdottoShop());
        			
                    return ProdottoListaSpesaResponseDTO.fromEntity(prodotto, prodottoDTO);
                })
                .collect(Collectors.toList())
                
        );

        return dto;
    }

}
