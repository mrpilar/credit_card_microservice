package com.example.mscartoes.application;

import com.example.mscartoes.domain.ClienteCartao;
import com.example.mscartoes.infra.repository.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {

    @Autowired
    ClienteCartaoRepository clienteCartaoRepository;

    public List<ClienteCartao> listClienteCartaoByCpf(String cpf) {
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
