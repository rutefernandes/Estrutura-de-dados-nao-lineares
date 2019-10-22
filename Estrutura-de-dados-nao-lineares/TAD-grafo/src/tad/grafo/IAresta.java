package tad.grafo;
public interface IAresta {       
    public abstract Vertice getVerticeDestino();
    public abstract void setVerticeDestino(Vertice verticeDestino);
    public abstract Vertice getVerticeOrigem();
    public abstract void setVerticeOrigem(Vertice verticeOrigem);
    public abstract boolean ÈDirecionada();
    public abstract void setDirecionada(boolean direcionada);
    public abstract double getValor();
    public abstract void setValor(double valor);
}
