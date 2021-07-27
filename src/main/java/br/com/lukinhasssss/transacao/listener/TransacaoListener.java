package br.com.lukinhasssss.transacao.listener;

import br.com.lukinhasssss.transacao.dto.TransacaoDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransacaoListener {

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoDto transacaoDto) {
        System.out.println(transacaoDto.toString());
    }

}
