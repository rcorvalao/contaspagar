package br.com.rmc.contaspagar.coreapi.controller;

import br.com.rmc.contaspagar.coreapi.dto.ContasPagarDto;
import br.com.rmc.contaspagar.coreapi.dto.ContasPagarListagemDto;
import br.com.rmc.contaspagar.coreapi.service.ContasPagarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/v1/contaspagar")
public class ContasPagarController {

    private ContasPagarService contasPagarService;


    public ContasPagarController(ContasPagarService contasPagarService) {
        this.contasPagarService = contasPagarService;
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<ContasPagarListagemDto>> listarContasPagar() {
        log.info("Listando contas a pagar");

        return ResponseEntity.ok(this.contasPagarService.listarContasPagar());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ContasPagarListagemDto> buscarContasPagar(@PathVariable(value = "id") Long id) {
        log.info("Buscando contas a pagar {}", id);

        return ResponseEntity.ok(this.contasPagarService.buscarContasPagar(id));
    }

    @PostMapping(value = ""
            , consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ContasPagarDto> criarConstaPagar(
            @RequestBody @Valid ContasPagarDto contasPagarDto) {
        log.info("Criar conta a pagar: {}", contasPagarDto);
        return ResponseEntity.ok(this.contasPagarService.criarContaPagar(contasPagarDto));
    }


    @PutMapping(value = "/{id}"
            , consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ContasPagarDto> alterarConstaPagar(@PathVariable(value = "id") Long id,
            @RequestBody @Valid ContasPagarDto contasPagarDto) {
        log.info("Alterar conta a pagar: {}", contasPagarDto);
        return ResponseEntity.ok(this.contasPagarService.alterarContaPagar(id, contasPagarDto));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ContasPagarListagemDto> deletarContasPagar(@PathVariable(value = "id") Long id) {
        log.info("Buscando contas a pagar {}", id);

        return ResponseEntity.ok(this.contasPagarService.deletarContasPagar(id));
    }
    /*
    GET /tickets - Retrieves a list of tickets
    GET /tickets/12 - Retrieves a specific ticket
    POST /tickets - Creates a new ticket
    PUT /tickets/12 - Updates ticket #12
    PATCH /tickets/12 - Partially updates ticket #12
    DELETE /tickets/12 - Deletes ticket #12
    */
}
