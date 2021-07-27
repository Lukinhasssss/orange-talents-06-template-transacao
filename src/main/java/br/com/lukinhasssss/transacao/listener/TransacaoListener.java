package br.com.lukinhasssss.transacao.listener;

import br.com.lukinhasssss.transacao.dto.TransacaoDto;
import br.com.lukinhasssss.transacao.entities.Compra;
import br.com.lukinhasssss.transacao.repositories.CompraRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransacaoListener {

    private CompraRepository compraRepository;

    public TransacaoListener(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoDto transacaoDto) {
        System.out.println(transacaoDto.toString());
        Compra compra = transacaoDto.converterParaEntidade();

        compraRepository.save(compra);
    }

}
