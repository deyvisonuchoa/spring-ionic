package br.com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
