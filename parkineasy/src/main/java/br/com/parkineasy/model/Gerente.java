package br.com.parkineasy.model;

import java.time.YearMonth;


public class Gerente {

    private YearMonth mesAno;
    private String codigoVaga;

    public YearMonth getMesAno() {
        return mesAno;
    }

    public void setMesAno(YearMonth mesAno) {
        this.mesAno = mesAno;
    }

    public String getCodigoVaga() {
        return codigoVaga;
    }

    public void setCodigoVaga(String codigoVaga) {
        this.codigoVaga = codigoVaga;
    }
}
