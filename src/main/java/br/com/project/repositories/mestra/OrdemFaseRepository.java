package br.com.project.repositories.mestra;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.domain.mestra.FaseProjeto;
import br.com.project.domain.mestra.OrdemFase;

public interface OrdemFaseRepository extends JpaRepository<OrdemFase, Long>{
    
    
    
}
