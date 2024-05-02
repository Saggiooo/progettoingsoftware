import { createRouter, createWebHistory } from 'vue-router';
import StatGenerali from './pages/StatGenerali.vue';
import TuttigliEventi from './pages/Tuttiglieventi.vue';
import InfoClienti from './pages/InformazioniClienti.vue';
import Impostazioni from './pages/Impostazioni.vue';
import GestioneAd from './pages/GestioneAd.vue';

const routes = [
  { path: '/dashboard-statistiche', component: StatGenerali },
  { path: '/dashboard-eventi', component: TuttigliEventi },
  { path: '/dashboard-clienti', component: InfoClienti },
  { path: '/dashboard-gestione-ads', component: GestioneAd },
  { path: '/dashboard-impostazioni', component: Impostazioni },
  { path: '/', redirect: '/dashboard-statistiche' }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;

