package tad.arvore.avl;

public class NoAVL {
    private int elemento;
    private NoAVL pai;
    private NoAVL filhoEsquerda;
    private NoAVL filhoDireita;
    private int fb;
    
    public NoAVL(){
    }
    
    public NoAVL(int elemento){
        this.elemento = elemento;
        this.fb = 0;
    }
    
     public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }
    
    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public NoAVL getPai() {
        return pai;
    }

    public void setPai(NoAVL pai) {
        this.pai = pai;
    }

    public NoAVL getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public void setFilhoEsquerda(NoAVL filhoEsquerda) {
        this.filhoEsquerda = filhoEsquerda;
    }

    public NoAVL getFilhoDireita() {
        return filhoDireita;
    }

    public void setFilhoDireita(NoAVL filhoDireita) {
        this.filhoDireita = filhoDireita;
    }
    
    @Override
    public String toString(){
        return "(" + this.getElemento() + ")"; 
    }


}
