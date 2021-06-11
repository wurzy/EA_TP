<template>
    <div id="home" class="home">

     <v-container>
      
      <v-row no-gutters>
        <v-col cols="12" sm="6" md="8" class="publicacoes">
          <h1>Publicações</h1>
          <v-container class="pubs">
            <v-row no-gutters>
              <v-col v-for="n in pubs" :key="n" cols="12" sm="6">
                <v-card class="pa-6 pub" outlined>
                  <v-row>
                    <v-col cols="12" sm="3">
                        <img class="img" :src="n.imagemURL"/>
                    </v-col>
                    <v-col cols="12" sm="9">
                        <span style="font-size: 20px; color: #53a6bf;"> {{ n.titulo }} <br/> </span>
                        <span> <b>Recurso: </b>{{ n.recurso }} <br/> </span>
                        <span> {{ n.autor }} há {{ n.dataPublicacao }} </span>
                    </v-col>
                  </v-row>
                </v-card> 
              </v-col>
            </v-row>
          </v-container>
        </v-col>

        <v-col cols="6" md="4" class="recursos">
            <h1>Novos Recursos</h1>
            <v-container class="recs">
            <v-row no-gutters>
              <v-col v-for="n in 1" :key="n" cols="12" sm="12">
                <v-card class="pa-2">
                  One of three columns
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
    
      </v-row>
    </v-container>
    
    </div>

</template>



<script>

export default {
    name: 'recursos',
    data() {
        return { 
            pubs: [
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 1', recurso: 'recurso 1', autor: 'João',  dataPublicacao: '2012-3-3'},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 2', recurso: 'recurso 2', autor: 'Ricardo', dataPublicacao: '2012-3-3'},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 3', recurso: 'recurso 1', autor: 'Abel', dataPublicacao: '2012-3-3'},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 4', recurso: 'recurso 0', autor: 'Joaquim', dataPublicacao: '2012-3-3'},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 5', recurso: 'recurso 3', autor: 'Filipa', dataPublicacao: '2012-3-3'},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 6', recurso: 'recurso 4', autor: 'Hugo', dataPublicacao: '2012-3-3'},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 7', recurso: 'recurso 1', autor: 'Válter', dataPublicacao: '2012-3-3'}
            ]
        }
    },
    components: {
    },
    methods: {
    }
}

</script>




<style>

.home {
    text-align: center;
    height: 100vh;
}

.home h1{
    font-size: 30px;
    text-decoration: underline;
    text-align: left;
    margin-top: 10px;
    text-underline-offset: 7px;
    text-decoration-thickness: 6px;
    text-decoration-color: #e88b00;
}

.publicacoes {
    padding-right: 30px !important;
}

.pubs {
    background-color: #a9a9a94a;
    border-radius: 5px;
    margin-top: 20px;
}

.pub {
    text-align: left;
    border-radius: 5px;
    margin: 10px;
}

.recursos {
    border-left: 2px solid #969090;
    background-color: white;
    padding-left: 30px !important;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.recursos h1 {
    text-align: center !important;
    text-decoration: none !important;
    background-color: #e88b00bd;
    width: fit-content;
    padding: 0px 5px;
}

.recs {
    margin-top: 20px;
}

.rec {
    border-radius: 5px;
    margin: 10px;
}

.img {
    text-align: right;
    max-height: 80px;
    max-width: 80px;
}

</style>