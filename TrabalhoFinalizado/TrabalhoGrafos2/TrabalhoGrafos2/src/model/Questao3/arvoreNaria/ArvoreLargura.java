package model.Questao3.arvoreNaria;

import model.Questao3.Grafo;
import model.Questao3.Vertice;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class ArvoreLargura implements ArvoreTipo {

    private final Arvore arv = new Arvore();

    public ArvoreLargura() {
    }

    @Override
    public String gerarArvore(Grafo grafo, Integer codigo) {
        Vertice verticeInicial = grafo.getVertices().get(codigo);
        NoArvore raiz = new NoArvore(verticeInicial.getCodigo());
        arv.setRaiz(raiz);

        verticeInicial.getVertices().stream().filter((vert) -> (vert.getVerticePai() != null)).filter((vert) -> (vert.getVerticePai().getCodigo() == verticeInicial.getCodigo())).forEachOrdered((vert) -> {
            addFilho(vert, raiz);
        });
        return arv.toString();
    }

    public void addFilho(Vertice vertice, NoArvore noArvore) {
        NoArvore<Integer> filho = new NoArvore(vertice.getCodigo());
        noArvore.inserirFilho(filho);

        if (vertice.getVertices() == null) {
            return;
        }

        vertice.getVertices().stream().filter((x) -> (x.getVerticePai() != null)).filter((x) -> (x.getVerticePai().getCodigo() == vertice.getCodigo())).forEachOrdered((x) -> {
            addFilho(x, filho);
        });
    }

}
