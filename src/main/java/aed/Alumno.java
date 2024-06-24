package aed;

public class Alumno {
    private String lu;
    private int cantidadMat;

    public Alumno(){
        this.lu = null;
        this.cantidadMat = 0;
    }

    public Alumno(String numeroLibreta, int cantidadMaterias) {
        this.lu = numeroLibreta;
        this.cantidadMat = cantidadMaterias;
    }

    public String getlu() {
        return this.lu;
    }

    public int getCantMat() {
        return this.cantidadMat; 
    }

    public void inscribirAMateria(String carrera, String materia, Trie<Carrera> carreras) {
        this.cantidadMat += 1;
        carreras.buscar(carrera).getMaterias().buscar(materia).getAlumnosInscriptos().add(this);
        carreras.buscar(carrera).getMaterias().buscar(materia).sumarInscripto();
    }

    public void desinscribirDeMateria(){
        cantidadMat -=1;
    }
    
}