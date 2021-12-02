package br.com.parkineasy.model;

import br.com.parkineasy.model.enums.TipoVaga;

public class Vaga {
    private String codigoVaga;
    private Integer situacaoVaga;
    private TipoVaga tipoVaga;

    public String getCodigoVaga() {
        return codigoVaga;
    }

    public void setCodigoVaga(String codigoVaga) {
        this.codigoVaga = codigoVaga;
    }

    public Integer getSituacaoVaga() {
        return situacaoVaga;
    }

    public void setSituacaoVaga(Integer situacaoVaga) {
        this.situacaoVaga = situacaoVaga;
    }

    public TipoVaga getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(TipoVaga tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "codigoVaga='" + codigoVaga + '\'' +
                ", situacaoVaga=" + situacaoVaga +
                ", tipoVaga=" + tipoVaga +
                '}';
    }
}
