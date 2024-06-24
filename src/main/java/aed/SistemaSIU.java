package aed;

import java.util.ArrayList;

import javax.sound.midi.MidiDevice.Info;

public class SistemaSIU {// cuando le pasas el lu te devuelve el nombre del estudiante, su carrera  las materias que esta cursando. 

    private Trie<Alumno> libretasArbolTrie; // cada rama es una libretaUniversitaria que lleva a un objeto de tipo Alumno
    private Trie<Carrera> carrerasArbolTrie;  // cada rama es el nombre de una carrera que lleva a un objeto de tipo Carrera. Aca vamos a meter todos los elementos de InfoMaterias 

    enum CargoDocente{
        PROF,
        JTP,
        AY1,
        AY2
    }

    

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        carrerasArbolTrie = new Trie<Carrera>();
        libretasArbolTrie = new Trie<Alumno>();
        for (InfoMateria InfoMateria : infoMaterias){
            int[] plantelDocenteInicial = new int[4];
            InfoMateria infoMateria = InfoMateria;
            Materia materia = new Materia(0, plantelDocenteInicial, 0, infoMateria); // creo la materia con la infoMateria correspondiente

            for (ParCarreraMateria par : InfoMateria.getParesCarreraMateria()){
                String nombreCarrera = par.getNombreCarrera();
                String nombreMateria = par.getNombreMateria();

                if (carrerasArbolTrie.buscar(nombreCarrera) == null){
                    Carrera nuevaCarrera = new Carrera(nombreCarrera);      // si la carrera todavía no existe, la creo
                    carrerasArbolTrie.agregar(nombreCarrera, nuevaCarrera);
                }
                
                // Agrego la materia a la carrera. 
                carrerasArbolTrie.buscar(nombreCarrera).getMaterias().agregar(nombreMateria, materia);
                
                // Como con todos los pares agrego el mismo objeto "materia", 
                // lo que le ocurra a "Algoritmos1" en "Ciencias de Datos" también le va a ocurrir a "Intro a la Programación" en "Ciencias de la Computación", por dar un ejemplo
            }
        }

        for (String libreta : libretasUniversitarias){
            Alumno alumno = new Alumno(libreta, 0);
            this.libretasArbolTrie.agregar(libreta, alumno);
        }
    }



    public void inscribir(String estudiante, String carrera, String materia){
        libretasArbolTrie.buscar(estudiante).sumarMateria();
        carrerasArbolTrie.buscar(carrera).getMaterias().buscar(materia).sumarInscripto();
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){// .ordinal() supuestamente devuelve el indice de su posicion enumerada 
        carrerasArbolTrie.buscar(carrera).getMaterias().buscar(materia).getDocentes()[cargo.ordinal()] += 1;
    }

    public int[] plantelDocente(String materia, String carrera){ // JAJAJAJAJAJJAJAJAJAJAJAJJAJAJAJAJAJA AJAJAJJAJAJAJAJAJAJAJAJAJAJAJAJAJAJJAJAJAJAJ ME HABIA OLVIDADO DE PONER .BUSCAR
        return carrerasArbolTrie.buscar(carrera).getMaterias().buscar(materia).getDocentes();
    }

    public void cerrarMateria(String materia, String carrera){   
    }

    public int inscriptos(String materia, String carrera){
        return carrerasArbolTrie.buscar(carrera).getMaterias().buscar(materia).getcantInscriptos();    
    }

    public boolean excedeCupo(String materia, String carrera){ 
        return carrerasArbolTrie.buscar(carrera).getMaterias().buscar(materia).getcantInscriptos() > carrerasArbolTrie.buscar(carrera).getMaterias().buscar(materia).calcularCupo(); // La especificación no coincide con lo que esperan los tests
    }

    public String[] carreras(){
        String[] listaCarreras = new String[0];

        

        return listaCarreras;
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");    
    }
}
