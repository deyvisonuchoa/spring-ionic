package br.com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
