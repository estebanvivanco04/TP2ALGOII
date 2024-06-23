package aed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SistemaSIU {// cuando le pasas el lu te devuelve el nombre del estudiante, su carrera  las materias que esta cursando. 

    private Trie<Alumno> libretasArbolTrie;
    private Trie<Materia> materiasArbolTrie;
    private InfoMateria[] ListaMaterias;
    private String[] ListalibretasUniversitarias;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        this.ListaMaterias = infoMaterias;
        this.ListalibretasUniversitarias = libretasUniversitarias; // creo que hay que agregar a todas las libretas universitarias al Trie, pero son las 4 de la mañana y ya no sé
    }

    public void inscribir(String estudiante, String carrera, String materia){
        Alumno existeAlumno = this.libretasArbolTrie.buscar(estudiante);
        if (existeAlumno != null) { // osea, si ya existe el alumno en el SIU solo quiero sumarle uno en su Objeto, a la cantidad de carreras que cursa
            existeAlumno.sumarMateria();
        }
        else {
            Alumno nuevoAlumno = new Alumno(estudiante, carrera, 1);
            this.libretasArbolTrie.agregar(estudiante, nuevoAlumno);
        }
        this.materiasArbolTrie.buscar(materia).sumarInscriptos();
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        this.materiasArbolTrie.buscar(materia).sumarDocente(cargo.ordinal());   // ordinal supuestamente devuelve el indice de su posicion enumerada 
    }

    public int[] plantelDocente(String materia, String carrera){
        return this.materiasArbolTrie.buscar(materia).getDocentes(); // JAJAJAJAJAJJAJAJAJAJAJAJJAJAJAJAJAJA AJAJAJJAJAJAJAJAJAJAJAJAJAJAJAJAJAJJAJAJAJAJ ME HABIA OLVIDADO DE PONER .BUSCAR
    }

    public void cerrarMateria(String materia, String carrera){
        this.materiasArbolTrie.buscar(materia).cerrarMateria();	    
    }

    public int inscriptos(String materia, String carrera){
        return this.materiasArbolTrie.buscar(materia).getcantInscriptos();	    
    }

    public boolean excedeCupo(String materia, String carrera){
        return this.materiasArbolTrie.buscar(materia).getcantInscriptos() > this.materiasArbolTrie.buscar(materia).getCupo();  
    }

    public String[] carreras(){
      
    }

    public String[] materias(String carrera){
        	    
    }

    public int materiasInscriptas(String estudiante){
        ;	    
    }
}
