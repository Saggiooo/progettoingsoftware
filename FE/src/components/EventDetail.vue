<template>
  <div class="event-detail">
    <h2>{{ event.name }}</h2>
    <p><strong>Date:</strong> {{ event.date }}</p>
    <p><strong>Location:</strong> {{ event.location }}</p>
    <p><strong>Description:</strong> {{ event.description }}</p>
    <p><strong>Organizer:</strong> {{ event.organizer }}</p>


    <button @click="goBack" class="back-button">
      <i class="fas fa-arrow-left"></i> Torna all'elenco
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';

// Ottieni l'ID dell'evento dalla rotta
const route = useRoute();
const eventId = route.params.id as string; // Assicurati che l'ID sia una stringa

// Variabile reattiva per memorizzare i dettagli dell'evento
const event = ref<{
  name: string;
  date: string;
  location: string;
  description: string;
  organizer: string;
}>({
  name: '',
  date: '',
  location: '',
  description: '',
  organizer: ''
});

// Funzione per caricare i dettagli dell'evento
const fetchEventDetail = async () => {
  try {
    const response = await axios.get('http://localhost:3000/api/events');
    const eventData = response.data.find((event: { id: string }) => event.id === eventId);

    if (eventData) {
      event.value = eventData;  // Se trova l'evento, salva i dati
    } else {
      console.error('Event not found');
    }
  } catch (error) {
    console.error('Error fetching event details:', error);
  }
};

// Carica i dettagli quando il componente è montato
onMounted(fetchEventDetail);

// Funzione per tornare indietro alla pagina precedente
const router = useRouter();
const goBack = () => {
  router.back(); // Torna alla pagina precedente
};
</script>

<style scoped>
/* Stile per il contenitore principale del dettaglio evento */
.event-detail {
  max-width: 900px; /* Limita la larghezza del contenitore */
  margin: 40px auto; /* Centra il contenitore */
  padding: 30px;
  background-color: #ffffff; /* Sfondo bianco */
  border-radius: 12px; /* Bordo arrotondato */
  box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.1); /* Ombra */
  font-family: 'Arial', sans-serif; /* Font moderno */
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* Spaziatura tra gli elementi */
  height: 400px; /* Imposta un'altezza fissa */
}

/* Stile per il bottone indietro */
.back-button {
  background-color: #007bff; /* Colore blu */
  color: white; /* Colore del testo */
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  margin-top: 55px; /* Spinge il bottone verso il basso */
  align-self: flex-end; /* Allinea il bottone a destra */
}

.back-button i {
  margin-right: 5px; /* Spazio tra l'icona e il testo */
}

/* Stile per il titolo dell'evento */
h2 {
  font-size: 40px;
  color: #333; /* Colore scuro per il titolo */
  font-weight: 700;
  margin-bottom: 20px;
  text-align: center; /* Centra il titolo */
  border-bottom: 2px solid #f0f0f0; /* Linea decorativa sotto il titolo */
  padding-bottom: 10px;
}

/* Stile per i paragrafi che mostrano le informazioni */
p {
  font-size: 21px;
  color: #555; /* Colore grigio per il testo */
  margin: 10px 0;
  line-height: 1.6; /* Spaziatura tra le righe */
}

/* Stile per le etichette (Date, Location, ecc.) */
p strong {
  color: #007bff; /* Colore blu per evidenziare le etichette */
  font-weight: 600;
}

/* Aggiunta di una linea separatrice tra le sezioni */
p + p {
  border-top: 1px solid #f0f0f0; /* Linea separatrice sottile */
  padding-top: 10px;
}

/* Aggiunta di un'animazione al caricamento */
.event-detail {
  opacity: 0;
  transform: translateY(200px);
  animation: fadeInUp 1.0s ease-out forwards;
}

@keyframes fadeInUp {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
