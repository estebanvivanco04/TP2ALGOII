package aed;
public class Carrera {
    private String carrera;
    private Trie<Materia> materiasDeLaCarrera;


    public Carrera(){
        this.carrera = null;
        this.materiasDeLaCarrera = new Trie<Materia>();
    }

    public Carrera(String carrera){
        this.carrera = carrera;
        this.materiasDeLaCarrera = new Trie<Materia>();

    }


    public agregarMateria(Materia materia) {
        this.materiasDeLaCarrera.agregar(materia);
    }

    public int cantidadDeMaterias() {

    }
    
    public 
}
