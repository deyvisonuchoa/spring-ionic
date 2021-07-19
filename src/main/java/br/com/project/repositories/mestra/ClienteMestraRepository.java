package br.com.project.repositories.mestra;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.mestra.ClienteMestra;
import br.com.project.domain.mestra.FaseProjeto;

public interface ClienteMestraRepository extends JpaRepository<ClienteMestra, Long>{

    List<ClienteMestra> findByCodigoContaining(String codigo, Sort sort);
    List<ClienteMestra> findByDescricaoContaining(String descricao, Sort sort);
    List<ClienteMestra> findByCodigoContainingAndDescricaoContaining(String codigo, String descricao, Sort sort);
    
}
