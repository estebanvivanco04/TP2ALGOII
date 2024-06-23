package aed;

public class Alumno {
    private String lu;
    private Carrera carrera;
    private int cantidadMat;

    public Alumno(){
        this.lu = null;
        this.carrera = null;
        this.cantidadMat = 0;
    }

    public Alumno(String numeroLibreta, Carrera carrera, int cantidadMaterias) {
        this.lu = numeroLibreta;
        this.carrera = carrera;
        this.cantidadMat = cantidadMaterias;
    }

    public String getlu() {
        return this.lu;
    }

    public Carrera getCarrera() {
        return this.carrera;
    }

    public int getCantMat() {
        return this.cantidadMat; 
    }

    public void sumarMateria() {
        this.cantidadMat += 1;
    }
    
}