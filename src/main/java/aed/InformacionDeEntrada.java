package aed;

public class InformacionDeEntrada  <Clave, T > extends Object{

    private T objeto;

    private Clave clave;

    public InformacionDeEntrada( Clave clave, T objeto ){
        this.clave=clave;
        this.objeto=objeto;
    }

    public InformacionDeEntrada( Clave clave ){
        this.objeto = null;
        this.clave = clave;
    }

    public T getObjeto( ) {
        return this.objeto;
    }
 
    public Clave getClave( ){
        return this.clave;
    }

    public void setObjeto( T obje){
        this.objeto=obje;
    }


    @Override
    public String toString( ){
        return ("Clave: " + this.clave.toString( ) + " del Objeto: " + this.objeto.toString( ) + "\n");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        InformacionDeEntrada<Clave, T> x= (InformacionDeEntrada<Clave, T>) obj;
        return (this.clave.equals(x.getClave()));
    }
       
}