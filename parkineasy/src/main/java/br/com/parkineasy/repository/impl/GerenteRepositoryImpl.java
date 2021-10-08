package br.com.parkineasy.repository.impl;

import br.com.parkineasy.model.Entrada;
import br.com.parkineasy.model.Relatorio;
import br.com.parkineasy.repository.GerenteRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GerenteRepositoryImpl implements GerenteRepository {

    private final ConsultaBancoDeDadosImpl consultaBancoDeDadosImpl = new ConsultaBancoDeDadosImpl();

    @Override
    public Boolean validarGerente(String username, String senha) {
        ResultSet resultSet = consultaBancoDeDadosImpl.executarConsulta(
                "SELECT * FROM gerente" +
                        " WHERE username_gerente = '" + username + "' AND password_gerente = '" + senha + "'");

        try {
            return resultSet.next();
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());

            return false;
        }
    }

    @Override
    public List<Relatorio> gerarRelatorio(YearMonth mesAno) {
        int mes = mesAno.getMonth().getValue();
        int ano = mesAno.getYear();
        ResultSet resultSet = consultaBancoDeDadosImpl.executarConsulta(
                "SELECT * FROM uso WHERE MONTH(data_hora_saida) = " + mes + " AND YEAR(data_hora_saida) = " + ano);
        List<Relatorio> relatorios = new ArrayList<>();
        DateTimeFormatter dataHoraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            while (resultSet.next()) {
                Relatorio relatorio = new Relatorio();

                relatorio.setCodigoTicket(resultSet.getInt("id_reserva"));
                relatorio.setCodigoVaga(resultSet.getString("id_vaga"));
                relatorio.setCodigoComprovante(resultSet.getInt("id_pagamento"));
                relatorio.setDataHoraEntrada(LocalDateTime.parse(resultSet.getString("data_hora_entrada"),
                        dataHoraFormato));
                relatorio.setDataHoraSaida(LocalDateTime.parse(resultSet.getString("data_hora_saida"),
                        dataHoraFormato));
                relatorio.setDataHoraPagamento(LocalDateTime.parse(resultSet.getString("data_hora_pagamento"),
                        dataHoraFormato));
                relatorio.setTotalHoras(resultSet.getTime("data_hora_total").toLocalTime());
                relatorio.setValorPago(resultSet.getBigDecimal("valor_pago"));

                relatorios.add(relatorio);
            }

            return relatorios;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());

            return null;
        }
    }

    @Override
    public Entrada recuperarPorCodigoVaga(String codigoVaga) {
        DateTimeFormatter dataHoraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ResultSet resultSet = consultaBancoDeDadosImpl.executarConsulta("select id_reserva,data_hora_entrada,id_vaga " +
                "FROM uso  where id_vaga = \"" + codigoVaga + "\" and data_hora_pagamento IS NULL;");

        try {
            if (resultSet.next()) {
                ResultSet resultSetEntrada = consultaBancoDeDadosImpl.executarConsulta("select id_reserva," +
                        "data_hora_entrada,id_vaga " +
                        "FROM uso  where id_vaga = \"" + codigoVaga + "\" and data_hora_pagamento IS NULL;");
                if (resultSetEntrada.next()) {
                    Entrada entrada = new Entrada();

                    entrada.setCodigoTicket(resultSetEntrada.getInt("id_reserva"));
                    entrada.setCodigoVaga(resultSetEntrada.getString("id_vaga"));
                    entrada.setDataHoraEntrada(
                            LocalDateTime.parse(resultSetEntrada.getString("data_hora_entrada"), dataHoraFormato));

                    return entrada;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
