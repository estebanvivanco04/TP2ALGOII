package aed;

import java.util.ArrayList;

public class NodoNigga<T>{
    
    private String letra;
    private T info;
    private ArrayList<NodoNigga<T>> hijos; //lista de hijos del nodo
    

    public NodoNigga(){
        letra = null;
        info = null;
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

    public T getInfo(){
        return info;
    }

    public ArrayList<NodoNigga<T>> getHijos(){
        return hijos;
    }
}
