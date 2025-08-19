package br.brener.desafio_alura.controller;


import br.brener.desafio_alura.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = repository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DadosListagemTopico(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * NOVO MÉTODO PARA ATUALIZAÇÃO
     * Responde a requisições PUT para /topicos/{id}.
     * A anotação @Transactional garante que o JPA gerencie a transação,
     * e ao final do método, as alterações no objeto 'topico' são persistidas no banco.
     */

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        // 1 Verifica se o tópico a ser atualizado existe
        var optionalTopico = repository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 2 Verifica se a atualização não cria um tópico duplicado
        var topicoDuplicado = repository.findByTituloAndMensagemAndEstado(dados.titulo(), dados.mensagem(), dados.estado());
        if (topicoDuplicado.isPresent() && !topicoDuplicado.get().getId().equals(id)) {
            return ResponseEntity.badRequest().body("Já existe um tópico com o mesmo título e mensagem.");
        }

        // 3 Efetua a atualização
        var topico = optionalTopico.get();
        topico.atualizarInformacoes(dados);

        // 4 Retorna o tópico atualizado
        return ResponseEntity.ok(new DadosListagemTopico(topico));
    }

    /**
     * Responde a requisições DELETE para /topicos/{id}.
     * Retorna o código 204 No Content em caso de sucesso.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

