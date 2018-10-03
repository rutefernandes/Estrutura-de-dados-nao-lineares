package tad.arvore.rubro.negra;

/**
 *
 * @author RUTE
 */
public class Teste {
    public static void main(String[] args) {
        RubroNegra arvore = new RubroNegra();
        arvore.adicionar(2);
        No a = arvore.buscar(2);
        arvore.adicionar(1);
        No b = arvore.buscar(1);
        arvore.adicionar(4);
        No c = arvore.buscar(4);
        arvore.adicionar(5);
        No d = arvore.buscar(5);
        arvore.adicionar(9);
        No e = arvore.buscar(9);
        arvore.adicionar(3);
        No f = arvore.buscar(3);
        arvore.adicionar(6);
        No g = arvore.buscar(6);
        arvore.adicionar(7);
        No h = arvore.buscar(6);
        arvore.mostrar();
    }
    
}
