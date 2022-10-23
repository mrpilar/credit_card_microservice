package com.example.msavaliadorcredito.application;

import com.example.msavaliadorcredito.domain.model.DadosCliente;
import com.example.msavaliadorcredito.domain.model.SituacaoCliente;
import com.example.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
       ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);

       return SituacaoCliente
               .builder()
               .cliente(dadosClienteResponse.getBody())
               .build();
    }
}
