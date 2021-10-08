package br.com.parkineasy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Relatorio {

    private Integer codigoTicket;
    private String codigoVaga;
    private Integer codigoComprovante;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private LocalDateTime dataHoraPagamento;
    private LocalTime totalHoras;
    private BigDecimal valorPago;

    public Integer getCodigoTicket() {
        return codigoTicket;
    }

    public void setCodigoTicket(Integer codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public String getCodigoVaga() {
        return codigoVaga;
    }

    public void setCodigoVaga(String codigoVaga) {
        this.codigoVaga = codigoVaga;
    }

    public Integer getCodigoComprovante() {
        return codigoComprovante;
    }

    public void setCodigoComprovante(Integer codigoComprovante) {
        this.codigoComprovante = codigoComprovante;
    }

    public LocalDate getDataEntrada() {
        return LocalDate.from(dataHoraEntrada);
    }

    public LocalTime getHoraEntrada() {
        return LocalTime.from(dataHoraEntrada);
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public LocalDate getDataSaida() {
        return LocalDate.from(dataHoraSaida);
    }

    public LocalTime getHoraSaida() {
        return LocalTime.from(dataHoraSaida);
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public LocalDate getDataPagamento() {
        return LocalDate.from(dataHoraPagamento);
    }

    public LocalTime getHoraPagamento() {
        return LocalTime.from(dataHoraPagamento);
    }

    public void setDataHoraPagamento(LocalDateTime dataHoraPagamento) {
        this.dataHoraPagamento = dataHoraPagamento;
    }

    public LocalTime getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(LocalTime totalHoras) {
        this.totalHoras = totalHoras;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }
}
