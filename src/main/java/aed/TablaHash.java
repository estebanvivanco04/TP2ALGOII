package aed;

public class TablaHash <Clave, T> {

    private int numeroDatos;
    private int numeroSlots;

    private ListaCD<InformacionDeEntrada <Clave,T>> [ ] informacionEntrada;

    public TablaHash() {
        this.numeroDatos = 0;
        this.numeroSlots = 11;
         this.informacionEntrada = new ListaCD [this.numeroSlots];

         this.inicializarListas( );
    }

    public TablaHash( int numeroSlots) {
        this.numeroDatos =0;
        this.numeroSlots = numeroSlots;
        this.informacionEntrada =  new ListaCD [numeroSlots];

         this.inicializarListas( );
    }

    public T insertar( Clave clave, T objeto ) {
        int indice=0;
        InformacionDeEntrada<Clave,T> objetoAnterior=null;
        if(clave==null){
            throw new RuntimeException("La Clave de Objeto no puede ser vacia!!!");
        }
        else{
            indice =index(clave);
            objetoAnterior = this.registrarEntrada(indice, clave );
            if( objetoAnterior== null ){
                InformacionDeEntrada<Clave,T> nuevoObjeto = new InformacionDeEntrada( clave, objeto );
                this.informacionEntrada[ indice ].insertarAlFinal(nuevoObjeto);
                this.numeroDatos+=1;
                return nuevoObjeto.getObjeto();
            }
            else
                 objetoAnterior.setObjeto( objeto);
        }
            return (T)objetoAnterior.getObjeto();
    }

    public T eliminar( Clave clave ) {
        int i=0;
        InformacionDeEntrada objeto;
           if(clave==null){
               throw new RuntimeException("La Clave de Objeto no puede ser vacia!!!");
           }
           else{
                int indice =index(clave);
               ListaCD<InformacionDeEntrada<Clave,T>> listaObjeto = this.informacionEntrada[ indice ];
               objeto = new InformacionDeEntrada( clave );
               i=listaObjeto.getIndice(objeto);
                    if(i==-1)
                        objeto=null;
                    else{
                       objeto = ( InformacionDeEntrada )listaObjeto.eliminar(i);
                       this.numeroDatos--;
                    }
               }
               return (T)objeto.getObjeto();
       }

    public boolean esta(Clave clave){
        return(this.getObjeto(clave)!=null);
    }

     public Object getObjeto( Clave clave ) {
         InformacionDeEntrada objeto;
        if ( clave == null )
            throw new IllegalArgumentException("Clave null no permitida" );
        else{
            int indice =index(clave);
            ListaCD<InformacionDeEntrada<Clave,T>> listaObjeto = this.informacionEntrada[ indice ];
            objeto= new InformacionDeEntrada( clave );
            int i=listaObjeto.getIndice(objeto);
             if(i==-1)
                    return null;
             else{
                objeto = listaObjeto.get(i);
              }
          }
          return objeto.getObjeto();
      }

    private InformacionDeEntrada registrarEntrada( int indice, Clave clave ){
        InformacionDeEntrada<Clave,T> objeto = new InformacionDeEntrada( clave );
        ListaCD<InformacionDeEntrada<Clave,T>> listaEntradas = this.informacionEntrada[ indice ];//Slot de la tabla donde deberia encontrarse el objeto
        int i=listaEntradas.getIndice(objeto);
        if(i==-1)
            objeto=null;
        else
            objeto = listaEntradas.get(i);
        return objeto;
    }
    

    public int index( Clave clave ){
        int hcode=clave.hashCode();
        double num= ((Math.sqrt(5.0) - 1.0)/2.0);//numero que se utiliza para mejor distribucion de las entradas en la tabla hash.
        double t = (Math.abs( hcode ) * num);//(this.numeroDatos+1); //valor absoluto de hash de objeto 
        return ((int) ((t - (int)t) *( this.numeroSlots) ));
    }

    public void eliminarTodo(){
        this.numeroDatos = 0;
        for ( int i = 0; i < this.informacionEntrada.length; i++ )
            this.informacionEntrada[ i ] = null;
    }

    public int getNumeroDatos() {
        return numeroDatos;
    }

    public int getNumeroSlots() {
        return numeroSlots;
    }

    public ListaCD<InformacionDeEntrada<Clave, T>>[] getInformacionEntrada() {
        return informacionEntrada;
    }

    public int numSlotOcupados(){
        int i=0;
        int cant=0;
        while(i<this.numeroSlots){
            if(!this.informacionEntrada[i].esVacia())
                cant++;
                i++;
        }
        return cant;
    }

    public void setNumeroSlots(int numeroSlots) {
        this.numeroSlots = numeroSlots;
    }

    public void setInformacionEntrada(ListaCD<InformacionDeEntrada<Clave, T>>[] informacionEntrada) {
        this.informacionEntrada = informacionEntrada;
    }

    private void inicializarListas( ){
        for( int i = 0; i < this.informacionEntrada.length; i++ ){
            this.informacionEntrada[i] = new ListaCD( );
        }
    }

    private int obtenerPrimo( int numero ) {
        int primo = numero -1;
        while( !esPrimo( primo ) ){
            primo+=2;
        }
        return primo;
    }

    public boolean esPrimo( int numero ){
        boolean esPrimo = false;
        int raizCuadrada = ( int )Math.sqrt( numero );
        for( int i = 3; i <= raizCuadrada && !(esPrimo); i+= 2 )   {
            if( numero % i != 0 ){
                esPrimo = true;
            }
        }
        return esPrimo;
    }

    public String imprimir(){
        String msg="";
         for ( int i = 0; i < this.informacionEntrada.length; i++ )
            if(this.informacionEntrada[ i ] != null)
                msg+="Slot de la tabla numero"+i+" ==>"+this.informacionEntrada[ i ].toString()+"\n";
         return msg;
    }

    public boolean esVacia() {
        return (this.numeroDatos==0);
    }

}



