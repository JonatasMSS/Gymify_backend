package com.gymify.gymify.controller;


import com.gymify.gymify.dto.TreinoDTO;
import com.gymify.gymify.exception.RegraNegocioException;
import com.gymify.gymify.model.entity.Treino;
import com.gymify.gymify.model.repository.TreinoRepository;
import com.gymify.gymify.service.TreinoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/treino")
public class TreinoController {
    
    private final TreinoService service;
    private final TreinoRepository repository;

    public TreinoController(TreinoService service, TreinoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("{id}")
    public ResponseEntity getTreino(@PathVariable("id") Long id) {
        Treino treino = service.getTreino(id);
        return ResponseEntity.ok(treino);
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<Treino>> buscarTodosTreinos(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Treino> treinosPaginados = service.buscarTodosTreinosPaginados(pageRequest);

        return ResponseEntity.ok(treinosPaginados);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody TreinoDTO dto) {
        try {
            Treino entidade = converter(dto);

            entidade = service.salvarTreino(entidade);
            return new ResponseEntity(entidade, HttpStatus.CREATED);

        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody TreinoDTO dto) {
        return service.obterTreinoPorId(id).map( entity -> {
            try {
                Treino treino = entity;

                treino.setId(dto.getId());
                treino.setNome(dto.getNome());
                treino.setDescricao(dto.getDescricao());
                treino.setRepeticoes(dto.getRepeticoes());
                treino.setDescanso(dto.getDescanso());
                treino.setPeso(dto.getPeso());


                service.atualizarTreino(treino);
                return ResponseEntity.ok(treino);
            } catch (RegraNegocioException e ) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Treino não encontrado.", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        return service.obterTreinoPorId(id).map(entity -> {
            service.deletarTreino(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity("Treino não encontrado.", HttpStatus.BAD_REQUEST));
    }

    private Treino converter(TreinoDTO dto) {
        Treino treino = new Treino();

        treino.setId(dto.getId());
        treino.setNome(dto.getNome());
        treino.setDescricao(dto.getDescricao());
        treino.setRepeticoes(dto.getRepeticoes());
        treino.setDescanso(dto.getDescanso());
        treino.setPeso(dto.getPeso());
        return treino;

    }
}
