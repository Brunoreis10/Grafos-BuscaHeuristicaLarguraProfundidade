package model.Questao3.arvoreNaria;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class NoArvore<T> {

    private T info;
    private NoArvore<T> primeiro;
    private NoArvore<T> proximo;

    public NoArvore(T info) {
        this.info = info;
        primeiro = null;
        proximo = null;
    }

    public T getInfo() {
        return info;
    }

    public void inserirFilho(NoArvore<T> sa) {
        sa.proximo = primeiro;
        primeiro = sa;
    }

    public NoArvore<T> getPrimeiro() {
        return primeiro;
    }

    public NoArvore<T> getProximo() {
        return proximo;
    }
}
