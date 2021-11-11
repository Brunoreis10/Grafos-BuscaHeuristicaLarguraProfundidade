package model.Questao3.fila;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class FilaLista<T> implements Fila<T> {

    private final ListaEncadeada<T> lista;

    public FilaLista() {
        lista = new ListaEncadeada<>();
    }

    @Override
    public void inserir(T valor) {
        lista.inserirNoFinal(valor);
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public T peek() {
        return lista.getPrimeiro().getInfo();
    }

    @Override
    public T retirar() {
        T valor = peek();
        lista.retirar(valor);
        return valor;
    }

    @Override
    public void liberar() {
        while (!estaVazia()) {
            retirar();
        }
    }

}
