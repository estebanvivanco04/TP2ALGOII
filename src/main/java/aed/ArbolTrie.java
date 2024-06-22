package aed;

public class ArbolTrie{
    
    NodoTrie raiz;

    public ArbolTrie() {
        this.raiz = new NodoTrie("nul");
    }
    
    public boolean agregar(String dato){
        if(dato==null)
            return false;
        dato = quitarTildes(dato);
        if(!comprobar(dato))
            return false;
        else{
              String dat[] = dato.split("");
              agregar(0, dat, this.raiz);
              return true; 
            }
    }
    
   private void agregar(int index, String dat[], NodoTrie letra){
        
            if(index == dat.length){
                return;
            }
            ListaS<NodoTrie> hijosLetra = letra.getHijos();   
            String letraPalabra = dat[index];  
            
            if(index+1 < dat.length){
                if(esDoble(dat[index], dat[index+1])){
                    letraPalabra+=dat[index+1];  
                    index++;
                }
            }
            NodoTrie letraBuscada = esta(letraPalabra, hijosLetra);
            if(letraBuscada == null){
                
                NodoTrie nuevo = new NodoTrie(letraPalabra);
                letra.setHijos(agregarAlf(hijosLetra, nuevo));
                nuevo.setFinPalabra(index+1 == dat.length);
                agregar(++index, dat, nuevo);
            }else{
                if(index+1 == dat.length)
                    letraBuscada.setFinPalabra(true);
                agregar(++index, dat, letraBuscada);
            }
    }
   
   private ListaS<NodoTrie> agregarAlf(ListaS<NodoTrie> l, NodoTrie n){
       char insertar = n.getInfo().charAt(0);
       ListaS<NodoTrie> aux = new ListaS<>();
       boolean inserto = false;
       if(l.esVacia()){
           l.insertarAlFinal(n);
           return l;
       }
            for(NodoTrie no : l){
                if((n.getInfo().length() == 1) && (no.getInfo().charAt(0)-insertar == 0)){
                    aux.insertarAlFinal(n);
                    inserto=true;
                }
                if(!inserto && !(no.getInfo().charAt(0)-insertar <= 0)){
                         aux.insertarAlFinal(n);
                         inserto=true;
                    
                }
                aux.insertarAlFinal(no);
            }
            if(!inserto)
                aux.insertarAlFinal(n);
       return aux;
   }
    
    private boolean esDoble(String letra1, String letra2){
        return (letra1.equals("c") && letra2.equals("h"))
                || (letra1.equals("l") && letra2.equals("l"))
                || (letra1.equals("r") && letra2.equals("r"));
    }
    
    private String quitarTildes(String datos){
        datos = datos.toLowerCase();
        String rta = "";
        char d[] = datos.toCharArray();
        for (int i = 0; i < d.length; i++) {
            if(d[i] == 'á' || d[i] == 'à')
                d[i] = 'a';
            if(d[i] == 'é' || d[i] == 'è')
                d[i] = 'e';
            if(d[i] == 'í' || d[i] == 'ì')
                d[i] = 'i';
            if(d[i] == 'ó' || d[i] == 'ò')
                d[i] = 'o';
            if(d[i] == 'ú' || d[i] == 'ù')
                d[i] = 'u';
            rta+=d[i]; 
        }
        return rta;
    }
    
    public boolean buscar(String palabra){
    
        return buscar(palabra, this.raiz, 0);
    }
    
    private boolean buscar(String palabra, NodoTrie l,int index){
        
        if(index != palabra.length()){
        String c = palabra.charAt(index)+"";
        if(index+1 < palabra.length()){
                if(esDoble(palabra.charAt(index)+"", palabra.charAt(index+1)+"")){
                    c+=palabra.charAt(index+1); 
                    index++;
                }
            }
        ListaS<NodoTrie> hijosLetra = l.getHijos();
        
        NodoTrie aux = esta(c, hijosLetra);
        if(aux != null){
            if(aux.isFinPalabra() && index == palabra.length()-1)
                return true;
            return buscar(palabra, aux, ++index);
        }
        }
        return false;
    }

    
    @Override
    public String toString(){
        String s[] = {""};
        return toString(this.raiz.getHijos(),"",s);
    }
    
    public int getProfundidad(){
        return getProfundidad(this.raiz, 0, new int[1]);
    }
    
    private int getProfundidad(NodoTrie n, int cont, int []mayor){
    
        if(n.getHijos().esVacia()){
            if(cont>mayor[0])
                mayor[0] = cont;
            cont = 0;
            return cont;
        }
        ListaS<NodoTrie> l = n.getHijos();
        int i = 0;
        for (NodoTrie no : l) {
            if(i>0)
                cont -= 1;  //le resto uno cada vez que salte a otra rama
              getProfundidad(no, ++cont,mayor);
            i++;
        }
        return mayor[0];
    }

    private String toString(ListaS<NodoTrie> l, String rta, String[] s){
    
        if(l.esVacia())
            return "";
        
        for(NodoTrie dato : l){
            
            if(!dato.equals(l.get(0))){ 
               
                char aux[] = rta.toCharArray();
                rta="";
                for (int i = 0; i < aux.length-1; i++) {
                    rta+=aux[i];
                }
                rta+="-->"+dato.getInfo();
            }else
                rta += "-->"+dato.getInfo();
            
            if(dato.isFinPalabra()){
                s[0] += rta+";";
            }
            toString(dato.getHijos(),rta,s);
        } 
        return s[0];
    }
    
    private NodoTrie esta(String let, ListaS<NodoTrie> l){
        
        if(l.esVacia())
            return null;
        for (NodoTrie letra : l) {
            if(letra.getInfo().equals(let))
                return letra;
        }
        return null;
    }
    
    private boolean comprobar(String dato){
        
        if(dato == null)
            return false;
        char arr[] = dato.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < 97){
                return false;
            }
            if(arr[i] > 122){
                if(arr[i] != 'ñ'){
                        return false;
                    }
            }
        }
        return true;
    }

    public NodoTrie getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoTrie raiz) {
        this.raiz = raiz;
    }
}

