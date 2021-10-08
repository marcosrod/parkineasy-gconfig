package br.com.parkineasy.repository;

import br.com.parkineasy.model.Entrada;
import br.com.parkineasy.model.Relatorio;

import java.sql.SQLException;
import java.time.YearMonth;
import java.util.List;

public interface GerenteRepository {

    Boolean validarGerente(String username, String senha) throws SQLException;

    List<Relatorio> gerarRelatorio(YearMonth mesAno);

    Entrada recuperarPorCodigoVaga(String codigoVaga);
}
