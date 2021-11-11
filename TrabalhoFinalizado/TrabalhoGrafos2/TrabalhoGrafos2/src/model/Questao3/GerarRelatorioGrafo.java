package model.Questao3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class GerarRelatorioGrafo {

    public void gerarRelatorioGrafoDados(Grafo grafo) throws IOException {
        File file = new File("dados_grafo.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write("Número de vértices: " + grafo.getQuantidadeVertices() + "\n");
        fw.write("Número de arestas: " + grafo.getQuantidadeArestas() + "\n");
        fw.write("Sequência de grau: " + grafo.getTotalSequenciaGrau() + "\n");

        System.out.println(grafo.getData() + " - Gerado acompanhamento com os dados gerais do grafo: " + file.getAbsolutePath());
        fw.flush();
        fw.close();
    }

    public String gerarRelatorioComparativo(BuscaTipoGrafo busca, Grafo grafo, String nomeArquivo, Integer representacaoGrafo, String tipoBusca) {
        Random random = new Random();
        StringBuilder sbLargura = new StringBuilder();
        boolean deveAddNovoArquivo = false;
        boolean devePularLinha = true;
        String campoBusca = "";
        try {
            File file = new File("comparativo_busca_" + tipoBusca + ".csv");
            if (!file.exists()) {
                file.createNewFile();
                deveAddNovoArquivo = true;
                devePularLinha = false;
            }
            FileWriter fw = new FileWriter(file, true);

            if ("BuscaLargura".equalsIgnoreCase(tipoBusca)) {
                campoBusca = "BFS";
            } else if ("BuscaProfundidade".equalsIgnoreCase(tipoBusca)) {
                campoBusca = "DFS";
            }

            if (deveAddNovoArquivo) {
                fw.write(gerarCabecalhoComparativo(campoBusca));
            }

            if (devePularLinha) {
                fw.write("\r\n" + nomeArquivo.concat(";"));

            } else {
                fw.write(nomeArquivo.concat(";"));
            }

            BigDecimal totalTempo = BigDecimal.ZERO;

            Integer contadorBusca = 1;
            while (contadorBusca <= 10) {
                Integer codigoVertice = random.nextInt(grafo.getQuantidadeVertices());
                if (codigoVertice > 0) {
                    BigDecimal tempo = busca.percorrerGrafo(grafo, codigoVertice, false);
                    sbLargura.append("V").append(codigoVertice).append(" - ").append(tempo).append("s").append(";");
                    contadorBusca++;
                    totalTempo = totalTempo.add(tempo);
                }
            }
            contadorBusca--;
            BigDecimal media = totalTempo.divide(BigDecimal.valueOf(contadorBusca), new MathContext(4)).setScale(6, RoundingMode.UP);

            sbLargura.append(media).append("s");

            fw.write(sbLargura.toString());
            fw.flush();
            fw.close();

            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String gerarCabecalhoComparativo(String tipoBusca) {
        StringBuilder sbCabecalho = new StringBuilder();
        sbCabecalho.append(tipoBusca).append(";");

        Integer numeroCabecalho = 1;
        while (numeroCabecalho <= 10) {
            sbCabecalho.append("T").append(numeroCabecalho).append(";");
            numeroCabecalho++;
        }
        sbCabecalho.append("Media").append("\n");

        return sbCabecalho.toString();
    }
}
