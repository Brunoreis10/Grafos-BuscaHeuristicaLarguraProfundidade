package model.Questao3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import model.Questao3.arvoreNaria.ArvoreProfundidade;
import model.Questao3.arvoreNaria.ArvoreTipo;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class BuscaProfundidade implements BuscaTipoGrafo {

    private Integer tempo = 0;

    private final ArvoreTipo arv = new ArvoreProfundidade();

    public BuscaProfundidade() {
    }

    private void dfsVisitados(Vertice verticeVisitado, FileWriter fw) throws IOException {
        fw.write("VPai:" + verticeVisitado.getCodigo() + " Dist:" + verticeVisitado.getDistanciaOrigem() + "\n");
        fw.flush();
        verticeVisitado.setSituacao(Situacao.CINZA);
        tempo += 1;
        verticeVisitado.setDistanciaOrigem(tempo);
        for (Vertice vertice : verticeVisitado.getVertices()) {
            if (Situacao.BRANCO.equals(vertice.getSituacao())) {
                vertice.setDistanciaOrigem(verticeVisitado.getDistanciaOrigem());
                vertice.setVerticePai(verticeVisitado);
                fw.write("\tVFilho:" + vertice.getCodigo() + " Dist:" + vertice.getDistanciaOrigem() + "\n");
                fw.flush();
                dfsVisitados(vertice, fw);
            }
        }
        tempo += 1;
        verticeVisitado.setSituacao(Situacao.PRETO);
        verticeVisitado.setDistanciaOrigem(tempo);

        fw.write("VPai:" + verticeVisitado.getCodigo() + " Dist:" + verticeVisitado.getDistanciaOrigem() + "\n");
        fw.flush();
    }

    @Override
    public void iniciarGrafo(Grafo grafo) {
        if (grafo != null) {
            grafo.getVertices().values().forEach(vertice -> {
                vertice.setSituacao(Situacao.BRANCO);
                vertice.setVerticePai(null);
                vertice.setDistanciaOrigem(0);
                tempo = 0;
            });
        }
    }

    @Override
    public BigDecimal percorrerGrafo(Grafo grafo, Integer codigo, Boolean percorrerGerarArquivo) {
        try {
            if (percorrerGerarArquivo) {
                BigDecimal tempoInicial = BigDecimal.valueOf(System.nanoTime());
                iniciarGrafo(grafo);
                File fileGeracaoGrafo = new File("geracao_arvore_busca_profundidade_vertice_" + grafo.getVertices().get(codigo).getCodigo() + "_" + grafo.getDataSemHora().replaceAll("/", "_") + ".txt");
                FileWriter fwGeracaoGrafo = new FileWriter(fileGeracaoGrafo);
                fwGeracaoGrafo.write("Arvore Gerada: " + arv.gerarArvore(grafo, codigo) + "\n");
                fwGeracaoGrafo.flush();
                fwGeracaoGrafo.close();
                iniciarGrafo(grafo);
                Vertice verticeInicial = grafo.getVertices().get(codigo);
                if (verticeInicial != null) {
                    File file = new File("busca_profundidade_vertice_" + verticeInicial.getCodigo() + "_" + grafo.getDataSemHora().replaceAll("/", "_") + ".txt");
                    FileWriter fw = new FileWriter(file);

                    if (Situacao.BRANCO.equals(verticeInicial.getSituacao())) {
                        dfsVisitados(verticeInicial, fw);
                    }
                    System.out.println(grafo.getData() + " - Arvore de busca em profundidade gerada em: " + file.getAbsolutePath());
                    fw.flush();
                    fw.close();
                }

                BigDecimal tempoFinal = BigDecimal.valueOf(System.nanoTime());
                BigDecimal total = tempoFinal.subtract(tempoInicial).divide(BigDecimal.valueOf(1000000000), new MathContext(4)).setScale(6, RoundingMode.UP);

                return total;
            } else {
                return percorrerGrafoSemGerarArquivo(grafo, codigo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal percorrerGrafoSemGerarArquivo(Grafo grafo, Integer codigo) {
        try {
            BigDecimal tempoInicial = BigDecimal.valueOf(System.nanoTime());
            iniciarGrafo(grafo);
            Vertice verticeInicial = grafo.getVertices().get(codigo);
            if (verticeInicial != null) {
                if (Situacao.BRANCO.equals(verticeInicial.getSituacao())) {
                    dfsVisitadosSemGerarArquivo(verticeInicial);
                }
            }

            BigDecimal tempoFinal = BigDecimal.valueOf(System.nanoTime());
            BigDecimal total = tempoFinal.subtract(tempoInicial).divide(BigDecimal.valueOf(1000000000), new MathContext(4)).setScale(6, RoundingMode.UP);

            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void dfsVisitadosSemGerarArquivo(Vertice verticeVisitado) throws IOException {
        verticeVisitado.setSituacao(Situacao.CINZA);
        tempo += 1;
        verticeVisitado.setDistanciaOrigem(tempo);
        for (Vertice vertice : verticeVisitado.getVertices()) {
            if (Situacao.BRANCO.equals(vertice.getSituacao())) {
                vertice.setDistanciaOrigem(verticeVisitado.getDistanciaOrigem());
                dfsVisitadosSemGerarArquivo(vertice);
            }
        }
        tempo += 1;
        verticeVisitado.setSituacao(Situacao.PRETO);
        verticeVisitado.setDistanciaOrigem(tempo);
    }
}
