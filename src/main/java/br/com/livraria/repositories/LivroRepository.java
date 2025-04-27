package br.com.livraria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.livraria.entities.Escritor;
import br.com.livraria.entities.Livro;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

	   // JPQL para buscar livros pelo título com LIKE
    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Livro> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

	Optional<Escritor> findById(Long id);



	
	  
	  Optional<Livro> findByTituloAndEscritor_Id(String titulo, Integer escritorId);
}
