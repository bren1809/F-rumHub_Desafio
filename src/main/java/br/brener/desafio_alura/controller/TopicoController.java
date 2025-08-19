package br.brener.desafio_alura.controller;


import br.brener.desafio_alura.domain.topico.DadosCadastroTopico;
import br.brener.desafio_alura.domain.topico.Topico;
import br.brener.desafio_alura.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        // Regra de negócio: Não permitir tópicos duplicados
        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Já existe um tópico com o mesmo título e mensagem.");
        }

        var topico = new Topico(dados);
        repository.save(topico);

        // Retorna o código 201 Created, com o cabeçalho Location e o tópico recém-criado no corpo
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        // Para retornar o corpo da resposta, podemos criar um DTO de detalhamento.
        // Por simplicidade aqui, retornamos a própria entidade (não é a melhor prática).
        return ResponseEntity.created(uri).body(topico);
    }

}

