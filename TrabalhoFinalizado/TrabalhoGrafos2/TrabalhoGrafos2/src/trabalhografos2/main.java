package trabalhografos2;

import model.Questao3.BuscaLargura;
import model.Questao3.BuscaProfundidade;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.Questao2.AlgoritmoAEstrela;
import model.Questao2.ObjetoAEstrela;
import model.Questao3.GerarGrafo;
import model.Questao3.GerarRelatorioGrafo;
import model.Questao3.Grafo;
import model.Questao3.BuscaTipoGrafo;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class main {

    public static GerarRelatorioGrafo gerarRelatorio = new GerarRelatorioGrafo();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Informar o número da questão a ser utilizada: '2' - Questão número 2, '3' - Questão número 3 ou '0' para finalizar a execução");
        Scanner scanner = new Scanner(System.in);
        Integer codigoQuestao = scanner.nextInt();
        if (codigoQuestao == 2) {
            ObjetoAEstrela[][] labirinto = null;
            ObjetoAEstrela origem = null;
            ObjetoAEstrela destino = null;
            ArrayList<ObjetoAEstrela> caminhosBloqueados = new ArrayList();
            labirinto = new ObjetoAEstrela[7][7];
            for (int i = 0; i < labirinto.length; i++) {
                for (int y = 0; y < labirinto[i].length; y++) {
                    labirinto[i][y] = new ObjetoAEstrela(i, y);
                }
            }
            caminhosBloqueados.add(labirinto[1][6]);
            caminhosBloqueados.add(labirinto[2][6]);
            caminhosBloqueados.add(labirinto[1][4]);
            caminhosBloqueados.add(labirinto[2][4]);
            caminhosBloqueados.add(labirinto[1][2]);
            caminhosBloqueados.add(labirinto[2][2]);
            caminhosBloqueados.add(labirinto[5][1]);
            caminhosBloqueados.add(labirinto[4][1]);
            caminhosBloqueados.add(labirinto[4][2]);
            caminhosBloqueados.add(labirinto[4][3]);
            caminhosBloqueados.add(labirinto[4][4]);
            caminhosBloqueados.add(labirinto[5][4]);
            origem = labirinto[0][6];
            destino = labirinto[5][2];
            AlgoritmoAEstrela estrela = new AlgoritmoAEstrela(labirinto, origem, destino);
            caminhosBloqueados.forEach((obj) -> {
                estrela.adicionarCaminhosBloqueados(obj);
            });
            if (!estrela.inicioAlgoritmo()) {
                System.out.println("Ocorreu um problema ao definir um caminho pelo labirinto");
            }
            imprimirCaminholabirinto(origem, destino, estrela, labirinto);

        } else if (codigoQuestao == 3) {
            Integer codigoUsuario = -1;
            Integer representacaoGrafo = 0;
            String filePath = "";
            Grafo grafo = new Grafo();
            File file = null;
            scanner = new Scanner(System.in);
            System.out.println(grafo.getData() + " - Informar caminho do arquivo a ser carregado: ");

            try {
                file = new File(scanner.next());
                grafo = gerarGrafo(file);
                gerarRelatorio.gerarRelatorioGrafoDados(grafo);

                while (codigoUsuario != 0) {

                    System.out.println(grafo.getData() + " - Informar modo de busca no grafo: '1' - Busca em largura, '2' - Busca em profundidade ou '0' para finalizar a execução");
                    codigoUsuario = scanner.nextInt();

                    if (codigoUsuario > 0) {
                        System.out.println(grafo.getData() + " - Informar vertice para inicio da busca:");
                        Integer codigoBusca = scanner.nextInt();

                        if (codigoUsuario == 1) {
                            System.out.println(grafo.getData() + " - Iniciando busca em largura no grafo a partir do vértice: " + codigoBusca);
                            executarBusca(grafo, codigoBusca, new BuscaLargura(), true);
                            System.out.println(grafo.getData() + " - Finalizado a busca no grafo");
                            System.out.println(grafo.getData() + " - Iniciando execução de busca em largura em vértices aleatórios");
                            filePath = gerarRelatorio.gerarRelatorioComparativo(new BuscaLargura(), grafo, file.getName(), representacaoGrafo, "BuscaLargura");
                            System.out.println(grafo.getData() + " - Gerado comparativo busca em largura: " + filePath);
                        } else if (codigoUsuario == 2) {
                            System.out.println(grafo.getData() + " - Iniciando busca em profundidade no grafo a partir do vértice: " + codigoBusca);
                            executarBusca(grafo, codigoBusca, new BuscaProfundidade(), true);
                            System.out.println(grafo.getData() + " - Finalizado a busca no grafo");
                            System.out.println(grafo.getData() + " - Iniciando execução de busca em profundidade em vértices aleatórios");
                            filePath = gerarRelatorio.gerarRelatorioComparativo(new BuscaProfundidade(), grafo, file.getName(), representacaoGrafo, "BuscaProfundidade");
                            System.out.println(grafo.getData() + " - Gerado comparativo busca em profundidade: " + filePath);
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void executarBusca(Grafo grafo, Integer codigoBusca, BuscaTipoGrafo busca, Boolean percorrerGerarArquivo) {
        busca.percorrerGrafo(grafo, codigoBusca, percorrerGerarArquivo);
    }

    private static Grafo gerarGrafo(File file) throws Exception {
        try {
            Grafo grafo = new Grafo();

            Scanner scanner = new Scanner(file);

            System.out.println(grafo.getData() + " - Iniciando a geração do grafo com o arquivo: " + file.getAbsolutePath());
            GerarGrafo gerarGrafo = new GerarGrafo();
            grafo = gerarGrafo.construirGrafo(scanner.nextLine());
            System.out.println(grafo.getData() + " - Finalizado a geração do grafo");

            System.out.println(grafo.getData() + " - Populando o grafo");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                gerarGrafo.popularGrafo(grafo, line);
            }

            System.out.println(grafo.getData() + " - Grafo populado");

            return grafo;
        } catch (Exception e) {
            throw new Exception("Houve um erro ao gerar o grafo");
        }
    }

    public static void imprimirCaminholabirinto(ObjetoAEstrela origem, ObjetoAEstrela destino, AlgoritmoAEstrela estrela, ObjetoAEstrela[][] labirinto) {
        System.out.println("Gerando o caminho do labirinto com base no algoritmo A*");
        for (int i = 0; i < labirinto.length; i++) {
            System.out.println("");
            for (int j = 0; j < labirinto[i].length; j++) {
                if (origem.getX() == j && origem.getY() == i) {
                    System.out.print("[ O ]");
                } else if (destino.getX() == j && destino.getY() == i) {
                    System.out.print("[ D ]");
                } else {
                    boolean caminhoValido = false;
                    for (int z = 0; z < estrela.getListaCaminhos().size(); z++) {
                        if (estrela.getListaCaminhos().get(z).getX() == j && estrela.getListaCaminhos().get(z).getY() == i) {
                            System.out.print("[ · ]");
                            caminhoValido = true;
                            break;
                        }
                    }
                    boolean caminhoBloqueado = false;
                    for (int z = 0; z < estrela.getListaCaminhosBloqueados().size(); z++) {
                        if (estrela.getListaCaminhosBloqueados().get(z).getX() == j && estrela.getListaCaminhosBloqueados().get(z).getY() == i) {
                            System.out.print("[ x ]");
                            caminhoBloqueado = true;
                            break;
                        }
                    }
                    if (!caminhoValido && !caminhoBloqueado) {
                        System.out.print("[   ]");
                    }
                }

            }
        }
        System.out.println("\n\n" + "Realizado a busca do labirinto com base no algoritmo A*");
    }
}
