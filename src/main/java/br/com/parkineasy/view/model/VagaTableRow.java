package br.com.parkineasy.view.model;

import br.com.parkineasy.model.Vaga;
import br.com.parkineasy.model.enums.TipoVaga;

public class VagaTableRow {

    private String codigoVaga;
    private String situacaoVaga;
    private TipoVaga tipoVaga;

    public VagaTableRow(Vaga vaga) {
        this.codigoVaga = vaga.getCodigoVaga();
        this.situacaoVaga = vaga.getSituacaoVaga() == 0 ? "LIVRE" : "OCUPADA";
        this.tipoVaga = vaga.getTipoVaga();
    }

    public String getCodigoVaga() {
        return codigoVaga;
    }

    public void setCodigoVaga(String codigoVaga) {
        this.codigoVaga = codigoVaga;
    }

    public String getSituacaoVaga() {
        return situacaoVaga;
    }

    public void setSituacaoVaga(String situacaoVaga) {
        this.situacaoVaga = situacaoVaga;
    }

    public TipoVaga getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(TipoVaga tipoVaga) {
        this.tipoVaga = tipoVaga;
    }
}
