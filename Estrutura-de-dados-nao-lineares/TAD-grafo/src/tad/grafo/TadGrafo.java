package tad.grafo;

import java.util.Vector;

/**
 *
 * @author RUTE
 */
public class TadGrafo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	 Grafo grafo = new Grafo();
         Vertice v1 = new Vertice(1, 20);
         Vertice v2 = new Vertice(2, 20);
         Vertice v3 = new Vertice(3, 20);
         Vertice v4 = new Vertice(4, 20);
         
         grafo.inserirVertice(v1);
         grafo.inserirVertice(v2);
         grafo.inserirVertice(v3);
         grafo.inserirVertice(v4);
         grafo.insereAresta(v1, v2, 1);
         grafo.insereAresta(v1, v3, 2);
         grafo.mostraMatriz();
       //  System.out.println(grafo.grau(v4));
         
        /* Vector<Aresta> arestas = grafo.ArestaIncidentes(v1);
         for(int i = 0; i < arestas.size(); i++) {
             System.out.println(arestas.get(i));
         }*/
     }
    }
   

