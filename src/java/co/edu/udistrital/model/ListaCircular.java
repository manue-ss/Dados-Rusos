package co.edu.udistrital.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una estructura de datos de Lista Enlazada Circular personalizada.
 * Diseñada específicamente para gestionar turnos y eliminación en un juego
 * estilo Ruleta Rusa. Mantiene un ciclo continuo de nodos (jugadores)
 * asegurando que el último elemento siempre apunte al primero.
 *
 * @author Manuel Salazar
 * @author Sebastian Guzman
 */
public class ListaCircular {

    /**
     * Referencia al primer nodo de la lista.
     */
    private Nodo cabeza;

    /**
     * Referencia al nodo que tiene el turno actual en el juego.
     */
    private Nodo actual;

    /**
     * Cantidad total de nodos (jugadores) activos en la lista.
     */
    private int tamanio;

    /**
     * Constructor de la clase. Inicializa la lista circular con un primer
     * elemento.
     *
     * @param dato El valor o nombre del primer jugador a insertar en la lista.
     */
    public ListaCircular(String dato) {
        cabeza = new Nodo(dato);
        actual = cabeza;
        actual.setSiguiente(cabeza);
        tamanio = 1;
    }

    /**
     * Añade un nuevo nodo a la lista circular. El nuevo nodo se inserta a
     * continuación del nodo actual y se enlaza nuevamente con la cabeza para
     * mantener la circularidad.
     *
     * @param dato El valor o nombre del jugador a añadir.
     */
    public void add(String dato) {
        Nodo nuevo = new Nodo(dato);
        actual.setSiguiente(nuevo);
        nuevo.setSiguiente(cabeza);
        actual = nuevo;
        tamanio++;
    }

    /**
     * Obtiene la representación en texto del nodo que tiene el turno actual.
     *
     * @return Una cadena de texto (String) con los datos del nodo actual.
     */
    public String getActual() {
        return actual.toString();
    }

    /**
     * Busca y elimina un nodo específico de la lista circular basándose en su
     * dato. Gestiona la reasignación de punteros para evitar la pérdida de
     * circularidad e identifica si el nodo eliminado es la cabeza o el nodo
     * actual.
     *
     * @param dato El valor o nombre del jugador que se desea eliminar de la
     *             ronda.
     *
     * @return {@code true} si el jugador fue encontrado y eliminado
     *         exitosamente, {@code false} si la lista está vacía o el jugador
     *         no existe.
     */
    public boolean remove(String dato) {
        if (tamanio == 0) {
            return false;
        }

        if (tamanio == 1) {
            if (cabeza.getDato().equals(dato)) {
                cabeza = null;
                actual = null;
                tamanio = 0;
                return true;
            }
            return false;
        }

        Nodo anterior = cabeza;
        Nodo temp = cabeza.getSiguiente();

        for (int i = 0; i < tamanio; i++) {
            if (temp.getDato().equals(dato)) {

                anterior.setSiguiente(temp.getSiguiente());

                if (temp == cabeza) {
                    cabeza = temp.getSiguiente();
                }

                if (temp == actual) {
                    actual = temp.getSiguiente();
                }

                temp.setSiguiente(null);
                tamanio--;
                return true;
            }

            anterior = temp;
            temp = temp.getSiguiente();
        }

        return false;
    }

    /**
     * Avanza el puntero del turno actual al siguiente nodo en la lista.
     * Fundamental para iterar a través de los jugadores (ej. al lanzar el
     * dado).
     */
    public void next() {
        actual = actual.getSiguiente();
    }

    /**
     * Recopila y retorna todos los datos de los nodos presentes en la lista.
     * Útil para actualizar interfaces gráficas o consolas de estado.
     *
     * @return Una lista de tipo {@code List<String>} que contiene la
     *         representación en texto de todos los jugadores activos en su
     *         orden actual.
     */
    public List<String> getAll() {
        ArrayList<String> datosNodos = new ArrayList<>();
        Nodo temp = cabeza;

        for (int i = 0; i < tamanio; i++) {
            datosNodos.add(temp.toString());
            temp = temp.getSiguiente();
        }

        return datosNodos;
    }
}
