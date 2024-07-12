package aed;

import java.lang.reflect.Array;
import java.util.ArrayList;

import aed.InfoMateria;

public class Materia { 

    private int cupo;
    private int[] docentes;
    private int cantInscriptos;
    private InfoMateria infoMateria;
    private ArrayList<Alumno> alumnosInscriptos;
    
    public Materia(int cupo, int[] docentes, int cantInscriptos, InfoMateria infoMaterias) {
        this.cupo = cupo;
        this.docentes = docentes;
        this.cantInscriptos = cantInscriptos;
        this.infoMateria = infoMaterias;
        this.alumnosInscriptos = new ArrayList<Alumno>();
    }
    public void cerrarMateria() {
        this.cupo = 0;
        this.docentes = null;
        this.cantInscriptos = 0;
        this.infoMateria = null; // ¿así se cerraría la materia? i think not
    }
    
    public int getCupo() {
        return this.cupo;
    }

    public int[] getDocentes() {
        return docentes;
    }

    public int getcantInscriptos() {
        return this.cantInscriptos; 
    }

    public void sumarInscripto() {
        cantInscriptos += 1;
    }

    public void restarInscripto() {
        cantInscriptos -= 1;
    }

    public InfoMateria getInfoMateria() {
        return  this.infoMateria;
    }

    public int calcularCupo(){
        int[] posiblesCupos = {docentes[0]*250, docentes[1]*100, docentes[2]*20, docentes[3]*30};
        int min = posiblesCupos[0];
        for (int i = 0; i < posiblesCupos.length - 1; i++){
            if (posiblesCupos[i]<=min){
                min = posiblesCupos[i];
            }
        }
        return min;
    }

    public ArrayList<Alumno> getAlumnosInscriptos(){
        return alumnosInscriptos;
    }

}
