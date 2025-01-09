<template>
  <div>
    <Sidebar />
  </div>
  <div class="contenutopagina">
    <div class="event-detail">
      <h2>TEst</h2>
      <p><strong>Date:</strong> Test</p>
      <p><strong>Location:</strong>Test</p>
      <p><strong>Description:</strong>Te</p>
      <p><strong>Organizer:</strong> test</p>


      <button @click="goBack" class="back-button">
        <i class="fas fa-arrow-left"></i> Torna all'elenco

      </button>
      </div>
  <div class="top-bar">
    <div class="container">
      <div class="row align-items-center">
        <div class="col">
          <button @click="goBack" class="back-button">
            <i class="fas fa-arrow-left"></i>
          </button>
        </div>
        <div class="col title-text">
          Recensioni evento {{ eventId }}
        </div>
        <div class="col"></div> <!-- Spazio vuoto per centrare la barra -->
      </div>
    </div>
  </div>

  <div class="page-container">

    <div v-if="message" :class="['alert', messageType === 'success' ? 'alert-success' : 'alert-danger']" class="message-box">
      {{ message }}
    </div>
    <!-- Contenuto della pagina -->
    <div class="recensioni">
      <div @click="toggleReviewForm" class="toggle-review-form">Scrivi una recensione</div>
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
    <div class="filter-container">
      <!-- filter rating -->
      <label for="filterRating">Filtra per valutazione:</label>
      <div class="star-rating">
        <span v-for="star in 5" :key="star" @click="setFilterRating(star)">
          <i :class="['fa', 'fa-star', {'checked': star <= filterRating}]"></i>
        </span>
      </div>

      <!-- filtro per data desc o asc -->
      <label for="dateOrder">Ordina per data:</label>
        <div class="select-data">
          <select class="selezione" id="dateOrder" v-model="dateOrder" @change="fetchReviews">
            <option value="desc">Dal più recente</option>
            <option value="asc">Dal più vecchio</option>
          </select>
        </div>

      <!-- resetta i filtri -->
      <button @click="resetFilter" class="btn btn-secondary reset-filtro">Resetta filtro</button>
    </div>

    <!-- sezione per vedere la media delle recensioni -->
    <div class="review-stats">
      <h3>Statistiche Recensioni</h3>
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



    <div class="reviews-list row" ref="reviewsSection">
      <h3 class="col-12 text-center">Recensioni</h3>
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
          console.error("Errore nel pubblicare la recensione", error);  //messaggio di errore in caso di mancato inserimento
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

    // Recupera gli ID da Mockoon tramite il backend
    async fetchIds() {
      try {
        const urlParams = new URLSearchParams(window.location.search);
        const eventId = urlParams.get('Id'); // Prendi l'ID dalla query
        console.log("ID recuperato dall'URL:", eventId);
        this.eventId=eventId;
        const response = await axios.get('http://localhost:3000/api/ids');  // Endpoint BE per ottenere IDs
        // Aggiungi un log per vedere la risposta
        console.log("Risposta ricevuta:", response);
        this.userId = response.data.userId;
        await this.fetchReviews(); //una volta recuperato l'id devo recuperare anche tutte le recensioni
      } catch (error) {
        console.error("Errore nel recupero degli ID", error);
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
    }
  },

};
</script>

<style scoped>
  @import '../assets/styles/review.css';
</style>
