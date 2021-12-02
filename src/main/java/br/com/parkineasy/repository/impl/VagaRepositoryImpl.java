package br.com.parkineasy.repository.impl;

import br.com.parkineasy.model.Vaga;
import br.com.parkineasy.model.enums.TipoVaga;
import br.com.parkineasy.repository.ConsultaBancoDeDados;
import br.com.parkineasy.repository.VagaRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VagaRepositoryImpl implements VagaRepository {
    private final ConsultaBancoDeDados consultaBancoDeDados = new ConsultaBancoDeDadosImpl();

    @Override
    public List<Vaga> recuperarTodas() {
        ResultSet resultSet = consultaBancoDeDados.executarConsulta("SELECT * FROM vaga");
        List<Vaga> vagas = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Vaga vaga = new Vaga();
                vaga.setCodigoVaga(resultSet.getString("id_vaga"));
                vaga.setSituacaoVaga(resultSet.getInt("sit_vaga"));
                vaga.setTipoVaga(TipoVaga.values()[resultSet.getInt("tip_vaga") - 1]);
                vagas.add(vaga);
            }
            return vagas;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return null;
        }
    }

    @Override
    public List<Vaga> recuperarTodasPorTipoESituacaoLivre(Integer tipo) {
        ResultSet resultSet = consultaBancoDeDados.executarConsulta(
                "SELECT * FROM vaga WHERE tip_vaga ='" + tipo + "' and sit_vaga = 0");
        List<Vaga> vagas = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Vaga vaga = new Vaga();
                vaga.setCodigoVaga(resultSet.getString("id_vaga"));
                vaga.setSituacaoVaga(resultSet.getInt("sit_vaga"));
                vaga.setTipoVaga(TipoVaga.values()[resultSet.getInt("tip_vaga") - 1]);
                vagas.add(vaga);
            }
            return vagas;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return null;
        }
    }
}
