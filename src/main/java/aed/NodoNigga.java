package aed;

import java.util.ArrayList;

public class NodoNigga<T>{
    
    private String letra;
    private boolean esFinPalabra;
    private T info;
    private ArrayList<NodoNigga<T>> hijos; //lista de hijos del nodo
    

    public NodoNigga(){
        letra = null;
        info = null;
        esFinPalabra = false;
        hijos = new ArrayList<>(256);
    }

    public NodoNigga(String caracter, T informacion){ // java go brr
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

    public ArrayList<NodoNigga<T>> getHijos(){
        return hijos;
    }
    public NodoNigga<T> getHijoPorCarater(char ch) {
        for (NodoNigga<T> hijo : hijos) {
            if (hijo.getLetra().equals(String.valueOf(ch))) {// comparo los valores del input con la letra de mi hijo actual.
                return hijo;
        }
    }
    return null;
    }
    public void agregarHijo(NodoNigga<T>  hijo) {
        hijos.add(hijo); // lo agrego a la lista de hijos.
    }
}

