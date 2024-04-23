<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';

/* Inseriamo in una variabile reattiva chiamata data */
const data = ref(0);

/* Inseriamo nella variabile data il risultato della chiamata al backend */
axios.get("/api/testMysql").then(response => {
 console.log(JSON.stringify(response.data))
 data.value = response.data
 })
 class MyTable{
  "id": number;
  "description": string;
 }


</script>

<template>
  <div>
    <table>
      <tr v-for="items in data" >
        <td>{{ items.id }}</td>
        <td>{{ items.description }}</td>
      </tr>
    </table>
    
  </div>
</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

</style>
