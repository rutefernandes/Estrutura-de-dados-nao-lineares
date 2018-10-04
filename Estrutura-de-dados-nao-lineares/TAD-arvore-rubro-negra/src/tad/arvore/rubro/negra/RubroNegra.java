package tad.arvore.rubro.negra;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author RUTE
 */
public class RubroNegra implements IRubroNegra {
     private No raiz;
    private int tamanho;
    Comparator c = new Comparator();
    private ArrayList<No> inOrder = new ArrayList<No>();
    private ArrayList<No> preOrder = new ArrayList<No>();
    private ArrayList<No> posOrder = new ArrayList<No>();
    
    public RubroNegra(){
        this.raiz = null;
        this.tamanho = 0;
    }
    
    public RubroNegra(int o){
        raiz = new No(o);
        this.raiz.setCor("negro");
	tamanho++;
    }
    
    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
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
    
    public int height(No v){
        if(isExternal(v)){
            return 0;
        } else {
            int h=0;
            Iterator itr = children(v);
            while(itr.hasNext()){
               No filho = (No) itr.next();
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
            No no = (No) itr.next();
            int elemento = no.getElemento();
            elementos.add(elemento);
        }
        itr = elementos.iterator();
        return itr; 
    }
    
    @Override
    public No root() {
        return getRaiz();
    }

    @Override
    public No parent(No no) {
        return no.getPai();
    }

    @Override
    public Iterator children(No no) {
       ArrayList<No> n = new ArrayList<No>();
       n.add(no.getFilhoEsquerda());
       n.add(no.getFilhoDireita());
       Iterator itr = n.iterator();
       return itr;
    }

    @Override
    public boolean isInternal(No no) {
        return (no==null)? false : (no.getFilhoDireita()!=null || no.getFilhoEsquerda()!= null);
    }

    @Override
    public boolean isExternal(No no) {
        return (no==null)? true : (no.getFilhoDireita()==null && no.getFilhoEsquerda()==null);
    }

    @Override
    public boolean isRoot(No no) {
        return (no == getRaiz());
    }

    @Override
    public int depth(No no) {
        return (isRoot(no)?0:1+depth(parent(no)));
    }

    @Override
    public No leftChild(No no) {
        return hasLeft(no)?(no.getFilhoEsquerda()):null;
    }

    @Override
    public No rightChild(No no) {
        return hasRight(no)?(no.getFilhoDireita()):null;
    }

    @Override
    public boolean hasLeft(No no) {
        return (no.getFilhoEsquerda() != null);
    }

    @Override
    public boolean hasRight(No no) {
        return (no.getFilhoDireita() != null);
    }

    @Override
    public No buscar(int chave) {
        return buscar(chave, getRaiz());
    }
  
    @Override
    public No buscar(int chave, No atual) {
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
        No novoNo = new No(o);
        int resultado;
        if(isEmpty()){
            setRaiz(novoNo);
            insercaoRN(novoNo);
            tamanho++;
            System.out.println(novoNo+" adicionado como Raiz.");
        } else {
            No segura = buscar(o, getRaiz());
            resultado = (int) c.compare(segura.getElemento(), novoNo.getElemento());
            if(resultado==0){
                System.out.println("Elemento ja existe.");
            } else if(resultado>0){
                segura.setFilhoEsquerda(novoNo);
                novoNo.setPai(segura);
                novoNo.setCor("rubro");
                insercaoRN(novoNo);
                tamanho++;
                System.out.println( novoNo + " adicionado a esquerda de " + segura.getElemento());
            } else if(resultado<0){
                segura.setFilhoDireita(novoNo);
                novoNo.setPai(segura);
                novoNo.setCor("rubro");
                insercaoRN(novoNo);
                tamanho++;
                System.out.println( novoNo + " adicionado a direita de " + segura.getElemento());
            }
        }
    }
    
    
    public int leftOrRight(No no){
        if(isRoot(no)){
            return 0;
        } else{
            No pai = no.getPai();
            if(pai.getElemento()< no.getElemento()){
                return -1;
            } else {
                return 1;
            }    
        }
    }    
    
    public void insercaoRN(No no){
        if(isRoot(no)){
            no.setCor("negro");
        } else {
            No pai = no.getPai();
            if(pai.getCor().equalsIgnoreCase("negro")){
                no.setCor("rubro");
            } else if(pai.getCor().equalsIgnoreCase("rubro") && isInternal(pai)){
                No avo = pai.getPai();
                No tio = null;
                if(avo.getCor().equalsIgnoreCase("negro")){
                    //verificar se é filho esq ou dir
                    if(leftOrRight(no)==1 && hasRight(avo)){
                        tio = avo.getFilhoDireita();
                    } else if(leftOrRight(no)==-1 && hasLeft(avo)){
                        tio = avo.getFilhoEsquerda();
                    }
                    if(tio!=null && tio.getCor().equalsIgnoreCase("rubro")){
                        
                        avo.setCor("rubro");
                        tio.setCor("negro");
                        pai.setCor("negro");
                        insercaoRN(avo);
                    } else if(tio==null || tio.getCor().equalsIgnoreCase("negro")){
                    //fazer rotações com pai, v, avo e tio. Existe 4 subcasos:
                        int paiPosicao = leftOrRight(pai);
                        int vPosicao = leftOrRight(no);
                        pai.setCor("negro");
                        avo.setCor("rubro");
                        if(paiPosicao==-1 && vPosicao==-1){
                            // se pai filho direito e v direito = direita simples
                            rotacaoEsquerda(avo);
                        } else if(paiPosicao==1 && vPosicao==1){
                            // se pai esquerdo e v esquerdo = esquerda simples
                            rotacaoDireta(avo);
                        } else if(paiPosicao==-1 && vPosicao==1){
                            // se pai direito e v esquerdo = esquerda dupla
                            rotacaoDEsquerda(avo);
                        } else if(paiPosicao==-1 && vPosicao==-1){
                            // se pai esquerdo e v direito = direita dupla
                            rotacaaoDDireita(avo);
                        }
                    }
                }
            }
        }
    }
    
    public String toString () {
        Iterator itr = inOrder();
        int h = this.height() + 5;
        int l = this.size() + 5;
        
        No matrix[][] = new No[h][l];
        
        int i = 0;
        while (itr.hasNext()) {
            No n = (No) itr.next();
            int d = this.depth(n);
            matrix[d][i] = n;
            i++;
        }
        
        String str = "";
        
        for (i = 0; i < h; i++){
            for (int j = 0; j < l; j++) {
                str += matrix[i][j] == null ? "      " : (matrix[i][j].getElemento() >= 0 ? " " + matrix[i][j] : matrix[i][j]);
            }
            str += "\n";
        }
        
        return str;
    }
    
    public int replace(No n1, int o){
        int oldN = n1.getElemento();
        n1.setElemento(o);
        return oldN;
    }

    public void removerE(int n){
        No resultado = buscar(n);
        remover(resultado);
    }
    
    
    @Override
    public No remover(No no) {
        return remover(no, no.getElemento());
    }

    private No remover(No n, Object o) {
        if (n != null) {
            /*folha*/
            if (isExternal(n)) {
                if ((int) c.compare(n.getElemento(), n.getPai().getElemento()) <= 0) {
                    n.getPai().setFilhoEsquerda(null);
                } else {
                    n.getPai().setFilhoDireita(null);
                }
                remocaoRN(n);
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
                remocaoRN(n);
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
                remocaoRN(n);
                this.tamanho--;
                return n;
            }
            /*dois nós*/
            No andaEsq = n.getFilhoDireita();
            while (andaEsq.getFilhoEsquerda() != null) {
                andaEsq = andaEsq.getFilhoEsquerda();
            }
            Object valorBackup = andaEsq.getElemento();
            remover(andaEsq, valorBackup);
            n.setElemento((int) valorBackup);
            remocaoRN(n);
            this.tamanho--;
            return n;
        }
        return null;
    }
    
    public boolean hasLeftAndRight(No no){
        if(no.getFilhoDireita()!=null && no.getFilhoEsquerda()!= null){
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void remocaoRN(No no) {
//O 1 caso verifica se o pai do nó não é nulo, se for vai para o segundo caso. 
        if(no.getPai()!=null){
            int posicaoV = leftOrRight(no);
            No irmao = null;
            if(posicaoV==1 && hasRight(no.getPai())){
                irmao = no.getPai().getFilhoDireita();
            } else if(posicaoV==-1 && hasLeft(no.getPai())){
                irmao = no.getPai().getFilhoEsquerda();
            }
//No 2 caso, se o nó e seu pai forem pretos    
            if(no.getCor().equalsIgnoreCase("negro") && no.getPai().getCor().equalsIgnoreCase("negro")){
                    if(irmao!=null){
//e seu irmão for vermelho o pai deve ser pintado de vermelho e o irmão de preto
                        if(irmao.getCor().equalsIgnoreCase("rubro")){  
                            no.getPai().setCor("rubro");
                            irmao.setCor("negro");
//e então se o nó for filho esquerdo, faz a rotação à esquerda de seu pai e vai pro próximo caso 
                            if(posicaoV==1){
                                rotacaoEsquerda(no.getPai());
                            } else if(posicaoV==-1){
//se for filho direito, rotaciona o pai à direita e vai pro próximo caso.                                   
                                rotacaoDireta(no.getPai());
                            }
//se o pai do nó, o irmão, o filho esquerdo e direito do irmão forem todos pretos, pinta o irmão de vermelho                            
                        } else if(irmao.getCor().equalsIgnoreCase("negro")){
                            if(hasLeftAndRight(irmao)){
                                if(leftChild(irmao).getCor().equalsIgnoreCase("negro") && rightChild(irmao).getCor().equalsIgnoreCase("negro")){
                                    irmao.setCor("rubro");
//volte para o primeiro caso com o pai do nó
                                    remocaoRN(no.getPai());
                                }
                            }
                        }  
                    }   
            } else if(no.getCor().equalsIgnoreCase("negro") && no.getPai().getCor().equalsIgnoreCase("rubro") ){
                if(hasLeftAndRight(irmao)){
// No quarto caso, se o irmão e o filho esquerdo e direito do irmão forem pretos e o pai do nó for vermelho
                    if(irmao.getCor().equalsIgnoreCase("negro") && leftChild(irmao).getCor().equalsIgnoreCase("negro") && rightChild(irmao).getCor().equalsIgnoreCase("negro")){
//deve pintar o irmão de vermelho e o pai do nó de preto                          
                        irmao.setCor("rubro");
                        no.getPai().setCor("negro");     
/*o quinto caso, se o nó for filho esquerdo e o filho direito do irmão for preto deverá pintar o irmão de vermelho e o filho 
esquerdo do irmão de preto e aí sim rotacionar à direita o irmão, */ 
                    } else if(posicaoV==1 && rightChild(irmao).getCor().equalsIgnoreCase("negro")){
                        irmao.setCor("rubro");
                        leftChild(irmao).setCor("negro");
                        rotacaoDireta(irmao);
/*mas se o nó for filho direito deverá pintar o irmão de vermelho e o filho direito do irmão de preto e então rotacionar 
para esquerda o irmão*/
                    } else if(posicaoV==-1 && rightChild(irmao).getCor().equalsIgnoreCase("negro")){
                        irmao.setCor("rubro");
                        rightChild(irmao).setCor("negro");
                        rotacaoEsquerda(irmao);
// Ao chegar no último caso deverá pintar o pai do nó de preto                 
                    } else {
                        no.getPai().setCor("negro");
// caso o nó seja filho esquerdo, pinta o filho direito do irmão do nó de preto e rotaciona o pai do nó para a esquerda, 
                        if(posicaoV==1){
                            rightChild(irmao).setCor("negro");
                            rotacaoEsquerda(no.getPai());
// se o nó for filho direito, pinta o filho esquerdo do irmão de preto e rotaciona o pai para direita.
                        } else if(posicaoV==-1){
                            leftChild(irmao).setCor("negro");
                            rotacaoDireta(no.getPai());
                        }
                    }
                }
            }
        }
        
    }

    @Override
    public void mostrar() {
        System.out.println(this.toString());
    }
    
       
    public Iterator preOrder(){
        preOrder = new ArrayList();
        return (root()==null)?null:preOrder(root()); 
    }
    
    public Iterator preOrder(No v){
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
    
    public Iterator inOrder(No v) { 
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
    
    public Iterator posOrder(No v){
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
    public int alturaNegra(){
        return (root()==null?-1:alturaNegra(root()));
    }
   
    public int alturaNegra(No no) {
        int altura = 0;
        if(no != null){
            if (no.getCor().equalsIgnoreCase("preto")) {
                altura = 1 + Math.max(alturaNegra(no.getFilhoEsquerda()), alturaNegra(no.getFilhoDireita()));
            } else {
                altura = Math.max(alturaNegra(no.getFilhoEsquerda()), alturaNegra(no.getFilhoEsquerda()));
            }
        }
        return altura;
    }

    @Override
    public void rotacaoEsquerda(No no) {
         if(hasRight(no)){
            No subroot = rightChild(no);
            if(hasLeft(rightChild(no))){
                No netoesquerdo = leftChild(subroot);
                netoesquerdo.setPai(no);
                no.setFilhoDireita(netoesquerdo);
            } else {
                no.setFilhoDireita(null);
            }
            if(isRoot(no)){
                setRaiz(subroot);
                subroot.setPai(null);
            } else {
                No novopai = no.getPai();
                if(no.getElemento() < novopai.getElemento()){
                    novopai.setFilhoEsquerda(subroot);
                } else {
                    novopai.setFilhoDireita(subroot);
                }
                subroot.setPai(novopai);
            }
            subroot.setFilhoEsquerda(no);
            no.setPai(subroot);
        }
    }

    @Override
    public void rotacaoDireta(No no) {
        if(hasLeft(no)){
            No subroot = leftChild(no);
            if(hasRight(leftChild(no))){
                No netodireito = rightChild(subroot);
                netodireito.setPai(no);
                no.setFilhoEsquerda(netodireito);
            } else {
                no.setFilhoEsquerda(null);
            }
            if(isRoot(no)){
                setRaiz(subroot);
            } else {
                No novopai = no.getPai();
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
    public void rotacaaoDDireita(No no) {
        rotacaoEsquerda(no.getFilhoEsquerda());
        rotacaoDireta(no);
        
    }

    @Override
    public void rotacaoDEsquerda(No no) {
       rotacaoDireta(no.getFilhoDireita()); 
       rotacaoEsquerda(no);
       
    }
}