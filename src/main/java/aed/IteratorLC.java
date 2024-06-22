package aed;

import java.util.Iterator;

public class IteratorLC<T> implements Iterator<T>{

    private Nodo<T> cabeza;  

    private Nodo<T> posicion;    
    


    IteratorLC(Nodo<T> cab){            
        this.cabeza=cab;
        this.posicion=this.cabeza.getSig();            
    }

    @Override
    public boolean hasNext(){            
        return (this.posicion!=this.cabeza);                
    }

    @Override
    public T next(){            
        if(!this.hasNext())
            return (null);
        Nodo<T> aux = posicion;
        this.posicion=this.posicion.getSig();
        return(aux.getInfo());
    }

    @Override
    public void remove(){}
	
}
