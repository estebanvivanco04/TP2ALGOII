package aed;

import aed.InfoMateria;

public class Materia { 

    private int cupo;
    private int[] docentes;
    private int cantInscriptos;
    private InfoMateria[] infoMateria; 
    
    public Materia(int cupo, int[] docentes, int cantInscriptos, InfoMateria[] infoMaterias) {
        this.cupo = cupo;
        this.docentes = docentes;
        this.cantInscriptos = cantInscriptos;
        this.infoMateria = infoMaterias;
    }
    
    public int getCupo() {
        return this.cupo;
    }

    public int[] getDocentes() {
        return docentes;
    }

    public int cantidadTotalDeDocentes() {
        return (docentes[0]*250 + docentes[1]*100 + docentes[2]*20 + docentes[3]*30);
    }

    public int getcantInscriptos() {
        return this.cantInscriptos; 
    }

    public void sumarInscriptos() {
        cantInscriptos += 1;
    }
    public void sumarDocente(int cargo) { // le vamos a pasar directamente lo que "vale el docente" osea si es 0 es AY2 si es 1 es AY1, si es 2 es JTP 
        docentes[cargo] += 1; // le sumo un docente, del tipo cargo, hay 4 tipos de cargo, 0 1 2 y 3. estan en docentes enum en SISTEMASIU
    }

    public InfoMateria[] getInfoMateria() {
        return this.infoMateria;
    }

}
