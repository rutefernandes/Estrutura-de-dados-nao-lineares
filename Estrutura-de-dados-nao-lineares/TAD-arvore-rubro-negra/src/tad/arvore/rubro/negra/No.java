package tad.arvore.rubro.negra;

/**
 *
 * @author RUTE
 */
public class No {
    private int elemento;
    private No pai;
    private No filhoEsquerda;
    private No filhoDireita;
    private String cor;
    
    public No(){
    }
    
    public No(int elemento){
        this.elemento = elemento;
    }
    
    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public String getCor(){
        return cor;
    }
    
    public void setCor(String cor){
        this.cor = cor;
    }
    
    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public void setFilhoEsquerda(No filhoEsquerda) {
        this.filhoEsquerda = filhoEsquerda;
    }

    public No getFilhoDireita() {
        return filhoDireita;
    }

    public void setFilhoDireita(No filhoDireita) {
        this.filhoDireita = filhoDireita;
    }
    
    @Override
    public String toString(){
        return "(" + this.getElemento() + ")"; 
    }
}
