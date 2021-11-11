package model.Questao3.arvoreNaria;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class NoLista<T> {

    private T info;
    private NoLista proximo;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public void setProximo(NoLista<T> proximo) {
        this.proximo = proximo;
    }

    public NoLista<T> getProximo() {
        return this.proximo;
    }
}
