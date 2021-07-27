package br.com.lukinhasssss.transacao.dto;

import br.com.lukinhasssss.transacao.entities.Compra;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class TransacaoDto {

    private String id;
    private BigDecimal valor;
    private Map<String, String> estabelecimento;
    private Map<String, String> cartao;
    private LocalDateTime efetivadaEm;

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setEstabelecimento(Map<String, String> estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Map<String, String> getCartao() {
        return cartao;
    }

    public void setCartao(Map<String, String> cartao) {
        this.cartao = cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public void setEfetivadaEm(LocalDateTime efetivadaEm) {
        this.efetivadaEm = efetivadaEm;
    }

    public Compra converterParaEntidade() {
        return new Compra(this);
    }

    @Override
    public String toString() {
        return "TransacaoDto{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }
}
