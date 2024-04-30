<template>
    <div class="sidebar">
    <div>
        <img src="@/assets/img/logosocial.png" alt="Logo del Social" class="logo"></img>
    </div>
    <ul>
        <li :class="{ active: isRouteActive('/') }"><a href="/" @click.prevent="navigaVersoPagina1"><img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="icona">Statistiche generali</a></li>
        <li :class="{ active: isRouteActive('/eventi') }"><a href="/eventi" @click.prevent="navigaVersoPagina2"><img src="@/assets/img/eventi.png" alt="Icona Gestione Eventi" class="icona">Tutti gli eventi</a></li>
        <li><a href="#"><img src="@/assets/img/clienti.png" alt="Icona Gestione Clienti" class="icona">Informazioni sui Clienti</a></li>
        <li><a href="#"><img src="@/assets/img/ad.png" alt="Icona pubblicità" class="icona">Gestione pubblicità</a></li>
        <li><a href="#"><img src="@/assets/img/impostazioni.png" alt="Icona Impostazioni" class="icona">Impostazioni</a></li>
        <li><a href="#"><img src="@/assets/img/esci.png" alt="Icona Impostazioni" class="icona">Esci</a></li>
    </ul>
</div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

const navigaVersoPagina2 = () => {
    console.log('Cliccato su link per navigare alla Eventi');
  router.push('/eventi');
};
const navigaVersoPagina1 = () => {
    console.log('Cliccato su link per navigare alla Pagina Home');
  router.push('/');
};
const isRouteActive = (route: string) => {
  return router.currentRoute.value.path === route;
};
</script>
<style>
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
    .sidebar li.active {
        background-color: #646464;
        padding: 20px 20px;
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