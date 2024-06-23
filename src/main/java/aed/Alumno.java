package aed;

public class Alumno {
    private String lu;
    private String carrera;
    private int cantidadMat;

    public Alumno(){
        this.carrera = null;
        this.cantidadMat = 0;
    }

    public Alumno(String numeroLibreta, String carrera, int cantidadMaterias) {
        this.lu = numeroLibreta;
        this.carrera = carrera;
        this.cantidadMat = cantidadMaterias;
    }

    public void definirCarrera(String carre) {
        this.carrera = carre;
    }

    public String getlu() {
        return this.lu;
    }

    public String getCarrera() {
        return this.carrera;
    }

    public int getCantMat() {
        return this.cantidadMat; 
    }

    public void sumarMateria() {
        this.cantidadMat += 1;
    }
    
}