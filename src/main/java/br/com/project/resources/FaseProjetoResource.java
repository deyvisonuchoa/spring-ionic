package br.com.project.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import br.com.project.domain.Categoria;
import br.com.project.domain.FaseProjeto;
import br.com.project.dto.CategoriaDTO;
import br.com.project.dto.FaseProjetoDTO;
import br.com.project.repositories.FaseProjetoRepository;

@RestController
@RequestMapping(value = "/fases")
public class FaseProjetoResource {
    
    @Autowired
    FaseProjetoRepository faseRepo;
    
//    @GetMapping
//    public ResponseEntity<Page<FaseProjeto>> getFases(@PageableDefault(size = 8)Pageable page){
//        return ResponseEntity.ok().body(faseRepo.findAll(page));
//    }
    
    @GetMapping
    public ResponseEntity<List<FaseProjeto>> getFases(@RequestParam(value="codigo", required = false) String codigo,
                                                      @RequestParam(value="descricao", required = false) String descricao,
                                                      @RequestParam(value="orderBy", required = true) String orderBy){
        if(codigo != null && descricao != null)
            return ResponseEntity.ok().body(faseRepo.findByCodigoContainingAndDescricaoContaining(codigo, descricao, Sort.by(Sort.Direction.ASC, orderBy)));
        else if(codigo !=null && descricao == null)
            return ResponseEntity.ok().body(faseRepo.findByCodigoContaining(codigo, Sort.by(Sort.Direction.ASC, orderBy)));
        else if(descricao !=null && codigo == null)
            return ResponseEntity.ok().body(faseRepo.findByDescricaoContaining(descricao, Sort.by(Sort.Direction.ASC, orderBy)));
        else
            return ResponseEntity.ok().body(faseRepo.findAll(Sort.by(Sort.Direction.ASC, orderBy)));
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody FaseProjetoDTO objDto){
        FaseProjeto obj = new FaseProjeto(null, objDto.getCodigo(), objDto.getDescricao());
        obj = faseRepo.save(obj);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();     
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody FaseProjetoDTO objDto){
        FaseProjeto obj = faseRepo.findById(id).orElseThrow();
        obj.setCodigo(objDto.getCodigo());
        obj.setDescricao(objDto.getDescricao());
        faseRepo.save(obj);
        return ResponseEntity.noContent().build();      
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        faseRepo.deleteById(id);
        return ResponseEntity.noContent().build();      
    }

}
