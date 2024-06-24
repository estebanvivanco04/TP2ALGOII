package aed;

import java.util.ArrayList;

public class NodoTrie<T>{
    
    private String letra;
    private boolean esFinPalabra;
    private T info;
    private ArrayList<NodoTrie<T>> hijos; //lista de hijos del nodo
    

    public NodoTrie(){
        letra = null;
        info = null;
        esFinPalabra = false;
        hijos = new ArrayList<>(256);
    }

    public NodoTrie(String caracter, T informacion){
        letra = caracter;
        info = informacion;
        hijos = new ArrayList<>(256);
    }

    public String getLetra(){
        return letra;
    }

    public void setInfo(T info){
        this.info = info;
    }
    public boolean esFinPalabra() {
        return esFinPalabra;
    }
    public void setFinDePalabra(boolean esFinPalabra) {
        this.esFinPalabra = esFinPalabra;
    }
    public T getInfo(){
        return info;
    }

    public ArrayList<NodoTrie<T>> getHijos(){
        return hijos;
    }
    public NodoTrie<T> getHijoPorCarater(char ch) {
        for (NodoTrie<T> hijo : hijos) {
            if (hijo.getLetra().equals(String.valueOf(ch))) {// comparo los valores del input con la letra de mi hijo actual.
                return hijo;
        }
    }
    return null;
    }
    public void agregarHijo(NodoTrie<T>  hijo) {// convierto la letra del hijo a su valor ASCII y meto el nodo en la posición correspondiente en la lista de hijos
        char caracter = hijo.letra.toCharArray()[0];
        int ascii = (int) caracter;

        hijos.set(ascii,hijo);
    }
}

