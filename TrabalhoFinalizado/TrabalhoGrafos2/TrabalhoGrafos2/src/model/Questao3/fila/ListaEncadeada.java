package model.Questao3.fila;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class ListaEncadeada<T> {

    private NoLista<T> primeiro;
    private NoLista<T> ultimo;

    public ListaEncadeada() {
        primeiro = null;
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
        return primeiro == null;
    }

    public NoLista<T> buscar(T info) {
        NoLista<T> p = primeiro;
        while (p != null) {
            if (p.getInfo() == info) {
                return p;
            }
            p = p.getProximo();
        }
        return null;
    }

    public void retirar(T info) {
        NoLista<T> anterior = null;
        NoLista<T> p = primeiro;

        while (p != null && (!p.getInfo().equals(info))) {
            anterior = p;
            p = p.getProximo();
        }
        if (p != null) {
            if (p.getProximo() == null) {
                ultimo = anterior;
            }
            if (anterior == null) {
                this.primeiro = p.getProximo();
            } else {
                anterior.setProximo(p.getProximo());
            }
        }
    }

    public int obterComprimento() {
        Object aux = getPrimeiro();
        int c = 0;
        while (aux != null) {
            c++;
            aux = ((NoLista<T>) aux).getProximo();
        }
        return c;
    }

    public NoLista<T> obterNo(int idx) {
        int comprimento = obterComprimento();
        NoLista<T> p = getPrimeiro();

        if (idx < 0 || idx > comprimento) {
            throw new IndexOutOfBoundsException();
        } else {
            int c = 0;
            while (idx != c) {
                p = p.getProximo();
                c++;
            }
            return p;
        }
    }

    @Override
    public String toString() {
        NoLista<T> aux = getPrimeiro();
        String valores = "";

        if (obterComprimento() > 0) {
            valores += aux.getInfo();
            aux = aux.getProximo();
            while (aux != null) {
                valores += "," + aux.getInfo();
                aux = aux.getProximo();
            }
        }

        return valores;//retorna a string pronta
    }

    public void inserirNoFinal(T valor) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(valor);
        novo.setProximo(null);
        if (estaVazia()) {
            primeiro = novo;
        } else {
            ultimo.setProximo(novo);
        }
        ultimo = novo;
    }
}
