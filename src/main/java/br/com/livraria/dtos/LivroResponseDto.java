package br.com.livraria.dtos;

import lombok.Data;

@Data
public class LivroResponseDto {

	private Integer id;
	private String titulo;
	private String escritor;
	private String editora;
	private String anoPublicacao;
	private String genero;;
}
