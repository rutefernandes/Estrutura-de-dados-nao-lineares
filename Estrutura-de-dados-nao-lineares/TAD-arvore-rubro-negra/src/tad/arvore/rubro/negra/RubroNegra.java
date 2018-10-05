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
    No nil;
    Comparator c = new Comparator();
    private ArrayList<No> inOrder = new ArrayList<No>();
    private ArrayList<No> preOrder = new ArrayList<No>();
    private ArrayList<No> posOrder = new ArrayList<No>();
    
    public RubroNegra(){
        this.raiz = null;
        this.tamanho = 0;
        nil = new No(-1);
        nil.setCor("negro");
        nil.setFilhoDireita(nil);
        nil.setFilhoEsquerda(nil);
    }
    
    public RubroNegra(int o){
        raiz = new No(o);
        this.raiz.setCor("negro");
        nil = new No(-1);
        nil.setCor("negro");
        nil.setFilhoDireita(nil);
        nil.setFilhoEsquerda(nil);
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

     
    public boolean isLeftChild(No no){
        if(no.getPai()!=null){
            if(no.getPai().getFilhoEsquerda()==no){
                return true;
            }
        }
        return false;
    }   
    
    
    public No remover(int valor) {
       No deletar = buscar(valor);
       if(deletar == null){
           return this.nil;
       }
       No deletado = remover(deletar, deletar.getElemento());
       corrigirRemocao(deletado);
       return deletado;
    }   
   
    
    public void corrigirRemocao(No n) {
        // Situação 2: no é negro e seu sucessor é rubro
        // Já corrigido na remoção, porém deve-se pintar o sucessor de negro
        // Situação 3: n é negro e seu sucessor é negro
        if (n.getCor().equalsIgnoreCase("negro")) {
            No pai = n.getPai();
            if (pai != null) {
                boolean isLeft = isLeftChild(n);
                No irmao = isLeft ? rightChild(n.getPai()): leftChild(n.getPai());
                irmao = irmao != null ? irmao : this.nil;
                No sobrinhoEsquerda = leftChild(irmao) != null ? leftChild(irmao): this.nil;
                No sobrinhoDireita = rightChild(irmao) != null ? rightChild(irmao)  : this.nil;
                
                
                if (!irmao.getCor().equalsIgnoreCase("negro")) { // Tem irmão rubro
                    if (pai.getCor().equalsIgnoreCase("negro")) { // Tem pai negro
                        // Caso 1:  se x é negro e x tem irmão w rubro e pai negro
                        pai.setDuploNegro(true);
                        rotacaoEsquerda(pai);
                        irmao.setCor("negro");
                        pai.setCor("rubro");
                        
                    } else { // Tem pai rubro
                        
                    }
                } 
                else { // Tem irmão negro
                    if (irmao != this.nil) { 
                        if(sobrinhoEsquerda.getCor().equalsIgnoreCase("negro") && sobrinhoDireita.getCor().equalsIgnoreCase("negro")) { // filhos do irmão são negros
                            if (pai.getCor().equalsIgnoreCase("negro")) { // Tem pai negro
                                // Caso 2a: se x é negro, tem irmão w negro com filhos negros e pai negro
                                irmao.setCor("rubro");
                            } else { // Tem pai rubro
                                // Caso 2b: se x é negro, tem irmão w negro com filhos negros e pai rubro
                                irmao.setCor("rubro");
                                pai.setCor("negro");
                            }
                        }
                        
                        if (!sobrinhoEsquerda.getCor().equalsIgnoreCase("negro") && sobrinhoDireita.getCor().equalsIgnoreCase("negro")) {
                            // Caso 3: se x é negro, tem irmão w negro, tem
                            // pai de qualquer cor (rubro ou negro), tem
                            // irmão w com filho esquerdo rubro e irmão w
                            // com filho direito negro.
                            rotacaoDireta(irmao);
                            irmao.setCor("rubro");
                            sobrinhoEsquerda.setCor("negro");
                        }
                        
                        if (!sobrinhoDireita.getCor().equalsIgnoreCase("negro")) {
                            // Caso 4: se x é negro, tem irmão w negro, tem
                            // pai de qualquer cor (rubro ou negro), tem
                            // irmão w com filho esquerdo qualquer cor e
                            // irmão w com filho direito rubro.
                            rotacaoEsquerda(pai);
                            irmao.setCor(pai.getCor());
                            pai.setCor("negro");
                            sobrinhoDireita.setCor("negro");
                        }
                            
                    } 
                }
                
                
                if (pai.getCor().equalsIgnoreCase("negro")) {
                    // Caso 1: n é negro e tem irmão rubro e pai negro
                    if (!irmao.getCor().equalsIgnoreCase("negro")) { // Tem irmão rubro
                        
                    }
                    // Caso 2a: n é negro, tem irmão negro com filhos negros e pai negro
                    else { // Tem irmão negro
                        if (irmao != this.nil && sobrinhoEsquerda.getCor().equalsIgnoreCase("negro") && sobrinhoDireita.getCor().equalsIgnoreCase("negro")) { // filhos do irmão são pretos
                            irmao.setCor("rubro");
                        }
                    }
                } else { // tem o pai rubro
                    // Caso 2b: se n é negro, tem irmão negro com filhos negros e pai rubro
                    if (irmao.getCor().equalsIgnoreCase("negro")) {
                        if (irmao != this.nil && sobrinhoEsquerda.getCor().equalsIgnoreCase("negro") && sobrinhoDireita.getCor().equalsIgnoreCase("negro")) { // filhos do irmão são pretos
                            irmao.setCor("rubro");
                            pai.setCor("negro");
                        }
                    }
                }
                // Pai de qualquer cor
            }
            
        }
        
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
            No andaEsq = n.getFilhoDireita();
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
    
    public boolean hasLeftAndRight(No no){
        if(no.getFilhoDireita()!=null && no.getFilhoEsquerda()!= null){
            return true;
        } else {
            return false;
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