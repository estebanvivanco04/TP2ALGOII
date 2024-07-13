package aed;

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
        actual.setInfo(null);
    }
}


