package aed;

//InvRep: raiz != null

public class Trie<T>{

    private NodoTrie<T> raiz;

    public Trie(){
        raiz = new NodoTrie<>();
    }

    public NodoTrie<T> getRaiz(){
        return raiz;
    }

    public void agregar(String nombre, T objeto){
        NodoTrie<T> actual = raiz;
        for (char ch : nombre.toCharArray()) {
            NodoTrie<T> hijo = actual.getHijoPorCaracter(ch);
            if (hijo == null){
                hijo = new NodoTrie<>(String.valueOf(ch),null); // uso String.valueof porque lo estoy pasando desde toCharArray
                actual.agregarHijo(hijo);
            }
            actual = hijo;
        }
        actual.setFinDePalabra(true);
        actual.setInfo(objeto);
    }
    public T buscar(String nombre) {
        NodoTrie<T> actual = raiz;
        for (char ch : nombre.toCharArray()) {
            NodoTrie<T> hijo = actual.getHijoPorCaracter(ch);
            if (hijo == null) {
                return null;
            }
            actual = hijo;
        }
        if (actual.esFinPalabra()) {
            return actual.getInfo();
        } else {
            return null;
        }

    }

    public void eliminar(String nombre){
        NodoTrie<T> actual = raiz;
        for (char ch : nombre.toCharArray()) {
            NodoTrie<T> hijo = actual.getHijoPorCaracter(ch);
            if (hijo == null) {
                return;
            }
            actual = hijo;
        }
        
        boolean todosHijosNulos = true;

        for (int i = 0; i < 256; i++){// O(256) = O(1)
            if (actual.getHijos().get(i) != null){
                todosHijosNulos = false;
                break;
            }
        }

        if(todosHijosNulos == false){ // si actual tiene al menos 1 hijo no nulo, solo le quito la info
            actual.setInfo(null);
            actual.setFinDePalabra(false);
        }else{
            // seteo la posicion de actual en la lista de hijos del padre como null
            char caracter = actual.getLetra().charAt(0);
            int ascii = (int) caracter;
            actual.getpadre().getHijos().set(ascii, null);

            // hay que deshacerse de los nodos inútiles
            borrarHaciaArribaRecursivo(actual.getpadre());
        }
        
    }

    private void borrarHaciaArribaRecursivo (NodoTrie<T> actual){
        boolean todosHijosNulos = true;

        for (int i = 0; i < 256; i++){// O(256) = O(1)
            if (actual.getHijos().get(i) != null){
                todosHijosNulos = false;
                break;
            }
        }

        if(todosHijosNulos == false){// si actual tiene al menos 1 hijo no nulo, no hago nada
            return;
        }else{
            if(actual.getpadre() != null && !actual.getLetra().isEmpty()){// si actual es la raíz me detengo
                // seteo la posicion de actual en la lista de hijos del padre como null
                char caracter = actual.getLetra().charAt(0);
                int ascii = (int) caracter;
                actual.getpadre().getHijos().set(ascii, null);

                // continúo hacia arriba
                borrarHaciaArribaRecursivo(actual.getpadre());
            }
        }
    }
}


