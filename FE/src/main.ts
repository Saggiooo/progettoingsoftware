import { createApp, ref, watch } from 'vue';
import App from './App.vue';
import router from './router';

const app = createApp(App);
app.use(router);
app.mount('#app');

// Get the stored state from local storage
const storedState = localStorage.getItem('checkbox_state');

// Set the initial state from local storage or default to false
const isChecked = ref(storedState ? JSON.parse(storedState) : false);

// Update the css var based on the initial state
updateBackground(isChecked.value);

watch(isChecked, (newVal) => {
    // Store the updated state in local storage
    localStorage.setItem('checkbox_state', JSON.stringify(newVal));
    updateBackground(newVal);
});

function updateBackground(newVal: any) {
    document.documentElement.style.setProperty('--sfondo', newVal ? '#404040' : '#F2F2F2');
    document.documentElement.style.setProperty('--coloretestosidebarhover', newVal ? '#fff' : '#1a1a1a');
    document.documentElement.style.setProperty('--bianco', newVal ? '#222' : '#fff');
    document.documentElement.style.setProperty('--nero', newVal ? '#FFF' : '#000');
    document.documentElement.style.setProperty('--invert', newVal ? 'invert(0%)' : 'invert(100%)');
    document.documentElement.style.setProperty('--coloretestibox', newVal ? '#fff' : '#1a1a1a');
    document.documentElement.style.setProperty('--ombra-esterna', newVal ? '0 6px 20px rgb(90, 90, 90)' : '0 7px 25px rgb(192, 192, 192)');
    }