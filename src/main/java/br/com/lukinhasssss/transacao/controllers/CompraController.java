package br.com.lukinhasssss.transacao.controllers;

import br.com.lukinhasssss.transacao.dto.CompraDto;
import br.com.lukinhasssss.transacao.entities.Compra;
import br.com.lukinhasssss.transacao.repositories.CompraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private CompraRepository compraRepository;

    public CompraController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTransacoes(@PathVariable String id, @PageableDefault(sort = "efetivadaEm", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Compra> compras = compraRepository.findAllByIdCartao(id, pageable);

        if (compras.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Este cartão não possuí nenhuma transação!"));

        return ResponseEntity.ok().body(CompraDto.convert(compras));
//        return ResponseEntity.ok().body(compras.stream().map(CompraDto::new).collect(Collectors.toList()));
    }

}
