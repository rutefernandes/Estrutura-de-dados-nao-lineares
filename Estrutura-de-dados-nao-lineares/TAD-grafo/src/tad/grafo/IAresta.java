package tad.grafo;
public interface IAresta {
    //private Vertice verticeOrigem;
    //private Vertice verticeDestino;
    //private double valor;
    //private boolean direcionada;   
    //public Aresta(Vertice verticeOrigem, Vertice verticeDestino) 
    //public Aresta(Vertice verticeOrigem, Vertice verticeDestino,double valor)
    //public Aresta(Vertice verticeOrigem, Vertice verticeDestino,double valor, boolean direcionada)        
    public abstract Vertice getVerticeDestino();
    public abstract void setVerticeDestino(Vertice verticeDestino);
    public abstract Vertice getVerticeOrigem();
    public abstract void setVerticeOrigem(Vertice verticeOrigem);
    public abstract boolean éDirecionada();
    public abstract void setDirecionada(boolean direcionada);
    public abstract double getValor();
    public abstract void setValor(double valor);
}
