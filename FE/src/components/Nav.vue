<template>
     <div>
             <h1>Statistiche Generali</h1>
            </div>
            <div class="user-info">
                <div>
                    <p>Filippo Fontana</p>
                    <p class="company-name">Nome Azienda Eventi</p>
                </div>
                 <img src="@/assets/img/fotouseresempio.png" alt="Immagine Utente">
                
            </div>
</template>

<style>
.user-info{
    display: flex;
    align-items: center;
    text-align: right;
}

.user-info img {
    width: 55px;
    height: 55px;
    border-radius: 50%;
    margin-left: 10px;
}

.user-info p {
    font-size: 21px;
    margin: 0;
}

.company-name {
    font-size: 14px !important;
    color: #BC6F35;
}
</style>