n<template>
  <div class="sidebar">
    <div>
        <a href="/"><img src="@/assets/img/logosocial.png" alt="Logo del Social" class="logo"></img></a>
    </div>
    <div>
    <ul>
        <li :class="{ active: isRouteActive('/dashboard-statistiche') }"><a href="/dashboard-statistiche"><img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="icona"><span class="title">Statistiche generali</span></a></li>
        <li :class="{ active: isRouteActive('/dashboard-eventi') }"><a href="/dashboard-eventi"><img src="@/assets/img/eventi.png" alt="Icona Gestione Eventi" class="icona"><span class="title">Tutti gli eventi</span></a></li>
        <li :class="{ active: isRouteActive('/dashboard-clienti') }"><a href="/dashboard-clienti"><img src="@/assets/img/clienti.png" alt="Icona Gestione Clienti" class="icona"><span class="title">Informazioni sui Clienti</span></a></li>
        <li :class="{ active: isRouteActive('/dashboard-gestione-ads')}"><a href="/dashboard-gestione-ads"><img src="@/assets/img/ad.png" alt="Icona pubblicità" class="icona"><span class="title">Gestione pubblicità</span></a></li>
        <li :class="{ active: isRouteActive('/dashboard-impostazioni')}"><a href="/dashboard-impostazioni"><img src="@/assets/img/impostazioni.png" alt="Icona Impostazioni" class="icona"><span class="title">Impostazioni</span></a></li>
        <li><a href="#"><img src="@/assets/img/esci.png" alt="Icona Impostazioni" class="icona"><span class="title">Esci</span></a></li>
    </ul>
    </div>
    </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

// inizio - NON IN USO - per cambiare pagina senza ricaricare
// esempio - <li :class="{ active: isRouteActive('/') }"><a href="/" @click.prevent="navigaVersoPagina1"> ...
/*const navigaVersoPagina2 = () => {
    console.log('Cliccato su link per navigare alla Eventi');
  router.push('/dashboard-eventi');
};
const navigaVersoPagina1 = () => {
    console.log('Cliccato su link per navigare alla Pagina Home');
  router.push('/dashboard-statistiche');
};*/

const isRouteActive = (route: string) => {
  return router.currentRoute.value.path === route;
};
</script>