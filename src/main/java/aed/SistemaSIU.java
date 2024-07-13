package aed;

import java.util.ArrayList;
public class SistemaSIU {

    private Trie<Alumno> libretasTrie; // cada rama es una LU que lleva a un objeto de tipo Alumno. Buscar en este trie es O(1) porque las claves son acotadas
    private Trie<Carrera> carrerasTrie;// cada rama es el nombre de una carrera que lleva a un objeto de tipo Carrera.

    enum CargoDocente{
        PROF,
        JTP,
        AY1,
        AY2
    }

    

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        carrerasTrie = new Trie<Carrera>();
        libretasTrie = new Trie<Alumno>();
        for (InfoMateria InfoMateria : infoMaterias){ // O(cantidad de materias)
            int[] plantelDocenteInicial = new int[4];

            // creo la materia con la infoMateria correspondiente
            Materia materia = new Materia(0, plantelDocenteInicial, 0, InfoMateria);

            for (ParCarreraMateria par : InfoMateria.getParesCarreraMateria()){
                String nombreCarrera = par.getNombreCarrera();
                String nombreMateria = par.getNombreMateria();

                // si la carrera todavía no existe, la creo
                if (carrerasTrie.buscar(nombreCarrera) == null){
                    Carrera nuevaCarrera = new Carrera(nombreCarrera);
                    carrerasTrie.agregar(nombreCarrera, nuevaCarrera);
                }
                
                // Agrego la materia a la carrera. 
                carrerasTrie.buscar(nombreCarrera).getMaterias().agregar(nombreMateria, materia);
                
                // Como a todos los pares les agrego el mismo objeto "materia", 
                // lo que le ocurra a "Algoritmos1" en "Ciencias de Datos" también le va a ocurrir a "Intro a la Programación" en "Ciencias de la Computación", por dar un ejemplo
            }
        }
        

        for (String libreta : libretasUniversitarias){
            Alumno alumno = new Alumno(libreta, 0);
            this.libretasTrie.agregar(libreta, alumno);
        }
    }



    public void inscribir(String estudiante, String carrera, String materia){// O(1 + |c| + |m|) = O(|c| + |m|)
        libretasTrie.buscar(estudiante).inscribirAMateria(carrera, materia, carrerasTrie);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){ // O(1 + |c| + 1 + |m| + 1) = O(|c| + |m|)
        carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getDocentes()[cargo.ordinal()] += 1;
    }

    public int[] plantelDocente(String materia, String carrera){ // O(|c| + 1 + |m| + 1) = O(|c| + |m|)
        return carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getDocentes();
    }

    public void cerrarMateria(String materia, String carrera){
        ArrayList<Alumno> alumnosADesinscribir = carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getAlumnosInscriptos();
        for (Alumno alumnoADesinscribir : alumnosADesinscribir){ // O(|c| + 1 + |m|) = O(|n| + |m|) este for se va a hacer cantidadDeInscriptos() veces.
            alumnoADesinscribir.desinscribirDeMateria();
        }

        InfoMateria infoMateria = carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getInfoMateria(); // O(|c| + O(1) + O|m|) = O(|c| + |m|)
        
        for (ParCarreraMateria par : infoMateria.getParesCarreraMateria()){ // O(cantidadDeParesCarreraMateria)
            carrerasTrie.buscar(par.getNombreCarrera()).getMaterias().eliminar(materia);
        }

    }

    public int inscriptos(String materia, String carrera){ // O(|c| + 1 + |m| + 1) = O(|c| + |m|)
        return carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getCantInscriptos();    
    }

    public boolean excedeCupo(String materia, String carrera){ // O(2 * (|c| + 1 |m| + 1)) = O(|c| + |m|)
        return carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getCantInscriptos() > carrerasTrie.buscar(carrera).getMaterias().buscar(materia).calcularCupo(); // La especificación no coincide con lo que esperan los tests
    }

    public String[] carreras(){
        String[] listaCarreras = new String[0];

        

        return listaCarreras;
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){ // O(1 + 1) = O(1)
        return libretasTrie.buscar(estudiante).getCantMat();    
    }
}

// INVARIANTES

// Alumno: longitud de "lu" > 0 && cantidadDeMaterias >= 0  Trie: existe raiz && raiz es NodoTrie && Si letra es fin de palabra -> es True.  NodoTrie: todo Hijo tiene padre

//String carrera tiene longitud > 0
//Trie<Materia> materiasDeLaCarrera tiene al menos una rama (materia)
//cupo es >= 0
//int[] docentes es al menos >= 0 en sus 4 posiciones respectivamente
//cantInscriptos es >= 0