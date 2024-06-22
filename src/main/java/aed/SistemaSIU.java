package aed;

public class SistemaSIU {

    private ArbolTrie lu; // cuando le pasas el lu te devuelve el nombre del estudiante, su carrera  las materias que esta cursando.

    private ArbolTrie libretasArbolTrie;
    private ArbolTrie materiasArbolTrie;
    private Tuple<String,Carrera, Integer> Alumno;
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
        this.libretasArbolTrie.agregar(estudiante);
        this.estudiantesArbolTrie.agregar()

    }
    public Integer cantidadMateriasEstudiante(String estudiante){
        this.estudiantesArbolTrie.estudiante()
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
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

}
