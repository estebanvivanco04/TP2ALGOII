package aed;
import java.util.ArrayList;

//InvRep: (libretasTrie != null) && (carrerasTrie != null) 

public class SistemaSIU {

    private Trie<Alumno> libretasTrie; // cada rama es una LU que lleva a un objeto de tipo Alumno. Buscar, agregar y eliminar en este Trie es O(1) porque las claves son acotadas
    private Trie<Carrera> carrerasTrie;// cada rama es el nombre de una carrera que lleva a un objeto de tipo Carrera.

    enum CargoDocente{
        PROF,
        JTP,
        AY1,
        AY2
    }


    
    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){ // O(\sum de (|c| * |Mc|) por cada c en C  +  \sum de (\sum de |n| por cada n en Nm) por cada m en M + E)
        carrerasTrie = new Trie<Carrera>();
        libretasTrie = new Trie<Alumno>();
        for (InfoMateria InfoMateria : infoMaterias){// #iteraciones = #materiasDistintas
            int[] plantelDocenteInicial = new int[4];

            // creo la materia con la infoMateria correspondiente
            Materia materia = new Materia(0, plantelDocenteInicial, 0, InfoMateria);

            for (ParCarreraMateria par : InfoMateria.getParesCarreraMateria()){// #iteraciones = #nombresDeLaMateria
                String nombreCarrera = par.getNombreCarrera();
                String nombreMateria = par.getNombreMateria();
                Carrera nuevaCarrera = new Carrera(nombreCarrera);
                
                // si la carrera todavía no existe, la creo
                if (carrerasTrie.buscar(nombreCarrera) == null){ // este if se va a ejecutar #carreras veces
                    carrerasTrie.agregar(nombreCarrera, nuevaCarrera);// O(|c|)
                }
                carrerasTrie.buscar(nombreCarrera).getMaterias().agregar(nombreMateria, materia);// O(|c| + 1 + |n|) = O(|c| + |n|)
                MateriaParaCarrera mateParaCarre = new MateriaParaCarrera(carrerasTrie.buscar(nombreCarrera), nombreMateria);// O(|c|)
                carrerasTrie.buscar(nombreCarrera).getMaterias().buscar(nombreMateria).getCarreras().add(mateParaCarre);// O(|c| + 1 + |n| + 1 + 1) = O(|c| + |n|)
            }
                
                // Como a todos los pares les agrego el mismo objeto "materia", 
                // lo que le ocurra a "Algoritmos1" en "Ciencias de Datos"
                // también le va a ocurrir a "Intro a la Programación" en "Ciencias de la Computación", por dar un ejemplo
        }
        // La línea 35 se va a ejecutar #carreras veces independientemente de lo for's ya que sólo agrega
        // la carrera al Trie de carreras si no existe previamente. Como busca la carrera y ocasionalmente la agrega,
        // la complejidad de este paso es O(\sum de |c| por cada c en C)

        //  Línea 37: agregamos la Materia al Trie de materias de la Carrera, esto se va a ejecutar una vez por cada materia de cada carrera
        //  Línea 39: agregamos el MateriaParaCarrera en "carrerasDeLaMateria" de la Materia, esto se va a ejecutar una vez por cada nombre de cada materia
        // Como la cantidad de nombres de todas las materias es igual a la cantidad de materias de todas las carreras, la complejidad resulta
        // O(\sum de (|c| * |Mc|) por cada c en C  +  \sum de (\sum de |n| por cada n en Nm) por cada m en M )

        for (String libreta : libretasUniversitarias){ // #iteraciones = E
            Alumno alumno = new Alumno(libreta, 0);
            this.libretasTrie.agregar(libreta, alumno);// O(1)
        }
    }



    public void inscribir(String estudiante, String carrera, String materia){// O(1 + |c| + |m|) = O(|c| + |m|)
        libretasTrie.buscar(estudiante).inscribirAMateria(carrera, materia, carrerasTrie);
    }



    public int inscriptos(String materia, String carrera){ // O(|c| + 1 + |m| + 1) = O(|c| + |m|)
        return carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getCantInscriptos();    
    }



    public void agregarDocente(CargoDocente cargo, String carrera, String materia){ // O(1 + |c| + 1 + |m| + 1) = O(|c| + |m|)
        carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getDocentes()[cargo.ordinal()] += 1;
    }



    public int[] plantelDocente(String materia, String carrera){ // O(|c| + 1 + |m| + 1) = O(|c| + |m|)
        return carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getDocentes();
    }



    public boolean excedeCupo(String materia, String carrera){ // O(2 * (|c| + 1 |m| + 1)) = O(|c| + |m|)
        return carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getCantInscriptos() > carrerasTrie.buscar(carrera).getMaterias().buscar(materia).calcularCupo(); // La especificación no coincide con lo que esperan los tests
    }



    public String[] carreras(){ // O(1 + 1 + sum de |c| por cada c en C + 1) = O(sum de |c| por cada c en C)
        ArrayList<String> listaCarreras = new ArrayList<String>();
        NodoTrie<Carrera> actual = carrerasTrie.getRaiz();

        inOrderCarrerasRecursivo(listaCarreras, actual);

        return listaCarreras.toArray(new String[0]);
    }

    private void inOrderCarrerasRecursivo(ArrayList<String> listaCarreras, NodoTrie<Carrera> actual){// O(sum de |c| por cada c en C)

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
    // inOrderCarrerasRecursivo() en su totalidad se va a ejecutar en O(# de hijos de todos los nodos no nulos del Trie).
    // Como cada nodo tiene 256 hijos, es lo mismo que decir en O(256 * #nodos no nulos del Trie) = O(#nodos no nulos del Trie) 
    // por lo que en el peor caso es O(sumatoria de las longitudes de todos los nombres definidos en el Trie)
    // que es igual a O(\sum de |c| por cada c en C)



    public String[] materias(String carrera){ // O(|c| + \sum de |mc|  por cada mc en Mc)
        ArrayList<String> listaMaterias = new ArrayList<String>();
        NodoTrie<Materia> actual = carrerasTrie.buscar(carrera).getMaterias().getRaiz(); // O(|c| + 1 + 1) = O(|c|)

        inOrderMateriasRecursivo(listaMaterias, actual, actual.getLetra());

        return listaMaterias.toArray(new String[0]); // O(\sum de 1 por cada mc en Mc)
    }

    private void inOrderMateriasRecursivo(ArrayList<String> listaMaterias, NodoTrie<Materia> actual, String nombreMateria){ // O(sum de |mc| por cada mc en Mc)

        for (int i = 0; i < 256; i++){

            if(actual.getHijos().get(i) != null){  // si el i-ésimo hijo de actual no es null, comienzo a formar o sigo formando el nombre de la materia,
                                                   // , luego chequeo esFinPalabra y llamo a la función con este mismo hijo.
                String nombre = new String();
                nombre = nombreMateria + actual.getHijos().get(i).getLetra();

                 if(actual.getHijos().get(i).esFinPalabra()){ // esFinPalabra() == true <-> el i-ésimo hijo "apunta" a un objeto de tipo Materia
                    listaMaterias.add(nombre); // agrego el nombre de la materia a la lista: O(1)
                 }
 
                 inOrderMateriasRecursivo(listaMaterias, actual.getHijos().get(i), nombre); // si todos los hijos son null, entonces este hijo es una hoja
                                                                                            // y este llamado de función se va a ejecutar en O(256) = O(1)
                                                                                    
             }
         }
    }
    // inOrderMateriasasRecursivo() en su totalidad se va a ejecutar en O(# de hijos de todos los nodos no nulos del Trie).
    // Como cada nodo tiene 256 hijos, es lo mismo que decir en O(256 * #nodos no nulos del Trie) = O(#nodos no nulos del Trie) 
    // por lo que en el peor caso es O(sumatoria de las longitudes de todos los nombres definidos en el Trie)
    // que es igual a O(sum de |mc| por cada mc en Mc)



    public int materiasInscriptas(String estudiante){ // O(1 + 1) = O(1)
        return libretasTrie.buscar(estudiante).getCantMat();
    }



    public void cerrarMateria(String materia, String carrera){ // O(|c| + |m| + \sum de |n| por cada n en Nm + Em)

        ArrayList<Alumno> alumnosADesinscribir = carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getAlumnosInscriptos(); // O(|c| + 1 + |m| + 1) = O(|c| + |m|)

        for (Alumno alumnoADesinscribir : alumnosADesinscribir){ //#iteraciones = Materia.getCantInscriptos() = Em
            alumnoADesinscribir.desinscribirDeMateria(); // O(1)
        }

        // Primero accedo a la carrera que se pasó como input,
        // luego busco la materia y de ahí obtengo las distintas carreras en las que está
        ArrayList<MateriaParaCarrera> carrerasAEditar = carrerasTrie.buscar(carrera).getMaterias().buscar(materia).getCarreras(); // O(|c| + 1 + |m| + 1) = O(|c| + |m|)

        for (MateriaParaCarrera carre : carrerasAEditar){ // #iteraciones = Materia.getCarreras().size(), esto  es igual a \sum de 1 por cada n en Nm
                carre.getCarrera().getMaterias().eliminar(carre.getMateria()); // O(1 + 1 + 1 + |n|) = O(|n|)
        }
    }
}