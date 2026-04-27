<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="es">
    <head>
        <title>Ruleta Rusa - Mesa de Juego</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="css/game-style.css" />
    </head>
    <body>
        <main class="game-wrapper">
            <div class="table-container" id="game-table">
                
                <div class="center-ui">
                    <h1 class="turn-indicator">Turno de:<br><span id="current-player">${Actual}</span></h1>
                    
                    <c:if test="${not empty ResultadoDado}">
                        <p class="dice-result">Sacaste un: <strong>${ResultadoDado}</strong></p>
                    </c:if>

                    <c:if test="${not empty MensajeEliminacion}">
                        <p class="elimination-msg"><ion-icon name="skull"></ion-icon> ${MensajeEliminacion}</p>
                    </c:if>
                    
                    <form action="ThrowDiceServlet" method="POST">
                        <input type="hidden" name="jugadorActual" value="${Actual}">
                        
                        <button type="submit" id="btn-roll" class="btn-roll">
                            <ion-icon name="dice-outline"></ion-icon>
                            Tirar Dado
                        </button>
                    </form>
                </div>

                <c:forEach var="jugador" items="${ArregloJugadores}" varStatus="loop">
                    <article class="player-card ${jugador == Actual ? 'active-turn' : ''}" id="player${loop.index + 1}">
                        <ion-icon name="${jugador == Actual ? 'person' : 'person-outline'}"></ion-icon>
                        <h2 class="player-name">${jugador}</h2>
                    </article>
                </c:forEach>

            </div>
        </main>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="js/game-script.js"></script>
    </body>
</html>