package br.com.livraria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livraria.entities.Escritor;

public interface EscritorRepository extends JpaRepository<Escritor, Integer> {

	Optional<Escritor> findByNome(String nome);

	Optional<Escritor> findByNomeContainingIgnoreCase(String nome); // Método para busca flexível (LIKE)
}
