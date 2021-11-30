package br.com.parkineasy.service.impl;

import br.com.parkineasy.repository.PagamentoRepository;
import br.com.parkineasy.repository.UsoRepository;
import br.com.parkineasy.repository.impl.PagamentoRepositoryImpl;
import br.com.parkineasy.repository.impl.UsoRepositoryImpl;
import br.com.parkineasy.service.PagamentoService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalTime;

public class PagamentoServiceImpl implements PagamentoService {

    private final UsoRepository usoRepository = new UsoRepositoryImpl();
    private final PagamentoRepository pagamentoRepository = new PagamentoRepositoryImpl();
    private final BigDecimal VALOR_HORA = new BigDecimal("5.0");
    private final MathContext PRECISAO = new MathContext(4);

    @Override
    public BigDecimal calcularValorPagamento(Integer codigoTicket) {
        LocalTime horasDeUso = usoRepository.recuperarHorasDeUso(codigoTicket);
        double horas = horasDeUso.getHour() + horasDeUso.getMinute() / 60.0;
        return BigDecimal.valueOf(horas).multiply(VALOR_HORA).round(PRECISAO);
    }

    public Boolean efetuarPagamento(Integer codigoTicket, Integer metodoPagamento) {
        BigDecimal valorTotal = calcularValorPagamento(codigoTicket);

        return pagamentoRepository.salvar(codigoTicket, metodoPagamento, valorTotal);
    }

}

