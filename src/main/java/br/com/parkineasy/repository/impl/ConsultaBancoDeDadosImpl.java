package br.com.parkineasy.repository.impl;

import br.com.parkineasy.repository.ConsultaBancoDeDados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaBancoDeDadosImpl implements ConsultaBancoDeDados {

    public Integer executarAtualizacao(String sql) {
        Statement statement = ConexaoBancoDeDadosImpl.getInstancia().recuperarStatement();

        try {
            return statement.executeUpdate(sql);
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());

            return null;
        }
    }

    public ResultSet executarConsulta(String sql) {
        Statement statement = ConexaoBancoDeDadosImpl.getInstancia().recuperarStatement();

        try {
            return statement.executeQuery(sql);
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());

            return null;
        }
    }
}
