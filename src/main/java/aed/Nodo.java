package aed;

 class Nodo<T>{

    private T info;

    private Nodo<T> sig;

    public Nodo(){
        this.info=null;
        this.sig=null;        
    }

    public Nodo(T info, Nodo<T> sig){
        this.info=info;
        this.sig=sig;
    }

    protected T getInfo(){
        return this.info;
    }

    protected Nodo<T> getSig(){        
        return this.sig;        
    }

    protected void setInfo(T nuevo){        
        this.info=nuevo;
    }

    protected void setSig(Nodo<T> nuevo){
        this.sig=nuevo;
    }
    
}