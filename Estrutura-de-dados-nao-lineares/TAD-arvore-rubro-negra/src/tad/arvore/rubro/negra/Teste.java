package tad.arvore.rubro.negra;

/**
 *
 * @author RUTE
 */
public class Teste {
    public static void main(String[] args) {
        RubroNegra arvore = new RubroNegra();
        /*
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
       
        */
        arvore.adicionar(33);
        arvore.adicionar(14);
        arvore.adicionar(47);
        arvore.adicionar(10);
        arvore.adicionar(20);
        arvore.adicionar(38);
        arvore.adicionar(51);
        arvore.adicionar(5);
        arvore.adicionar(18);
        arvore.adicionar(36);
        arvore.adicionar(39);
        arvore.adicionar(53);
        
        arvore.adicionar(17);
        arvore.adicionar(16);
        arvore.adicionar(15);
        arvore.adicionar(37);
        
        arvore.removerE(47);
        arvore.removerE(51);
        arvore.removerE(37);
        arvore.removerE(36);
        arvore.removerE(39);
        arvore.removerE(38);
        arvore.mostrar();
    }
    
}
