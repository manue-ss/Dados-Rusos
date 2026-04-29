document.addEventListener("DOMContentLoaded", () => {
    distribuirJugadores();
});

/**
 * Distribuye las cartas de los jugadores de forma equidistante
 * en sentido horario alrededor de la mesa circular.
 */
function distribuirJugadores() {
    const cartas = document.querySelectorAll(".player-card");
    const totalJugadores = cartas.length;

    if (totalJugadores === 0) return;

    // --- CONFIGURACIÓN DE LA MESA ---
    // El tamaño de tu mesa en CSS es de 450x450
    const centroX = 450 / 2;
    const centroY = 450 / 2;

    // Radio del círculo imaginario donde se sentarán los jugadores.
    const radio = 225;

    // --- CONFIGURACIÓN DE LAS CARTAS ---
    const anchoCarta = 80;
    const altoCarta = 100;

    // Ángulo de separación entre cada jugador en radianes (Círculo completo = 2 * PI)
    const pasoAngulo = (2 * Math.PI) / totalJugadores;

    // Iteramos sobre cada carta para calcular su posición
    cartas.forEach((carta, index) => {
        // Calculamos el ángulo para este jugador.
        const angulo = -(Math.PI / 2) + index * pasoAngulo;

        // Fórmula paramétrica del círculo: x = h + r * cos(θ), y = k + r * sin(θ)
        let posX = centroX + radio * Math.cos(angulo);
        let posY = centroY + radio * Math.sin(angulo);

        posX = posX - anchoCarta / 2;
        posY = posY - altoCarta / 2;

        // Aplicamos las coordenadas al CSS
        carta.style.left = `${posX}px`;
        carta.style.top = `${posY}px`;
    });
}
