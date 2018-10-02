package tad.arvore.rubro.negra;

/**
 *
 * @author RUTE
 */
public class Comparator implements IComparator   {
    @Override
    public Object compare(Object x, Object y) {
        return (int) x - (int) y;
    }
}
