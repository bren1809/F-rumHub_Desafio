package br.brener.desafio_alura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    /**
     * Busca um tópico pelo título e mensagem.
     * Este novo método será usado para a validação de duplicados na atualização.
     * @param titulo Título do tópico.
     * @param mensagem Mensagem do tópico.
     * @return um Optional contendo o tópico se encontrado.
     */
    Optional<Topico> findByTituloAndMensagemAndEstado(String titulo, String mensagem, StatusTopico estado);
}