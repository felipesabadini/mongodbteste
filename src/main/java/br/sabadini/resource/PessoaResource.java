package br.sabadini.resource;

import br.sabadini.entity.Pessoa;
import br.sabadini.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pessoas")
public class PessoaResource {

    private PessoaRepository repository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public PessoaResource(PessoaRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @RequestMapping("/criar")
    public Pessoa criar() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("felipe");
        return repository.save(pessoa);
    }

    @RequestMapping("/criar2")
    public Pessoa criar2(@RequestParam String nome, @RequestParam String sobrenome) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setSobrenome(sobrenome);
        return repository.save(pessoa);
    }


    @RequestMapping
    public List<Pessoa> todos() {
        return repository.findAll();
    }

    @RequestMapping("/{id}")
    public Pessoa porId(@PathVariable String id) {
        return repository.findOne(id);
    }

    @RequestMapping("/buscar-por-nome")
    public Pessoa porNome(@RequestParam String nome) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(nome));

        return mongoTemplate.findOne(query, Pessoa.class);
    }
}
