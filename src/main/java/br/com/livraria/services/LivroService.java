package br.com.livraria.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livraria.dtos.LivroAtualizarResponseDto;
import br.com.livraria.dtos.LivroDeletadoResponseDto;
import br.com.livraria.dtos.LivroRequestDto;
import br.com.livraria.dtos.LivroResponseDto;
import br.com.livraria.entities.Escritor;
import br.com.livraria.entities.Livro;
import br.com.livraria.repositories.EscritorRepository;
import br.com.livraria.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private EscritorRepository escritorRepository;

	// metodo para cadastrar livro
	public LivroResponseDto cadastrarLivro(LivroRequestDto request) {
		// 1. Remover espaços extras do nome do autor
		String nomeEscritor = request.getEscritor().trim();

		// 2. Consultar o autor (escritor) com busca flexível
		Escritor escritor = escritorRepository.findByNomeContainingIgnoreCase(nomeEscritor).orElseGet(() -> {
			// Criar um novo escritor se não for encontrado
			Escritor novoEscritor = new Escritor();
			novoEscritor.setNome(nomeEscritor);
			return escritorRepository.save(novoEscritor); // Salvar o novo escritor no banco
		});

		// 3. Criar o objeto Livro e associar o escritor
		var livro = new Livro();
		livro.setTitulo(request.getTitulo());
		livro.setEscritor(escritor); // Associando o escritor encontrado ou criado
		livro.setEditora(request.getEditora());
		livro.setAnoPublicacao(request.getAnoPublicacao());
		livro.setGenero(request.getGenero());

		// 4. Salvar o livro no banco de dados
		livro = livroRepository.save(livro);

		// 5. Criar a resposta com os dados do livro
		var response = new LivroResponseDto();
		response.setId(livro.getId());
		response.setTitulo(livro.getTitulo());
		response.setEscritor(livro.getEscritor().getNome()); // Acessando o nome do escritor diretamente
		response.setEditora(livro.getEditora());
		response.setAnoPublicacao(livro.getAnoPublicacao());
		response.setGenero(livro.getGenero());

		return response;
	}

	// metodo para listar livros
	public List<LivroResponseDto> listarLivros() {

		// Lógica para listar todos os livros
		List<Livro> livros = livroRepository.findAll();

		List<LivroResponseDto> response = new ArrayList<>();

		for (Livro livro : livros) {
			LivroResponseDto livroResponse = new LivroResponseDto();
			livroResponse.setId(livro.getId());
			livroResponse.setTitulo(livro.getTitulo());
			livroResponse.setEscritor(livro.getEscritor().getNome());
			livroResponse.setEditora(livro.getEditora());
			livroResponse.setAnoPublicacao(livro.getAnoPublicacao());
			livroResponse.setGenero(livro.getGenero());

			response.add(livroResponse);
		}

		return response;

	}

	// metodo para buscar livro por id
	public LivroResponseDto buscarLivroPorNome(String nome) {
		List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(nome);

		if (livros.isEmpty()) {
			throw new IllegalArgumentException("Nenhum livro encontrado com o título fornecido");
		}

		// Pega o primeiro livro encontrado
		Livro livro = livros.get(0); // Pegando o primeiro livro da lista

		// Criar o DTO de resposta
		LivroResponseDto response = new LivroResponseDto();
		response.setId(livro.getId());
		response.setTitulo(livro.getTitulo());
		response.setEscritor(livro.getEscritor().getNome());
		response.setEditora(livro.getEditora());
		response.setAnoPublicacao(livro.getAnoPublicacao());
		response.setGenero(livro.getGenero());

		return response;
	}

	// metodo para atualizar livro
	public LivroAtualizarResponseDto atualizarLivro(Integer id, LivroRequestDto request) {
		// Lógica para atualizar o livro
		Livro livro = livroRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

		livro.setTitulo(request.getTitulo());
		livro.setEditora(request.getEditora());
		livro.setEscritor(escritorRepository.findByNomeContainingIgnoreCase(request.getEscritor()).orElseGet(() -> {
			// Criar um novo escritor se não for encontrado
			Escritor novoEscritor = new Escritor();
			novoEscritor.setNome(request.getEscritor());
			return escritorRepository.save(novoEscritor); // Salvar o novo escritor no banco
		}));
		livro.setAnoPublicacao(request.getAnoPublicacao());
		livro.setGenero(request.getGenero());
		livro = livroRepository.save(livro);
		LivroAtualizarResponseDto response = new LivroAtualizarResponseDto();
		response.setId(livro.getId());
		response.setTitulo(livro.getTitulo());
		response.setEscritor(livro.getEscritor().getNome());
		response.setEditora(livro.getEditora());

		response.setAnoPublicacao(livro.getAnoPublicacao());
		response.setGenero(livro.getGenero());
		response.setMensagem("Livro atualizado com sucesso!");
		
		return response;

	}
	
	// metodo para deletar livro,
	public LivroDeletadoResponseDto deletarLivro(Integer id) {
		Livro livro = livroRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

		livroRepository.delete(livro);
		
		var response = new LivroDeletadoResponseDto();
		response.setMensagem("Livro deletado com sucesso!");
	
		return response;
		
	}

}
