package br.com.project.repositories.mestra;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.mestra.FaseProjeto;
import br.com.project.domain.mestra.Metodologia;

public interface MetodologiaRepository extends JpaRepository<Metodologia, Long>{

    List<Metodologia> findByCodigoContaining(String codigo, Sort sort);
    List<Metodologia> findByDescricaoContaining(String descricao, Sort sort);
    List<Metodologia> findByCodigoContainingAndDescricaoContaining(String codigo, String descricao, Sort sort);
    
}
