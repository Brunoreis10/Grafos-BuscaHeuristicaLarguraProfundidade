package model.Questao3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class Vertice {

    private Integer codigo = 0;
    private Integer distanciaOrigem = 0;
    private Integer sequenciaGrau = 0;
    private Integer nivel = 0;

    private final List<Vertice> vertices = new ArrayList<>();
    private Vertice verticePai = null;

    private Situacao situacao = Situacao.BRANCO;

    public Vertice(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Integer getDistanciaOrigem() {
        return distanciaOrigem;
    }

    public void setDistanciaOrigem(Integer distanciaOrigem) {
        this.distanciaOrigem = distanciaOrigem;
    }

    public Vertice getVerticePai() {
        return verticePai;
    }

    public void setVerticePai(Vertice verticePai) {
        this.verticePai = verticePai;
    }

    public Integer getSequenciaGrau() {
        return sequenciaGrau;
    }

    public void setSequenciaGrau(Integer sequenciaGrau) {
        this.sequenciaGrau = sequenciaGrau;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}
