package br.com.parkineasy.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Entrada {

    private Integer codigoTicket;
    private LocalDateTime dataHoraEntrada;
    private String codigoVaga;

    public Integer getCodigoTicket() {
        return codigoTicket;
    }

    public void setCodigoTicket(Integer codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public String getCodigoVaga() {
        return codigoVaga;
    }

    public void setCodigoVaga(String codigoVaga) {
        this.codigoVaga = codigoVaga;
    }

    @Override
    public String toString() {
        return "Código do Ticket: " + codigoTicket +
                "\n" +
                "Código da Vaga: " + codigoVaga +
                "\n" +
                "Data de Entrada: " + LocalDate.from(dataHoraEntrada) +
                "\n" +
                "Horário de Entrada: " + LocalTime.from(dataHoraEntrada);
    }
}

