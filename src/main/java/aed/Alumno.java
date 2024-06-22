package aed;

public class Alumno {
    private int lu;
    private String carrera;
    private int cantidadMat;

    public Alumno(int numeroLibreta, String carrera, int cantidadMaterias) {
        this.lu = numeroLibreta;
        this.carrera = carrera;
        this.cantidadMat = cantidadMaterias;
    }
    
    public int getlu() {
        return numeroLibreta;
    }

    public String getCarrera() {
        return carrera;
    }

    public int getCantMat() {
        return cantidadMaterias; 
    }

    public void sumarMateria() {
        this.cantidadMat += 1;
    }

}