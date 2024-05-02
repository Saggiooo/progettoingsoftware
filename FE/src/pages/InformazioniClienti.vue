<!-- ParentComponent.vue -->
<template>
    <div>
      <InfoClienti />
    </div>
  </template>
  
  <script setup lang="ts">
  import InfoClienti from '@/components/InformazioniClienti.vue';
  </script>
  
  <router-view></router-view>