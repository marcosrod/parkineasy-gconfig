package br.com.parkineasy.service;

import java.math.BigDecimal;

public interface PagamentoService {

    BigDecimal calcularValorPagamento(Integer codigoTicket);

    Boolean efetuarPagamento(Integer codigoTicket, Integer metodoPagamento);

}

