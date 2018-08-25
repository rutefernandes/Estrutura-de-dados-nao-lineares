package tad.arvore.avl;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author RUTE
 */
public class ArvoreAVL implements IarvoreAVL {
    private NoAVL raiz;
    private int tamanho;
    private ArrayList<NoAVL> inOrder = new ArrayList<NoAVL>();
    private ArrayList<NoAVL> preOrder = new ArrayList<NoAVL>();
    private ArrayList<NoAVL> posOrder = new ArrayList<NoAVL>();
    Comparador c = new Comparador();

    
    public ArvoreAVL(){
        this.raiz = null;
        this.tamanho = 0;
    }
    
    public ArvoreAVL(int o){
        raiz = new NoAVL(o);
	tamanho++;
    }
    
    public NoAVL getRaiz() {
        return raiz;
    }

    public void setRaiz(NoAVL raiz) {
        this.raiz = raiz;
    }
    
    public int getTamanho() {
        return tamanho;
    }
    
    @Override
    public int size() {
        return this.getTamanho();
    }

    @Override
    public boolean isEmpty() {
        return (getRaiz() == null);
    }

    @Override
    public int height() { 
        return (root()==null?-1:height(root()));
    }
    
    public int height(NoAVL v){
        if(isExternal(v)){
            return 0;
        } else {
            int h=0;
            Iterator itr = children(v);
            while(itr.hasNext()){
               NoAVL filho = (NoAVL) itr.next();
               h = Math.max(h, height(filho));
           }
            return h+1;
        }
    }
    
    @Override
    public Iterator nos() { 
        return (root()==null)?null:inOrder(root());
    }

    @Override
    public Iterator elements() { 
        Iterator itr = (root()==null)?null:inOrder(root()); 
        ArrayList elementos = new ArrayList();
        while(itr.hasNext()) {
            NoAVL no = (NoAVL) itr.next();
            int elemento = no.getElemento();
            elementos.add(elemento);
        }
        itr = elementos.iterator();
        return itr; 
    }
    
    @Override
    public NoAVL root() {
        return getRaiz();
    }

    @Override
    public NoAVL parent(NoAVL no) {
        return no.getPai();
    }

    @Override
    public Iterator children(NoAVL no) {
       ArrayList<NoAVL> n = new ArrayList<NoAVL>();
       n.add(no.getFilhoEsquerda());
       n.add(no.getFilhoDireita());
       Iterator itr = n.iterator();
       return itr;
    }

    @Override
    public boolean isInternal(NoAVL no) {
        return (no==null)? false : (no.getFilhoDireita()!=null || no.getFilhoEsquerda()!= null);
    }

    @Override
    public boolean isExternal(NoAVL no) {
        return (no==null)? true : (no.getFilhoDireita()==null && no.getFilhoEsquerda()==null);
    }

    @Override
    public boolean isRoot(NoAVL no) {
        return (no == getRaiz());
    }

    @Override
    public int depth(NoAVL no) {
        return (isRoot(no)?0:1+depth(parent(no)));
    }

    @Override
    public NoAVL leftChild(NoAVL no) {
        return hasLeft(no)?(no.getFilhoEsquerda()):null;
    }

    @Override
    public NoAVL rightChild(NoAVL no) {
        return hasRight(no)?(no.getFilhoDireita()):null;
    }

    @Override
    public boolean hasLeft(NoAVL no) {
        return (no.getFilhoEsquerda() != null);
    }

    @Override
    public boolean hasRight(NoAVL no) {
        return (no.getFilhoDireita() != null);
    }

    @Override
    public NoAVL buscar(int chave) {
        return buscar(chave, getRaiz());
    }
  
    @Override
    public NoAVL buscar(int chave, NoAVL atual) {
        if(isExternal(atual)){
            return atual;       
        }
        if(chave < atual.getElemento()){
            return (atual.getFilhoEsquerda()!=null)? buscar(chave, atual.getFilhoEsquerda()) : atual;
        } else if (chave == atual.getElemento()){
           return atual; 
        } else if (chave > atual.getElemento()){
            return (atual.getFilhoDireita()!=null)? buscar(chave, atual.getFilhoDireita()) : atual;
        } else {
            return atual; 
        }
    }

    @Override
    public void adicionar(int o) {
        NoAVL novoNoAVL = new NoAVL(o);
        int resultado;
        if(isEmpty()){
            setRaiz(novoNoAVL);
            tamanho++;
            System.out.println(novoNoAVL+" adicionado como Raiz.");
            atualizarFB(novoNoAVL, 1, true, 0);
        } else {
            NoAVL segura = buscar(o, getRaiz());
            resultado = (int) c.compare(segura.getElemento(), novoNoAVL.getElemento());
            if(resultado==0){
                System.out.println("Elemento ja existe.");
            } else if(resultado>0){
                segura.setFilhoEsquerda(novoNoAVL);
                novoNoAVL.setPai(segura);
                tamanho++;
                atualizarFB(novoNoAVL, 1, true, 1);
                System.out.println( novoNoAVL + " adicionado a esquerda de " + segura.getElemento());
            } else if(resultado<0){
                segura.setFilhoDireita(novoNoAVL);
                novoNoAVL.setPai(segura);
                tamanho++;
                atualizarFB(novoNoAVL, 1, false, -1);
                System.out.println( novoNoAVL + " adicionado a direita de " + segura.getElemento());
            }
        }
    }
    
    public String toString () {
        Iterator itr = inOrder();
        int h = this.height() + 5;
        int l = this.size() + 5;
        
        Object matrix[][] = new Object[h][l];
        
        int i = 0;
        while (itr.hasNext()) {
            NoAVL n = (NoAVL) itr.next();
            int d = this.depth(n);
            matrix[d][i] = n.getElemento();
            i++;
        }
        
        String str = "";
        
        for (i = 0; i < h; i++){
            for (int j = 0; j < l; j++) {
                str += matrix[i][j] == null ? "  " : ((int) matrix[i][j] >= 0 ? " " + matrix[i][j] : matrix[i][j]);
            }
            str += "\n";
        }
        
        return str;
    }
    
    public int replace(NoAVL n1, int o){
        int oldN = n1.getElemento();
        n1.setElemento(o);
        return oldN;
    }

    @Override
    public NoAVL remover(NoAVL no) {
        return remover(no, no.getElemento());
    }

    private NoAVL remover(NoAVL n, Object o) {
        if (n != null) {
            /*folha*/
            if (isExternal(n)) {
                if ((int) c.compare(n.getElemento(), n.getPai().getElemento()) <= 0) {
                    n.getPai().setFilhoEsquerda(null);
                } else {
                    n.getPai().setFilhoDireita(null);
                }
                this.tamanho--;
                return n;
            }
            /*um nó*/
            if (n.getFilhoEsquerda() != null && n.getFilhoDireita() == null) {
                if ((int) c.compare(n.getElemento(), n.getPai().getElemento()) <= 0) {
                    n.getPai().setFilhoEsquerda(n.getFilhoEsquerda());
                    n.getFilhoEsquerda().setPai(n.getPai());
                } else {
                    n.getPai().setFilhoDireita(n.getFilhoEsquerda());
                    n.getFilhoEsquerda().setPai(n.getPai());
                }
                this.tamanho--;
                return n;
            }
            if (n.getFilhoEsquerda() == null && n.getFilhoDireita() != null) {
                if ((int) c.compare(n.getElemento(), n.getPai().getElemento()) <= 0) {
                    n.getPai().setFilhoEsquerda(n.getFilhoDireita());
                    n.getFilhoDireita().setPai(n.getPai());
                } else {
                    n.getPai().setFilhoDireita(n.getFilhoDireita());
                    n.getFilhoDireita().setPai(n.getPai());
                }
                this.tamanho--;
                return n;
            }
            /*dois nós*/
            NoAVL andaEsq = n.getFilhoDireita();
            while (andaEsq.getFilhoEsquerda() != null) {
                andaEsq = andaEsq.getFilhoEsquerda();
            }
            Object valorBackup = andaEsq.getElemento();
            remover(andaEsq, valorBackup);
            n.setElemento((int) valorBackup);
            this.tamanho--;
            return n;
        }
        return null;
    }

    @Override
    public void mostrar() {
        System.out.println(this.toString());
    }
    
       
    public Iterator preOrder(){
        preOrder = new ArrayList();
        return (root()==null)?null:preOrder(root()); 
    }
    
    public Iterator preOrder(NoAVL v){
        preOrder.add(v);
        if(leftChild(v)!=null){
            preOrder(leftChild(v));
        }
        if(rightChild(v)!=null){
            preOrder(rightChild(v));
        }
        Iterator itr = preOrder.iterator();
        return itr;
    }
    
    public Iterator inOrder(){
        inOrder = new ArrayList();
        return (root()==null)?null:inOrder(root()); 
    }
    
    public Iterator inOrder(NoAVL v) { 
        if(isInternal(v)){
            inOrder(leftChild(v)); 
            inOrder.add(v);
        } else if(v!=null) {
            inOrder.add(v);
        }
        if(isInternal(v)){
            inOrder(rightChild(v));
        }
        Iterator itr = inOrder.iterator();
        return itr;
    }
    
    public Iterator posOrder(){
        posOrder = new ArrayList();
        return (root()==null)?null:posOrder(root()); 
    }
    
    public Iterator posOrder(NoAVL v){
        if(leftChild(v)!=null){
            posOrder(leftChild(v));
        }
        if(rightChild(v)!=null){
            posOrder(rightChild(v));
        }
        posOrder.add(v);
        Iterator itr = posOrder.iterator();
        return itr;
    }

    @Override
    public void rotacaoSEsquerda(NoAVL no) {
        if(hasRight(no)){
            NoAVL subroot = rightChild(no);
            if(hasLeft(rightChild(no))){
                NoAVL netoesquerdo = leftChild(subroot);
                netoesquerdo.setPai(no);
                no.setFilhoDireita(netoesquerdo);
            }
            if(isRoot(no)){
                setRaiz(subroot);
            } else {
                NoAVL novopai = no.getPai();
                if(no.getElemento() < novopai.getElemento()){
                    novopai.setFilhoEsquerda(subroot);
                } else {
                    novopai.setFilhoDireita(subroot);
                }
                subroot.setPai(novopai);
            }
            subroot.setFilhoEsquerda(no);
            no.setPai(subroot);

        int fb_b_novo, fb_a_novo = 0;
        fb_b_novo= ((no.getFb() + 1) - min(rightChild(no).getFb(), 0));
        fb_a_novo = ((rightChild(no).getFb() + 1) - max(fb_b_novo, 0));
        subroot.setFb(fb_a_novo);
        no.setFb(fb_b_novo);
        }
    }

    @Override
    public void rotacaoSDireta(NoAVL no) {
        if(hasLeft(no)){
            NoAVL subroot = leftChild(no);
            if(hasRight(leftChild(no))){
                NoAVL netodireito = rightChild(subroot);
                netodireito.setPai(no);
                no.setFilhoEsquerda(netodireito);
            }
            if(isRoot(no)){
                setRaiz(subroot);
            } else {
                NoAVL novopai = no.getPai();
                if(no.getElemento() < novopai.getElemento()){
                    novopai.setFilhoEsquerda(subroot);
                } else {
                    novopai.setFilhoDireita(subroot);
                }
                subroot.setPai(novopai);
            }
            subroot.setFilhoDireita(no);
            no.setPai(subroot);
        }
    }
    

    @Override
    public void rotacaaoDDireita(NoAVL no) {
        rotacaoSEsquerda(no.getFilhoEsquerda());
        rotacaoSDireta(no);
        
    }

    @Override
    public void rotacaoDEsquerda(NoAVL no) {
       rotacaoSDireta(no.getFilhoDireita()); 
       rotacaoSEsquerda(no);
       
    }
    
    @Override
    public void atualizarFB(NoAVL no, int tipo, boolean op, int nofb) {
        System.out.println(no.getElemento() + " " +  no.getFb());
        int fb = no.getFb();
        no.setFb(fb + nofb);
        // rotações
        if (fb == 2) {
            if (hasLeft(no) && leftChild(no).getFb() >= 0) {
                rotacaoSDireta(no);
            } else if(hasLeft(no) && leftChild(no).getFb() < 0) {
                rotacaaoDDireita(no);
            }
        } else if (fb == -2) {
            System.out.println("aq " + no.getFb());
            if (hasRight(no) && rightChild(no).getFb() <= 0) {
                rotacaoSEsquerda(no);
            } else if(hasRight(no) && rightChild(no).getFb() > 0) {
                rotacaoDEsquerda(no);
            }
        }
        //fb
        if(tipo==1){
            if(no.getPai()!=null && no.getPai().getFb()!=0){
                if(op){
                    atualizarFB(no.getPai(), tipo, op, 1);
                } else {
                    atualizarFB(no.getPai(), tipo, op, -1);
                }
            }
        } else if(tipo == 2) {
            if(no.getPai()!=null && no.getPai().getFb()==0){
                if(op){
                    atualizarFB(no.getPai(), tipo, op, -1);
                } else {
                    atualizarFB(no.getPai(), tipo, op, 1);
                }
            }
        }
        
    }
}