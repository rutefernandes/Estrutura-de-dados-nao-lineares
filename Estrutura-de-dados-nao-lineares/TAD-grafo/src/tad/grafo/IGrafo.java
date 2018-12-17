package tad.grafo;
import java.util.Vector;

public interface IGrafo {
    //private int qtdVertice;
    //private Vector Vertice;
    //private Aresta matrizAdj[][];
    //public GrafoSimples()
    
    //Grafo Simples
    public abstract void inserirVertice(Vertice Vertice);
    public abstract void removerVertice(Vertice Vertice);
    public abstract Aresta insereAresta(Vertice VerticeUm, Vertice VerticeDois, 
            double valor);
    public abstract Aresta insereAresta(Vertice VerticeUm, Vertice VerticeDois);
    public abstract void removeAresta(Aresta Aresta);
    public abstract Aresta insereArco(Vertice VerticeUm, Vertice VerticeDois,
            double valor);
    public abstract Aresta insereArco(Vertice VerticeUm, Vertice VerticeDois);
    public abstract void removeArco(Aresta Aresta);
    public abstract int grau(Vertice Vertice);
    public abstract int ordem();
    public abstract Vector Vertice();
    public abstract Vector Aresta();
    public abstract Vector ArestaIncidentes(Vertice vertice);
    public abstract Vector finalVertice(Aresta a);
    public abstract Vertice oposto(Vertice v, Aresta a) throws OpostoError;
    public abstract boolean éAdjacente(Vertice v, Vertice w);
        
    // Grafo Direcionado 
    public abstract void substituir(Vertice v, double x);
    public abstract void substituir(Aresta a, double x); 
    public abstract void inserirArestaDirecionada(Vertice v, Vertice w, double o);
    public boolean ehDirecionada(Aresta aresta);
}
