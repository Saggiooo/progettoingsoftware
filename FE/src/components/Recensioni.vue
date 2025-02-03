<template>
  <div>
    <Sidebar />
  </div>
  <div class="contenutopagina">
    <div class="event-detail" >

      <div class="namedesctiprion">
      <h2>{{ event.name }}</h2>
        <p>{{ event.description }}</p>
      </div>

      <div class="vertical-line"></div>

      <div class="otherinfoevent">
      <p><strong>Data:</strong> {{ event.date }}</p>
      <p><strong>Luogo:</strong> {{ event.location }}</p>

      <p><strong>Gestione:</strong> {{ event.organizer }}</p>
      </div>
    </div>


    <button @click="goBack" class="back-button">
      <i class="fas fa-arrow-left"></i> Torna all'elenco
    </button>


    <div class="page-container">

      <div v-if="message" :class="['alert', messageType === 'success' ? 'alert-success' : 'alert-danger']" class="message-box">
        {{ message }}
      </div>
      <!-- Contenuto della pagina -->
      <div class="recensioni">
        <div class="header-review">
            <h2>Recensioni</h2>
            <div @click="toggleReviewForm" class="toggle-review-form">Scrivi una recensione</div>
        </div>
            <div v-if="showReviewForm" class="recensioni-form">
              <form @submit.prevent="submitReview">
                <div class="form-group">
                  <input type="text" id="title" v-model="title" class="form-control" placeholder="Titolo Recensione"/>
                </div>
                <div class="form-group">
                  <textarea id="reviewText" v-model="reviewText" class="form-control" placeholder="Testo Recensione"></textarea>
                </div>
                <div class="form-group">
                  <label for="generalRating">Voto evento:</label>
                  <div class="star-rating">
                    <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= generalRating}" @click="setRating('generalRating', star)"></span>
                    <span>{{ generalRating }}</span>
                  </div>
                </div>
                <div @click="toggleSubcategorySliders" class="toggle-subcategories">
                  Vota altro
                </div>
                <div v-if="showSubcategorySliders">
                  <div class="form-group">
                    <label for="category1Rating">Location:</label>
                    <div class="star-rating">
                      <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= category1Rating}" @click="setRating('category1Rating', star)"></span>
                      <span>{{ category1Rating }}</span>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="category2Rating">Staff:</label>
                    <div class="star-rating">
                      <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= category2Rating}" @click="setRating('category2Rating', star)"></span>
                      <span>{{ category2Rating }}</span>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="category3Rating">Prezzo:</label>
                    <div class="star-rating">
                      <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= category3Rating}" @click="setRating('category3Rating', star)"></span>
                      <span>{{ category3Rating }}</span>
                    </div>
                  </div>
                </div>
                <button @click.prevent="submitReview" class="btn btn-success submit-button">Pubblica</button>
                <button @click.prevent="resetForm" class="btn btn-danger reset-button">Resetta</button>
              </form>
            </div>

      </div>

<div class="statisticheandfilter">
      <!-- sezione per vedere la media delle recensioni -->
      <div class="review-stats">
        <h5>Statistiche Recensioni</h5>
        <ul>
          <!-- Itera attraverso ogni livello di rating, dal 5 stelle (più alto) al 1 stella -->
          <li v-for="star in [5, 4, 3, 2, 1]" :key="star" @click="setFilterRating(star)" class="clickable">
            <!-- Per ogni livello di rating, crea le stelle dorate in base al numero di stelle -->
            <span v-for="i in star" :key="i" class="fa fa-star gold-star"></span>:
            <!-- Mostra il numero di recensioni per ciascun livello di rating (da 1 a 5 stelle) -->
            {{ ratingCount[star] || 0 }} recensioni
          </li>
        </ul>
        <!-- Mostra la media complessiva delle recensioni con una stella dorata accanto -->
        <div>Media complessiva: {{ averageRating }} <span class="fa fa-star gold-star"></span></div>
      </div>

      <div class="filter-container">
        <!-- filter rating -->
        <h5>Filtra:</h5>
        <div style="display: flex; margin-bottom: 25px; margin-top:20px; " >
          <label for="filterRating">Filtra:</label>
          <div class="star-rating">
        <span v-for="star in 5" :key="star" @click="setFilterRating(star)">
          <i :class="['fa', 'fa-star', {'checked': star <= filterRating}]"></i>
        </span>
          </div>
        </div>
        <div style="display: flex; margin-bottom: 30px;" >
          <!-- filtro per data desc o asc -->
          <label for="dateOrder">Ordina per data: &nbsp;&nbsp; </label>
          <div class="select-data">
            <select class="selezione" id="dateOrder" v-model="dateOrder" @change="fetchReviews">
              <option value="desc">Dal più recente</option>
              <option value="asc">Dal più vecchio</option>
            </select>
          </div>
        </div>

        <!-- resetta i filtri -->
        <button @click="resetFilter" class="filterreset-button">Resetta filtro</button>

      </div>
</div>


      <div class="reviews-list row" ref="reviewsSection">
        <h3 class="col-12 text-center"></h3>
        <div v-for="review in reviews" :key="review.reviewId" class="review-item">
          <h4>{{ review.title }}
            <!-- Pulsante "Cancella" visibile solo se l'utente corrente ha scritto la recensione -->
            <button
                v-if="review.userId === this.userId"
                class="trash-button"
                @click="deleteReview(review.reviewId)"
            > <img src="@/assets/img/trash.png" alt="f" class="trash-icon" />
            </button>
          </h4>
          <p>{{ review.userId }}</p>
          <p>{{formatDate(review.date)}}</p>
          <p>{{ review.text }}</p>
          <div>Voto generale: <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= review.generalRating}"></span></div>
          <div>Voto Location: <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= review.locationRating}"></span></div>
          <div>Voto Staff: <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= review.staffRating}"></span></div>
          <div>Voto Prezzo: <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= review.priceRating}"></span></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import dayjs from 'dayjs';
import Sidebar from "@/components/Sidebar.vue";

export default {
  components: {Sidebar},
  data() {
    return {
      title: '',
      reviewText: '',
      generalRating: 3,
      category1Rating: null,
      category2Rating: null,
      category3Rating: null,
      showReviewForm: false,
      showSubcategorySliders: false,  //sotto categorie nascoste
      eventId: null,
      userId: null,
      reviews: [],  // Nuova proprietà per le recensioni
      filterRating: null,  // Valutazione per il filtro
      dateOrder:'desc',  //imposto di default desc come filtro
      message: '',
      messageType: '',
      averageRating: null,
      ratingCount: {},
      event: {
        name: '',
        date: '',
        location: '',
        description: '',
        organizer: '',
      },
    };
  },
  methods: {
// Funzione per inviare la recensione al backend
    async submitReview() {
      // Messaggi di errore specifici per i campi mancanti
      if (!this.title && !this.reviewText) {
        this.displayMessage("Compila tutti i campi obbligatori prima di inviare la recensione.", "error");
      } else if (!this.title) {
        this.displayMessage("Titolo della recensione mancante.", "error");
      } else if (!this.reviewText) {
        this.displayMessage("Testo della recensione mancante.", "error");
      } else {

        const reviewData = {
          title: this.title,
          text: this.reviewText,
          generalRating: this.generalRating,
          locationRating: this.category1Rating,
          staffRating: this.category2Rating,
          priceRating: this.category3Rating,
          eventId: this.eventId,  // Include eventId
          userId: this.userId,    // Include userId
        };

        try {
          const response = await axios.post('/api/recensioni', reviewData);  // POST al backend per salvare la recensione
          console.log("Recensione pubblicata con successo!", response.data);  //debug nel log della console
          console.log("Dati recensione inviati:", reviewData);  //debug nel log della console
          this.displayMessage("Recensione inviata con successo!", "success");
          this.resetForm();  // Resetta il modulo dopo l'invio
          this.fetchReviews(); // Aggiorna le recensioni visualizzate
          this.fetchStatistics(); // Aggiorna le statistiche visualizzate
        } catch (error) {
          console.error("Errore  nel pubblicare la recensione", error.response);  //messaggio di errore in caso di mancato inserimento
          this.displayMessage("Errore nel pubblicare la recensione", "error");
        }
      }
    }
    ,
    // Metodo per eliminare una recensione
    async deleteReview(reviewId) {
      try {
        await axios.delete(`/api/recensioni/${reviewId}`); // API call per eliminare la recensione
        this.displayMessage('Recensione cancellata con successo.', 'success');
        this.fetchReviews(); // Aggiorna la lista delle recensioni
        this.fetchStatistics();  //Aggiorna le statistiche
      } catch (error) {
        this.displayMessage('Errore durante la cancellazione della recensione.', 'error');
      }
    },

    // metodo che recupera i dati dell'evento
    async fetchEventDetail() {
      try {
        // Filtro gli eventi per trovare quello che corrisponde a eventId
        const response = await axios.get('http://localhost:3000/api/events');
        const eventData = response.data.find((event) => event.id === String(this.eventId));  // Assicurati che l'id sia confrontato come stringa

        if (eventData) {
          this.event = eventData; // Salva i dati dell'evento
        } else {
          console.error('Event not found');
        }
      } catch (error) {
        console.error('Error fetching event details:', error);
      }
    }
    ,


    displayMessage(msg, type){
      this.message = msg;
      this.messageType = type;

      // imposto timeout di visualizzazione messaggio successo o errore nell'inserimento
      setTimeout(() =>{
        this.message = '';
      }, 3000);
    },
    // Funzione per tornare indietro alla pagina precedente
    goBack() {
      window.history.back();
    },

    // Funzione per mostrare/nascondere il modulo di recensione
    toggleReviewForm() {
      this.showReviewForm = !this.showReviewForm;
    },

    // Funzione per mostrare/nascondere le opzioni aggiuntive di valutazione
    toggleSubcategorySliders() {
      this.showSubcategorySliders = !this.showSubcategorySliders;
    },

    // Funzione per impostare la valutazione
    setRating(category, star) {
      this[category] = star;
    },

    //imposta il rating del filtro
    setFilterRating(rating) {
      this.filterRating = rating;
      this.fetchReviews(); //ricarico le recensioni corrispondenti al filtro indicato

      // Aspetta un breve istante affinché i dati siano caricati e quindi esegui lo scorrimento
      this.$nextTick(() => {
        const reviewsSection = this.$refs.reviewsSection;
        if (reviewsSection) {
          reviewsSection.scrollIntoView({ behavior: 'smooth' }); // Scorri con animazione fluida
        }
      });
    },

    // Funzione per resettare il modulo di recensione
    resetForm() {
      this.title = '';
      this.reviewText = '';
      this.generalRating = 3;
      this.category1Rating = null;
      this.category2Rating = null;
      this.category3Rating = null;
      this.filterRating = null;
    },


    //recupero tutte le recensioni per un determinato id evento
    async fetchReviews() {
      try {
        const response = await axios.get(`/api/recensioni`,{
          params: {eventId: this.eventId, generalRating: this.filterRating, dateOrder: this.dateOrder}
        });
        this.reviews = response.data;
      } catch (error) {
        console.error("Errore nel recupero delle recensioni", error);
      }
    },

// Recupera l'ID utente da Mockoon e l'ID evento dalla query string
    async fetchIds() {
      // Recupera l'eventId dalla query string
      const eventIdFromQuery = this.$route.query.eventId;

      if (eventIdFromQuery) {
        // Converte l'eventId in un numero intero
        this.eventId = parseInt(eventIdFromQuery, 10); // Assicurati che sia un numero intero
        if (isNaN(this.eventId)) {
          console.error("eventId non è un numero valido.");
          return; // Termina se l'eventId non è un numero valido
        }
      } else {
        console.error("Nessun eventId trovato nella query string.");
        return; // Termina se l'eventId non è disponibile
      }

      try {
        // Chiamata a Mockoon per ottenere l'userId
        const response = await axios.get('http://localhost:3000/api/ids'); // Endpoint BE per ottenere IDs
        this.userId = response.data.userId;

        // Crea l'oggetto contenente i due ID
        const ids = {
          eventId: this.eventId,
          userId: this.userId,
        };

        // Stampa l'oggetto nella console
        console.log("Oggetto contenente gli ID:", ids);

        // Dopo aver ottenuto gli ID, carica le recensioni e le statistiche
        await this.fetchReviews();
        await this.fetchStatistics();
      } catch (error) {
        console.error("Errore nel recupero degli ID utente da Mockoon", error);
      }
    },

    //recupero le stats dal BE
    async fetchStatistics(){
      try {
        const response = await axios.get('api/recensioni/statistiche', {
          params: {eventId: this.eventId}
        });
        // Arrotonda il valore di averageRating a un decimale
        this.averageRating = Math.round(response.data.averageRating * 10) / 10;
        this.ratingCount = response.data.ratingCount;
      } catch (error){
        console.error("Errore nel recupero stats", error);
      }
    },


    //formatto la data
    formatDate(date){
      return dayjs(date).format('DD-MM-YYYY');
    },

    // resetto il filtro, rating: tutti, data decrescente
    resetFilter(){
      this.filterRating = null; //resetto la valutazione del filtro
      this.dateOrder = 'desc';
      this.fetchReviews(); //recupero tutte le reviews
    }
  },

  //al caricamneto della pagina voglio che vengano mostrate le recensioni e le statistiche
  async mounted() {
    await this.fetchIds();
    if (this.eventId) {
      this.fetchStatistics();
      this.fetchEventDetail(); // Recupera i dettagli dell'evento
    }
  },

};
</script>

<style scoped>
@import '../assets/styles/review.css';
</style>