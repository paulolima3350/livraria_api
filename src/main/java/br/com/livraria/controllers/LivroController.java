package br.com.livraria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.dtos.LivroAtualizarResponseDto;
import br.com.livraria.dtos.LivroDeletadoResponseDto;
import br.com.livraria.dtos.LivroRequestDto;
import br.com.livraria.dtos.LivroResponseDto;
import br.com.livraria.services.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/livro")
@RestController
public class LivroController {

    @Autowired
    LivroService livroService;

    // Método para cadastrar livro
    @Operation(summary = "Endpoint para cadastrar livro na livraria", description = "Este endpoint permite cadastrar um novo livro na livraria.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para o livro"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/cadastrar")
    public LivroResponseDto cadastrarLivro(
            @Parameter(description = "Dados do livro a ser cadastrado") LivroRequestDto livro) {
        return livroService.cadastrarLivro(livro);
    }

    // Método para listar todos os livros
    @Operation(summary = "Endpoint para listar todos os livros da livraria", description = "Este endpoint retorna uma lista de todos os livros cadastrados na livraria.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/listar-todos")
    public List<LivroResponseDto> listarLivros() {
        return livroService.listarLivros();
    }

    // Método para buscar livro ao digitar
    @Operation(summary = "Endpoint para buscar livro pelo nome", description = "Este endpoint permite buscar um livro pelo seu título.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/buscar-por-nome")
    public LivroResponseDto buscarLivroPorNome(
            @Parameter(description = "Nome do livro a ser buscado") String nome) {
        return livroService.buscarLivroPorNome(nome);
    }

    // Método para atualizar livro
    @Operation(summary = "Endpoint para atualizar livro", description = "Este endpoint permite atualizar as informações de um livro já cadastrado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/atualizar")
    public LivroAtualizarResponseDto atualizarLivro(
            @Parameter(description = "ID do livro a ser atualizado") Integer id,
            @Parameter(description = "Dados do livro para atualização") LivroRequestDto livro) {
        return livroService.atualizarLivro(id, livro);
    }

    // Método para deletar livro
    @Operation(summary = "Endpoint para deletar livro", description = "Este endpoint permite deletar um livro da livraria.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/deletar")
    public LivroDeletadoResponseDto deletarLivro(
            @Parameter(description = "ID do livro a ser deletado") Integer id) {
        return livroService.deletarLivro(id);
    }
}
