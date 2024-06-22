package aed;

import java.util.Iterator;

public class ListaCD <T> implements Iterable<T>{

    private NodoD<T> cabeza;  

    private int tamanio=0;    

    public ListaCD() {
        this.cabeza=new NodoD<T> (null,null,null);
        this.cabeza.setSig(cabeza);
        cabeza.setAnt(cabeza);        
    }

    public void insertarAlInicio(T dato){
            NodoD<T> x=new NodoD<T> (dato, cabeza.getSig(), cabeza);
            cabeza.setSig(x);
            x.getSig().setAnt(x);
            this.tamanio++;
        }

    public void insertarAlFinal(T dato){
        NodoD<T>x=new NodoD<T>(dato, cabeza, cabeza.getAnt());
        cabeza.getAnt().setSig(x);
        cabeza.setAnt(x);
        this.tamanio++;
    }

    public void insertarOrdenado(T info){
        if (this.esVacia())
            this.insertarAlInicio(info);
        else{
            NodoD<T> x=this.cabeza;
            NodoD<T> y=x;
            x = x.getSig();
            while(x!=this.cabeza){
                Comparable comparador=(Comparable)info;
                int rta=comparador.compareTo(x.getInfo());
                if(rta<0)
                    break;
                y=x;
                x=x.getSig();
            }
            if(x==cabeza.getSig())
                this.insertarAlInicio(info);
            else{
                y.setSig(new NodoD<T>(info, x, y));
                x.setAnt(y.getSig());
                this.tamanio++;
                }
            }
     }

    public T eliminar(int i){
        try{
            NodoD<T> x;
            if(i==0){
                x = this.cabeza.getSig();
                this.cabeza.setSig(x.getSig());
                this.cabeza.getSig().setAnt(this.cabeza);
                x.setSig(null);
                x.setAnt(null);
                this.tamanio--;
                return (x.getInfo());
            }
            x=this.getPos(i-1);
            NodoD<T> y = x.getSig();
            x.setSig(y.getSig());
            y.getSig().setAnt(x);
            y.setSig(null);
            y.setAnt(null);
            this.tamanio--;
            return(y.getInfo());
        }catch(ExceptionUFPS ex) {
            System.err.println(ex.getMessage());
        }
    return(null);	    	    
    }

    public void vaciar(){ 
        this.cabeza=new NodoD<T> (null,null,null);
        this.cabeza.setSig(cabeza);
        cabeza.setAnt(cabeza);
        this.tamanio=0;
    }

    public T get(int i){
        try {
                NodoD<T> x=this.getPos(i);
                return(x.getInfo());
            }catch (ExceptionUFPS ex) {
                System.err.println(ex.getMessage());
            }
            return (null);
        }

    public void set(int i, T dato){
        try{
            NodoD<T> t=this.getPos(i);        
            t.setInfo(dato);
        }catch(ExceptionUFPS e){
            System.err.println(e.getMessage());
        }
    }

    public int getTamanio(){
        return (this.tamanio);
    }

    public boolean esVacia(){
        return(cabeza==cabeza.getSig() || this.getTamanio()==0);
    }

    public boolean esta(T info)
    {
        return (this.getIndice(info)!=-1);
    }
	      
    @Override
    public Iterator<T> iterator(){
        return (new IteratorLCD<T>(this.cabeza));
    }

    public Object[] aVector(){
         if(this.esVacia())
                return (null);
        Object vector[]=new Object[this.getTamanio()];
        Iterator<T> it=this.iterator();
        int i=0;
        while(it.hasNext())
            vector[i++]=it.next();
        return(vector);
    }

    @Override
    public String toString(){
        if (this.esVacia())
            return ("Lista Vacia");
        String r="";
        for(NodoD<T> x=this.cabeza.getSig();x.getInfo()!=null;x=x.getSig())
            r+=x.getInfo().toString()+"<->";
        return(r);
    }

    @SuppressWarnings("empty-statement")
    private NodoD<T> getPos(int i)throws ExceptionUFPS{
        if(i<0||i>=this.tamanio){
            throw new ExceptionUFPS("El índice solicitado no existe en la Lista Doble");
        }
        else
        {
            NodoD<T> t=this.cabeza.getSig();        
            while(i>0){            
                t=t.getSig();
                i--;            
            }        
            return(t);   
        }
     
    }

    public int getIndice(T dato)    {
        int i=0;
        for(NodoD<T> x=this.cabeza.getSig();x!=this.cabeza;x=x.getSig()){
            if(x.getInfo().equals(dato))
                return(i);
            i++;
        }
        return (-1);
    }    
    
}
