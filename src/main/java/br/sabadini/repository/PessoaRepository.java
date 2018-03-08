package br.sabadini.repository;

import br.sabadini.entity.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felipesabadinifacina on 07/03/18.
 */
@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
}
