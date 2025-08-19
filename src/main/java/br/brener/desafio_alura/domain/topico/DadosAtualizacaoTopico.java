package br.brener.desafio_alura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para receber os dados de atualização de um tópico.
 * Apenas os campos que podem ser modificados são incluídos.
 */
public record DadosAtualizacaoTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        StatusTopico estado
) {
}
