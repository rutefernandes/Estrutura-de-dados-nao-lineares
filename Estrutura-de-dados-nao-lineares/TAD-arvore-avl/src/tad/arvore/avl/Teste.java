package tad.arvore.avl;

/**
 *
 * @author RUTE
 */
public class Teste {

    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
           arvore.adicionar(8);
           arvore.adicionar(9);
           arvore.adicionar(10);
           arvore.adicionar(11);
           arvore.adicionar(7);
           arvore.adicionar(6);
           arvore.remover(arvore.buscar(6));
           arvore.remover(arvore.buscar(8));
           arvore.remover(arvore.buscar(7));
        arvore.mostrar();
    }
    
}
