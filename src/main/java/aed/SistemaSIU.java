package aed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SistemaSIU {// cuando le pasas el lu te devuelve el nombre del estudiante, su carrera  las materias que esta cursando. hola me llamo joaquin

    private ArbolTrie libretasArbolTrie;
    private ArbolTrie materiasArbolTrie;
    private dict<String,Alumno> luAAlumno = new dict<>(0);
    private dict<String,Materia> datosMateria;
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
        if (this.libretasArbolTrie.buscar(estudiante) ==  true){
            this.luAAlumno[estudiante].sumarMateria();
            this.cantidadDeInscriptosMateria[materia].sumarInsciptos();
        }
        else {
            nuevoAlumno = new Alumno(estudiante,carrera,0);
            this.luAAlumno[estudiante] = nuevoAlumno;
            this.luAAlumno[estudiante].sumarMateria();
            this.cantidadDeInscriptosMateria[materia] = new Materia(0, this.docentes , 0, this.ListaMaterias);
            this.cantidadDeInscriptosMateria[materia].sumarMateria();
            this.materiasArbolTrie.agregar(materia);
            this.libretasArbolTrie.agregar(estudiante);
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
        if (datosMateria[materia].cupo() )	    
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
        Set<String> todasLasMaterias = new HashSet<>();
        for(ParCarreraMateria par : this.ListaMaterias){
            if (carrera == par.getCarrera()){
                todasLasMaterias.add(par.getNombreMateria());
            }
        }
        return ListaMaterias.toArray(new String[0]);;	    
    }

    public int materiasInscriptas(String estudiante){
        ;	    
    }
}
