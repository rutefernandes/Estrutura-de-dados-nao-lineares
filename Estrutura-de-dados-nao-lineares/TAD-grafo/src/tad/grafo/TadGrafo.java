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
    	 Grafo g = new Grafo();
         Vertice v1 = new Vertice(1, 20);
         Vertice v2 = new Vertice(2, 20);
         Vertice v3 = new Vertice(3, 20);
         Vertice v4 = new Vertice(4, 20);
         g.inserirVertice(v1);
         g.inserirVertice(v2);
         g.inserirVertice(v3);
         g.inserirVertice(v4);
         g.insereAresta(v1, v2, 1);
         g.insereAresta(v1, v3, 2);
     //    g.mostraMatriz();
         System.out.println(g.grau(v4));
         Vector<Aresta> arestas = g.ArestaIncidentes(v1);
         for(int i = 0; i < arestas.size(); i++) {
             System.out.println(arestas.get(i));
         }
     }
    }
   

