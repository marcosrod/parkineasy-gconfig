package br.com.parkineasy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ComprovantePagamento {

    private Integer codigoTicket;
    private LocalDateTime dataHoraSaida;
    private BigDecimal valorPago;

    public Integer getCodigoTicket() {
        return codigoTicket;
    }

    public void setCodigoTicket(Integer codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public String toString() {
        return "Codigo do Comprovante = " + codigoTicket +
                "\n" +
                "Data do Pagamento = " + LocalDate.from(dataHoraSaida) +
                "\n" +
                "Horário do Pagamento = " + LocalTime.from(dataHoraSaida) +
                "\n" +
                "Valor Total Pago = " + valorPago;
    }
}
