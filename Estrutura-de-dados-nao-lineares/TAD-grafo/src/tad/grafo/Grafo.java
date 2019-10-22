package tad.grafo;

import java.util.Iterator;
import java.util.Vector;

public class Grafo implements IGrafo {
    private int qtdVertice;
    private Vector vertices;
    private Aresta matrizAdj[][];
    
    public Grafo(){
        qtdVertice = 0;
        vertices = new Vector();
    }
    
    @Override
    public void inserirVertice(Vertice vertice) {
        qtdVertice++;
        vertices.add(vertice);
        Aresta matrizTemp[][] = new Aresta[qtdVertice][qtdVertice];

        for(int i = 0; i < qtdVertice - 1; i++){
            for(int j = 0; j < qtdVertice - 1; j++){
            	matrizTemp[i][j] = matrizAdj[i][j];              
            }
        }
        
        for(int i = 0;i <qtdVertice-1;i++){
        	matrizTemp[qtdVertice-1][i] = matrizTemp[i][qtdVertice-1] = null;          
        }
        
        matrizAdj = matrizTemp;
    }

    @Override
    public void removerVertice(Vertice vertice) {
        qtdVertice--;
        int indice = achaIndice(vertice.getChave());
        vertices.remove(indice);  
        Aresta tempMatrizAdj[][]=new Aresta[qtdVertice][qtdVertice];
        int ff=0,gg;
        for(int f=0; f<qtdVertice+1; f++){
            gg=0;
            for(int g=0;g<qtdVertice+1;g++){
                if(f!=indice && g!=indice){
                  tempMatrizAdj[ff][gg]= matrizAdj[f][g];                  
                  if(g!=indice)
                      gg++;                  
                   }                
            }
            if(f!=indice)
                ff++;
        }
        matrizAdj=tempMatrizAdj;
    }

    @Override
    public Aresta insereAresta(Vertice VerticeUm, Vertice VerticeDois, double valor) {
        Aresta a = new Aresta(VerticeUm, VerticeDois, valor);
        int ind1 = achaIndice(VerticeUm.getChave());
        int ind2 = achaIndice(VerticeDois.getChave());
        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = a;
        return a;
    }

    @Override
    public Aresta insereAresta(Vertice VerticeUm, Vertice VerticeDois) {
        Aresta a = new Aresta(VerticeUm, VerticeDois);
        int ind1 = achaIndice(VerticeUm.getChave());
        int ind2 = achaIndice(VerticeDois.getChave());
        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = a;
        return a;
    }

    @Override
    public void removeAresta(Aresta a) {
        int ind1 = achaIndice(a.getVerticeDestino().getChave());
        int ind2 = achaIndice(a.getVerticeOrigem().getChave());
        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = null;
    }

    @Override
    public Aresta insereArco(Vertice VerticeUm, Vertice VerticeDois, double valor) {
        Aresta A = new Aresta(VerticeUm, VerticeDois, valor, true);
        int ind1 = achaIndice(VerticeUm.getChave());
        int ind2 = achaIndice(VerticeDois.getChave());
        matrizAdj[ind1][ind2] = A; 
        return A;
    }

    @Override
    public Aresta insereArco(Vertice verticeUm, Vertice verticeDois) {
        Aresta a = new Aresta(verticeUm, verticeDois);
        int ind1 = achaIndice(verticeUm.getChave());
        int ind2 = achaIndice(verticeDois.getChave());
        matrizAdj[ind1][ind2] = a;
        return a;
    }

    @Override
    public void removeArco(Aresta aresta) {
        int ind1 = achaIndice(aresta.getVerticeOrigem().getChave());
        int ind2 = achaIndice(aresta.getVerticeDestino().getChave());
        matrizAdj[ind1][ind2] = null;
    }

    @Override
    public int ordem() {
        return qtdVertice;
    }

    @Override
    public Vector Vertice() {
        return vertices;
    } 
    
    private int achaIndice(int chave) {
       Iterator i = vertices.iterator();
       int idx = 0;
       while(i.hasNext()){
           Vertice v = (Vertice)(i.next());
           if(v.getChave() == chave){
               return idx;
           }
           ++idx;
       }
       return -1;
    }
    
    @Override
    public int grau(Vertice vertice) {
        return ArestaIncidentes(vertice).size();
    }
    
    @Override
    public Vector Aresta() {
        Vector v = new Vector();
        for(int i = 0; i< qtdVertice; ++i){
            for(int j = 0; j<qtdVertice; ++j){
                v.add(matrizAdj[i][j]);
            }
        }
        return v;
    }

    @Override
    public Vector ArestaIncidentes(Vertice vertice) {
        Iterator i = Aresta().iterator();
        Vector<Aresta> arestasInc = new Vector();
        
        while(i.hasNext()){
            Aresta aAtual = (Aresta) i.next();
            if(aAtual.getVerticeDestino().getChave() == vertice.getChave()){
                arestasInc.add(aAtual);
            }
        }
        return arestasInc;
    }

    @Override
    public Vector finalVertice(Aresta a) {
        Vector<Vertice> vF = new Vector<>();
        vF.add(a.getVerticeDestino());
        vF.add(a.getVerticeOrigem());
        return vF;
    }

    @Override
    public boolean ÈAdjacente(Vertice v, Vertice w) {
        int ind1 = achaIndice(v.getChave());
        int ind2 = achaIndice(w.getChave());
        return matrizAdj[ind1][ind2] != null;
    }
    
    
    @Override
    public Vertice oposto(Vertice v, Aresta a) throws OpostoError {
        Vertice aDestino = a.getVerticeDestino();
        Vertice aOrigem = a.getVerticeOrigem();
        
        if(aDestino.getChave() == v.getChave()){
            if(ÈAdjacente(aOrigem, v)){
                return aOrigem;
            }
        }
        
        if(aOrigem.getChave() == v.getChave()){
            if(ÈAdjacente(aDestino, v)){
                return aDestino;
            }
        }
        
        return null;
    }
    
    public void mostraVertices() {
        for (int f = 0; f < vertices.size(); f++) {
            System.out.print(vertices.elementAt(f) + ",");
        }
    }

    public void mostraMatriz() {
        for (int f = 0; f < qtdVertice; f++) {
            for (int g = 0; g < qtdVertice; g++) {
            	if(matrizAdj[f][g]!=null) {
            		System.out.print(matrizAdj[f][g].getValor() + " ");
            	} else {
            		System.out.println("null");
            	}
            	
            }
            System.out.println();
        }
    }
    
}
