package aed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SistemaSIU {

    private ArbolTrie lu; // cuando le pasas el lu te devuelve el nombre del estudiante, su carrera  las materias que esta cursando.

    private ArbolTrie libretasArbolTrie;
    private ArbolTrie materiasArbolTrie;
    private dict<String,Alumno> luAAlumno;
    private dict<String,Int> cantidadDeInscriptosMateria;
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
        this.ListalibretasUniversitarias = libretasUniversitarias;
    }

    public void inscribir(String estudiante, String carrera, String materia){
        if (this.libretasArbolTrie.buscar(estudiante) == True){
            this.AlumnoActual = estudiante;
        }
        else {
            nuevoAlumno = new Alumno(estudiante,carrera,0);
            this.libretasArbolTrie.agregar(estudiante)
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
    public Boolean pertence(T[] s, T e) {
        for( T t : s){
            if (t == e){
                return True;
            }
        }
        return False;
    }

    public String[] materias(String carrera){
        Set<String> todasLasMaterias = new HashSet<>();
        for(ParCarreraMateria par : this.infoMaterias){
            if (carrera == par.getCarrera()){
                todasLasMaterias.add(par.getNombreMateria());
            }
        }
        return todasLasCarreras.toArray(new String[0]);;	    
    }

    public int materiasInscriptas(String estudiante){
        ;	    
    }
}
