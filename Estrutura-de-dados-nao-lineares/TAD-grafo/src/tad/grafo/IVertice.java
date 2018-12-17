package tad.grafo;
public interface IVertice {
    //private int chave;
    //private double valor;
    //public Vertice(int chave, double valor) 
    public abstract int getChave();
    public abstract void setChave(int chave);
    public abstract double getValor();
    public abstract void setValor(double valor);
}
