package aed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SistemaSIU {// cuando le pasas el lu te devuelve el nombre del estudiante, su carrera  las materias que esta cursando. 

    private TrieNigga<Alumno> libretasArbolTrie;
    private TrieNigga<Materia> materiasArbolTrie;
    private InfoMateria[] ListaMaterias;
    private String[] ListalibretasUniversitarias;
    private CargoDocente docente;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        this.ListaMaterias = infoMaterias;
        this.ListalibretasUniversitarias = libretasUniversitarias;
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
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int[] plantelDocente(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] carreras(){
        Set<String> todasLasCarreras = new HashSet<>();
        for(ParCarreraMateria par : this.infoMaterias){
            String carreraActual = par.getCarrera();
            if (!todasLasCarreras.contains(carreraActual)){
                todasLasCarreras.add(carreraActual);
            }
        }
        return todasLasCarreras.toArray(new String[0]);	    
    }

    public String[] materias(String carrera){
        	    
    }

    public int materiasInscriptas(String estudiante){
        ;	    
    }
}
