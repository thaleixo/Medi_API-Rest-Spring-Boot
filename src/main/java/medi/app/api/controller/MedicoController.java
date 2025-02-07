package medi.app.api.controller;


import jakarta.validation.Valid;
import medi.app.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("medicos")
@RestController
public class MedicoController {

    @Autowired
    private MedicoRepository repository;


    @Transactional
    @PostMapping
    public ResponseEntity cadastrarMedico(@RequestBody @Valid Dadosmedico dados, UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosMedicoDetalhados(medico));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listarMedicos(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity Detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosMedicoDetalhados(medico));

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok( new DadosMedicoDetalhados(medico));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.deletar();

        return ResponseEntity.noContent().build();
    }


}
