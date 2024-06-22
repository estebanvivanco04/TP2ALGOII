package aed;
import java.util.ArrayList;

import aed.InfoMateria;

public class Materia { 

    private int cupo;
    private Array<Int> docentes = new ArrayList<>(5);
    private int cantInscriptos;
    private InfoMateria[] infoMateria; 
    
    public Materia(int cupo, Array<Int> docentes, int cantInscriptos, InfoMateria[] infoMaterias) {
        this.cupo = cupo;
        this.docentes = docentes;
        this.cantInscriptos = cantInscriptos;
        this.infoMateria = infoMaterias;
    }
    
    public int getcupo() {
        return this.cupo;
    }

    public String getdocentes() {
        return this.docentes;
    }

    public int getcantInscriptos() {
        return this.cantInscriptos; 
    }

    public void sumarInsciptos() {
        cantInscriptos += 1;
    }

    public infoMateria getInfoMateria() {
        return this.infoMateria;
    }

}
