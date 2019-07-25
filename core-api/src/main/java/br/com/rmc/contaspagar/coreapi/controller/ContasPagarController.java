package br.com.rmc.contaspagar.coreapi.controller;

import br.com.rmc.contaspagar.coreapi.dto.ContasPagarDto;
import br.com.rmc.contaspagar.coreapi.dto.ContasPagarListagemDto;
import br.com.rmc.contaspagar.coreapi.service.ContasPagarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class ContasPagarController {

    private ContasPagarService contasPagarService;


    public ContasPagarController(ContasPagarService contasPagarService) {
        this.contasPagarService = contasPagarService;
    }

    @GetMapping(value = "/v1/contaspagar", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<ContasPagarListagemDto>> buscarContasPagar() {
        log.info("Listando contas a pagar");

        return ResponseEntity.ok(this.contasPagarService.listarContasPagar());
    }

    @PostMapping(value = "/v1/contaspagar"
            , consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ContasPagarDto> criarConstaPagar(
            @RequestBody @Valid ContasPagarDto contasPagarDto) {
        log.info("Criar conta a pagar: {}", contasPagarDto);
        return ResponseEntity.ok(this.contasPagarService.criarContaPagar(contasPagarDto));
    }

}
