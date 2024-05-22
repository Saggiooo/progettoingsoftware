<script setup lang="ts">
import Sidebar from './Sidebar.vue';
import Nav from './Nav.vue';

import axios from 'axios';
import { ref, watch } from 'vue';
const data1 = ref(0);

document.addEventListener('DOMContentLoaded', async () => {
    const eventList = document.getElementById('eventList');
    const eventTemplate = document.getElementById('eventTemplate');

    if (!eventList || !eventTemplate) {
        console.error('One or both of the elements are missing.');
        return;
    }

    try {
        const response = await axios.get('/api/callPROVA');
        const events = response.data;

        events.forEach(event => {
            // Verifichiamo che il template sia stato correttamente selezionato e non sia nullo
            if (eventTemplate.content) {
                // Cloniamo il template per creare un nuovo elemento "li"
                const listItem = eventTemplate.content.querySelector('li').cloneNode(true);
                // Verifichiamo che l'elemento clonato non sia nullo
                if (listItem) {
                    // Impostiamo il testo dell'elemento "li"
                    listItem.textContent = `${event.name} - ${event.date}`;
                    // Aggiungiamo l'elemento "li" alla lista degli eventi
                    eventList.appendChild(listItem);
                } else {
                    console.error('Error creating list item for event:', event);
                }
            } else {
                console.error('Event template is null.');
            }
        });
    } catch (error) {
        console.error('Error fetching events:', error);
    }
});

</script>

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
             
             <div class="provaaa">
                <ul id="eventList">
                <!-- Event items will be added here -->
                </ul>

                 <!-- Template for event item -->
             <template id="eventTemplate">
                <li></li>
         </template>
              </div>


        <!-- fine del contenuto della pagina-->
        </div>
        
    
    </div>
    </template>
    
    <style>
    </style>
    
