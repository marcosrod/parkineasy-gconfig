package br.com.parkineasy.repository;

import br.com.parkineasy.model.Vaga;

import java.util.List;

public interface VagaRepository {

    List<Vaga> recuperarTodas();

    List<Vaga> recuperarTodasPorTipoESituacaoLivre(Integer tipo);
}
