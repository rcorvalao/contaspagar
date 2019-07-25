package br.com.rmc.contaspagar.coreapi.repository;

import br.com.rmc.contaspagar.coreapi.entity.ContasPagar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasPagarRepository extends CrudRepository<ContasPagar, Long> {

}
