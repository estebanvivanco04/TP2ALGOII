package aed;
import java.util.ArrayList;

//InvRep: (libretasTrie != null) && (carrerasTrie != null) 

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
        for (InfoMateria InfoMateria : infoMaterias){
            int[] plantelDocenteInicial = new int[4];

            // creo la materia con la infoMateria correspondiente
            Materia materia = new Materia(0, plantelDocenteInicial, 0, InfoMateria);

            for (ParCarreraMateria par : InfoMateria.getParesCarreraMateria()){
                String nombreCarrera = par.getNombreCarrera();
                String nombreMateria = par.getNombreMateria();
                Carrera nuevaCarrera = new Carrera(nombreCarrera);
                
                // si la carrera todavía no existe, la creo
                if (carrerasTrie.buscar(nombreCarrera) == null){// 
                    carrerasTrie.agregar(nombreCarrera, nuevaCarrera);
                }
                carrerasTrie.buscar(nombreCarrera).getMaterias().agregar(nombreMateria, materia);
                MateriaParaCarrera mateParaCarre = new MateriaParaCarrera(carrerasTrie.buscar(nombreCarrera), nombreMateria);
                carrerasTrie.buscar(nombreCarrera).getMaterias().buscar(nombreMateria).getCarreras().add(mateParaCarre);
            }
                
                // Como a todos los pares les agrego el mismo objeto "materia", 
                // lo que le ocurra a "Algoritmos1" en "Ciencias de Datos"
                // también le va a ocurrir a "Intro a la Programación" en "Ciencias de la Computación", por dar un ejemplo
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

        ArrayList<Alumno> alumnosADesinscribir = carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getAlumnosInscriptos(); // O(|c| + 1 + |m| + 1) = O(|n| + |m|)

        for (Alumno alumnoADesinscribir : alumnosADesinscribir){ // este for se va a hacer cantidadDeInscriptos() veces.
            alumnoADesinscribir.desinscribirDeMateria();
        }
        //primero entro a la carrera principal
        // luego busco la materia y de ahí obtengo las distintas carreras en las que esta
         // O(|c| + O(1) + O|m|) = O(|c| + |m|)
        ArrayList<MateriaParaCarrera> carrerasAEditar = carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getCarreras(); // O(|c| + |m|)
        for (MateriaParaCarrera carre : carrerasAEditar){
                carre.getCarrera().getMaterias().eliminar(carre.getMateria());
        }
    }

    public int inscriptos(String materia, String carrera){ // O(|c| + 1 + |m| + 1) = O(|c| + |m|)
        return carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getCantInscriptos();    
    }

    public boolean excedeCupo(String materia, String carrera){ // O(2 * (|c| + 1 |m| + 1)) = O(|c| + |m|)
        return carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getCantInscriptos() > carrerasTrie.buscar(carrera).getMaterias().buscar(materia).calcularCupo(); // La especificación no coincide con lo que esperan los tests
    }

    public String[] carreras(){ // O(1 + 1 + x + 1) = O()
        ArrayList<String> listaCarreras = new ArrayList<String>();
        NodoTrie<Carrera> actual = carrerasTrie.getRaiz();

        inOrderCarrerasRecursivo(listaCarreras, actual);

        return listaCarreras.toArray(new String[0]);
    }

    private void inOrderCarrerasRecursivo(ArrayList<String> listaCarreras, NodoTrie<Carrera> actual){

        for (int i = 0; i < 256; i++){

           if(actual.getHijos().get(i) != null){ // si el i-ésimo hijo de actual no es null, chequeo esFinPalabra y llamo a la función con este mismo hijo

                if(actual.getHijos().get(i).esFinPalabra()){ // esFinPalabra() == true <-> el i-ésimo hijo "apunta" a un objeto de tipo Carrera
                    listaCarreras.add(actual.getHijos().get(i).getInfo().getNombreCarrera()); // agrego el nombre de la carrera a la lista: O(1)
                }

                inOrderCarrerasRecursivo(listaCarreras, actual.getHijos().get(i)); // si todos los hijos son null, entonces este hijo es una hoja
                                                                                   // y este llamado de función se va a ejecutar en O(256) = O(1)
            }

        }
    }
    // inOrderCarrerasRecursivo() en su totalidad se va a ejecutar en O(# de hijos de todos los nodos no nulos del Trie)
    // como cada nodo tiene 256 hijos, es lo mismo que decir en O(256 * #nodos no nulos del Trie) = O(#nodos no nulos del Trie) 
    // por lo que en el peor caso es O(sumatoria de las longitudes de todos los nombres definidos en el Trie)


    public String[] materias(String carrera){
        ArrayList<String> listaMaterias = new ArrayList<String>();
        NodoTrie<Materia> actual = carrerasTrie.buscar(carrera).getMaterias().getRaiz(); // O(|c| + 1 + 1) = O(|c|)

        inOrderMateriasRecursivo(listaMaterias, actual, actual.getLetra());

        return listaMaterias.toArray(new String[0]);	    
    }

    private void inOrderMateriasRecursivo(ArrayList<String> listaMaterias, NodoTrie<Materia> actual, String nombreMateria){

        for (int i = 0; i < 256; i++){

            if(actual.getHijos().get(i) != null){
                String nombre = new String();
                nombre = nombreMateria + actual.getHijos().get(i).getLetra();

                 if(actual.getHijos().get(i).esFinPalabra()){
                    listaMaterias.add(nombre);
                 }
 
                 inOrderMateriasRecursivo(listaMaterias, actual.getHijos().get(i), nombre);
                                                                                    
             }
         }
    }

    public int materiasInscriptas(String estudiante){ // O(1 + 1) = O(1)
        return libretasTrie.buscar(estudiante).getCantMat();
    }

}