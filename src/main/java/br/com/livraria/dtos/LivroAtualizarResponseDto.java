package br.com.livraria.dtos;

import lombok.Data;

@Data
public class LivroAtualizarResponseDto {
	
	private Integer id;
	private String titulo;
	private String escritor;
	private String editora;
	private String anoPublicacao;
	private String genero;;
	private String mensagem;
	
}
