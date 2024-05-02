<template>
    <div class="fullpage">
        <!-- Inserisco la Sidebar "Sidebar.vue" -->
        <div>
                <Sidebar/>
        </div>
       
        <div class="contenutopagina">
        <!-- Inizio del contenuto della pagina-->
            <!-- Inserisco l'intestazione "Nav.vue"-->
             <div>
                <Nav/>
             </div>
    
        <!-- fine del contenuto della pagina-->
        </div>
        
    
    </div>
    </template>
    
    <style>
    </style>
    
    <script setup lang="ts">
    import Sidebar from './Sidebar.vue';
    </script>