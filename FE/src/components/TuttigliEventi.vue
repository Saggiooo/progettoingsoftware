<template>
<div class="fullpage">
    <!-- "Sidebar.vue" -->
    <div>
            <Sidebar/>
    </div>
    <!-- fine "Sidebar.vue" -->
    
    <div class="ContainerEventi">
        <nav>
            <!-- "Nav.vue" -->
            <Nav/>
            <!-- fine "Nav.vue" -->
        </nav>
        <div id="box1">
            1
        </div>
        <div id="box2">
            2
        </div>
    </div>
    </div>
</template>


<style>
   /*
    .ContainerEventi{
    margin-left: 270px;
    padding: 20px;
    display: grid;
    height:95vh;
    grid-template-columns: 1fr;
    grid-template-rows: 0.4fr 2fr 1.2fr;
    grid-template-areas: 
        "nav"
        "box1"
        "box2";
    grid-gap: 0.8rem;


}
    #box1{
    background: #fff;
    grid-area: box1;
    border-radius:var(--main-radius);
    }
    #box2{
    background: #fff;
    grid-area: box2;
    border-radius:var(--main-radius);
    }
    */
</style>

<script setup lang="ts">

import Sidebar from './Sidebar.vue';
import Nav from './Nav.vue';
</script>