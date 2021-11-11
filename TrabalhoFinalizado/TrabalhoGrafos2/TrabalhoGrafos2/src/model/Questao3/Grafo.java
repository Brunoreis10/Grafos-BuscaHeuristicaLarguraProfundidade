package model.Questao3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class Grafo {

    private Integer quantidadeVertices;
    private Map<Integer, Vertice> vertices = new HashMap<>();
    private Integer quantidadeArestas = 0;
    private Integer totalSequenciaGrau = 0;

    public Grafo(Integer quantidadeVertices) {
        this.quantidadeVertices = quantidadeVertices;
    }

    public Grafo() {
    }

    public Integer getQuantidadeVertices() {
        return quantidadeVertices;
    }

    public Map<Integer, Vertice> getVertices() {
        return vertices;
    }

    public Integer getQuantidadeArestas() {
        return quantidadeArestas;
    }

    public void setQuantidadeArestas(Integer quantidadeArestas) {
        this.quantidadeArestas = quantidadeArestas;
    }

    public Integer getTotalSequenciaGrau() {
        return totalSequenciaGrau;
    }

    public void setTotalSequenciaGrau(Integer totalSequenciaGrau) {
        this.totalSequenciaGrau = totalSequenciaGrau;
    }

    public String getData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getDataSemHora() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
