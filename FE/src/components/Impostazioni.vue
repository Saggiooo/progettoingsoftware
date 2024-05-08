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
             

            <div>
             <label class="tastonotte">Notte <input type="checkbox" @click="toggleNightMode"> <span class="check"></span></label>
            </div>
            <div class="overlay" v-show="nightMode"></div>
        <!-- fine del contenuto della pagina-->
        </div>
        
    
    </div>
    </template>
    
    <style>
    </style>
    
    <script setup lang="ts">
    import Sidebar from './Sidebar.vue';
    import Nav from './Nav.vue';
    </script>