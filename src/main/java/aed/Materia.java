package aed;
import java.util.ArrayList;

//InvRep: (cupo >= 0) && (|docentes| == 4) && (cantInscriptos >= 0) && (|alumnosInscriptos| >=0)

public class Materia { 

    private int cupo;
    private int[] docentes;
    private int cantInscriptos;
    private ArrayList<Alumno> alumnosInscriptos;
    private ArrayList<MateriaParaCarrera> carrerasDeLaMateria;
    
    public Materia(int cupo, int[] docentes, int cantInscriptos, InfoMateria infoMaterias) {
        this.cupo = cupo;
        this.docentes = docentes;
        this.cantInscriptos = cantInscriptos;
        this.alumnosInscriptos = new ArrayList<Alumno>();
        this.carrerasDeLaMateria = new ArrayList<MateriaParaCarrera>();
    }
    
    public int getCupo() {
        return this.cupo;
    }

    public int[] getDocentes() {
        return docentes;
    }

    public int getCantInscriptos() {
        return this.cantInscriptos; 
    }

    public void sumarInscripto() {
        cantInscriptos += 1;
    }

    public void restarInscripto() {
        cantInscriptos -= 1;
    }
    
    public ArrayList<MateriaParaCarrera> getCarreras(){
        return carrerasDeLaMateria; 
    }

    public int calcularCupo(){// O(1)
        int[] posiblesCupos = {docentes[0]*250, docentes[1]*100, docentes[2]*20, docentes[3]*30};
        int min = posiblesCupos[0];
        for (int i = 0; i < posiblesCupos.length; i++){ // O(posiblesCupos.length) = O(4) = O(1)
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
