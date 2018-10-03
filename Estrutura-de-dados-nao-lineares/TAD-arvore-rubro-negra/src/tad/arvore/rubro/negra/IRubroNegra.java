package tad.arvore.rubro.negra;

import java.util.Iterator;

/**
 *
 * @author RUTE
 */
public interface IRubroNegra {
    /* Métodos genéricos */
    public int size();
    public boolean isEmpty();
    public int height();
    /** Retorna um iterator com os elementos armazenados na árvore(preOrder)*/
    public Iterator elements();
    /** Retorna um iterator com as posições (nós) da árvore (inOrder)*/
    public Iterator nos();

    /* Métodos de acesso*/
    public No root();
    public No parent(No no);
    public Iterator children(No no);

    /* Métodos de consulta */
    public boolean isInternal(No no);
    public boolean isExternal(No no);
    public boolean isRoot(No no);
    public int depth(No no);   

    public int replace(No no, int o);
    
   /* Métodos de arvore binaria */
    public No leftChild(No no);;
    public No rightChild(No no);;
    public boolean hasLeft(No no);
    public boolean hasRight(No no);
    
    public void adicionar(int o);
    public No buscar(int posicao);
    public No buscar(int posicao, No raiz);
    public No remover(No no);
    public void mostrar();
    
    /* Métodos da arvore rubro negra */
    public int alturaNegra();
    public void rotacaoEsquerda(No no);
    public void rotacaoDireta(No no);
    public void rotacaoDEsquerda(No no);
    public void rotacaaoDDireita(No no);
    public void insercaoRN(No no);
}
