package br.brener.desafio_alura.domain.topico;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para retornar os dados de um tópico na listagem.
 * Garante que apenas os dados necessários sejam expostos pela API.
 */
public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico estado,
        String autor,
        String curso
) {

    /**
     * Construtor que converte uma entidade Topico para o DTO de listagem.
     * @param topico A entidade JPA a ser convertida.
     */
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getEstado(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
