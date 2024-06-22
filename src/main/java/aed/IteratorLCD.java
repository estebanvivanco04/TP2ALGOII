package aed;

import java.util.Iterator;

public class IteratorLCD<T> implements Iterator<T>{

    private NodoD<T> cab;            
    private NodoD<T> posicion;   
    

    IteratorLCD(NodoD<T> cab) {

        this.cab=cab;                       
        this.posicion=this.cab.getSig();

    }
 
    @Override
    public boolean hasNext() {
            return (this.posicion!=this.cab);
    }
    

    @Override
    public T next() {
        if(!this.hasNext())
            return (null);
        this.posicion=this.posicion.getSig();
        return(this.posicion.getAnt().getInfo());
    }
 
    @Override
    public void remove() {}

}
