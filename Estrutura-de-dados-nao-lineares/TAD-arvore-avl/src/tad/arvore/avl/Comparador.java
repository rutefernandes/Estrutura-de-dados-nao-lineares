package tad.arvore.avl;

/**
 *
 * @author RUTE
 */
public class Comparador implements IComparador {

    @Override
    public Object compare(Object x, Object y) {
        return (int) x - (int) y;
    }
    
}
