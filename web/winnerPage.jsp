<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>¡Tenemos un Ganador!</title>
        <link rel="stylesheet" href="css/winner-style.css" />
    </head>
    <body>
        <main class="winner-wrapper">
            <div class="winner-card">
                <div class="icon-container">
                    <ion-icon name="trophy"></ion-icon>
                </div>

                <h1>¡Felicidades!</h1>
                <p class="subtitle">El superviviente de la ruleta es:</p>

                <h2 class="winner-name">${Ganador}</h2>

                <form action="index.html" method="GET">
                    <button type="submit" class="btn-restart">
                        <ion-icon name="refresh-outline"></ion-icon>
                        Jugar de Nuevo
                    </button>
                </form>
            </div>
        </main>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    </body>
</html>