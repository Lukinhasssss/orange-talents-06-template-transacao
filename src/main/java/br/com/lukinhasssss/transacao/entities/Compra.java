package br.com.lukinhasssss.transacao.entities;

import br.com.lukinhasssss.transacao.dto.TransacaoDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private BigDecimal valor;

    private String idCartao;

    private LocalDateTime efetivadaEm;

    public Compra() {}

    public Compra(TransacaoDto transacao) {
        valor = transacao.getValor();
        idCartao = transacao.getCartao().get("id");
        efetivadaEm = transacao.getEfetivadaEm();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
