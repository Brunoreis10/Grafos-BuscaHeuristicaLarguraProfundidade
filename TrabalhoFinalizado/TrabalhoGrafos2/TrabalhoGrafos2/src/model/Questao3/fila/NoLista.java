package model.Questao3.fila;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class NoLista<T> {

    private T info;
    private NoLista<T> proximo;

    public T getInfo() {
        return info;
    }

    public NoLista<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoLista<T> proximo) {
        this.proximo = proximo;
    }

    public void setInfo(T info) {
        this.info = info;
    }

}
