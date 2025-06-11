package it.nextre.nextcart.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.nextre.nextcart.dto.CategoriaDTO;
import it.nextre.nextcart.service.ServizioCategoria;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientCat implements ServizioCategoria {
	
	private final List<CategoriaDTO> categorie = new ArrayList<>();

	
	public ClientCat() {
        categorie.add(new CategoriaDTO(1L, "Carne", "carne.jpg"));
        categorie.add(new CategoriaDTO(2L, "Frutta & Verdura", "frutta_verdura.jpg"));
        categorie.add(new CategoriaDTO(3L, "Uova & Latticini", "uova_latticini.jpg"));
        categorie.add(new CategoriaDTO(4L, "Igiene", "igiene.jpg"));
        categorie.add(new CategoriaDTO(5L, "Salumi & Formaggi", "salumi_formaggi.jpg"));
        categorie.add(new CategoriaDTO(6L, "Pesce", "pesce.jpg"));
    }
	
	

	public List<CategoriaDTO> trovaTutte() {
		return categorie;
	}


	@Override
	public Optional<CategoriaDTO> trovaPerId(Long id) {
		for (int i = 0; i < categorie.size(); i++) {
			if (id.equals(categorie.get(i).getId())) {
				return Optional.ofNullable(categorie.get(i));
			}
		}

		return Optional.empty();
	}


}
