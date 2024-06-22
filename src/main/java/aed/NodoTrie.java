package aed;

public class NodoTrie {

    private String info;
    private ListaS<NodoTrie> hijos;
    private boolean finPalabra;

    public NodoTrie(String info) {
        this.info = info;
        this.hijos = new ListaS();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ListaS<NodoTrie> getHijos() {
        return hijos;
    }

    public void setHijos(ListaS<NodoTrie> hijos) {
        this.hijos = hijos;
    }

    public boolean isFinPalabra() {
        return finPalabra;
    }

    public void setFinPalabra(boolean finPalabra) {
        this.finPalabra = finPalabra;
    }

}