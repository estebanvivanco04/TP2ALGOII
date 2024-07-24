package aed;

//InvRep: |materia| > 0 && carrera != null

public class MateriaParaCarrera {
    private Carrera carrera;
    private String materia;

    public MateriaParaCarrera(Carrera carrera, String materia){
        this.carrera = carrera;
        this.materia = materia;
    }

    public Carrera getCarrera(){
        return carrera;
    }
    
    public String getMateria(){
        return materia;
    }
}
