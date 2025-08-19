package br.brener.desafio_alura.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private String autor;
    private String curso;

    @Enumerated(EnumType.STRING)
    private StatusTopico estado;

    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.estado = dados.estado();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    /**
     * Método para atualizar as informações do tópico a partir de um DTO.
     * Ele verifica se os dados não são nulos antes de fazer a alteração.
     * @param dados DTO com os novos dados para o tópico.
     */
    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.estado() != null) {
            this.estado = dados.estado();
        }
    }
}
