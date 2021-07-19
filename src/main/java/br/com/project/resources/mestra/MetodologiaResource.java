package br.com.project.resources.mestra;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.project.domain.mestra.Metodologia;
import br.com.project.dto.FaseProjetoDTO;
import br.com.project.repositories.mestra.MetodologiaRepository;

@RestController
@RequestMapping(value = "/metodologias")
public class MetodologiaResource {
    
    @Autowired
    MetodologiaRepository repo;
    
    @GetMapping
    public ResponseEntity<List<Metodologia>> find(@RequestParam(value="codigo", required = false) String codigo,
                                                      @RequestParam(value="descricao", required = false) String descricao,
                                                      @RequestParam(value="orderBy", required = true) String orderBy){
        if(codigo != null && descricao != null)
            return ResponseEntity.ok().body(repo.findByCodigoContainingAndDescricaoContaining(codigo, descricao, Sort.by(Sort.Direction.ASC, orderBy)));
        else if(codigo !=null && descricao == null)
            return ResponseEntity.ok().body(repo.findByCodigoContaining(codigo, Sort.by(Sort.Direction.ASC, orderBy)));
        else if(descricao !=null && codigo == null)
            return ResponseEntity.ok().body(repo.findByDescricaoContaining(descricao, Sort.by(Sort.Direction.ASC, orderBy)));
        else
            return ResponseEntity.ok().body(repo.findAll(Sort.by(Sort.Direction.ASC, orderBy)));
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody FaseProjetoDTO objDto){
        Metodologia obj = new Metodologia(null, objDto.getCodigo(), objDto.getDescricao());
        obj = repo.save(obj);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();     
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody FaseProjetoDTO objDto){
        Metodologia obj = repo.findById(id).orElseThrow();
        obj.setCodigo(objDto.getCodigo());
        obj.setDescricao(objDto.getDescricao());
        repo.save(obj);
        return ResponseEntity.noContent().build();      
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        repo.deleteById(id);
        return ResponseEntity.noContent().build();      
    }

}
