package tad.grafo;
public class Aresta implements IAresta{
    private Vertice verticeOrigem;
    private Vertice verticeDestino;
    private double valor;
    private boolean direcionada;   
    
    public Aresta(Vertice verticeOrigem, Vertice verticeDestino){
        this.verticeDestino = verticeDestino;
        this.verticeOrigem = verticeOrigem;
    }
    
    public Aresta(Vertice verticeOrigem, Vertice verticeDestino,double valor){
        this.verticeDestino = verticeDestino;
        this.verticeOrigem = verticeOrigem;
        this.valor = valor;
    }
    
    public Aresta(Vertice verticeOrigem, Vertice verticeDestino,double valor, boolean direcionada){
        this.verticeDestino = verticeDestino;
        this.verticeOrigem = verticeOrigem;
        this.valor = valor;
        this.direcionada = direcionada;
    }      
    
    @Override
    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    @Override
    public void setVerticeDestino(Vertice verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    @Override
    public Vertice getVerticeOrigem() {
        return this.verticeOrigem;
    }

    @Override
    public void setVerticeOrigem(Vertice verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }

    @Override
    public boolean ÈDirecionada() {
        return direcionada;
    }

    @Override
    public void setDirecionada(boolean direcionada) {
        this.direcionada = direcionada;
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
