package model.Questao3.arvoreNaria;

import java.util.ArrayList;
import java.util.List;
import model.Questao3.Grafo;
import model.Questao3.Situacao;
import model.Questao3.Vertice;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class ArvoreProfundidade implements ArvoreTipo {

    private final Arvore arv = new Arvore();

    public ArvoreProfundidade() {
    }

    @Override
    public String gerarArvore(Grafo grafo, Integer codigo) {
        Vertice verticeInicial = grafo.getVertices().get(codigo);
        if (verticeInicial != null) {
            verticeInicial.setSituacao(Situacao.CINZA);
        }
        NoArvore raiz = new NoArvore(grafo.getVertices().get(codigo).getCodigo());
        arv.setRaiz(raiz);
        verticeInicial.getVertices().stream().filter((vertice) -> (Situacao.BRANCO.equals(vertice.getSituacao()))).forEachOrdered((vertice) -> {
            addFilho(vertice, raiz);
        });

        List<Vertice> vertices = new ArrayList<>(grafo.getVertices().values());

        for (Vertice vert : vertices) {
            if (Situacao.BRANCO.equals(vert.getSituacao())) {
                return arv.toString() + gerarArvore(grafo, vert.getCodigo());
            }
        }
        return arv.toString();
    }

    public void addFilho(Vertice vertice, NoArvore noArvore) {
        NoArvore<Integer> filho = new NoArvore(vertice.getCodigo());
        noArvore.inserirFilho(filho);
        vertice.setSituacao(Situacao.CINZA);

        if (!vertice.getVertices().isEmpty()) {
            vertice.getVertices().stream().filter((vert) -> (vert.getSituacao().equals(Situacao.BRANCO))).forEachOrdered((vert) -> {
                addFilho(vert, filho);
            });
        }
    }
}
