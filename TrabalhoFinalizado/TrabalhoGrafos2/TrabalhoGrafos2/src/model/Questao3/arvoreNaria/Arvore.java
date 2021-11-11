package model.Questao3.arvoreNaria;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class Arvore<T> {

    private NoArvore<T> raiz;

    public Arvore() {
        raiz = null;
    }

    public void setRaiz(NoArvore<T> raiz) {
        this.raiz = raiz;
    }

    public NoArvore<T> getRaiz() {
        return raiz;
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "";
        } else {
            return obterRepresentacaoTextual(raiz);
        }
    }

    private String obterRepresentacaoTextual(NoArvore<T> no) {
        String s = "<";
        s += no.getInfo();
        NoArvore<T> p = no.getPrimeiro();

        while (p != null) {
            s += obterRepresentacaoTextual(p);
            p = p.getProximo();
        }
        s += ">";
        return s;
    }

    public boolean pertence(T info) {
        if (raiz == null) {
            return false;
        } else {
            return pertence(raiz, info);
        }
    }

    private boolean pertence(NoArvore<T> no, T info) {
        if (no.getInfo().equals(info)) {
            return true;
        } else {

            NoArvore<T> p = no.getPrimeiro();

            while (p != null) {
                if (pertence(p, info)) {
                    return true;
                }
                p = p.getProximo();
            }
        }
        return false;
    }

    public int contarNos() {
        if (raiz == null) {
            return 0;
        } else {
            return contarNos(raiz);
        }
    }

    private int contarNos(NoArvore<T> no) {
        if (no == null) {
            return 0;
        } else {
            int cont = 1;
            NoArvore<T> p = no.getPrimeiro();

            while (p != null) {
                cont += contarNos(p);
                p = p.getProximo();
            }
            return cont;
        }
    }
}
