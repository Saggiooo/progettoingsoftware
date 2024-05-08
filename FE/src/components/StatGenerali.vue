<script setup lang="ts">



/* Inseriamo in una variabile reattiva chiamata data */


import axios from 'axios';
import { ref } from 'vue';
const data1 = ref(0);

axios.get("/api/callREST").then(response => {
    console.log(response.data);
    let data1 = response.data;
    
    let output = document.getElementById("myOutput");
    
    data1.forEach((item: MyTable) => {
        let newText = document.createTextNode(`${item.id}`);
        //${item.description}
        let breakLine = document.createElement("br");
        
        output.appendChild(newText);
        output.appendChild(breakLine);
    });
})



import Sidebar from './Sidebar.vue';
import Nav from './Nav.vue';

import Chart from 'chart.js/auto';
import { onMounted } from 'vue';

const labels = [
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

const data = {
    labels: labels,
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
    labels: labels,
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
                    <div class="numbers"><p id="myOutput"></p></div>
                    <div class="cardName">Partecipanti</div>
                </div>
                <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div></a>
    
             <div class="card">
                <div>
                    <div class="numbers">AAA</div>
                    <div class="cardName">Prova</div>
                </div>
                 <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div>

            <div class="card">
                <div>
                    <div class="numbers">7232</div>
                    <div class="cardName">Test</div>
                </div>
                 <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div>

            <div class="card">
                <div>
                    <div class="numbers">912</div>
                    <div class="cardName">Number</div>
                </div>
                 <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div>
        </div>

        <!-- Implemento un grafico -->
        <div class="graphBox">
            <div class="box">
                <canvas class="graficoUno" id="myChart"></canvas>
            </div>
            <div class="box">
                <canvas class="graficoUno" id="myChart2"></canvas>
            </div>
        </div>
        
    <!-- fine del contenuto della pagina-->
    </div>
</div>
</template>

<style>
</style>


