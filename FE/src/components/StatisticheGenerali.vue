<template>
    <div class="sidebar">
    <div>
        <img src="./images/logosocial.png" alt="Logo del Social" class="logo"></img>
    </div>
    <ul>
        <li><a href="#"><img src="./images/icon-statistiche.png" alt="Icona Statistiche" class="icona">Statistiche generali</a></li>
        <li><a href="#"><img src="./images/eventi.png" alt="Icona Gestione Eventi" class="icona">Tutti gli eventi</a></li>
        <li><a href="#"><img src="./images/clienti.png" alt="Icona Gestione Clienti" class="icona">Informazioni sui Clienti</a></li>
        <li><a href="#"><img src="./images/ad.png" alt="Icona pubblicità" class="icona">Gestione pubblicità</a></li>
        <li><a href="#"><img src="./images/impostazioni.png" alt="Icona Impostazioni" class="icona">Impostazioni</a></li>
        <li><a href="#"><img src="./images/esci.png" alt="Icona Impostazioni" class="icona">Esci</a></li>
    </ul>
</div>
<div class="content">
    <h1 class="main-title">Statistiche generali</h1>
    <div class="box-sx">
        <h2>Main Box</h2>
        <!-- Contenuto principale vuoto -->
    </div>
    <div class="box-vuoto">
        <div class="box-sx">
        <h2>Numero di Utenti</h2>
        <p>1000</p>
    </div>
    <div class="box-dx">
        <h2>Numero di Eventi</h2>
        <p>500</p>
    </div>
    <div class="box-sx">
        <h2>Numero di Clienti</h2>
        <p>200</p>
    </div>
    <div class="box-dx">
        <h2>Pubblicità attive</h2>
        <p>10</p>
    </div>
    </div>
    <div class="box-sx">
        <!-- Box vuoto -->
    </div>
</div>
</template>

<style>
     body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }
    .sidebar {
        margin: 10px;
        width: 250px;
        height: 95vh;
        background-color: #1a1a1a;
        position: fixed;
        left: 0;
        top: 0;
        padding-top: 20px;
        overflow-y: auto;
        border-radius: 20px;
        text-align: center;
    }
    .sidebar ul {
        list-style-type: none;
        padding: 0;
        margin: 20px;
    }
    .sidebar li {
        padding: 15px 20px;
        margin-bottom: 10px;
        border-radius: 10px;
        font-size: 0.82em;
        background-color: #333;
        display: flex; 
        align-items: center;
    }
    .sidebar li:last-child {
        border-bottom: none;
    }
    .sidebar li a {
        color: #fff;
        text-decoration: none;
        display: block;
    }
    .sidebar li:hover {
        background-color: #595959;
    }
    .sidebar li:active {
        background-color: #595959;
    }
    .content {
        margin-left: 250px;
        padding: 20px;
    }
    .main-title {
        text-align: left;
        margin-bottom: 20px;
        color: #2e2e2e;
    }
    .box-sx {
        background-color: #fff;
        margin: 5px;
        width: 47%;
        height: 47%;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        box-sizing: border-box;
        float: left;
        
    }
    .box-dx {
        background-color: #fff;
        width: 47%;
        height: 47%;
        margin: 5px;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        box-sizing: border-box;
        float: right;
        
    }

    .box-vuoto {
        background-color: #fff;
        width: 47%;
        height: 47%;
        box-sizing: border-box;
        float: right;
        
    }

    .icona{
        width: 16px; 
        height: 16px;
        margin-right: 10px;
        vertical-align: middle;
    }

    .logo{
        display: block;
        margin: auto;
        margin-bottom: 30px;
        width: 170px;
    }
  
</style>