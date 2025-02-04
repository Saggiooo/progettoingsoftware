<template>
  <div class="fullpage">
    <!-- Inserisci la Sidebar "Sidebar.vue" -->
    <div>
      <Sidebar />
    </div>

    <div class="contenutopagina">
      <!-- Inizio del contenuto della pagina-->

      <!-- Inserisci l'intestazione "Nav.vue"-->
      <div>
        <Nav />
      </div>

      <!-- Contenitore per gli eventi -->
      <div class="event-container">
        <ul id="eventList">
          <!-- Ciclo attraverso gli eventi attuali e li rendo cliccabili con un router-link -->
          <li v-for="event in currentEvents" :key="event.id" class="event-item">
            <router-link :to="`/Recensioni?eventId=${event.id}`" class="event-link">
              <h3>{{ event.name }}</h3>
              <p>{{ event.date }}</p>
              <p>{{ event.location }}</p>
            </router-link>
          </li>


        </ul>
      </div>
      <button class="buttonend"></button>


      <!-- Bottoni per la paginazione -->
      <div class="pagination">
        <button @click="prevPage" :disabled="currentPage === 1" class="page-button">← Indietro</button>
        <span class="page-info">Pagina {{ currentPage }} di {{ totalPages }}</span>
        <button @click="nextPage" :disabled="currentPage === totalPages" class="page-button">Avanti →</button>
      </div>
      <!-- Fine del contenuto della pagina-->
    </div>
  </div>



</template>

<script setup lang="ts">
// Importa i componenti Sidebar e Nav
import Sidebar from './Sidebar.vue';
import Nav from './Nav.vue';
// Importa axios per le richieste HTTP
import axios from 'axios';
// Importa ref e computed da Vue
import { ref, computed } from 'vue';

// Variabile reattiva per la lista degli eventi
const eventList = ref([]);

// Funzione per ottenere i dati degli eventi da Mockoon
const fetchEvents = async () => {
  try {
    // Effettua una richiesta GET all'endpoint specificato
    const response = await axios.get('/api/callPROVA');
    console.log('Fetched events:', response.data); // Per debug
    // Assegna i dati ricevuti alla variabile reattiva eventList
    eventList.value = response.data;
  } catch (error) {
    // Logga un messaggio di errore in caso di fallimento della richiesta
    console.error('Error fetching events:', error);
  }
};

// Chiama la funzione per ottenere gli eventi al caricamento della pagina
fetchEvents();

// Variabile per il numero di eventi per pagina
const itemsPerPage = 22; // Numero di eventi per pagina
// Variabile reattiva per la pagina corrente
const currentPage = ref(1);

// Calcola il numero totale di pagine
const totalPages = computed(() => Math.ceil(eventList.value.length / itemsPerPage));

// Calcola gli eventi da visualizzare nella pagina corrente
const currentEvents = computed(() => {
  // Calcola l'indice di partenza e di fine per la pagina corrente
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  // Restituisce una fetta dell'array eventList corrispondente agli eventi della pagina corrente
  return eventList.value.slice(startIndex, endIndex);
});

// Funzione per passare alla pagina precedente
const prevPage = () => {
  // Decrementa currentPage se non è già alla prima pagina
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

// Funzione per passare alla pagina successiva
const nextPage = () => {
  // Incrementa currentPage se non è già all'ultima pagina
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
};
</script>

<style>
.buttonend{
 color: white;
  background: white;
  cursor: default!important;
  border: 1px solid var(--sfondo);
}
/* Stile per il contenitore principale della pagina */
.fullpage {
  display: flex;
  flex-direction: row;
  background-color: #f5f5f5; /* Sfondo generale */
  min-height: 100vh;
}

/* Stile per il contenitore del contenuto della pagina */
.contenutopagina {
  flex: 1;
  padding: 20px;
  background-color: #ffffff; /* Sfondo bianco per il contenuto */
}

/* Stile per il contenitore degli eventi */
.event-container {
  margin-top: 20px;
}

/* Stile per ciascun elemento della lista di eventi */
.event-item {
  background-color: #fff;
  padding: 15px;
  margin-right: 10px; /* Aggiunge spazio tra gli elementi */
  margin-bottom: 10px; /* Aggiunge spazio tra le righe */

  border: 1px solid #ddd;
  border-radius: 12px;
  box-sizing: border-box;
  box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1); /* Ombra leggera */
  transition: transform 0.2s, box-shadow 0.2s; /* Effetto hover */
  width: calc(50% - 10px); /* Imposta la larghezza degli elementi al 50% del container con lo spazio tra gli elementi */
  float: left; /* Allinea gli elementi a sinistra */
}

.event-item:hover {
  transform: scale(1.02); /* Ingrossa l'elemento del 10% */
  box-shadow: 0px 10px 15px rgba(0, 0, 0, 0.1); /* Ombra più leggera */
}

/* Rimuove i punti elenco e padding dagli ul */
ul {
  list-style-type: none;
  padding: 0;
}

/* Stile per gli header h3 */
h3 {
  margin: 0 0 5px 0;
  font-size: 20px;
}

/* Stile per i paragrafi */
p {
  margin: 0;
  color: #666;
  font-size: 16px;
}

/* Stile per la sezione di paginazione */
.pagination {
  margin-top: 20px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Stile per i bottoni di paginazione */
.pagination button {
  margin: 0 5px;
  padding: 10px 20px;
  background-color: #000000;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 18px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s;
}

/* Stile per i bottoni di paginazione al passaggio del mouse */
.pagination button:hover {
  background-color: #ffa600; /* Cambia il colore di sfondo al passaggio del mouse */
}

/* Stile per l'informazione della pagina corrente */
.page-info {
  margin: 0 10px;
  font-weight: bold;
  color: #333;
  font-size: 18px;
}


.event-link {
  text-decoration: none;
  color: darkslategray; /* Mantiene il colore del testo */
}

.event-link:hover {
  text-decoration: none;
  color: #ad6738; /* Evita che diventi blu al passaggio del mouse */
}
</style>