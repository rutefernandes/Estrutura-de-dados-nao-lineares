package tad.grafo;
public interface IVertice {
    //private int chave;
    //private double valor;
    //public Vertice(int chave, double valor) 
    public abstract int getChave();
    public abstract void setChave(int chave);
    public abstract double getValor();
    public abstract void setValor(double valor);
    
    public abstract boolean ehDirecionada();
    public abstract void setEhDirecionada(boolean isDirected);
    public abstract void setVerticeDestino(Vertice v);
    public abstract Vertice getVerticeDestino();
    public abstract void setVerticeOrigem(Vertice v);  
    public abstract Vertice getVerticeOrigem();
}
