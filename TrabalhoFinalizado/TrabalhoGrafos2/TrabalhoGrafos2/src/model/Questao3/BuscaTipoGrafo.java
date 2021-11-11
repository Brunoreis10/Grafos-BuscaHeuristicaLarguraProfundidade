package model.Questao3;

import java.math.BigDecimal;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public interface BuscaTipoGrafo {

    public BigDecimal percorrerGrafo(Grafo grafo, Integer codigo, Boolean percorrerGerarArquivo);

    public void iniciarGrafo(Grafo grafo);
}
