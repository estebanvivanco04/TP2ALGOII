package aed;

class NodoD<T> implements java.io.Serializable{

    private T info;      

    private NodoD<T> ant;   

    private NodoD<T> sig;   

    public NodoD() {
        this.info=null;
        this.ant=null;
        this.sig=null; 
    }

    public NodoD(T info, NodoD<T> sig, NodoD<T> ant){        
        this.info=info;
        this.sig=sig;
        this.ant=ant;        
    }

    protected T getInfo(){        
        return(this.info);        
    }

    protected NodoD<T> getAnt(){        
        return (this.ant);        
    }

    protected NodoD<T> getSig(){        
        return (this.sig);        
    }

    protected void setInfo(T info){        
        this.info = info;            
    }

    protected void setAnt(NodoD<T> ant){        
        this.ant=ant;        
    }

    protected void setSig(NodoD<T> sig){        
        this.sig=sig;	        
    }

}
