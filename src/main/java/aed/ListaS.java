package aed;
import java.util.Iterator;


public class ListaS<T> implements Iterable<T> {

    private Nodo<T> cabeza;
  
    private int tamanio; 
    
  
    public ListaS(){        
        this.cabeza=null;
        this.tamanio=0;    
    }
   
    public void insertarAlInicio(T x){        
        this.cabeza=new Nodo<T>(x, this.cabeza);
        this.tamanio++;        
    }

   
    public void insertarAlFinal(T x){        
        if(this.cabeza==null)
            this.insertarAlInicio(x);
        else {            
            try {                
                Nodo<T> ult=this.getPos(this.tamanio-1);
                if(ult==null)
                    return;
                ult.setSig(new Nodo<T>(x, null));
                this.tamanio++;                
            }catch(ExceptionUFPS e) {                
                System.err.println(e.getMensaje());                
            }            
        }        
    }
    
   
    public void insertarOrdenado(T info){
        if (this.esVacia())
            this.insertarAlInicio(info);
        else{
            Nodo<T> x=this.cabeza;
            Nodo<T> y=x;
                while(x!=null){
                    Comparable comparador=(Comparable)info;
                    int rta=comparador.compareTo(x.getInfo());
                    if(rta<0)
                        break;
                    y=x;
                    x=x.getSig();
                }
            if(x==y)
                this.insertarAlInicio(info);
            else{
                y.setSig(new Nodo<T>(info, x));
                this.tamanio++;
                }
            }
    }

    
    public T eliminar(int i) {        
        if(this.esVacia())
            return null;        
        Nodo<T> t=this.cabeza;        
        if(i==0)
            this.cabeza=this.cabeza.getSig();
        else{            
            try {                
                Nodo<T> y=this.getPos(i-1);
                t=y.getSig();
                y.setSig(t.getSig());                
            }catch(ExceptionUFPS e){                
                    System.err.println(e.getMensaje());
                    return (null);
            }            
        }        
        t.setSig(null);        
        this.tamanio--;        
        return(t.getInfo());        
    }

   
    public void vaciar(){        
        this.cabeza=null; 
        this.tamanio=0;           
    }

    
    public T get(int i) {        
        try {            
            Nodo<T> t=this.getPos(i);
            return (t.getInfo());            
        }catch(ExceptionUFPS e) {            
            System.err.println(e.getMensaje());   
            return (null);
        }        
           
    }

          
    public void set(int i, T dato){        
        try{            
            Nodo<T> t=this.getPos(i);
             t.setInfo(dato);            
        }catch(ExceptionUFPS e){            
            System.err.println(e.getMensaje());            
        }        
    } 

    public int getTamanio() {        
        return (this.tamanio);        
    }

   
    public boolean esVacia(){        
        return(this.cabeza==null);        
    }

  
    public boolean esta(T info) {        
        return (this.getIndice(info)!=-1);        
    }

   
    @Override
    public Iterator<T> iterator() {        
        return new IteratorLS<T>(this.cabeza) {};        
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
    public String toString() {        
        if (this.esVacia())
            return ("Lista Vacia");        
        String r="";        
        for(Nodo<T> x=this.cabeza;x!=null;x=x.getSig())
            r+=x.getInfo().toString()+"->";        
        return(r);        
    }
    
   
    private Nodo<T> getPos(int i)throws ExceptionUFPS{        
        if(this.esVacia() || i>this.tamanio  || i<0){
            throw new ExceptionUFPS("El índice solicitado no existe en la Lista Simple");
        }            
        Nodo<T> t=this.cabeza;        
        while(i>0){            
            t=t.getSig();
            i--;            
        }        
        return(t);        
    }

    
    public int getIndice(T info){        
        int i=0;       
        for(Nodo<T> x=this.cabeza;x!=null;x=x.getSig()){            
            if(x.getInfo().equals(info))
                return (i);            
            i++;            
        }        
        return (-1);        
    }    
}