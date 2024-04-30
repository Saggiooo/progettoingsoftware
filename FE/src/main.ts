import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // Assicurati di importare il tuo file router correttamente

const app = createApp(App);
app.use(router); // Usa Vue Router nella tua app
app.mount('#app');

