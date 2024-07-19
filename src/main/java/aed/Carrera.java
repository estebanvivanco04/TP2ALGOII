package aed;

//InvRep: |carrera| > 0 materiasDeLaCarrera != null

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

    public void agregarMateria(String nombreMateria, Materia materia) {
        this.materiasDeLaCarrera.agregar(nombreMateria, materia);
    }
    
    public String getNombreCarrera(){
        return carrera;
    }

    public Trie<Materia> getMaterias(){
        return materiasDeLaCarrera;
    }
}
