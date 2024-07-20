package aed;
import java.util.ArrayList;

//InvRep: (0 <= |letra| <= 1) &&
//        (esFinPalabra == true <-> info != null) &&
//        (|letra| == 0 -> (padre == null && info == null)) &&
//        (esFinPalabra == true -> |letra| == 1) &&
//        (|hijos| = 256) && (forall n: NodoTrie<T>)(|letra| == 1 -> n pertenece a padre.hijos) 

public class NodoTrie<T>{
    
    private String letra;
    private boolean esFinPalabra;
    private T info;
    private ArrayList<NodoTrie<T>> hijos;
    private NodoTrie<T> padre;
    

    public NodoTrie(){
        letra = "";
        info = null;
        esFinPalabra = false;
        hijos = new ArrayList<NodoTrie<T>>(256);// un lugar por cada caracter del código ASCII
        for (int i = 0; i<256; i++){
            hijos.add(i,null);
        }
        padre = null;
    }

    public NodoTrie(String caracter, T informacion){
        letra = caracter;
        info = informacion;
        hijos = new ArrayList<NodoTrie<T>>(256);
        for (int i = 0; i<256; i++){
            hijos.add(i,null);
        }
        padre = null;
    }

    public String getLetra(){
        return letra;
    }

    public void setInfo(T info){
        this.info = info;
    }

    public T getInfo(){
        return info;
    }

    public boolean esFinPalabra() {
        return esFinPalabra;
    }

    public void setFinDePalabra(boolean esFinPalabra) {
        this.esFinPalabra = esFinPalabra;
    }
    
    public ArrayList<NodoTrie<T>> getHijos(){
        return hijos;
    }
    
    public NodoTrie<T> getHijoPorCaracter(char ch) {
        for (NodoTrie<T> hijo : hijos) {
            if (hijo != null){
                if (hijo.getLetra().equals(String.valueOf(ch))) {// comparo los valores del input con la letra de mi hijo actual.
                    return hijo;
                }   
            }
        }
    return null;
    }
    
    public void agregarHijo(NodoTrie<T>  hijo) {// convierto la letra del hijo a su valor ASCII y meto el nodo en la posición correspondiente en la lista de hijos
        char caracter = hijo.letra.toCharArray()[0];
        int ascii = (int) caracter;

        hijos.set(ascii,hijo);
        hijo.padre = this;
    }

    public NodoTrie<T> getpadre(){
        return padre;
    }
}