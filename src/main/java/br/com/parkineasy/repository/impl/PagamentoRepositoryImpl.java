package br.com.parkineasy.repository.impl;

import br.com.parkineasy.model.ComprovantePagamento;
import br.com.parkineasy.repository.ConsultaBancoDeDados;
import br.com.parkineasy.repository.PagamentoRepository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class PagamentoRepositoryImpl implements PagamentoRepository {

    private final ConsultaBancoDeDados consultaBancoDeDados = new ConsultaBancoDeDadosImpl();

    @Override
    public Boolean salvar(Integer codigoTicket, Integer metodoPagamento, BigDecimal valorTotal) {
        DateTimeFormatter dataHoraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHoraFormatada = LocalDateTime.now().format(dataHoraFormato);
        Integer consultasASeremRealizadas = 3;
        Integer consultasComSucesso = 0;
        Random random = new Random();
        int comprovante = random.nextInt(1000);

        consultasComSucesso = consultaBancoDeDados.executarAtualizacao(
                "UPDATE pagamento SET data_hora_pagamento = '" + dataHoraFormatada + "'," +
                        " met_pagamento = " + metodoPagamento + "" +
                        " WHERE id_pagamento = " + codigoTicket + "");
        consultasComSucesso += consultaBancoDeDados.executarAtualizacao(
                "UPDATE uso SET data_hora_pagamento = (SELECT data_hora_pagamento FROM pagamento" +
                        " WHERE id_pagamento = " + codigoTicket + "), data_hora_total =" +
                        " timediff(data_hora_pagamento, data_hora_entrada), valor_pago = " + valorTotal + " WHERE " +
                        "id_reserva = " + codigoTicket);
        consultasComSucesso += consultaBancoDeDados.executarAtualizacao(
                "UPDATE pagamento SET comprovante_pagamento = " + comprovante + " where id_pagamento = " + codigoTicket);

        return consultasComSucesso.equals(consultasASeremRealizadas);
    }

    @Override
    public ComprovantePagamento mostrarComprovante(Integer codigoTicket) {
        DateTimeFormatter dataHoraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ResultSet resultSet = consultaBancoDeDados.executarConsulta("select pagamento.data_hora_pagamento," +
                "comprovante_pagamento," +
                "valor_pago from pagamento,uso where pagamento.id_pagamento = " + codigoTicket + " and uso.id_reserva" +
                " = " + codigoTicket);
        try {
            if (resultSet.next()) {
                ComprovantePagamento comprovante = new ComprovantePagamento();
                comprovante.setCodigoTicket(resultSet.getInt("comprovante_pagamento"));
                comprovante.setValorPago(resultSet.getBigDecimal("valor_pago"));
                comprovante.setDataHoraSaida(
                        LocalDateTime.parse(resultSet.getString("data_hora_pagamento"), dataHoraFormato));

                return comprovante;

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }


        return null;
    }

    @Override
    public Boolean conferirComprovanteDePagamento(Integer comprovanteSaida) {
        ResultSet resultSet = consultaBancoDeDados.executarConsulta(
                "SELECT * FROM pagamento WHERE comprovante_pagamento = " + comprovanteSaida);

        try {
            if (resultSet.next()) {
                Integer result = consultaBancoDeDados.executarAtualizacao(
                        "UPDATE vaga SET sit_vaga = 0 WHERE id_vaga = " +
                                "(SELECT id_vaga FROM uso WHERE id_pagamento = (SELECT id_pagamento FROM pagamento " +
                                "WHERE comprovante_pagamento = " + comprovanteSaida + "))");

                result += consultaBancoDeDados.executarAtualizacao("UPDATE uso set data_hora_saida = now() where" +
                        " id_pagamento = (select id_pagamento from pagamento " +
                        " where comprovante_pagamento = " + comprovanteSaida + ");");

                return result == 2;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean conferirTicketEntrada(Integer codigoTicket) {
        ResultSet resultSet =
                consultaBancoDeDados.executarConsulta("SELECT id_reserva from uso where id_reserva = " + codigoTicket);
        try {
            return resultSet.next();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
}
