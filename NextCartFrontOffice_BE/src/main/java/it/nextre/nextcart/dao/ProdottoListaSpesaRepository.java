package it.nextre.nextcart.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.nextre.nextcart.entity.ProdottoListaSpesa;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdottoListaSpesaRepository implements PanacheRepository<ProdottoListaSpesa>{

}
