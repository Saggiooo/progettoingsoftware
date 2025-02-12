<template>
    <div class="fullpage">
        <!-- Inserisco la Sidebar "Sidebar.vue" -->
        <div>
                <Sidebar/>
        </div>
       
        <div class="contenutopagina" :class="{ 'content': true, 'night-mode': nightMode }">
        <!-- Inizio del contenuto della pagina-->
            <!-- Inserisco l'intestazione "Nav.vue"-->
             <div>
                <Nav/>
             </div>
        <div class="grigliaimpostazioni">
                <div class="boximpostazioni">
                     <div>
                         <p class="testodelleimpostazioni">Gestisci l'aspetto della Dashboard  </p>
                    </div>
                    <div>
                      <label class="switch">
                     <input type="checkbox" v-model="isChecked" >
                     <span class="slider"></span>
                    </label>
                     </div>
                </div>

                <div class="boximpostazioni">
                     <div>
                         <p class="testodelleimpostazioni">Altre impostazioni  </p>
                    </div>
                </div>
        </div>
        <!-- fine del contenuto della pagina-->
        </div>
        
    
    </div>
    </template>
    
    <style>
    /* The switch - the box around the slider */
.switch {
  font-size: 17px;
  position: relative;
  display: inline-block;
  width: 3.5em;
  height: 2em;
}

/* Hide default HTML checkbox */
.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

/* The slider */
.slider {
  --background: #565656;
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: var(--background);
  transition: .5s;
  border-radius: 30px;
}

.slider:before {
  position: absolute;
  content: "";
  height: 1.4em;
  width: 1.4em;
  border-radius: 50%;
  left: 10%;
  bottom: 15%;
  box-shadow: inset 8px -4px 0px 0px #fff000;
  background: var(--background);
  transition: .5s;
}

input:checked + .slider {
  background-color: #9a9a9a;
}

input:checked + .slider:before {
  transform: translateX(100%);
  box-shadow: inset 15px -4px 0px 15px #fff000;
}
    </style>
    
    <script setup lang="ts">
    import Sidebar from './Sidebar.vue';
    import Nav from './Nav.vue';
    import { ref, watch } from 'vue';

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
    document.documentElement.style.setProperty('--coloretestibox', newVal ? '#b8b8b8' : '#1a1a1a');
    document.documentElement.style.setProperty('--ombra-esterna', newVal ? '0 6px 20px rgb(90, 90, 90)' : '0 7px 25px rgb(192, 192, 192)');
}

    </script>