package model.Questao3;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class GerarGrafo {

    private Integer sequenciaVertice = 0;

    public GerarGrafo() {
        sequenciaVertice = 1;
    }

    public Grafo construirGrafo(String numero) throws Exception {
        Integer quantidade = Integer.parseInt(numero);
        if (quantidade >= 0) {
            Grafo grafo = new Grafo(quantidade);
            while (sequenciaVertice <= quantidade) {
                Vertice vertice = criarVertice(sequenciaVertice);
                grafo.getVertices().put(vertice.getCodigo(), vertice);
                sequenciaVertice++;
            }
            return grafo;
        } else {
            throw new Exception("Quantidade de vértices inválida");
        }
    }

    public void popularGrafo(Grafo grafo, String line) throws Exception {
        String[] split = line.split(" ");
        if (line != null && split.length == 2) {
            Integer codigoVertice1 = Integer.parseInt(split[0]);
            Integer codigoVertice2 = Integer.parseInt(split[1]);

            Vertice vertice1 = grafo.getVertices().get(codigoVertice1);
            Vertice vertice2 = grafo.getVertices().get(codigoVertice2);

            vertice1.getVertices().add(vertice2);
            vertice1.setSequenciaGrau(vertice1.getSequenciaGrau() + 1);

            vertice2.getVertices().add(vertice1);
            vertice2.setSequenciaGrau(vertice2.getSequenciaGrau() + 1);

            grafo.setQuantidadeArestas(grafo.getQuantidadeArestas() + 1);
            grafo.setTotalSequenciaGrau(grafo.getTotalSequenciaGrau() + 2);

        } else {
            throw new Exception("Quantidade de vértices inválida");
        }
    }

    public Vertice criarVertice(Integer codigo) {
        Vertice vertice = new Vertice(codigo);
        return vertice;
    }

}
