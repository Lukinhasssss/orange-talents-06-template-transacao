package br.com.lukinhasssss.transacao.dto;

import br.com.lukinhasssss.transacao.entities.Compra;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraDto {

    private BigDecimal valor;
    private LocalDateTime dataCompra;

    public CompraDto(Compra compra) {
        valor = compra.getValor();
        dataCompra = compra.getEfetivadaEm();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public static Page<CompraDto> convert(Page<Compra> compras) {
        return compras.map(CompraDto::new);
    }
}

