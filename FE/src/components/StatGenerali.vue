<script setup lang="ts">



/* Inseriamo in una variabile reattiva chiamata data */


import axios from 'axios';
import { ref, watch } from 'vue';
const data1 = ref(0);


  axios.get('/api/allData')
  .then(response => {
    const data = response.data;
    const partecipanti = data.partecipanti;
    const utenti = data.utenti;
    const valutazioni = data.valutazioni;
    const coso = data.coso;
    
    // Aggiorna i placeholder nel HTML con i valori ottenuti
    document.getElementById('partecipanti-placeholder').innerText = `${partecipanti}`;
    document.getElementById('utenti-placeholder').innerText = `${utenti}`;
    document.getElementById('valutazioni-placeholder').innerText = `${valutazioni}`;
    document.getElementById('coso-placeholder').innerText = `${coso}`;


    // Ora puoi utilizzare partecipanti e utenti come desideri
    console.log("Partecipanti:", partecipanti);
    console.log("Utenti:", utenti);
    console.log("valutazioni:", partecipanti);
    console.log("coso:", utenti);
  })
  .catch(error => {
    console.error('Si è verificato un errore durante il recupero dei dati:', error);
  });



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
                    const listItem = document.importNode(eventTemplate.content, true).querySelector('li');
                    listItem.textContent = `${event.name} - ${event.date}`;
                    eventList.appendChild(listItem);
                });
            } catch (error) {
                console.error('Error fetching events:', error);
            }
        });








import Sidebar from './Sidebar.vue';
import Nav from './Nav.vue';

import Chart from 'chart.js/auto';
import { onMounted } from 'vue';

const labels1 = [
    'Gennaio',
    'Febbraio',
    'Marzo',
    'Aprile',
    'Maggio',
    'Giugno',
    'Luglio',
    'Agosto',
    'Settembre',
    'Ottobre',
    'Novembre',
    'Dicembre'
];

const labels2 = [
    'Evento di benvenuto',
    'Evento ciao',
    'Evento estivo',
    'Mattinata insieme',
    'Incontro con il presidente',
    'Sessione di cuicna',
    'Evento',
    'Agosto',
    'Settembre',
    'Lezioni di cucina',
    'Evento al portico di Pino',
    'Evento In compagnia'
];


const data = {
    labels: labels1,
    datasets: [{
        label: 'Il mio dataset',
        backgroundColor: '#1786FF', // bianco con opacità del 50%
        //backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#8A2BE2', '#FF7F50', '#20B2AA', '#9370DB', '#FFD700', '#32CD32', '#4682B4', '#BA55D3', '#FFA07A'], // Cambia i colori dei segmenti del grafico a torta
        borderColor: '#1786FF', // blu
        borderWidth: 2,
        data: [0, 10, 5, 6, 20, 30, 45],
    }]
};

const data2 = {
    labels: labels2,
    datasets: [{
        label: 'Il mio dataset',
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#8A2BE2', '#FF7F50', '#20B2AA', '#9370DB', '#FFD700', '#32CD32', '#4682B4', '#BA55D3', '#FFA07A'],
        borderColor: '#ffffff', // colore del bordo del segmento
        borderWidth: 2,
        data: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120],
    }]
};

const config2 = {
    type: 'pie',
    data: data2,
    options: {}
};



const config = {
    type: 'line',
    data: data,
    options: {}
};


    onMounted(() => {
        const myChart = new Chart(
            document.getElementById('myChart'),
            config
        );

        const myChart2 = new Chart(
        document.getElementById('myChart2'),
        config2
    );
        window.addEventListener('resize', () => myChart.resize());
    })

import { createApp } from 'vue';
import VCalendar from 'v-calendar';

createApp().use(VCalendar);



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
        <!-- Implemento i Box con i numeri -->
        <div class="cardbox">
            <a href="/dashboard-clienti">
             <div class="card">
                <div>
                    <div class="numbers"><div id="partecipanti-placeholder">{{ partecipanti }}</div></div>
                    <div class="cardName">Partecipanti</div>
                </div>
                <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div></a>
    
             <div class="card">
                <div>
                    <div class="numbers"><div id="utenti-placeholder">{{ utenti }}</div></div>    
                    <div class="cardName">Prova</div>
                </div>
                 <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div>

            <div class="card">
                <div>
                    <div class="numbers"><div id="valutazioni-placeholder">{{ valutazioni }}</div></div>
                    <div class="cardName">Test</div>
                </div>
                 <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div>

            <div class="card">
                <div>
                    <div class="numbers"><div id="coso-placeholder">{{ coso }}</div></div>
                    <div class="cardName">Number</div>
                </div>
                 <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div>
        </div>

        <!-- Implemento un grafico -->
        <div class="graphBox">
            <div class="box1">
                <canvas class="graficoUno" id="myChart"></canvas>
            </div>
            <div class="box">
                <canvas class="graficoUno" id="myChart2"></canvas>

            </div>
            <div class="box-eventi">
                <div class="listaeventi">
            <ul id="eventList">
        <!-- Event items will be added here -->
    </ul>

    <!-- Template for event item -->
    <template id="eventTemplate">
        <li class="listadeglieventi"></li>
    </template>
        </div>
    <!-- fine del contenuto della pagina-->
    </div>

            </div>
        </div>
        
</div>
</template>

<style>
</style>


