package com.example.mscartoes.infra.mqueue;

import com.example.mscartoes.domain.Cartao;
import com.example.mscartoes.domain.ClienteCartao;
import com.example.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import com.example.mscartoes.infra.repository.CartaoRepository;
import com.example.mscartoes.infra.repository.ClienteCartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmissaoCartaoSubscriber {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);
        } catch (Exception e) {
            log.error("Erro ao receber solicitação de emissão de cartão: {}", e.getMessage());
        }
    }
}
