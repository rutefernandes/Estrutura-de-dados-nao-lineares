package tad.grafo;

import java.util.Vector;

public class Grafo implements IGrafo {
    private int qtdVertice;
    private Vector Vertice;
    private Aresta matrizAdj[][];
    
    public Grafo(){
        
    }
    
    @Override
    public void inserirVertice(tad.grafo.Vertice Vertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removerVertice(tad.grafo.Vertice Vertice) {
        qtdVertice--;
        int índice=achaIndice(Vertice.getChave());
        Vertice.remove(índice);  // remove o vértice do vector    
        // remove linhas e colunas da matriz de adjacência
        Aresta tempMatrizAdj[][]=new Aresta[qtdVertice][qtdVertice];
        int ff=0,gg;
        for(int f=0;f<qtdVertice+1;f++){
            gg=0;
            for(int g=0;g<qtdVertice+1;g++){
                if(f!=índice && g!=índice){
                  tempMatrizAdj[ff][gg]= matrizAdj[f][g];                  
                  if(g!=índice)
                      gg++;                  
                }                
            }
            if(f!=índice)
                ff++;
        }
        matrizAdj=tempMatrizAdj;
    }

    @Override
    public tad.grafo.Aresta insereAresta(tad.grafo.Vertice VerticeUm, tad.grafo.Vertice VerticeDois, double valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public tad.grafo.Aresta insereAresta(tad.grafo.Vertice VerticeUm, tad.grafo.Vertice VerticeDois) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAresta(tad.grafo.Aresta Aresta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public tad.grafo.Aresta insereArco(tad.grafo.Vertice VerticeUm, tad.grafo.Vertice VerticeDois, double valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public tad.grafo.Aresta insereArco(tad.grafo.Vertice VerticeUm, tad.grafo.Vertice VerticeDois) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeArco(tad.grafo.Aresta Aresta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int grau(tad.grafo.Vertice Vertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ordem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector Vertice() {
        return Vertice;
    }

    @Override
    public Vector Aresta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector ArestaIncidentes(tad.grafo.Vertice vertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector finalVertice(tad.grafo.Aresta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public tad.grafo.Vertice oposto(tad.grafo.Vertice v, tad.grafo.Aresta a) throws OpostoError {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean éAdjacente(tad.grafo.Vertice v, tad.grafo.Vertice w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void substituir(tad.grafo.Vertice v, double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void substituir(tad.grafo.Aresta a, double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserirArestaDirecionada(tad.grafo.Vertice v, tad.grafo.Vertice w, double o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ehDirecionada(tad.grafo.Aresta aresta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int achaIndice(int chave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
