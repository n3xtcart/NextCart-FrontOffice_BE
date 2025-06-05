package it.nextre.nextcart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.nextre.nextcart.dto.CategoriaDTO;
import it.nextre.nextcart.dto.ProdottoDTO;

public class ClientProd implements ServizioProdotto {
	
    private final List<ProdottoDTO> prodotti = new ArrayList<>();
    private final ClientCat clientCat = null;

	
	public ClientProd() {
		
	        // Carne
			CategoriaDTO carne = clientCat.trovaPerId(1L).get();
	        prodotti.add(new ProdottoDTO(1L, "Bistecca di manzo", new BigDecimal("0.5"), "bistecca.jpg", carne));
	        prodotti.add(new ProdottoDTO(2L, "Petto di pollo", new BigDecimal("0.4"), "pollo.jpg", carne));
	        prodotti.add(new ProdottoDTO(3L, "Salsiccia", new BigDecimal("0.3"), "salsiccia.jpg", carne));
	        prodotti.add(new ProdottoDTO(4L, "Spezzatino", new BigDecimal("0.6"), "spezzatino.jpg", carne));
	        prodotti.add(new ProdottoDTO(5L, "Carne macinata", new BigDecimal("0.5"), "macinato.jpg", carne));

	        // Frutta & Verdura
			CategoriaDTO fruttaVerdura = clientCat.trovaPerId(2L).get();
	        prodotti.add(new ProdottoDTO(6L, "Mela", new BigDecimal("0.25"), "mela.jpg", fruttaVerdura));
	        prodotti.add(new ProdottoDTO(7L, "Insalata", new BigDecimal("0.15"), "insalata.jpg", fruttaVerdura));
	        prodotti.add(new ProdottoDTO(8L, "Zucchina", new BigDecimal("0.30"), "zucchina.jpg", fruttaVerdura));
	        prodotti.add(new ProdottoDTO(9L, "Carota", new BigDecimal("0.20"), "carota.jpg", fruttaVerdura));
	        prodotti.add(new ProdottoDTO(10L, "Banana", new BigDecimal("0.22"), "banana.jpg", fruttaVerdura));

	        // Uova & Latticini
			CategoriaDTO uovaLatticini = clientCat.trovaPerId(3L).get();
	        prodotti.add(new ProdottoDTO(11L, "Latte intero", new BigDecimal("1.00"), "latte.jpg", uovaLatticini));
	        prodotti.add(new ProdottoDTO(12L, "Yogurt bianco", new BigDecimal("0.15"), "yogurt.jpg", uovaLatticini));
	        prodotti.add(new ProdottoDTO(13L, "Uova bio", new BigDecimal("6"), "uova.jpg", uovaLatticini));
	        prodotti.add(new ProdottoDTO(14L, "Burro", new BigDecimal("0.25"), "burro.jpg", uovaLatticini));
	        prodotti.add(new ProdottoDTO(15L, "Formaggio fresco", new BigDecimal("0.30"), "formaggio_fresco.jpg", uovaLatticini));

	        // Igiene
			CategoriaDTO igiene = clientCat.trovaPerId(4L).get();
	        prodotti.add(new ProdottoDTO(16L, "Shampoo", new BigDecimal("1"), "shampoo.jpg", igiene));
	        prodotti.add(new ProdottoDTO(17L, "Sapone liquido", new BigDecimal("1"), "sapone.jpg", igiene));
	        prodotti.add(new ProdottoDTO(18L, "Dentifricio", new BigDecimal("1"), "dentifricio.jpg", igiene));
	        prodotti.add(new ProdottoDTO(19L, "Carta igienica", new BigDecimal("4"), "carta_igienica.jpg", igiene));
	        prodotti.add(new ProdottoDTO(20L, "Detergente viso", new BigDecimal("1"), "detergente.jpg", igiene));

	        // Salumi & Formaggi
			CategoriaDTO salumiForm = clientCat.trovaPerId(5L).get();
	        prodotti.add(new ProdottoDTO(21L, "Prosciutto crudo", new BigDecimal("0.2"), "prosciutto.jpg", salumiForm));
	        prodotti.add(new ProdottoDTO(22L, "Salame", new BigDecimal("0.3"), "salame.jpg", salumiForm));
	        prodotti.add(new ProdottoDTO(23L, "Mortadella", new BigDecimal("0.2"), "mortadella.jpg", salumiForm));
	        prodotti.add(new ProdottoDTO(24L, "Pecorino", new BigDecimal("0.25"), "pecorino.jpg", salumiForm));
	        prodotti.add(new ProdottoDTO(25L, "Parmigiano", new BigDecimal("0.3"), "parmigiano.jpg", salumiForm));

	        // Pesce
			CategoriaDTO pesce = clientCat.trovaPerId(6L).get();
	        prodotti.add(new ProdottoDTO(26L, "Salmone", new BigDecimal("0.4"), "salmone.jpg", pesce));
	        prodotti.add(new ProdottoDTO(27L, "Tonno fresco", new BigDecimal("0.35"), "tonno.jpg", pesce));
	        prodotti.add(new ProdottoDTO(28L, "Merluzzo", new BigDecimal("0.45"), "merluzzo.jpg", pesce));
	        prodotti.add(new ProdottoDTO(29L, "Gamberetti", new BigDecimal("0.2"), "gamberetti.jpg", pesce));
	        prodotti.add(new ProdottoDTO(30L, "Orata", new BigDecimal("0.5"), "orata.jpg", pesce));



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
