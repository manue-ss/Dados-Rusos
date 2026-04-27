package co.edu.udistrital.model;

import java.util.Random;

/**
 * Representa un dado virtual con un número configurable de caras. Utilizado
 * para generar resultados aleatorios.
 *
 * @author Manuel Salazar
 */
public class Dado {

    /**
     * Número de caras que tiene el dado (ej. 6 para un dado tradicional).
     */
    private final int caras;

    /**
     * Generador de números aleatorios. Se instancia una sola vez para evitar la
     * repetición de secuencias si se lanza el dado múltiples veces en el mismo
     * milisegundo.
     */
    private final Random aleatorio;

    /**
     * Constructor de la clase Dado.
     *
     * @param caras La cantidad de caras del dado (debe ser mayor o igual a 4).
     */
    public Dado(int caras) {
        this.caras = caras;
        this.aleatorio = new Random();
    }

    /**
     * Simula el lanzamiento del dado generando un número aleatorio.
     *
     * @return Un número entero aleatorio comprendido entre 1 y el número de
     * caras (inclusive).
     */
    public int lanzar() {
        return 1 + aleatorio.nextInt(caras);
    }
}
