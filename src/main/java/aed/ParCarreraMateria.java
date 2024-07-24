package aed;


public class ParCarreraMateria {
    String carrera;
    String materia;

    public ParCarreraMateria(String carrera, String materia) {
        this.carrera = carrera;
        this.materia = materia;
    }

    public String getNombreMateria() {
        return this.materia;
    }

    public String getNombreCarrera() {
        return this.carrera;
    }
}
