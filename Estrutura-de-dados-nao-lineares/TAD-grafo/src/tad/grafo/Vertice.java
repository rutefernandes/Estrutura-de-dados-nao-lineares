package tad.grafo;
public class Vertice implements IVertice{
    private int chave;
    private double valor;
    
    public Vertice(int chave, double valor) {
        this.chave = chave;
        this.valor = valor;
    }
    
    @Override
    public int getChave() {
        return chave;
    }

    @Override
    public void setChave(int chave) {
        this.chave = chave;
    }

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }
}
