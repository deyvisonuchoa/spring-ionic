package br.com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
