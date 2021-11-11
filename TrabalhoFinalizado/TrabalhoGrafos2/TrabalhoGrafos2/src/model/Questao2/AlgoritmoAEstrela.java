package model.Questao2;

import java.util.ArrayList;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class AlgoritmoAEstrela {

    private ObjetoAEstrela[][] labirinto;
    private ObjetoAEstrela origem;
    private ObjetoAEstrela destino;
    private final ArrayList<ObjetoAEstrela> listaCaminhos = new ArrayList<>();
    private final ArrayList<ObjetoAEstrela> listaCaminhosBloqueados = new ArrayList<>();
    private final ArrayList<ObjetoAEstrela> listaCaminhosAbertos = new ArrayList<>();
    private final ArrayList<ObjetoAEstrela> listaCaminhosFechados = new ArrayList<>();

    public AlgoritmoAEstrela(ObjetoAEstrela labirinto[][], ObjetoAEstrela origem, ObjetoAEstrela destino) {
        this.labirinto = labirinto;
        this.origem = origem;
        this.destino = destino;
    }

    public boolean inicioAlgoritmo() {
        if (getOrigem() == getDestino()) {
            return true;
        }

        listaCaminhosAbertos.add(getOrigem());
        if (pesquisaCaminhos()) {
            return salvaCaminhoValido();
        }
        return false;
    }

    private boolean pesquisaCaminhos() {
        ObjetoAEstrela atual = listaCaminhosAbertos.get(0);
        for (int i = 1; i < listaCaminhosAbertos.size(); i++) {
            if (atual.getCustoMaxCaminho() > listaCaminhosAbertos.get(i).getCustoMaxCaminho()) {
                atual = listaCaminhosAbertos.get(i);
            }
        }

        listaCaminhosFechados.add(atual);
        listaCaminhosAbertos.remove(atual);

        if (atual == destino) {
            return true;
        }

        verificaObjetoDireita(atual);
        verificaObjetoEsquerda(atual);
        verificaObjetoAbaixo(atual);
        verificaObjetoAcima(atual);

        if (listaCaminhosAbertos.isEmpty()) {
            return false;
        }

        return pesquisaCaminhos();
    }

    private boolean salvaCaminhoValido() {
        ObjetoAEstrela atual = getDestino();

        if (atual == null) {
            return false;
        }

        while (atual != null) {
            listaCaminhos.add(atual);
            atual = atual.getPaiEstrela();
        }
        return true;

    }

    public void adicionarCaminhosBloqueados(ObjetoAEstrela bloqueio) {
        listaCaminhosBloqueados.add(bloqueio);
    }

    private void verificaObjetoDireita(ObjetoAEstrela atual) {
        int direita = atual.getX() + 1;

        if (direita >= getLabirinto()[0].length) {
            return;
        }

        ObjetoAEstrela adjacenteDireta = getLabirinto()[direita][atual.getY()];
        if (!listaCaminhosFechados.contains(adjacenteDireta) && !listaCaminhosBloqueados.contains(adjacenteDireta)) {
            int custoPadrao = atual.getCusto() + 1;
            int custoHeuristica = Math.abs(destino.getX() - adjacenteDireta.getX()) + Math.abs(destino.getY() - adjacenteDireta.getY());

            if (!listaCaminhosAbertos.contains(adjacenteDireta)) {
                adjacenteDireta.setPaiEstrela(atual);
                listaCaminhosAbertos.add(adjacenteDireta);
                adjacenteDireta.setCusto(custoPadrao);
                adjacenteDireta.setCustoHeuristico(custoHeuristica);
            } else {
                if (adjacenteDireta.getCustoHeuristico() > custoHeuristica) {
                    adjacenteDireta.setPaiEstrela(atual);
                    adjacenteDireta.setCusto(custoPadrao);
                    adjacenteDireta.setCustoHeuristico(custoHeuristica);
                }
            }
        }
    }

    private void verificaObjetoEsquerda(ObjetoAEstrela atual) {
        int esquerda = atual.getX() - 1;

        if (esquerda < 0) {
            return;
        }

        ObjetoAEstrela adjacenteEsquerda = getLabirinto()[esquerda][atual.getY()];
        if (!listaCaminhosFechados.contains(adjacenteEsquerda) && !listaCaminhosBloqueados.contains(adjacenteEsquerda)) {
            int custoPadrao = atual.getCusto() + 1;
            int custoHeuristica = Math.abs(getDestino().getX() - adjacenteEsquerda.getX()) + Math.abs(getDestino().getY() - adjacenteEsquerda.getY());

            if (!listaCaminhosAbertos.contains(adjacenteEsquerda)) {
                adjacenteEsquerda.setPaiEstrela(atual);
                listaCaminhosAbertos.add(adjacenteEsquerda);
                adjacenteEsquerda.setCusto(custoPadrao);
                adjacenteEsquerda.setCustoHeuristico(custoHeuristica);
            } else {
                if (adjacenteEsquerda.getCustoHeuristico() > custoHeuristica) {
                    adjacenteEsquerda.setPaiEstrela(atual);
                    adjacenteEsquerda.setCusto(custoPadrao);
                    adjacenteEsquerda.setCustoHeuristico(custoHeuristica);
                }
            }
        }
    }

    private void verificaObjetoAbaixo(ObjetoAEstrela atual) {
        int abaixo = atual.getY() + 1;

        if (abaixo >= getLabirinto().length) {
            return;
        }

        ObjetoAEstrela adjacenteAbaixo = getLabirinto()[atual.getX()][abaixo];
        if (!listaCaminhosFechados.contains(adjacenteAbaixo) && !listaCaminhosBloqueados.contains(adjacenteAbaixo)) {
            int custoPadrao = atual.getCusto() + 1;
            int custoHeuristica = Math.abs(getDestino().getX() - adjacenteAbaixo.getX()) + Math.abs(getDestino().getY() - adjacenteAbaixo.getY());

            if (!listaCaminhosAbertos.contains(adjacenteAbaixo)) {
                adjacenteAbaixo.setPaiEstrela(atual);
                listaCaminhosAbertos.add(adjacenteAbaixo);
                adjacenteAbaixo.setCusto(custoPadrao);
                adjacenteAbaixo.setCustoHeuristico(custoHeuristica);
            } else {
                if (adjacenteAbaixo.getCustoHeuristico() > custoHeuristica) {
                    adjacenteAbaixo.setPaiEstrela(atual);
                    adjacenteAbaixo.setCusto(custoPadrao);
                    adjacenteAbaixo.setCustoHeuristico(custoHeuristica);
                }
            }
        }
    }

    private void verificaObjetoAcima(ObjetoAEstrela atual) {
        int acima = atual.getY() - 1;

        if (acima < 0) {
            return;
        }

        ObjetoAEstrela adjacenteAcima = getLabirinto()[atual.getX()][acima];
        if (!listaCaminhosFechados.contains(adjacenteAcima) && !listaCaminhosBloqueados.contains(adjacenteAcima)) {
            int custoPadrao = atual.getCusto() + 1;
            int custoHeuristica = Math.abs(getDestino().getX() - adjacenteAcima.getX()) + Math.abs(getDestino().getY() - adjacenteAcima.getY());

            if (!listaCaminhosAbertos.contains(adjacenteAcima)) {
                adjacenteAcima.setPaiEstrela(atual);
                listaCaminhosAbertos.add(adjacenteAcima);
                adjacenteAcima.setCusto(custoPadrao);
                adjacenteAcima.setCustoHeuristico(custoHeuristica);
            } else {
                if (adjacenteAcima.getCustoHeuristico() > custoHeuristica) {
                    adjacenteAcima.setPaiEstrela(atual);
                    adjacenteAcima.setCusto(custoPadrao);
                    adjacenteAcima.setCustoHeuristico(custoHeuristica);
                }
            }
        }
    }

    public ObjetoAEstrela[][] getLabirinto() {
        return labirinto;
    }

    public ArrayList<ObjetoAEstrela> getListaCaminhos() {
        return listaCaminhos;
    }

    public ArrayList<ObjetoAEstrela> getListaCaminhosBloqueados() {
        return listaCaminhosBloqueados;
    }

    public void setLabirinto(ObjetoAEstrela[][] labirinto) {
        this.labirinto = labirinto;
    }

    public ObjetoAEstrela getOrigem() {
        return origem;
    }

    public void setOrigem(ObjetoAEstrela origem) {
        this.origem = origem;
    }

    public ObjetoAEstrela getDestino() {
        return destino;
    }

    public void setDestino(ObjetoAEstrela destino) {
        this.destino = destino;
    }
}
