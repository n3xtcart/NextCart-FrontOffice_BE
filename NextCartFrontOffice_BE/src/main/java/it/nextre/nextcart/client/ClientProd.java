package it.nextre.nextcart.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.nextre.nextcart.dto.CategoriaDTO;
import it.nextre.nextcart.dto.ProdottoDTO;
import it.nextre.nextcart.service.ServizioProdotto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClientProd implements ServizioProdotto {
	
    private final List<ProdottoDTO> prodotti = new ArrayList<>();
    
    @Inject
    ClientCat clientCat;
	
    @PostConstruct
    public void init() {
		
	        // Carne
			CategoriaDTO carne = clientCat.trovaPerId(1L).get();
	        prodotti.add(new ProdottoDTO(1L, "Bistecca di manzo", new BigDecimal("100"), "bistecca.jpg", carne, null));
	        prodotti.add(new ProdottoDTO(2L, "Petto di pollo", new BigDecimal("100"), "pollo.jpg", carne, null));
	        prodotti.add(new ProdottoDTO(3L, "Salsiccia", new BigDecimal("100"), "salsiccia.jpg", carne, null));
	        prodotti.add(new ProdottoDTO(4L, "Spezzatino", new BigDecimal("100"), "spezzatino.jpg", carne, null));
	        prodotti.add(new ProdottoDTO(5L, "Carne macinata", new BigDecimal("100"), "macinato.jpg", carne, null));

	        // Frutta & Verdura
			CategoriaDTO fruttaVerdura = clientCat.trovaPerId(2L).get();
	        prodotti.add(new ProdottoDTO(6L, "Mela", new BigDecimal("100"), "mela.jpg", fruttaVerdura, null));
	        prodotti.add(new ProdottoDTO(7L, "Insalata", new BigDecimal("100"), "insalata.jpg", fruttaVerdura, null));
	        prodotti.add(new ProdottoDTO(8L, "Zucchina", new BigDecimal("100"), "zucchina.jpg", fruttaVerdura, null));
	        prodotti.add(new ProdottoDTO(9L, "Carota", new BigDecimal("100"), "carota.jpg", fruttaVerdura, null));
	        prodotti.add(new ProdottoDTO(10L, "Banana", new BigDecimal("100"), "banana.jpg", fruttaVerdura, null));

	        // Uova & Latticini
			CategoriaDTO uovaLatticini = clientCat.trovaPerId(3L).get();
	        prodotti.add(new ProdottoDTO(11L, "Latte intero", new BigDecimal("100"), "latte.jpg", uovaLatticini, null));
	        prodotti.add(new ProdottoDTO(12L, "Yogurt bianco", new BigDecimal("100"), "yogurt.jpg", uovaLatticini, null));
	        prodotti.add(new ProdottoDTO(13L, "Uova bio", new BigDecimal("100"), "uova.jpg", uovaLatticini, null));
	        prodotti.add(new ProdottoDTO(14L, "Burro", new BigDecimal("100"), "burro.jpg", uovaLatticini, null));
	        prodotti.add(new ProdottoDTO(15L, "Formaggio fresco", new BigDecimal("100"), "formaggio_fresco.jpg", uovaLatticini, "Grammi"));

	        // Igiene
			CategoriaDTO igiene = clientCat.trovaPerId(4L).get();
	        prodotti.add(new ProdottoDTO(16L, "Shampoo", new BigDecimal("100"), "shampoo.jpg", igiene, null));
	        prodotti.add(new ProdottoDTO(17L, "Sapone liquido", new BigDecimal("100"), "sapone.jpg", igiene, null));
	        prodotti.add(new ProdottoDTO(18L, "Dentifricio", new BigDecimal("100"), "dentifricio.jpg", igiene, null));
	        prodotti.add(new ProdottoDTO(19L, "Carta igienica", new BigDecimal("100"), "carta_igienica.jpg", igiene, null));
	        prodotti.add(new ProdottoDTO(20L, "Detergente viso", new BigDecimal("100"), "detergente.jpg", igiene, null));

	        // Salumi & Formaggi
			CategoriaDTO salumiForm = clientCat.trovaPerId(5L).get();
	        prodotti.add(new ProdottoDTO(21L, "Prosciutto crudo", new BigDecimal("100"), "prosciutto.jpg", salumiForm, null));
	        prodotti.add(new ProdottoDTO(22L, "Salame", new BigDecimal("100"), "salame.jpg", salumiForm, null));
	        prodotti.add(new ProdottoDTO(23L, "Mortadella", new BigDecimal("100"), "mortadella.jpg", salumiForm, null));
	        prodotti.add(new ProdottoDTO(24L, "Pecorino", new BigDecimal("100"), "pecorino.jpg", salumiForm, null));
	        prodotti.add(new ProdottoDTO(25L, "Parmigiano", new BigDecimal("100"), "parmigiano.jpg", salumiForm, null));

	        // Pesce
			CategoriaDTO pesce = clientCat.trovaPerId(6L).get();
	        prodotti.add(new ProdottoDTO(26L, "Salmone", new BigDecimal("100"), "salmone.jpg", pesce, null));
	        prodotti.add(new ProdottoDTO(27L, "Tonno fresco", new BigDecimal("100"), "tonno.jpg", pesce, null));
	        prodotti.add(new ProdottoDTO(28L, "Merluzzo", new BigDecimal("100"), "merluzzo.jpg", pesce, null));
	        prodotti.add(new ProdottoDTO(29L, "Gamberetti", new BigDecimal("100"), "gamberetti.jpg", pesce, null));
	        prodotti.add(new ProdottoDTO(30L, "Orata", new BigDecimal("100"), "orata.jpg", pesce, null));

    }
	
	
	public List<ProdottoDTO> trovaTutti() {
		return prodotti;
	}



	@Override
	public Optional<ProdottoDTO> trovaPerId(Long id) {
		for (int i = 0; i < prodotti.size(); i++) {
			if (id.equals(prodotti.get(i).getId())) {
				return Optional.ofNullable(prodotti.get(i));
			}
		}
		
		return Optional.empty();
	}


	@Override
	public List<ProdottoDTO> trovaPerIdCategoria(Long idCategoria) {
		List<ProdottoDTO> prodottiTrovati = new ArrayList<>();
		for (int i = 0; i < prodotti.size(); i++) {
			if (idCategoria.equals(prodotti.get(i).getCategoriaDTO().getId())) {
				prodottiTrovati.add(prodotti.get(i));
			}
		}
		return prodottiTrovati;

	} 

}
