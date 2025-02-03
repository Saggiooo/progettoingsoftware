<script setup lang="ts">
import Sidebar from './Sidebar.vue';
import Nav from './Nav.vue';
import axios from 'axios';
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';

// Variabili reattive per i dati
const partecipanti = ref(0);

const labelsNation = ref<string[]>([]); // Per i paesi (grafico a linee)
const dataNation = ref<number[]>([]); // Per i dati (grafico a linee)

const labelsPieSex = ref<string[]>([]); // Per i sessi (grafico a torta)
const dataPieSex = ref<number[]>([]); // Per i dati (grafico a torta)

onMounted(() => {
  // Recupero dati per il placeholder
  axios.get('/api/clientinfo')
      .then(response => {
        const data = response.data;
        partecipanti.value = data.partecipantitotali;

        // Aggiorna il placeholder
        document.getElementById('partecipanti-placeholder')!.innerText = `${partecipanti.value}`;
      })
      .catch(error => {
        console.error('Errore nel recupero dei dati:', error);
      });

  // Recupero informazioni per i grafici
  axios.get('/api/nationinfo')
      .then(response => {
        const nations = response.data;
        // Popolo labels e data per il grafico delle nazioni
        Object.entries(nations).forEach(([nation, value]) => {
          labelsNation.value.push(nation);
          dataNation.value.push(value);
        });

        // Configurazione per il grafico a linee
        const dataLine = {
          labels: labelsNation.value,
          datasets: [{
            label: 'Distribuzione per Nazione',
            backgroundColor: '#1786FF',
            borderColor: '#1786FF',
            borderWidth: 2,
            data: dataNation.value,
          }]
        };

        const configLine = {
          type: 'bar',
          data: dataLine,
          options: {}
        };

        new Chart(document.getElementById('ChartNation') as HTMLCanvasElement, configLine);
      })
      .catch(error => {
        console.error('Errore nel recupero dei dati per il grafico a linee:', error);
      });

  axios.get('/api/sexinfo')
      .then(response => {
        const sexes = response.data;
        // Popolo labels e data per il grafico a torta
        Object.entries(sexes).forEach(([sex, value]) => {
          labelsPieSex.value.push(sex.charAt(0).toUpperCase() + sex.slice(1)); // Capitalizzo la prima lettera
          dataPieSex.value.push(value);
        });

        // Configurazione per il grafico a torta
        const dataPie = {
          labels: labelsPieSex.value,
          datasets: [{
            label: 'Distribuzione per Sesso',
            backgroundColor: ['#FF6384', '#36A2EB'],
            borderColor: '#ffffff',
            borderWidth: 2,
            data: dataPieSex.value,
          }]
        };

        const configPie = {
          type: 'pie',
          data: dataPie,
          options: {}
        };

        new Chart(document.getElementById('ChartPieSex') as HTMLCanvasElement, configPie);
      })
      .catch(error => {
        console.error('Errore nel recupero dei dati per il grafico a torta:', error);
      });
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
      <!-- Implemento i Box con i numeri -->
      <div class="cardbox">
        <a href="/dashboard-clienti">
          <div class="card">
            <div>
              <div class="numbers"><div id="partecipanti-placeholder">{{ partecipanti }}</div></div>
              <div class="cardName">Partecipanti Totali</div>
            </div>
            <div class="iconBx">
              <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
            </div>
          </div></a>
      </div>

      <!-- Implemento un grafico -->
      <div class="graphBox">
        <div class="box1">
          <canvas class="graficoUno" id="ChartNation"></canvas>
        </div>
        <div class="box">
          <canvas class="graficoUno" id="ChartPieSex"></canvas>
        </div>

      </div>
      <!-- fine del contenuto della pagina-->
    </div>

  </div>
</template>

<style>
</style>
