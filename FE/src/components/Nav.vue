<template>
     <div>
        <h1 id="testoVariabile"></h1>
    </div>
            <div class="user-info">
                <div>
                    <p>Filippo Fontana</p>
                    <p class="company-name">Nome Azienda Eventi</p>
                </div>
                 <img src="@/assets/img/fotouseresempio.png" alt="Immagine Utente">
                
            </div>
</template>

<style>
.user-info{
    display: flex;
    align-items: center;
    text-align: right;
}

.user-info img {
    width: 55px;
    height: 55px;
    border-radius: 50%;
    margin-left: 10px;
}

.user-info p {
    font-size: 21px;
    margin: 0;
}

.company-name {
    font-size: 14px !important;
    color: #BC6F35;
}
</style>

<script>
 // Ottieni l'URL della pagina corrente
 var urlPagina = window.location.pathname;

// Variabile JavaScript per il testo in base all'URL della pagina
var testo;

// Verifica l'URL della pagina e imposta il testo di conseguenza
if (urlPagina === "/") {
    testo = "Statistiche Generali";
} else if (urlPagina === "/eventi") {
    testo = "Tutti gli Eventi";
} else {
    // Pagina sconosciuta, impostare un testo predefinito
    testo = "Pagina sconosciuta";
}

// Aspetta che il DOM sia caricato prima di eseguire il codice JavaScript
document.addEventListener("DOMContentLoaded", function() {
    // Seleziona l'elemento HTML (h1) in cui inserire il testo
    var elementoTesto = document.getElementById("testoVariabile");

    // Assegna il testo alla proprietà innerHTML dell'elemento
    elementoTesto.innerHTML = testo;
});
</script>