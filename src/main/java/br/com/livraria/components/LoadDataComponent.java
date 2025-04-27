package br.com.livraria.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.livraria.dtos.LivroRequestDto;
import br.com.livraria.entities.Escritor;
import br.com.livraria.repositories.EscritorRepository;
import br.com.livraria.repositories.LivroRepository;
import br.com.livraria.services.LivroService;

@Component
public class LoadDataComponent implements ApplicationRunner {

    @Autowired
    private LivroService livroService;

    @Autowired
    private EscritorRepository escritorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Inserir escritores fictícios (com verificação de duplicação)
        inserirEscritorSeNaoExistir("Gabriel García Márquez");
        inserirEscritorSeNaoExistir("J.K. Rowling");
        inserirEscritorSeNaoExistir("George Orwell");
        inserirEscritorSeNaoExistir("Haruki Murakami");
        inserirEscritorSeNaoExistir("F. Scott Fitzgerald");

        // Inserir livros fictícios (com verificação de duplicação)
        inserirLivroSeNaoExistir("Cem Anos de Solidão", "Gabriel García Márquez", "Editora Fictícia 1", "1967", "Realismo Mágico");
        inserirLivroSeNaoExistir("Harry Potter e a Pedra Filosofal", "J.K. Rowling", "Editora Fictícia 2", "1997", "Fantasia");
        inserirLivroSeNaoExistir("1984", "George Orwell", "Editora Fictícia 3", "1949", "Distopia");
        inserirLivroSeNaoExistir("Kafka à Beira-Mar", "Haruki Murakami", "Editora Fictícia 4", "2002", "Ficção Literária");
        inserirLivroSeNaoExistir("O Grande Gatsby", "F. Scott Fitzgerald", "Editora Fictícia 5", "1925", "Romance");

        // Mensagem para confirmar que os dados foram carregados
        System.out.println("5 escritores e 5 livros fictícios foram inseridos com sucesso!");
    }

    // Método para inserir escritor se não existir
    private void inserirEscritorSeNaoExistir(String nome) {
        // Verificar se o escritor já existe
        if (escritorRepository.findByNome(nome).isEmpty()) {
            Escritor escritor = new Escritor();
            escritor.setNome(nome);
            escritorRepository.save(escritor); // Salvar o escritor
        }
    }

    private void inserirLivroSeNaoExistir(String titulo, String nomeEscritor, String editora, String anoPublicacao, String genero) {
        // Consultar o escritor pelo nome
        Escritor escritor = escritorRepository.findByNome(nomeEscritor)
                .orElseThrow(() -> new IllegalArgumentException("Escritor não encontrado"));

        // Verificar se o livro já existe pelo título e ID do escritor
        if (livroRepository.findByTituloAndEscritor_Id(titulo, escritor.getId()).isEmpty()) {
            LivroRequestDto livroRequestDto = new LivroRequestDto();
            livroRequestDto.setTitulo(titulo);
            livroRequestDto.setEscritor(nomeEscritor);  // O nome será passado para o DTO, mas o ID será usado para buscar no banco
            livroRequestDto.setEditora(editora);
            livroRequestDto.setAnoPublicacao(anoPublicacao);
            livroRequestDto.setGenero(genero);

            // Chamar o serviço para cadastrar o livro
            livroService.cadastrarLivro(livroRequestDto);
        }
    }
}
