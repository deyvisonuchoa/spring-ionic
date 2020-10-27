package br.com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
