package br.com.parkineasy.repository;

import br.com.parkineasy.model.Entrada;

public interface ReservaRepository {
    Boolean salvar(String codigoVaga);

    Entrada recuperarUltimaEntrada();
}
