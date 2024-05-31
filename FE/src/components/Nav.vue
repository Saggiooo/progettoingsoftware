<template>
    <div class="navigatore">
      <div class="navbox1">
        <h1 class="titoloPagina">{{ pageTitle }}</h1>
      </div>
      <div class="navbox2">
        <div class="user-info">
          <div>
            <p>Filippo Fontana</p>
            <p class="company-name">Nome Azienda Eventi</p>
          </div>
          <img src="@/assets/img/fotouseresempio.png" alt="Immagine Utente">
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        pageTitle: '' // Inizialmente vuoto, verrà popolato dinamicamente
      };
    },
    mounted() {
      // Ottieni l'URL della pagina corrente
      const urlPagina = window.location.pathname;
  
      // Verifica l'URL della pagina e imposta il testo di conseguenza
      if (urlPagina === "/dashboard-statistiche") {
        this.pageTitle = "Statistiche Generali";
      } else if (urlPagina === "/dashboard-eventi") {
        this.pageTitle = "Tutti gli Eventi";
      } else if (urlPagina === "/dashboard-impostazioni") {
        this.pageTitle = "Impostazioni";
      } else if (urlPagina === "/dashboard-clienti") {
        this.pageTitle = "Informazioni sui Clienti";
      } else if (urlPagina === "/dashboard-gestione-ads") {
        this.pageTitle = "Gestione della Pubblicità";
      } else {
        // Pagina sconosciuta, impostare un testo predefinito
        this.pageTitle = "Pagina sconosciuta";
      }
    }
  };
  </script>
  
  <style>
  .titoloPagina {
    font-weight: 600;
    color: var(--nero);
  }
  
  .user-info {
    display: flex;
    align-items: center;
    text-align: right;
    color: var(--nero);
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
  
  .navigatore {
    position: relative;
    width: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
  
  .navbox1 {
    position: relative;
    padding: 30px;
    display: flex;
    justify-content: start;
  }
  
  .navbox2 {
    position: relative;
    padding: 30px;
    display: flex;
    justify-content: end;
  }
  </style>
  