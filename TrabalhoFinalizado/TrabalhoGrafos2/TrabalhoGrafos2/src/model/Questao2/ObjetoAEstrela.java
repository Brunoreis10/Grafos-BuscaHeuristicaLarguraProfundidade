package model.Questao2;

/**
 *
 * @author Bruno Henrique Wiedemann Reis e Bárbara Alessandra Maas
 */
public class ObjetoAEstrela {

    private int x = 0;

    private int y = 0;

    private int custo = 0;

    private int custoHeuristico = Integer.MAX_VALUE;

    private ObjetoAEstrela paiEstrela = null;

    public ObjetoAEstrela(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getCustoMaxCaminho() {
        return custo + custoHeuristico;
    }

    public ObjetoAEstrela getPaiEstrela() {
        return paiEstrela;
    }

    public void setPaiEstrela(ObjetoAEstrela paiEstrela) {
        this.paiEstrela = paiEstrela;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getCustoHeuristico() {
        return custoHeuristico;
    }

    public void setCustoHeuristico(int custoHeuristico) {
        this.custoHeuristico = custoHeuristico;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
