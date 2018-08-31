
package tad.arvore.avl;

import java.util.Iterator;

/**
 *
 * @author rute
 */
public interface IarvoreAVL {
    /* Métodos genéricos */
    public int size();
    public boolean isEmpty();
    public int height();
    /** Retorna um iterator com os elementos armazenados na árvore(preOrder)*/
    public Iterator elements();
    /** Retorna um iterator com as posições (nós) da árvore (inOrder)*/
    public Iterator nos();

    /* Métodos de acesso*/
    public NoAVL root();
    public NoAVL parent(NoAVL no);
    public Iterator children(NoAVL no);

    /* Métodos de consulta */
    public boolean isInternal(NoAVL no);
    public boolean isExternal(NoAVL no);
    public boolean isRoot(NoAVL no);
    public int depth(NoAVL no);   

    public int replace(NoAVL no, int o);
    
   /* Métodos da arvore binaria */
    public NoAVL leftChild(NoAVL no);;
    public NoAVL rightChild(NoAVL no);;
    public boolean hasLeft(NoAVL no);
    public boolean hasRight(NoAVL no);
    
    public void adicionar(int o);
    public NoAVL buscar(int posicao);
    public NoAVL buscar(int posicao, NoAVL raiz);
    public NoAVL remover(NoAVL no);
    public void mostrar();
    
    /* Métodos da árvore AVL */
    public void rotacaoSEsquerda(NoAVL no);
    public void rotacaoSDireta(NoAVL no);
    public void rotacaaoDDireita(NoAVL no);
    public void rotacaoDEsquerda(NoAVL no);
    public void atualizarFB(NoAVL no, int tipo, int nofb);
    
}
