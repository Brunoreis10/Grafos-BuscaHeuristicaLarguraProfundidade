package model.Questao3.fila;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public interface Fila<T> {

    public void inserir(T valor);

    public boolean estaVazia();

    public T peek();

    public T retirar();

    public void liberar();

}
