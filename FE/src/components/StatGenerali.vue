<template>
    <div class="fullpage">
    <!-- "Sidebar.vue" -->
    <div>
            <Sidebar/>
    </div>
    <!-- fine "Sidebar.vue" -->

    <div class="Container">
        <nav>
            <!-- "Nav.vue" -->
            <Nav/>
            <!-- fine "Nav.vue" -->
        </nav>
        <div id="content1">
            <div>
            <!-- Elemento HTML per il grafico -->
            <canvas id="myChart" width="400" height="400"></canvas>
             </div>
        </div>
        <div id="content2">
            2
        </div>
        <div id="content3">
            3
        </div>
        <div id="content4">
            4
        </div>
        <div id="content5">
            5
        </div>
        <div id="content6">
            6
        </div>
        <div id="content7">
            7
        </div>
    </div>
    </div>
</template>

<style>

:root{
    --main-radius:20px;
    --main-padding:5px;
}

.fullpage{
    background: #F2F2F2;
}

.Container{
    margin-left: 270px;
    padding: 20px;
    display: grid;
    height:95vh;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-rows: 0.4fr 0.8fr 0.8fr 1.6fr;
    grid-template-areas: 
        "nav nav nav nav"
        "content1 content1 content2 content3"
        "content1 content1 content4 content5"
        "content6 content6 content7 content7";
    grid-gap: 0.8rem;


}
nav{
    grid-area: nav;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

#content1{
    background: #fff;
    grid-area: content1;
    border-radius:var(--main-radius);
}
#content2{
    background: rgba(163, 203, 255, 0.6);
    grid-area: content2;
    border-radius:var(--main-radius);
}
#content3{
    background: rgba(255, 210, 166, 0.6);
    grid-area: content3;
    border-radius:var(--main-radius);
}
#content4{
    background: rgba(138, 233, 255, 0.6);
    grid-area: content4;
    border-radius:var(--main-radius);
}
#content5{
    background: rgba(255, 171, 141, 0.6);
    grid-area: content5;
    border-radius:var(--main-radius);
}
#content6{
    background: #fff;
    grid-area: content6;
    border-radius:var(--main-radius);
}
#content7{
    background: #fff;
    grid-area: content7;
    border-radius:var(--main-radius);
}

@media only screen and (max-width: 1000px){
    .Container{
        height: 200vh;
        grid-template-columns: 1fr 1fr;
        grid-template-rows: 0.4fr 2fr 2fr 2fr 2fr 2fr;
        grid-template-areas: 
            "nav nav" 
            "content1 content1" 
            "content2 content3" 
            "content4 content5" 
            "content6 content6" 
            "content7 content7"
    }
}
</style>


<script setup lang="ts">

import Sidebar from './Sidebar.vue';
import Nav from './Nav.vue';


</script>