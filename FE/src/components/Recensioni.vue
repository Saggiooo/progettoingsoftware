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
      <label for="filterRating">Filtra per valutazione:</label>
      <div class="star-rating">
        <span v-for="star in 5" :key="star" @click="setFilterRating(star)">
          <i :class="['fa', 'fa-star', {'checked': star <= filterRating}]"></i>
        </span>
      </div>
    </div>

    <div class="reviews-list row">
      <h3 class="col-12">Recensioni</h3>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: '',
      reviewText: '',
      generalRating: 3,
      category1Rating: 3,
      category2Rating: 3,
      category3Rating: 3,
      submitted: false,
      showReviewForm: false,
      showSubcategorySliders: false,
      filterRating: 1,
      reviews: [],
    };
  },
  methods: {
    submitReview() {
      this.reviews.push({
        id: Date.now(),
        title: this.title,
        text: this.reviewText,
        generalRating: this.generalRating,
        category1Rating: this.category1Rating,
        category2Rating: this.category2Rating,
        category3Rating: this.category3Rating,
      });
      this.resetForm();
    },
    goBack() {
      window.history.back();
    },
    toggleReviewForm() {
      this.showReviewForm = !this.showReviewForm;
    },
    toggleSubcategorySliders() {
      this.showSubcategorySliders = !this.showSubcategorySliders;
    },
    setRating(category, star) {
      this[category] = star;
    },
    resetForm() {
      this.title = '';
      this.reviewText = '';
      this.generalRating = 3;
      this.category1Rating = 3;
      this.category2Rating = 3;
      this.category3Rating = 3;
      this.submitted = false;
    },
    setFilterRating(star) {
      this.filterRating = star;
    }
  }
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
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1); /* Aggiungiamo una leggera ombra */
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
  align-items: center;
}

.star-rating .fa-star {
  font-size: 24px;
  margin-right: 5px;
  cursor: pointer;
}

.star-rating .checked {
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
}

</style>
