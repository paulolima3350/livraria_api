package br.com.livraria.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data	
@Entity
@Table(name = "escritores")
public class Escritor {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "nome", length = 100, nullable = false)
	    private String nome;

	    @OneToMany(mappedBy = "escritor")  // Relacionamento OneToMany
	    private List<Livro> livros;  // Lista de livros do escritor
}
