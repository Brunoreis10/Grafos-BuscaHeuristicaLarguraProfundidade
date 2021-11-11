package model.Questao3;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import model.Questao3.arvoreNaria.ArvoreLargura;
import model.Questao3.arvoreNaria.ArvoreTipo;
import model.Questao3.fila.FilaLista;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class BuscaLargura implements BuscaTipoGrafo {

    private final FilaLista<Vertice> fila = new FilaLista<>();

    private final ArvoreTipo arv = new ArvoreLargura();

    public BuscaLargura() {
    }

    @Override
    public void iniciarGrafo(Grafo grafo) {
        if (grafo != null) {
            grafo.getVertices().values().forEach(vertice -> {
                vertice.setSituacao(Situacao.BRANCO);
                vertice.setDistanciaOrigem(0);
                vertice.setVerticePai(null);
                vertice.setNivel(0);
            });
        }
    }

    @Override
    public BigDecimal percorrerGrafo(Grafo grafo, Integer codigo, Boolean percorrerGerarArquivo) {
        try {
            if (percorrerGerarArquivo) {
                BigDecimal tempoInicial = BigDecimal.valueOf(System.nanoTime());
                iniciarGrafo(grafo);
                Vertice verticeInicial = grafo.getVertices().get(codigo);
                if (verticeInicial != null) {
                    verticeInicial.setSituacao(Situacao.CINZA);
                    fila.inserir(verticeInicial);
                    File file = new File("busca_largura_vertice_" + verticeInicial.getCodigo() + "_" + grafo.getDataSemHora().replaceAll("/", "_") + ".txt");
                    FileWriter fw = new FileWriter(file);
                    while (!fila.estaVazia()) {
                        Vertice verticeEmpilhado = fila.retirar();
                        fw.write("VPai:" + verticeEmpilhado.getCodigo() + " N:" + verticeEmpilhado.getNivel() + "\n");
                        for (Vertice vertice : verticeEmpilhado.getVertices()) {
                            if (Situacao.BRANCO.equals(vertice.getSituacao())) {
                                fila.inserir(vertice);
                                vertice.setSituacao(Situacao.CINZA);
                                vertice.setVerticePai(verticeEmpilhado);
                                vertice.setDistanciaOrigem(verticeEmpilhado.getDistanciaOrigem() + 1);
                                vertice.setNivel(verticeEmpilhado.getNivel() + 1);
                                fw.write("\tVFilho:" + vertice.getCodigo() + " N:" + vertice.getNivel() + "\n");
                                fw.flush();
                            }
                        }
                        verticeEmpilhado.setSituacao(Situacao.PRETO);
                    }

                    System.out.println(grafo.getData() + " - Arvore de busca em largura gerada em: " + file.getAbsolutePath());
                    fw.flush();
                    fw.close();
                }

                File fileGeracaoGrafo = new File("geracao_arvore_busca_largura_vertice_" + grafo.getVertices().get(codigo).getCodigo() + "_" + grafo.getDataSemHora().replaceAll("/", "_") + ".txt");
                FileWriter fwGeracaoGrafo = new FileWriter(fileGeracaoGrafo);
                fwGeracaoGrafo.write("Arvore Gerada: " + arv.gerarArvore(grafo, codigo) + "\n");
                fwGeracaoGrafo.flush();
                fwGeracaoGrafo.close();

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
        BigDecimal tempoInicial = BigDecimal.valueOf(System.nanoTime());
        iniciarGrafo(grafo);
        Vertice verticeInicial = grafo.getVertices().get(codigo);
        if (verticeInicial != null) {
            verticeInicial.setSituacao(Situacao.CINZA);
            fila.inserir(verticeInicial);
            while (!fila.estaVazia()) {
                Vertice verticeEmpilhado = fila.retirar();
                List<Vertice> verticesAdjacentes = verticeEmpilhado.getVertices();
                for (Vertice vertice : verticesAdjacentes) {
                    if (Situacao.BRANCO.equals(vertice.getSituacao())) {
                        fila.inserir(vertice);
                        vertice.setSituacao(Situacao.CINZA);
                        vertice.setVerticePai(verticeEmpilhado);
                        vertice.setDistanciaOrigem(verticeEmpilhado.getDistanciaOrigem() + 1);
                        vertice.setNivel(verticeEmpilhado.getNivel() + 1);
                    }
                }
                verticeEmpilhado.setSituacao(Situacao.PRETO);
            }
        }
        BigDecimal tempoFinal = BigDecimal.valueOf(System.nanoTime());
        BigDecimal total = tempoFinal.subtract(tempoInicial).divide(BigDecimal.valueOf(1000000000), new MathContext(4)).setScale(6, RoundingMode.UP);

        return total;
    }
}
