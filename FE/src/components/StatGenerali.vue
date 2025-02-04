<script setup lang="ts">
import Sidebar from './Sidebar.vue';
import Nav from './Nav.vue';
import axios from 'axios';
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';

// Variabili reattive per i dati
const partecipanti = ref(0);
const utenti = ref(0);
const valutazioni = ref(0);
const coso = ref(0);

const labels1 = ref<string[]>([]); // Per i nomi degli eventi (grafico a linee)
const dataPartecipanti1 = ref<number[]>([]); // Per i partecipanti (grafico a linee)

const labels2 = ref<string[]>([]); // Per i nomi degli eventi (grafico a torta)
const dataPartecipanti2 = ref<number[]>([]); // Per i partecipanti (grafico a torta)

const eventList = ref<any[]>([]);

// Recupero dati per i placeholder e grafici
onMounted(() => {
  axios.get('/api/statsinfo')
      .then(response => {
        const data = response.data;
        partecipanti.value = data.partecipantitotali;
        utenti.value = data.mediaPartecipanti;
        valutazioni.value = data.eventoConMassimiPartecipanti;
        coso.value = data.eventoConMinimiPartecipanti;

        // Aggiorna i placeholder con i dati
        document.getElementById('partecipanti-placeholder')!.innerText = `${partecipanti.value}`;
        document.getElementById('utenti-placeholder')!.innerText = `${utenti.value}`;
        document.getElementById('valutazioni-placeholder')!.innerText = `${valutazioni.value}`;
        document.getElementById('coso-placeholder')!.innerText = `${coso.value}`;

        console.log("Partecipanti:", partecipanti.value);
        console.log("Utenti:", utenti.value);
        console.log("Valutazioni:", valutazioni.value);
        console.log("Coso:", coso.value);
      })
      .catch(error => {
        console.error('Si è verificato un errore durante il recupero dei dati:', error);
      });

  // Recupero degli eventi per popolare la lista
  axios.get('/api/callPROVA')
      .then(response => {
        eventList.value = response.data;
      })
      .catch(error => {
        console.error('Errore nel recupero degli eventi:', error);
      });

  // Recupero informazioni per i grafici
  axios.get('/api/eventinfo')
      .then(response => {
        const events = response.data;
        events.forEach((event: any) => {
          labels1.value.push(event.eventName);
          dataPartecipanti1.value.push(event.partecipanti);

          labels2.value.push(event.eventName);
          dataPartecipanti2.value.push(event.partecipanti);
        });

        // Configurazione per il grafico a linee
        const dataLine = {
          labels: labels1.value,
          datasets: [{
            label: 'Partecipanti per Evento',
            backgroundColor: '#1786FF',
            borderColor: '#1786FF',
            borderWidth: 2,
            data: dataPartecipanti1.value,
          }]
        };

        const configLine = {
          type: 'line',
          data: dataLine,
          options: {}
        };

        new Chart(document.getElementById('myChart') as HTMLCanvasElement, configLine);

        // Configurazione per il grafico a torta
        const dataPie = {
          labels: labels2.value,
          datasets: [{
            label: 'Partecipanti per Evento',
            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#8A2BE2', '#FF7F50', '#20B2AA', '#9370DB', '#FFD700', '#32CD32', '#4682B4', '#BA55D3', '#FFA07A'],
            borderColor: '#ffffff',
            borderWidth: 2,
            data: dataPartecipanti2.value,
          }]
        };

        const configPie = {
          type: 'pie',
          data: dataPie,
          options: {}
        };

        new Chart(document.getElementById('myChart2') as HTMLCanvasElement, configPie);

        // Rendi il grafico responsive
        window.addEventListener('resize', () => {
          const charts = [myChart, myChart2];
          charts.forEach(chart => chart.resize());
        });
      })
      .catch(error => {
        console.error('Errore nel recupero dei dati per i grafici:', error);
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
    
             <div class="card">
                <div>
                    <div class="numbers"><div id="utenti-placeholder">{{ utenti }}</div></div>    
                    <div class="cardName">Media Partecipanti</div>
                </div>
                 <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div>

            <div class="card">
                <div>
                    <div class="textstatsdashboard"><div id="valutazioni-placeholder">{{ valutazioni }}</div></div>
                    <div class="cardName">Il più prenotato</div>
                </div>
                 <div class="iconBx">
                    <img src="@/assets/img/icon-statistiche.png" alt="Icona Statistiche" class="iconCard">
                </div>
            </div>

            <div class="card">
                <div>
                    <div class="textstatsdashboard"><div id="coso-placeholder">{{ coso }}</div></div>
                    <div class="cardName">Il meno prenotato</div>
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
          <br>
        </div>

    </div>

</div>
</template>

<style>
</style>


