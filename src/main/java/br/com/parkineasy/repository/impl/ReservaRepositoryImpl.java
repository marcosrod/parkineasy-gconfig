package br.com.parkineasy.repository.impl;

import br.com.parkineasy.model.Entrada;
import br.com.parkineasy.repository.ConsultaBancoDeDados;
import br.com.parkineasy.repository.ReservaRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservaRepositoryImpl implements ReservaRepository {

    private final ConsultaBancoDeDados consultaBancoDeDados = new ConsultaBancoDeDadosImpl();

    @Override
    public Boolean salvar(String codigoVaga) {
        DateTimeFormatter dataHoraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHoraFormatada = LocalDateTime.now().format(dataHoraFormato);
        Integer consultasASeremRealizadas = 5;
        Integer atualizacoesComSucesso = 0;

        atualizacoesComSucesso = consultaBancoDeDados.executarAtualizacao(
                "INSERT INTO reserva(data_hora_entrada) VALUES ('" + dataHoraFormatada + "')");
        atualizacoesComSucesso += consultaBancoDeDados.executarAtualizacao("INSERT INTO pagamento() VALUES ()");
        atualizacoesComSucesso += consultaBancoDeDados.executarAtualizacao(
                "INSERT INTO uso(id_reserva, id_vaga, data_hora_entrada)" +
                        " SELECT id_reserva, \"" + codigoVaga + "\", data_hora_entrada FROM reserva" +
                        " WHERE data_hora_entrada = '" + dataHoraFormatada + "'");
        atualizacoesComSucesso += consultaBancoDeDados.executarAtualizacao(
                "UPDATE uso SET id_pagamento = id_reserva WHERE data_hora_entrada = '" + dataHoraFormatada + "'");
        atualizacoesComSucesso += consultaBancoDeDados.executarAtualizacao(
                "UPDATE vaga SET sit_vaga = 1 WHERE id_vaga = \"" + codigoVaga + "\"");

        return atualizacoesComSucesso.equals(consultasASeremRealizadas);
    }

    @Override
    public Entrada recuperarUltimaEntrada() {
        DateTimeFormatter dataHoraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ResultSet resultSet = consultaBancoDeDados.executarConsulta("SELECT MAX(id_reserva) AS id_reserva FROM uso");

        try {
            if (resultSet.next()) {
                ResultSet resultSetEntrada = consultaBancoDeDados.executarConsulta(
                        "SELECT id_reserva, id_vaga, data_hora_entrada FROM uso" +
                                " WHERE id_reserva = " + resultSet.getInt("id_reserva"));

                if (resultSetEntrada.next()) {
                    Entrada entrada = new Entrada();

                    entrada.setCodigoTicket(resultSetEntrada.getInt("id_reserva"));
                    entrada.setCodigoVaga(resultSetEntrada.getString("id_vaga"));
                    entrada.setDataHoraEntrada(
                            LocalDateTime.parse(resultSetEntrada.getString("data_hora_entrada"), dataHoraFormato));

                    return entrada;
                }
            }
            return null;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());

            return null;
        }
    }
}
