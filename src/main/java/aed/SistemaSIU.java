package aed;

import java.util.ArrayList;

public class SistemaSIU {// cuando le pasas el lu te devuelve el nombre del estudiante, su carrera  las materias que esta cursando. 

    private Trie<Alumno> libretasArbolTrie; // cada rama es una libretaUniversitaria que lleva a un objeto de tipo Alumno
    private Trie<Carrera> carrerasArbolTrie;  // cada rama es el nombre de una carrera que lleva a un objeto de tipo Carrera. Aca vamos a meter todos los elementos de InfoMaterias 
    private InfoMateria[] ListaMaterias;
    private String[] ListalibretasUniversitarias;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        for (InfoMateria materia : this.ListaMaterias){
            for (ParCarreraMateria par : materia.getParesCarreraMateria()){
                String nombreCarrera = par.getCarrera();
                String nombreMateria = par.getNombreMateria();
                this.carrerasArbolTrie.agregar(nombreCarrera);
                this.carrerasArbolTrie.buscar(nombreCarrera).agregarMateria(nombreMateria);
            }
        }
        for (String libreta : this.ListalibretasUniversitarias){
            Alumno soloLibreta = new Alumno(libreta, null, 0);
            this.libretasArbolTrie.agregar(libreta, soloLibreta);
        }
    }



    public void inscribir(String estudiante, String carrera, String materia){
        Alumno existeAlumno = this.libretasArbolTrie.buscar(estudiante);
        if (existeAlumno.getCarrera() != null) { // osea, si ya existe el alumno en el SIU solo quiero sumarle uno en su Objeto, a la cantidad de carreras que cursa
            existeAlumno.sumarMateria();
        }
        else {
            existeAlumno.definirCarrera(carrera);
            existeAlumno.sumarMateria();
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
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");    
    }
}
