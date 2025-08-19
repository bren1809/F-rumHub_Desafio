package br.brener.desafio_alura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "Mensagem é obrigatória")
        String mensagem,

        @NotNull
        StatusTopico estado,

        @NotBlank(message = "Autor é obrigatório")
        String autor,

        @NotBlank(message = "Curso é obrigatório")
        String curso
) {
}
