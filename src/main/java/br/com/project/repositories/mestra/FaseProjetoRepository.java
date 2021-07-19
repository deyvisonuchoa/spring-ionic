package br.com.project.repositories.mestra;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.mestra.FaseProjeto;

public interface FaseProjetoRepository extends JpaRepository<FaseProjeto, Long>{

    List<FaseProjeto> findByCodigoContaining(String codigo, Sort sort);
    List<FaseProjeto> findByDescricaoContaining(String descricao, Sort sort);
    List<FaseProjeto> findByCodigoContainingAndDescricaoContaining(String codigo, String descricao, Sort sort);
    
}
