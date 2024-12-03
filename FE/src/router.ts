import { createRouter, createWebHistory } from 'vue-router';
import StatGenerali from './pages/StatGenerali.vue';
import TuttigliEventi from './pages/Tuttiglieventi.vue';
import InfoClienti from './pages/InformazioniClienti.vue';
import Impostazioni from './pages/Impostazioni.vue';
import GestioneAd from './pages/GestioneAd.vue';
import Recensioni from './pages/Recensioni.vue';
import EventDetail from './components/EventDetail.vue';

const routes = [
  { path: '/dashboard-statistiche', component: StatGenerali },
  { path: '/dashboard-eventi', component: TuttigliEventi },
  { path: '/dashboard-clienti', component: InfoClienti },
  { path: '/dashboard-gestione-ads', component: GestioneAd },
  { path: '/dashboard-impostazioni', component: Impostazioni },
  { path: '/recensioni', component: Recensioni },
  {path: '/event/:id', name: 'EventDetail', component: EventDetail,},
  { path: '/', redirect: '/dashboard-statistiche' }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
