package br.com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
