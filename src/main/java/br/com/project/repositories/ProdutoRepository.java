package br.com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
