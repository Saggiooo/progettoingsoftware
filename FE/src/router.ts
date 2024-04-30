import { createRouter, createWebHistory } from 'vue-router';
import StatGenerali from './pages/StatGenerali.vue';
import TuttigliEventi from './pages/Tuttiglieventi.vue';

const routes = [
  { path: '/', component: StatGenerali },
  { path: '/eventi', component: TuttigliEventi }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;

