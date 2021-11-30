package br.com.parkineasy.repository;

import java.time.LocalTime;

public interface UsoRepository {

    LocalTime recuperarHorasDeUso(Integer codigoTicket);
}
