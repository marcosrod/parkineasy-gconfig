package br.com.parkineasy.repository;

import java.sql.ResultSet;

public interface ConsultaBancoDeDados {

    Integer executarAtualizacao(String sql);

    ResultSet executarConsulta(String sql);
}
