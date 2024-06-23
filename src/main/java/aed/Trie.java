package aed;

public class TrieNigga<T>{

    private NodoNigga<T> raiz;

    public TrieNigga(){
        raiz = new NodoNigga<>();
    }

    public void agregar(String nombre, T coso){
        NodoNigga<T> actual = raiz;
        for (char ch : nombre.toCharArray()) {
            NodoNigga<T> hijo = actual.getHijoPorCarater(ch);
            if (hijo == null){
                hijo = new NodoNigga<>(String.valueOf(ch),null); // uso String.valueof porque lo estoy pasando desde toCharArray
                actual.agregarHijo(hijo);
            }
            actual = hijo;
        }
        actual.setFinDePalabra(true);
        actual.setInfo(coso);
    }
    public T buscar(String nombre) {
        NodoNigga<T> actual = raiz;
        for (char ch : nombre.toCharArray()) {
            NodoNigga<T> hijo = actual.getHijoPorCarater(ch);
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
}
