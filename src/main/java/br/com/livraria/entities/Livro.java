package br.com.livraria.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O título não pode ser vazio")  // Garante que o título não seja nulo ou vazio
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres")  // Limite de caracteres
    private String titulo;

    @ManyToOne  // Relacionamento ManyToOne com Escritor
    @JoinColumn(name = "escritor_id")  // Nome da coluna que armazena a chave estrangeira
    @NotNull(message = "O escritor é obrigatório")  // Garante que o escritor não seja nulo
    private Escritor escritor;

    @NotBlank(message = "A editora não pode ser vazia")  // Garante que a editora não seja vazia
    @Size(max = 255, message = "A editora deve ter no máximo 255 caracteres")  // Limite de caracteres
    private String editora;

    @NotBlank(message = "O ano de publicação não pode ser vazio")  // Garante que o ano de publicação não seja vazio
    private String anoPublicacao;

    @NotBlank(message = "O gênero não pode ser vazio")  // Garante que o gênero não seja vazio
    @Size(max = 100, message = "O gênero deve ter no máximo 100 caracteres")  // Limite de caracteres
    private String genero;

}
