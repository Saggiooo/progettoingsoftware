<template>
  <div class="top-bar">
    <div class="container">
      <div class="row align-items-center">
        <div class="col">
          <button @click="goBack" class="back-button">
            <i class="fas fa-arrow-left"></i>
          </button>
        </div>
        <div class="col title-text">
          Recensioni evento Pippo
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
            <input type="text" id="title" v-model="title" class="form-control" placeholder="Titolo Recensione" required />
          </div>
          <div class="form-group">
            <textarea id="reviewText" v-model="reviewText" class="form-control" placeholder="Testo Recensione" required></textarea>
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
          <button type="submit" class="btn btn-success submit-button">Pubblica</button>
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
        <li v-for="star in [5, 4, 3, 2, 1]" :key="star">
          <!-- Per ogni livello di rating, crea le stelle dorate in base al numero di stelle -->
          <span v-for="i in star" :key="i" class="fa fa-star gold-star"></span>:
          <!-- Mostra il numero di recensioni per ciascun livello di rating (da 1 a 5 stelle) -->
          {{ ratingCount[star] || 0 }} recensioni
        </li>
      </ul>
      <!-- Mostra la media complessiva delle recensioni con una stella dorata accanto -->
      <div>Media complessiva: {{ averageRating }} <span class="fa fa-star gold-star"></span></div>
    </div>



    <div class="reviews-list row">
      <h3 class="col-12 text-center">Recensioni</h3>
      <div v-for="review in reviews" :key="review.reviewId" class="review-item">
        <h4>{{ review.title }}</h4>
        <p>{{formatDate(review.date)}}</p>
        <p>{{ review.text }}</p>
        <div>Voto generale: <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= review.generalRating}"></span></div>
        <div>Voto Location: <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= review.locationRating}"></span></div>
        <div>Voto Staff: <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= review.staffRating}"></span></div>
        <div>Voto Prezzo: <span v-for="star in 5" :key="star" class="fa fa-star" :class="{'checked': star <= review.priceRating}"></span></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import dayjs from 'dayjs';

export default {
  data() {
    return {
      title: '',
      reviewText: '',
      generalRating: 3,
      category1Rating: null,
      category2Rating: null,
      category3Rating: null,
      showReviewForm: false,
      showSubcategorySliders: false,
      eventId: null,
      userId: null,
      reviews: [],  // Nuova proprietà per le recensioni
      filterRating: null,  // Valutazione per il filtro
      dateOrder:'desc',
      message: '',
      messageType: '',
      averageRating: null,
      ratingCount: {},
    };
  },
  methods: {
    // Funzione per inviare la recensione al backend
    async submitReview() {
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
        console.log("Recensione pubblicata con successo!", response.data);
        console.log("Dati recensione inviati:", reviewData);
        this.displayMessage("Recensione inviata con successo!", "success");
        this.resetForm();  // Resetta il modulo dopo l'invio
        this.fetchReviews();
        this.fetchStatistics();
      } catch (error) {
        console.error("Errore nel pubblicare la recensione", error);
        this.displayMessage("Errore nel pubblicare la recensione", "error");
      }
    },

    displayMessage(msg, type){
      this.message = msg;
      this.messageType = type;

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
        const response = await axios.get('http://localhost:3000/api/ids');  // Endpoint BE per ottenere IDs
        // Aggiungi un log per vedere la risposta
        console.log("Risposta ricevuta:", response);
        this.eventId = response.data.eventId;
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
  async mounted() {
    await this.fetchIds();
    if (this.eventId) {
      this.fetchStatistics();
    }
  },

};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');
@import 'https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css';
@import 'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css';

html, body {
  margin: 0;
  padding: 0;
  background-color: #f8f9fa;
  color: #212529;
  font-family: 'Roboto', sans-serif;
  height: 100%;
}

.page-container {
  background-color: #ffffff; /* Sfondo chiaro */
  color: #212529; /* Testo scuro */
  min-height: 100vh;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.top-bar {
  background-color: #ffffff; /* Sfondo chiaro */
  color: #212529; /* Testo scuro */
  padding: 15px 0;
  border-radius: 10px;
  margin-bottom: 20px; /* Aggiungiamo uno spazio sotto la barra superiore */
  box-shadow: 2px 5px rgba(0, 0, 0, 0.1); /* Aggiungiamo una leggera ombra */
}

.back-button {
  padding: 5px 10px;
  font-size: 20px;
  color: #006fff;
  background-color: transparent;
  border: none;
  cursor: pointer;
  transition: color 0.3s;
}

.back-button:hover {
  color: #cccccc;
}

.back-button i {
  margin-right: 5px;
}

.title-text {
  text-align: center;
  font-size: 30px;
  font-weight: bold;
}

.recensioni {
  max-width: 600px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
}
.recensioni-form {
  margin-top: 10px;
}
.form-group {
  margin-bottom: 20px;
}

.star-rating {
  display: flex;
  margin-left: 7px;
}

.star-rating .fa-star {
  font-size: 24px;
  margin-right: 5px;
  cursor: pointer;
  color: #ccc; /* Colore grigio per le stelle non selezionate */
}

.star-rating .fa-star.checked {
  color: gold;
}

.gold-star{
  color: gold;
}

.submit-button {
  margin-top: 10px;
}

.toggle-review-form {
  cursor: pointer;
  color: #007bff;
  text-decoration: underline;
  margin-bottom: 10px;
}
.toggle-review-form:hover {
  color: #0056b3;
}

.toggle-subcategories {
  cursor: pointer;
  color: #007bff;
  font-size: 13px;
  margin: 20px 0;
}

.filter-container {
  margin-top: 20px;
  margin-bottom: 20px;
  text-align: center;
}

.selezione{
  border-radius: 5px;

}

/* Stili per l'elemento delle recensioni */
.reviews-list {
  width: 100%;
  max-width: 600px;
  margin: 20px auto;
}

.review-item {
  padding: 15px;
  border-bottom: 1px solid #e0e0e0;
}

.review-item h4 {
  font-weight: bold;
  margin-bottom: 5px;
}

.review-item .fa-star {
  color: rgba(205, 188, 188, 0.5); /* Colore dorato per le stelle */
  margin-right: 2px;
}

.review-item .fa-star.checked {
  color: gold;
}

.reset-filtro {
  margin-top: 10px;
  border: none;
  background-color: orange;
}

.message-box{
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  text-align: center;
  font-weight: bold;
}

.alert-success{
  background-color: #d4edda;
  color: #155724;
}

.alert-danger{
  background-color: #f8d7da;
  color: #721c24
}

.review-stats {
  margin: 20px 0;
  padding: 15px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.review-stats h3 {
  text-align: center;
  margin-bottom: 10px;
}

.review-stats ul {
  list-style: none;
  padding-left: 0;
}

.review-stats li {
  margin-bottom: 5px;
}


</style>
