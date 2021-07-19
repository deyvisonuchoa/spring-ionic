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

import br.com.project.domain.mestra.ClienteMestra;
import br.com.project.domain.mestra.FaseProjeto;
import br.com.project.dto.FaseProjetoDTO;
import br.com.project.repositories.mestra.ClienteMestraRepository;

@RestController
@RequestMapping(value = "/controle/clientes")
public class ClienteMestraResource {
    
    @Autowired
    ClienteMestraRepository clienteRepo;
    
//    @GetMapping
//    public ResponseEntity<Page<FaseProjeto>> getFases(@PageableDefault(size = 8)Pageable page){
//        return ResponseEntity.ok().body(faseRepo.findAll(page));
//    }
    
    @GetMapping
    public ResponseEntity<List<ClienteMestra>> getFases(@RequestParam(value="codigo", required = false) String codigo,
                                                      @RequestParam(value="descricao", required = false) String descricao,
                                                      @RequestParam(value="orderBy", required = true) String orderBy){
        if(codigo != null && descricao != null)
            return ResponseEntity.ok().body(clienteRepo.findByCodigoContainingAndDescricaoContaining(codigo, descricao, Sort.by(Sort.Direction.ASC, orderBy)));
        else if(codigo !=null && descricao == null)
            return ResponseEntity.ok().body(clienteRepo.findByCodigoContaining(codigo, Sort.by(Sort.Direction.ASC, orderBy)));
        else if(descricao !=null && codigo == null)
            return ResponseEntity.ok().body(clienteRepo.findByDescricaoContaining(descricao, Sort.by(Sort.Direction.ASC, orderBy)));
        else
            return ResponseEntity.ok().body(clienteRepo.findAll(Sort.by(Sort.Direction.ASC, orderBy)));
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody FaseProjetoDTO objDto){
        ClienteMestra obj = new ClienteMestra(null, objDto.getCodigo(), objDto.getDescricao());
        obj = clienteRepo.save(obj);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();     
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody FaseProjetoDTO objDto){
        ClienteMestra obj = clienteRepo.findById(id).orElseThrow();
        obj.setCodigo(objDto.getCodigo());
        obj.setDescricao(objDto.getDescricao());
        clienteRepo.save(obj);
        return ResponseEntity.noContent().build();      
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clienteRepo.deleteById(id);
        return ResponseEntity.noContent().build();      
    }

}
