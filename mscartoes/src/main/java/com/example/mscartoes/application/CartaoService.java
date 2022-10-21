package com.example.mscartoes.application;

import com.example.mscartoes.domain.Cartao;
import com.example.mscartoes.infra.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);;
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }
}
