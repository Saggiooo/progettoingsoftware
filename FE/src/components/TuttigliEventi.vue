<template>
  <div class="fullpage">
    <!-- Inserisci la Sidebar "Sidebar.vue" -->
    <div>
      <Sidebar />
    </div>

    <div class="contenutopagina">
      <!-- Inizio del contenuto della pagina-->

      <div>
            <router-link to="/Recensioni">
              <button> Recensioni</button>
            </router-link>
        </div>

      <!-- Inserisci l'intestazione "Nav.vue"-->
      <div>
        <Nav />
      </div>

      <!-- Contenitore per gli eventi -->
      <div class="event-container">
        <ul id="eventList">
          <!-- Ciclo attraverso gli eventi attuali e visualizzo ciascuno come un elemento di lista -->
          <li v-for="event in currentEvents" :key="event.id" class="event-item">
            <h3>{{ event.name }}</h3>
            <p>{{ event.date }}</p>
            <p>{{ event.location }}</p>
          </li>
        </ul>
      </div>
      <div>
        <router-link to="/Recensioni">
          <button> Recensioni</button>
        </router-link>
      </div>

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
/* Stile per il contenitore principale della pagina */
.fullpage {
  display: flex;
  flex-direction: row;
}

/* Stile per il contenitore del contenuto della pagina */
.contenutopagina {
  flex: 1;
  padding: 20px;
  max-width: 2000px;
}

/* Stile per il contenitore degli eventi */
.event-container {
  margin-top: 20px;
}

/* Stile per ciascun elemento della lista di eventi */
.event-item {
  background-color: #f9f9f9;
  padding: 10px;
  margin-right: 10px; /* Aggiunge spazio tra gli elementi */
  margin-bottom: 10px; /* Aggiunge spazio tra le righe */
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
  width: calc(50% - 10px); /* Imposta la larghezza degli elementi al 50% del container con lo spazio tra gli elementi */
  float: left; /* Allinea gli elementi a sinistra */
}

/* Rimuove i punti elenco e padding dagli ul */
ul {
  list-style-type: none;
  padding: 0;
}

/* Stile per gli header h3 */
h3 {
  margin: 0 0 5px 0;
  font-size: 18px;
}

/* Stile per i paragrafi */
p {
  margin: 0;
  color: #666;
}

/* Stile per la sezione di paginazione */
.pagination {
  margin-top: 20px;
  text-align: center;
  position: sticky;
  bottom: 15px;
  width: 100%;
  display: flex;
  justify-content: center;
}

/* Stile per i bottoni di paginazione */
.pagination button {
  margin: 0 5px;
  padding: 8px 15px;
  background-color: #000000; /* Colore di sfondo */
  color: white; /* Colore del testo */
  border: none; /* Rimuove il bordo */
  border-radius: 4px; /* Bordo arrotondato */
  cursor: pointer; /* Cambia il cursore al passaggio */
}

/* Stile per i bottoni di paginazione al passaggio del mouse */
.pagination button:hover {
  background-color: #ff8000; /* Cambia il colore di sfondo al passaggio del mouse */
}

/* Stile per l'informazione della pagina corrente */
.page-info {
  margin: 5px 0 3px;
  font-weight: bold;
  color: black; /* Colore del testo */
  font-size: 18px; /* Ingrandisce il font */
}
</style>

<<<<<<< HEAD
=======

>>>>>>> 605b63987967c9944009f880dbe049487259637d
