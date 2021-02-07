package br.com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
