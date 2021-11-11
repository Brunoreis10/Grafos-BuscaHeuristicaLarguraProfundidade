package model.Questao3.arvoreNaria;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class ListaEncadeada<T> {

    private NoLista primeiro;

    ListaEncadeada() {
        this.primeiro = null;
    }

    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    public void inserir(T info) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(info);
        novo.setProximo(primeiro);
        this.primeiro = novo;
    }

    public boolean estaVazia() {
        return this.primeiro == null;
    }

    public NoLista<T> buscar(T v) {
        NoLista<T> p = this.primeiro;
        while (p != null) {
            if (p.getInfo().equals(v)) {
                return p;
            }
            p = p.getProximo();
        }
        return null;
    }

    public void retirar(T v) {
        NoLista<T> anterior = null;
        NoLista<T> p = primeiro;

        while (p != null && !(p.getInfo().equals(v))) {
            anterior = p;
            p = p.getProximo();

        }

        if (p != null) {
            if (p == primeiro) {
                primeiro = primeiro.getProximo();
            } else {
                anterior.setProximo(p.getProximo());
            }
        }
    }

    public int obterComprimento() {
        int comprimento = 0;
        NoLista<T> p = primeiro;

        while (p != null) {
            comprimento++;
            p.getProximo();
        }

        return comprimento;
    }

    public NoLista<T> obterNo(int idx) {
        if (idx < 0) {
            throw new IndexOutOfBoundsException();
        }

        NoLista<T> p = getPrimeiro();
        while ((p != null) && (idx > 0)) {
            idx--;
            p = p.getProximo();
        }

        if (p == null) {
            throw new IndexOutOfBoundsException();
        }

        return p;
    }

    @Override
    public String toString() {
        String resultado = "";
        NoLista<T> p = primeiro;

        while (p != null) {

            if (p != primeiro) {
                resultado += ",";
            }

            resultado += p.getInfo().toString();
            p.getProximo();
        }

        return resultado;
    }
}
