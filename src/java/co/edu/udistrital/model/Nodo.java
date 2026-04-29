package co.edu.udistrital.model;

/**
 *
 * Representa el componente básico de la estructura de datos. Cada nodo 
 * almacena la información de un jugador y mantiene una referencia hacia el 
 * siguiente elemento, permitiendo la construcción de la lista circular.
 *
 * @author Manuel Salazar
 * @author Sebastian Guzman
 */
public class Nodo {

    /**
     * El valor o nombre del jugador almacenado en este nodo.
     */
    private String dato;
    
    /**
     * Puntero o referencia al siguiente nodo en la secuencia.
     */
    private Nodo siguiente;

    /**
     * Constructor de la clase Nodo. Inicializa un nuevo nodo con el
     * dato proporcionado y establece el enlace siguiente como nulo
     * por defecto.
     * 
     * @param dato El nombre del jugador a almacenar.
     */
    public Nodo(String dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     * 
     * @return El valor (String) contenido en el nodo.
     */
    public String getDato() {
        return dato;
    }

    /**
     * Actualiza el dato almacenado en el nodo.
     * 
     * @param dato El nuevo valor o nombre a asignar.
     */
    public void setDato(String dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo de la lista.
     * 
     * @return El objeto {@code Nodo} al que apunta este elemento.
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el enlace hacia el siguiente nodo.
     * 
     * @param siguiente El nodo que será el sucesor en la lista.
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Retorna una representación en texto del nodo.
     * 
     * @return El dato almacenado convertido a {@code String}
     */
    @Override
    public String toString() {
        return dato;
    }
}
